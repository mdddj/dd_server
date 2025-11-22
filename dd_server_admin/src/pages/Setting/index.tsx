import React from 'react';
import { PageContainer } from '@ant-design/pro-components';
import { Form, Input, Card, Button, Tabs } from 'antd';
import { request } from '@umijs/max';
import { Result } from '@/types/result';
import {removeJwtToken} from "@/utils/cache";
import MiniAppConfigModelForm from "@/pages/miniapp/MiniAppConfigSetting";
import MinioInfoWidget from '../minio/MinioInfoWidget';
import { MailConfigShow } from '../Email/EmailConfigSetting';
import ChangeZheConfigComponent from '@/components/zhe/ChangeZheConfigComponent';

///个人设置页面
export default function Page() {
  const submit = async  (values: any) => {
    let response = await request<Result<any>>("/api/auth/user-update-pass",{
      method: 'POST',
      data: values
    })
    console.log(response)
    if(response.success){
      removeJwtToken()
      window.location.href = "/login";
    }
  };
  return (
    <PageContainer>
      <Tabs items={[
        {
          key: "profile",
          label: "Profile",
          children: <Card>
            暂无
          </Card>
        },
        {
          key: "minio-config",
          label: "minio配置",
          children:   <MinioInfoWidget />
        },
        {
          key: "email-config",
          label: "邮件配置",
          children: <MailConfigShow />
        },
        {
          key: "zhe-config",
          label: "折淘客配置",
          children: <ChangeZheConfigComponent />
        }
        ,
        {
          key: "mini-app-config",
          label: "小程序配置",
          children: <Card><MiniAppConfigModelForm trigger={<Button>修改配置</Button>} /></Card>
        },
        {
          key: "password",
          label: "修改密码",
          children: <Card>
            <Form onFinish={submit} layout={'vertical'}>
              <Form.Item name={'currentPass'} label={'当前密码'} required={true} rules={[{required:true}]}>
                <Input  type='password' />
              </Form.Item>
              <Form.Item name={'rePassword'} label={'新密码'} dependencies={["currentPass"]} rules={[{required:true},(obj)=>({
                validator(_,value){
                  if(!value || value !== obj.getFieldValue("currentPass")){
                    return Promise.resolve()
                  }
                  return Promise.reject(new Error("新密码不能和旧密码一样"))
                }
              })]}>
                <Input  type='password' />
              </Form.Item>
              <Form.Item name={'rePassword2'} label={'确认新密码'} required={true} dependencies={["rePassword"]} rules={[
                {
                  required: true,
                },
                (obj) => ({
                  validator(_,value){
                    if(!value || value === obj.getFieldValue("rePassword")) {
                      return Promise.resolve()
                    }else{
                      return Promise.reject(new Error("密码不一致"))
                    }
                  }
                })
              ]}>
                <Input type='password' />
              </Form.Item>
              <Form.Item>
                <Button type="primary" htmlType="submit">修改密码</Button>
              </Form.Item>
            </Form>
          </Card>
        }
      ]}>

      </Tabs>
    </PageContainer>
  );
}


/// 
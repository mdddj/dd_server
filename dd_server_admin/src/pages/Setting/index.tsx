import React from 'react';
import { PageContainer } from '@ant-design/pro-components';
import { Tabs, Tab,  } from '@nextui-org/react';
import { Form, Input, Card, Button } from 'antd';
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
      <Tabs>
        <Tab key={'profile'} title={'个人资料'}>
          <Card>
            暂无
          </Card>
        </Tab>
        <Tab key="minio-config" title={'minio配置'}>
        <MinioInfoWidget />
        </Tab>
        <Tab key="email-config" title={'邮件配置'}>
        <MailConfigShow />
        </Tab>
        <Tab key="zhe-config" title={'折淘客配置'}>
        <ChangeZheConfigComponent />
        </Tab>
        <Tab key={'mini-app-config'} title={'小程序配置'}>
          <Card>
            <MiniAppConfigModelForm trigger={<Button>修改配置</Button>}/>
          </Card>
        </Tab>
        <Tab key={'password'} title={'修改密码'}>
          <Card>
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
        </Tab>
      </Tabs>
    </PageContainer>
  );
}


/// 
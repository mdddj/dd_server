import React from 'react';
import { PageContainer } from '@ant-design/pro-components';
import { Tabs, Tab,  } from '@nextui-org/react';
import { Form, Input, Card, Button } from 'antd';
import { request } from '@umijs/max';
import { Result } from '@/types/result';

///个人设置页面
export default function Page() {
  const submit = async  (values: any) => {
    let response = await request<Result<any>>("/api/auth/user-update-pass",{
      method: 'POST',
      data: values
    })
    console.log(response)
    if(response.success){
      window.location.href = "/login";
    }
  };
  return (
    <PageContainer>
      <Tabs>
        <Tab key={'profile'} title={'个人资料'}>
          <Card>
            ...
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
                  if(!value || value != obj.getFieldValue("currentPass")){
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
                    if(!value || value == obj.getFieldValue("rePassword")) {
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
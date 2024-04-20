import { setJwtToken } from '@/utils/cache';
import { useModel, useNavigate } from '@umijs/max';
import { Tabs, message } from 'antd';
import { useState } from 'react';

import {
  ApiIsRegisterAdminAccount,
  ApiLogin,
} from '@/services/user/UserController';
import { LockOutlined, MailOutlined, UserOutlined } from '@ant-design/icons';
import { LoginForm, ProFormText } from '@ant-design/pro-components';
import { useEffectOnce } from 'react-use';

export type LoginType = 'email' | 'account';

export default function Page() {
  let nav = useNavigate();

  ///检测是否有管理员账号，如果不存在则需要去初始化页面
  useEffectOnce(() => {
    ApiIsRegisterAdminAccount().then((v) => {
      if (v.success && !v.data) {
        //去初始化页面
        nav('/init');
      }
    });
  });
  const [loginType, setLoginType] = useState<LoginType>('email');
  const { setInitialState, initialState } = useModel('@@initialState');
  const doLogin = async (values: any) => {
    let hide = message.loading('正在登录');
    try {
      let result = await ApiLogin(
        { ...values, loginType: loginType },
        loginType,
      );
      setJwtToken(result.data.token);
      await setInitialState({ ...initialState, user: result.data.user });
      hide();
      nav('/');
    } catch (e) {
      hide();
    }
  };

  return (
    <div style={{ backgroundColor: 'white' }}>
      <LoginForm
        title="梁典典的博客后台"
        onFinish={doLogin}
      >
        <Tabs
          centered
          activeKey={loginType}
          onChange={(activeKey) => setLoginType(activeKey as LoginType)}
          items={[
            {
              label: '邮箱登录',
              key: 'email',
            },
            {
              label: '账号登录',
              key: 'account',
            },
          ]}
        />
        {loginType === 'account' && (
          <>
            <ProFormText
              name="loginNumber"
              fieldProps={{
                size: 'large',
                prefix: <UserOutlined className={'prefixIcon'} />,
              }}
              placeholder={'用户名'}
              rules={[
                {
                  required: true,
                  message: '请输入用户名!',
                },
              ]}
            />
            <ProFormText.Password
              name="password"
              fieldProps={{
                size: 'large',
                prefix: <LockOutlined className={'prefixIcon'} />,
              }}
              placeholder={'密码'}
              rules={[
                {
                  required: true,
                  message: '请输入密码！',
                },
              ]}
            />
          </>
        )}
        {loginType === 'email' && (
          <>
            <ProFormText
              fieldProps={{
                size: 'large',
                prefix: <MailOutlined className={'prefixIcon'} />,
              }}
              name="loginNumber"
              placeholder={'邮箱'}
              rules={[
                {
                  required: true,
                  message: '请输入邮箱！',
                },
              ]}
            />
            <ProFormText.Password
              name="password"
              fieldProps={{
                size: 'large',
                prefix: <LockOutlined className={'prefixIcon'} />,
              }}
              placeholder={'密码'}
              rules={[
                {
                  required: true,
                  message: '请输入密码！',
                },
              ]}
            />
          </>
        )}
      </LoginForm>
    </div>
  );
}

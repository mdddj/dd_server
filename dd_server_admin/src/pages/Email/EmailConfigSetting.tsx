import DesensitizationTextWidget from '@/components/widget/DesensitizationText';
import { Result } from '@/types/result';
import {
  ModalForm,
  ProDescriptions,
  ProFormCheckbox,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-components';
import { request, useRequest } from '@umijs/max';
import { Button, Card, Space } from 'antd';
import React from 'react';
import { useMount } from 'react-use';

export interface MyMailAccount {
  host: string;
  port: string;
  auth: boolean;
  from: string;
  user: string;
  pass: string;
  sslEnable: boolean;
  starttlsEnable: boolean;
  socketFactoryClass: string;
}

type ModelDef = MyMailAccount;

interface Props {
  trigger?: React.JSX.Element;
  onSuccess?: () => void;
  initialValue?: ModelDef;
}

export async function sendTestEmail(
  emailModel: TestEmail,
): Promise<Result<TestEmail>> {
  return request('/api/admin/mail/test', {
    method: 'POST',
    data: emailModel,
  });
}

export async function get(): Promise<Result<MyMailAccount>> {
  return request('/api/admin/mail/get', {
    method: 'GET',
  });
}

export async function save(
  myMailAccount: ModelDef,
): Promise<Result<MyMailAccount>> {
  return request('/api/admin/mail/update', {
    method: 'POST',
    data: myMailAccount,
  });
}

const MailConfigShow: React.FC = () => {
  const { run, refresh, data, loading } = useRequest(() => get(), {
    manual: true,
  });

  useMount(() => run());

  function onSendTestEmail() {}

  return (
    <Card
      title={'邮件通知服务配置'}
      loading={loading}
      extra={
        <Space>
          <TestModelForm
            trigger={
              <Button onClick={onSendTestEmail} size={'small'} key={'test'}>
                测试
              </Button>
            }
            initialValue={{
              content: 'test',
              title: 'test',
              to: '',
              isHtml: true,
            }}
          />

          <MyMailAccountModelForm
            trigger={
              <Button size={'small'} type={'primary'}>
                修改
              </Button>
            }
            initialValue={data}
            onSuccess={refresh}
            key={'edit'}
          />
        </Space>
      }
    >
      {data && (
        <ProDescriptions>
          <ProDescriptions.Item label={'SMTP地址'}>
            {data.host}
          </ProDescriptions.Item>
          <ProDescriptions.Item label={'SMTP端口'}>
            {data.port}
          </ProDescriptions.Item>
          <ProDescriptions.Item label={'发件人'}>
            <DesensitizationTextWidget text={data.from} />
          </ProDescriptions.Item>
          <ProDescriptions.Item label={'用户名'}>
            <DesensitizationTextWidget text={data.user} />
          </ProDescriptions.Item>
          <ProDescriptions.Item label={'密码'}>
            <DesensitizationTextWidget text={data.pass} />
          </ProDescriptions.Item>
          <ProDescriptions.Item label={'使用 STARTTLS安全连接'}>
            {data.starttlsEnable ? '是' : '否'}
          </ProDescriptions.Item>
          <ProDescriptions.Item label={'使用SSL安全连接'}>
            {data.sslEnable ? '是' : '否'}
          </ProDescriptions.Item>
          <ProDescriptions.Item label={'类名'}>
            {data.socketFactoryClass}
          </ProDescriptions.Item>
        </ProDescriptions>
      )}
    </Card>
  );
};

const MyMailAccountModelForm: React.FC<Props> = ({
  trigger,
  onSuccess,
  initialValue,
}) => {
  /// ToDo 实现保存/修改请求
  const onSubmit = async (values: ModelDef): Promise<boolean> => {
    let r = await save(values);
    return r.success;
  };

  ///提交数据
  const onFinish = async (values: ModelDef): Promise<boolean> => {
    let r = await onSubmit(values);
    if (r) {
      onSuccess?.();
    }
    return r;
  };

  return (
    <>
      <ModalForm<ModelDef>
        initialValues={initialValue}
        trigger={trigger}
        onFinish={onFinish}
      >
        <ProFormText
          name={'host'}
          label={'邮件服务器的SMTP地址'}
          tooltip={'邮件服务器的SMTP地址，可选，默认为smtp.<发件人邮箱后缀>'}
        />
        <ProFormText
          name={'port'}
          label={'邮件服务器的SMTP端口'}
          tooltip={'可选，默认25'}
        />
        <ProFormCheckbox
          name={'auth'}
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        >
          是否需要验证
        </ProFormCheckbox>
        <ProFormText
          name={'from'}
          label={'发件人（必须正确，否则发送失败）'}
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        />
        <ProFormText
          name={'user'}
          label={'用户名（注意：如果使用foxmail邮箱，此处user为qq号）'}
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        />
        <ProFormText
          name={'pass'}
          label={
            '密码（注意，某些邮箱需要为SMTP服务单独设置密码，详情查看相关帮助）'
          }
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        />
        <ProFormCheckbox
          name={'sslEnable'}
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        >
          使用SSL安全连接
        </ProFormCheckbox>
        <ProFormCheckbox
          name={'starttlsEnable'}
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        >
          使用 STARTTLS安全连接，STARTTLS是对纯文本通信协议的扩展。
        </ProFormCheckbox>
        <ProFormText
          name={'socketFactoryClass'}
          label={
            '指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字'
          }
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        />
      </ModalForm>
    </>
  );
};

export interface TestEmail {
  to: string;
  title: string;
  content: string;
  isHtml: boolean;
}

interface Props2 {
  trigger?: React.JSX.Element;
  onSuccess?: () => void;
  initialValue?: TestEmail;
}

const TestModelForm: React.FC<Props2> = ({
  trigger,
  onSuccess,
  initialValue,
}) => {
  /// ToDo实现保存/修改请求
  const onSubmit = async (values: TestEmail): Promise<boolean> => {
    let { success } = await sendTestEmail(values);
    return success;
  };

  ///提交数据
  const onFinish = async (values: TestEmail): Promise<boolean> => {
    let r = await onSubmit(values);
    if (r) {
      onSuccess?.();
    }
    return r;
  };

  return (
    <>
      <ModalForm<TestEmail>
        initialValues={initialValue}
        trigger={trigger}
        onFinish={onFinish}
      >
        <ProFormText
          name={'to'}
          label={'收件人邮箱'}
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        />
        <ProFormText
          name={'title'}
          label={'标题'}
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        />
        <ProFormTextArea
          name={'content'}
          label={'内容'}
          fieldProps={{ rows: 6 }}
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        />
        <ProFormCheckbox
          name={'isHtml'}
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        >
          是否为html文本
        </ProFormCheckbox>
      </ModalForm>
    </>
  );
};

export { MailConfigShow };

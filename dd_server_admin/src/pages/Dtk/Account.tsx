import DefaultDtsAccountWidget from '@/components/dtk/DefaultDtkAccount';
import DesensitizationTextWidget from '@/components/widget/DesensitizationText';
import { ChannelGenerateWidget } from '@/components/zhe/ChannelGenerateWidget';
import { DTKDetail } from '@/models/tdk';
import {
  MyDtkServiceAddApi,
  MyDtkServiceChangeDefaultAccountApi,
  MyDtkServiceDeleteApi,
  MyDtkServiceGetAllApi,
  MyDtkServiceVerifyDtkAccountApi,
} from '@/services/dtk/DtkService';
import {
  ModalForm,
  PageContainer,
  ProFormCheckbox,
  ProFormInstance,
  ProFormText,
  ProFormTextArea,
  ProTable,
} from '@ant-design/pro-components';
import {
  Button,
  Card,
  Checkbox,
  Form,
  Image,
  Popconfirm,
  Space,
  Typography,
  message,
} from 'antd';
import React, { useRef, useState } from 'react';

type DtkDetailFormProp = {
  updateModel?: DTKDetail;
  onSuccess?: () => void;
};

///添加或者删除表单
const DtkDetailForm: React.FC<DtkDetailFormProp> = ({
  updateModel,
  onSuccess,
}) => {
  const formRef = useRef<ProFormInstance<DTKDetail>>();
  const [form] = Form.useForm();
  const [verify, setVerify] = useState(false);
  const appKey = Form.useWatch('appKey', form);
  const appSecret = Form.useWatch('appSecret', form);

  return (
    <ModalForm<DTKDetail>
      title={updateModel ? '修改信息' : '添加大淘客账号'}
      trigger={
        <Button
          type={updateModel ? 'default' : 'primary'}
          size={updateModel ? 'small' : 'middle'}
        >
          {updateModel ? '修改' : '添加大淘客账号'}
        </Button>
      }
      onFinish={async (values: DTKDetail) => {
        if (updateModel) {
          values.id = updateModel.id;
        }
        message.loading('正在验证账号信息');
        let r = await MyDtkServiceVerifyDtkAccountApi({
          appKey: values.appKey,
          appSecret: values.appSecret,
        });
        if (!r.success) {
          return false;
        }
        message.loading(updateModel ? '正在修改账号信息' : '正在添加账号');
        let result = await MyDtkServiceAddApi(values);
        if (result.success) {
          if (onSuccess) {
            onSuccess();
          }
          return true;
        }
        return false;
      }}
      form={form}
      formRef={formRef}
      initialValues={updateModel}
      submitter={{
        render(_, dom) {
          return [
            <Button
              disabled={
                !appKey || !appSecret || appKey === '' || appSecret === ''
              }
              key={'verify'}
              loading={verify}
              onClick={async () => {
                if (appKey && appSecret) {
                  try {
                    message.loading('正在验证');
                    setVerify(true);
                    let r = await MyDtkServiceVerifyDtkAccountApi({
                      appKey,
                      appSecret,
                    });
                    setVerify(false);
                    if (r.data) {
                      message.success(r.message);
                    }
                  } catch (e) {
                    setVerify(false);
                  }
                }
              }}
            >
              验证账号是否有效
            </Button>,
            ...dom,
          ];
        },
      }}
      autoFocusFirstInput
      modalProps={{
        destroyOnClose: true,
      }}
    >
      <ProFormText
        name={'appKey'}
        label={'appKey'}
        rules={[
          {
            required: true,
            message: '请输入appKey',
          },
          {
            len: 13,
            message: '请输入正确的appKey',
          },
        ]}
      />

      <ProFormText
        name={'appSecret'}
        label={'appSecret'}
        rules={[
          {
            required: true,
            message: '请输入appSecret',
          },
        ]}
      />

      <ProFormText name={'title'} label={'标题'} />
      <ProFormTextArea name={'intro'} label={'介绍'} />
      <ProFormText name={'logo'} label={'图标'} />
      <ProFormText name={'relationId'} label={'渠道关系ID'} />
      <ProFormCheckbox name={'selectDefault'}>
        设置为默认应用 <DefaultDtsAccountWidget />
      </ProFormCheckbox>
    </ModalForm>
  );
};

export default function Page() {
  /**
   * 验证大淘客账号是否可用
   * @param account
   */
  const onCheckAccountStatus = async (account: DTKDetail) => {
    message.loading('正在验证');
    let result = await MyDtkServiceVerifyDtkAccountApi({
      appKey: account.appKey,
      appSecret: account.appSecret,
    });
    if (result.success) {
      message.success(result.message);
    }
  };

  return (
    <PageContainer
      title={'大淘客账号管理'}
      extra={[
        <DtkDetailForm key={'add'} />,
        <ChannelGenerateWidget key={'channel-tool'} />,
      ]}
    >
      <Card>
        <ProTable<DTKDetail>
          rowKey={'id'}
          columns={[
            {
              key: 'id',
              dataIndex: 'id',
              title: 'Id',
            },
            {
              key: 'appKey',
              dataIndex: 'appKey',
              title: 'appKey',
              render: (_, e) => {
                return (
                  <Typography.Paragraph copyable={{ text: e.appKey }}>
                    <DesensitizationTextWidget text={e.appKey} />
                  </Typography.Paragraph>
                );
              },
            },
            {
              key: 'appSecret',
              dataIndex: 'appSecret',
              title: 'appSecret',
              search: false,
              render: (_, e) => {
                return (
                  <Typography.Paragraph copyable={{ text: e.appSecret }}>
                    <DesensitizationTextWidget text={e.appSecret} />
                  </Typography.Paragraph>
                );
              },
            },
            {
              key: 'title',
              dataIndex: 'title',
              title: '名称',
            },
            {
              key: 'intro',
              dataIndex: 'intro',
              title: '介绍',
              search: false,
            },
            {
              key: 'selectDefault',
              dataIndex: 'selectDefault',
              title: '是否启用',
              search: false,
              render: (_, m, __, action) => (
                <Checkbox
                  checked={m.selectDefault}
                  onChange={async (e) => {
                    let c = e.target.checked;
                    let hide = message.loading('正在设置');
                    await MyDtkServiceChangeDefaultAccountApi({
                      action: c,
                      id: m.id!!,
                    });
                    hide();
                    action?.reload();
                  }}
                >
                  默认应用
                </Checkbox>
              ),
            },
            {
              key: 'logo',
              dataIndex: 'logo',
              title: '图标',
              search: false,
              render: (_, m) => <Image src={m.logo} width={32} />,
            },
            {
              title: '操作',
              search: false,
              render: (_, m, __, a) => (
                <Space>
                  <DtkDetailForm
                    updateModel={m}
                    onSuccess={() => a?.reload()}
                  />
                  <Button
                    onClick={() => onCheckAccountStatus(m)}
                    size={'small'}
                  >
                    验证
                  </Button>
                  <Popconfirm
                    title={'确认删除吗?'}
                    onConfirm={async () => {
                      message.loading('正在删除');
                      let result = await MyDtkServiceDeleteApi(m.id!!);
                      if (result.success) {
                        message.success(result.message);
                        a?.reload();
                      }
                    }}
                  >
                    <Button size={'small'}>删除</Button>
                  </Popconfirm>
                </Space>
              ),
            },
          ]}
          request={async (params, _, filter) => {
            let result = await MyDtkServiceGetAllApi({
              ...params,
              ...filter,
              remove: 1,
            });
            return {
              success: result.success,
              data: result.data.content,
              total: result.data.totalElements,
            };
          }}
        />
      </Card>
    </PageContainer>
  );
}

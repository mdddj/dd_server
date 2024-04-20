import ApiTable from '@/components/Apitable';
import FileSelectWidget from '@/components/file/FileSelectComponent';
import {
  DeleteRoleById,
  GetRoleListApiByPage,
  SaveOrUpdateRole,
} from '@/services/role/RoleController';
import { Role } from '@/services/role/types';
import {
  ModalForm,
  PageContainer,
  ProFormInstance,
  ProFormRadio,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-components';
import { Button, Card, Popconfirm, Space, message } from 'antd';
import React, { useRef } from 'react';
///弹窗参数
type CreateOrUpdateParams = {
  role?: Role | Record<string, any>;
  trigger?: React.ReactElement;
  title?: string;
  onFinish?: () => void;
};

///修改或者新建role
const CreateOrUpdate: React.FC<CreateOrUpdateParams> = (props) => {
  const formRef = useRef<ProFormInstance>();
  const onFinish = async (values: Role) => {
    values.id = props.role?.id;
    await SaveOrUpdateRole(values);
    return true;
  };
  return (
    <>
      <ModalForm<Role>
        initialValues={props.role}
        title={props.title ?? '新建权限'}
        trigger={props.trigger ?? <Button type="primary">新建权限</Button>}
        width={500}
        onFinish={onFinish}
        onFinishFailed={props.onFinish}
        layout={'vertical'}
        formRef={formRef}
      >
        <ProFormText name={'name'} label={'名称'} required />
        <ProFormTextArea name={'note'} label={'备注'} />

        <FileSelectWidget
          onFileSelect={(file) => {
            formRef.current?.setFieldValue('icon', file.url);
          }}
        >
          <ProFormText name={'icon'} label={'图标'} tooltip={'输入URL直链'} />
        </FileSelectWidget>
        <ProFormRadio.Group
          name={'status'}
          label={'状态'}
          options={[
            {
              label: '启用',
              value: 1,
            },
            {
              label: '禁用',
              value: 0,
            },
          ]}
          required
        />
        <ProFormText name={'sort'} label={'排序'} />
      </ModalForm>
    </>
  );
};

export default function Page() {
  return (
    <PageContainer>
      <Card title={<CreateOrUpdate />}>
        <ApiTable<Role>
          api={(params) => GetRoleListApiByPage(params)}
          columns={[
            {
              key: 'id',
              dataIndex: 'id',
              title: 'ID',
            },
            {
              key: 'name',
              dataIndex: 'name',
              title: '名称',
            },
            {
              key: 'note',
              dataIndex: 'note',
              title: '备注',
            },
            {
              key: 'sort',
              dataIndex: 'sort',
              title: '排序',
            },
            {
              key: 'action',
              render(dom, entity, index, action) {
                return (
                  <Space>
                    <CreateOrUpdate
                      title="修改权限"
                      trigger={<a>修改</a>}
                      role={entity}
                      onFinish={() => action?.reload()}
                    />
                    <Popconfirm
                      title={'确定删除吗? '}
                      onConfirm={async () => {
                        const hide = message.loading('正在删除...');
                        await DeleteRoleById(entity.id!!);
                        hide();
                        action?.reload();
                      }}
                    >
                      <a>删除</a>
                    </Popconfirm>
                  </Space>
                );
              },
            },
          ]}
          rowKey={'id'}
        />
      </Card>
    </PageContainer>
  );
}

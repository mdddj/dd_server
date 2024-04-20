import { FriendLink } from '@/pages/Friend/model';
import { JpaPage, Result } from '@/types/result';
import {
  ActionType,
  ModalForm,
  PageContainer,
  ProFormDatePicker,
  ProFormText,
  ProTable,
} from '@ant-design/pro-components';
import { Switch } from '@nextui-org/react';
import { request } from '@umijs/max';
import { Button, Popconfirm, Space } from 'antd';
import React, { ReactElement, useRef } from 'react';

async function getList(params: any): Promise<Result<JpaPage<FriendLink>>> {
  return request('/api/admin/friend/list', {
    method: 'GET',
    params,
  });
}

async function save(values: FriendLink): Promise<Result<FriendLink>> {
  return request('/api/admin/friend/save', {
    method: 'POST',
    data: values,
  });
}

async function deleteFriend(id: number) {
  return request('/api/admin/friend/delete', {
    method: 'DELETE',
    params: { id },
  });
}

export default function Page() {
  const ref = useRef<ActionType>();
  return (
    <PageContainer
      extra={[<AddFrom key={'add'} onSuccess={() => ref.current?.reload()} />]}
    >
      <ProTable<FriendLink>
        actionRef={ref}
        request={async (params, _, filter) => {
          const r = await getList({ ...params, remove: 1, ...filter });
          return {
            success: r.success,
            total: r.data.totalElements,
            data: r.data.content,
          };
        }}
        columns={[
          {
            key: 'id',
            dataIndex: 'id',
            title: 'id',
          },
          {
            key: 'name',
            dataIndex: 'name',
            title: 'name',
          },
          {
            key: 'url',
            dataIndex: 'url',
            title: 'url',
          },
          {
            key: 'intro',
            dataIndex: 'intro',
            title: 'intro',
          },
          {
            key: 'logo',
            dataIndex: 'logo',
            title: 'logo',
          },
          {
            key: 'state',
            dataIndex: 'state',
            title: 'state',
            render: (_, entity, __, action) => {
              return (
                <Switch
                  isSelected={entity.state === 1}
                  onValueChange={async (isSelected) => {
                    const r = await save({
                      ...entity,
                      state: isSelected ? 1 : 0,
                    });
                    if (r.success) {
                      action?.reload();
                    }
                  }}
                />
              );
            },
          },
          {
            key: 'createDate',
            dataIndex: 'createDate',
            title: 'createDate',
          },
          {
            key: 'email',
            dataIndex: 'email',
            title: 'email',
          },
          {
            title: '操作',
            render: (_, entity, __, action) => {
              return (
                <Space>
                  <AddFrom
                    trigger={<Button size={'small'}>修改</Button>}
                    initValue={entity}
                    onSuccess={() => action?.reload()}
                  />
                  <Popconfirm
                    title={'确定删除吗?'}
                    onConfirm={async () => {
                      const { id } = entity;
                      if (id) {
                        await deleteFriend(id);
                        action?.reload();
                      }
                    }}
                  >
                    <Button size={'small'} type={'text'}>
                      删除
                    </Button>
                  </Popconfirm>
                </Space>
              );
            },
          },
        ]}
        rowKey={'id'}
      />
    </PageContainer>
  );
}

const AddFrom: React.FC<{
  initValue?: FriendLink;
  trigger?: ReactElement;
  onSuccess?: () => void;
}> = ({ initValue, trigger, onSuccess }) => {
  return (
    <ModalForm
      initialValues={initValue}
      onFinish={async (values) => {
        const r = await save(values);
        if (r.success) {
          onSuccess?.();
        }
        return r.success;
      }}
      trigger={trigger ?? <Button>新增友链</Button>}
    >
      <ProFormText name={'id'} label={'id'} disabled={true} />

      <ProFormText name={'name'} label={'name'} />

      <ProFormText name={'url'} label={'url'} />

      <ProFormText name={'intro'} label={'intro'} />

      <ProFormText name={'logo'} label={'logo'} help={'随机出图api: https://t.mwm.moe/ycy'} />

      <ProFormText
        name={'state'}
        label={'state'}
        tooltip={'状态, 1： 表示可正常显示，0：表示需要审核'}
      />

      <ProFormDatePicker name={'createDate'} label={'createDate'} />

      <ProFormText name={'email'} label={'email'} />
    </ModalForm>
  );
};

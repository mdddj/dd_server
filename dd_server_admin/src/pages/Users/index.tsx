import ApiTable from '@/components/Apitable';
import UpdateFromModal from '@/components/users/form/Update';
import { ApiEnterpriseFindAll } from '@/services/enterprise/EnterpriseController';
import {
  ApiDeleteUser,
  ApiQueryUserList,
} from '@/services/user/UserController';
import { User, Vip } from '@/types/user';
import { ProFormSelect } from '@ant-design/pro-components';
import { Popconfirm, Space, Tag } from 'antd';

export default function Page() {
  return (
    <div>
      <ApiTable<User>
        rowKey={'id'}
        api={async (params) => {
          return await ApiQueryUserList(params);
        }}
        columns={[
          {
            key: 'id',
            title: 'id',
            dataIndex: 'id',
          },
          {
            key: 'loginNumber',
            title: '用户名',
            dataIndex: 'loginNumber',
          },
          {
            key: 'nickName',
            title: '昵称',
            dataIndex: 'nickName',
            render: (_, entity, number, action) => {
              return (
                <Space>
                  <span>{entity.nickName}</span>
                  <UpdateFromModal<{ nickName: string }>
                    title={'修改昵称'}
                    name={'nickName'}
                    initValue={entity.nickName}
                    label={'输入昵称'}
                    id={entity.id}
                    onSuccess={() => action?.reload()}
                  />
                </Space>
              );
            },
          },
          {
            key: 'email',
            title: '邮箱',
            dataIndex: 'email',
          },
          {
            key: 'phone',
            title: '手机',
            dataIndex: 'phone',
          },
          {
            key: 'loginTime',
            title: '登录时间',
            dataIndex: 'loginTime',
          },
          {
            key: 'openAiFlag',
            title: 'AI会员',
            dataIndex: 'openAiFlag',
            valueType: 'checkbox',
          },
          {
            key: 'wallet',
            title: '钱包余额',
            dataIndex: 'wallet',
          },
          {
            key: 'vip',
            title: '会员类型',
            dataIndex: 'vip',
            render: (dom, entity, number, action) => {
              let text = <span></span>;
              switch (entity.vip) {
                case Vip.no: {
                  text = <span>未开通</span>;
                  break;
                }
                case Vip.vip: {
                  text = <Tag color={'blue'}>普通会员</Tag>;
                  break;
                }
                case Vip.super: {
                  text = <Tag color={'purple'}>企业会员</Tag>;
                  break;
                }
              }
              return (
                <Space>
                  {text}
                  <UpdateFromModal<{ vip: string }>
                    title={'修改账号会员类型'}
                    id={entity.id}
                    dom={
                      <ProFormSelect
                        options={[
                          {
                            label: '普通会员 (20/月)',
                            value: 1,
                          },
                          {
                            label: '企业会员 (188/月)',
                            value: 2,
                          },
                          {
                            label: '关闭会员',
                            value: 0,
                          },
                        ]}
                        name={'vip'}
                        initialValue={entity.vip}
                      ></ProFormSelect>
                    }
                    onSuccess={() => action?.reload()}
                  />
                </Space>
              );
            },
          },
          {
            key: 'openAiTokens',
            title: 'Tokens余额',
            dataIndex: 'openAiTokens',
            render: (dom, entity, number, action) => {
              return (
                <Space>
                  {!entity.openAiTokens && <span>0</span>}
                  {entity.openAiTokens && <span>{entity.openAiTokens}</span>}
                  <UpdateFromModal
                    initValue={entity.openAiTokens?.toString() ?? '0'}
                    title={'充值token'}
                    id={entity.id}
                    onSuccess={() => action?.reload()}
                    name={'tokens'}
                    tigger={<a>充值</a>}
                  />
                </Space>
              );
            },
          },
          {
            key: 'status',
            title: '账号状态',
            dataIndex: 'status',
            render: (dom, entity) => {
              switch (entity.status) {
                case 0:
                  return <Tag color={'success'}>正常使用</Tag>;
                case 1:
                  return <Tag>账户密码失效</Tag>;
                case 2:
                  return <Tag>账号被锁定</Tag>;
                case 3:
                  return <Tag>账户密码被锁定</Tag>;
                case 4:
                  return <Tag>等待验证邮箱</Tag>;
              }
              return <>未知{entity.status}</>;
            },
          },
          {
            title: '企业',
            key: 'enterprise',
            dataIndex: 'enterprise',
            render: (dom, entity, index, action) => {
              return (
                <Space>
                  {!entity.enterprise && (
                    <UpdateFromModal<{ enterpriseId: number }>
                      id={entity.id}
                      onSuccess={() => action?.reload()}
                      dom={
                        <ProFormSelect
                          request={async () => {
                            let r = await ApiEnterpriseFindAll();
                            return [
                              ...r.data.map((value) => {
                                return {
                                  label: value.name,
                                  value: value.id,
                                };
                              }),
                            ];
                          }}
                          name={'enterpriseId'}
                        />
                      }
                      tigger={<a>关联企业</a>}
                      title={'绑定企业'}
                    />
                  )}
                  {entity.enterprise && <Tag>{entity.enterprise.name}</Tag>}
                </Space>
              );
            },
          },
          {
            title: '操作',
            key: 'action',
            render: (dom, entity, number, action) => {
              return (
                <Space>
                  <Popconfirm
                    title={'删除用户'}
                    description={'确定要删除该用户的相关数据吗? '}
                    onConfirm={async () => {
                      await ApiDeleteUser({ id: entity.id });
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
      />
    </div>
  );
}

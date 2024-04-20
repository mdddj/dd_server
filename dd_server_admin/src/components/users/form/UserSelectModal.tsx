import { ApiUserFindAll } from '@/services/user/UserController';
import { User } from '@/types/user';
import { ModalForm, ProFormSelect } from '@ant-design/pro-components';
import React from 'react';

type UserSelectModalProp = {
  onFinish?: (values: any, userId: number) => Promise<boolean>;
  trigger?: JSX.Element;
  title?: string;
  width?: number;
};

const UserSelectModal: React.FC<UserSelectModalProp> = (props) => {
  return (
    <ModalForm
      width={props.width ?? 400}
      title={props.title ?? '选择用户'}
      onFinish={async (values) => {
        if (props.onFinish) {
          return props.onFinish(values, values.userId);
        }
        return false;
      }}
      trigger={props.trigger ? props.trigger : undefined}
    >
      <ProFormSelect
        name={'userId'}
        showSearch
        rules={[
          {
            required: true,
            message: '请选择用户',
          },
        ]}
        request={async () => {
          const all = await ApiUserFindAll();
          let users = all.data;
          return users.map((v: User) => {
            return {
              label: v.email + '(' + v.nickName + ')',
              value: v.id,
            };
          });
        }}
      ></ProFormSelect>
    </ModalForm>
  );
};

export default UserSelectModal;

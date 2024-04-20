import { ApiUpdateUserInfo } from '@/services/user/UserController';
import { EditOutlined } from '@ant-design/icons';
import { ModalForm, ProFormText } from '@ant-design/pro-components';
import React from 'react';

type UpdateFromProp = {
  initValue?: string;
  label?: string;
  name?: string;
  title?: string;
  id: number;
  onSuccess: () => void;
  dom?: React.ReactNode;
  tigger?: JSX.Element | undefined;
};
export default class UpdateFromModal<
  T,
> extends React.Component<UpdateFromProp> {
  render() {
    return (
      <ModalForm<T>
        width={300}
        title={this.props.title}
        trigger={this.props.tigger ?? <EditOutlined />}
        autoFocusFirstInput={true}
        modalProps={{
          destroyOnClose: true,
        }}
        onFinish={async (values) => {
          await ApiUpdateUserInfo({
            ...values,
            id: this.props.id,
          });
          this.props.onSuccess();
          return true;
        }}
      >
        {!this.props.dom && (
          <ProFormText
            key={this.props.name}
            name={this.props.name}
            label={this.props.label}
            initialValue={this.props.initValue}
          ></ProFormText>
        )}
        {this.props.dom !== null && this.props.dom}
      </ModalForm>
    );
  }
}

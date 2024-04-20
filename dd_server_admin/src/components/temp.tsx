import { ModalForm } from '@ant-design/pro-components';
import React from 'react';

interface Entity {}

type ModelDef = Entity;

interface Props {
  trigger?: React.JSX.Element;
  onSuccess?: () => void;
  initialValue?: any;
}

const ModelForm: React.FC<Props> = ({
  trigger,
  onSuccess,
  initialValue,
}) => {
  /// ToDo实现保存/修改请求
  const onSubmit = async (values: ModelDef): Promise<boolean> => {
    return true;
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
      ></ModalForm>
    </>
  );
};

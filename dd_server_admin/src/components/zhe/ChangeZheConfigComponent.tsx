import DesensitizationTextWidget from '@/components/widget/DesensitizationText';
import { Result } from '@/types/result';
import {ModalForm, ProDescriptions, ProFormText} from '@ant-design/pro-components';
import { request, useRequest } from '@umijs/max';
import {Button, Card, Descriptions} from 'antd';
import React from 'react';
import { useMount } from 'react-use';

const ChangeZheConfigComponent: React.FC = () => {
  const { run, loading, data, refresh } = useRequest(getConfig, {
    manual: true,
  });

  useMount(() => run());

  return (
    <Card title={'折淘客'}
          extra={[
            <UpdateModalForm
                key={'update'}
                trigger={
                  <Button size={'small'} type={'primary'}>
                    修改
                  </Button>
                }
                onSuccess={refresh}
                initValue={data}
            />,
          ]}>
      {
        <ProDescriptions>
          <ProDescriptions.Item label={'sid'}>{data?.sid}</ProDescriptions.Item>
          <ProDescriptions.Item label={'appKey'}>
            <DesensitizationTextWidget text={data?.appKey} />
          </ProDescriptions.Item>
          <ProDescriptions.Item label={'淘宝回调地址'}>
            {data?.tbAuthBackUrl}
          </ProDescriptions.Item>
          <ProDescriptions.Item label={'唯品会sid'}>
            {data?.wphSid}
          </ProDescriptions.Item>
        </ProDescriptions>
      }
    </Card>
  );
};

const UpdateModalForm: React.FC<{
  trigger?: React.JSX.Element;
  onSuccess?: () => void;
  initValue?: ZheConfigModel;
}> = ({ trigger, onSuccess, initValue }) => {


  const onSubmit = async (values: ZheConfigModel): Promise<boolean> => {
    const r = await update(values);
    r.success && onSuccess?.();
    return r.success;
  };


  return (
    <>
      <ModalForm<ZheConfigModel>
        initialValues={initValue}
        onFinish={onSubmit}
        trigger={trigger}
      >
        <ProFormText
          name={'appKey'}
          label={'app key'}
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        />

        <ProFormText
          name={'sid'}
          label={'sid'}
          rules={[
            {
              message: '请输入字段内容',
              required: true,
            },
          ]}
        />

        <ProFormText name={'tbAuthBackUrl'} label={'淘宝授权回调URL'} />

        <ProFormText name={'wphSid'} label={'唯品会sid'} />
      </ModalForm>
    </>
  );
};

interface ZheConfigModel {
  appKey: string;
  sid: string;
  tbAuthBackUrl: string;
  wphSid: string;
}

async function update(model: ZheConfigModel): Promise<Result<ZheConfigModel>> {
  return request<Result<ZheConfigModel>>('/api/admin/zhe/update', {
    method: 'POST',
    data: model,
  });
}

export async function getConfig(): Promise<Result<ZheConfigModel>> {
  return request('/api/admin/zhe/get', {
    method: 'GET',
  });
}

export default ChangeZheConfigComponent;

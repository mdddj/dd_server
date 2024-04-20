import { Result } from '@/types/result';
import { ModalForm, ProFormText } from '@ant-design/pro-components';
import { request, useModel } from '@umijs/max';
import { Button, Modal, Typography, message } from 'antd';
import React from 'react';

async function channelGeneration(params: any): Promise<Result<string>> {
  return request('/api/zhe/channel-generation', {
    method: 'POST',
    data: params,
  });
}

///渠道码生成工具
export const ChannelGenerateWidget: React.FC = () => {
  const initState = useModel('@@initialState');

  const getSuccessLink = (code: string): React.ReactNode => {
    const url = `https://mos.m.taobao.com/inviter/register?inviterCode=${code}&src=pub&app=common&rtag=${initState.initialState?.user?.id}`;
    return (
      <Typography.Text copyable={{ text: url }} type={'success'}>
        {url}
      </Typography.Text>
    );
  };

  const submit = async (params: any): Promise<boolean> => {
    const hide = message.loading('生成中...');
    const result = await channelGeneration(params);
    hide();
    if (result.success) {
      Modal.success({ content: getSuccessLink(result.data), width: 600 });
    }
    return result.success;
  };

  return (
    <ModalForm trigger={<Button>渠道邀请码生成</Button>} onFinish={submit}>
      <ProFormText
        label={'渠道推广的物料类型，示例值：common'}
        name={'relationApp'}
      />
      <ProFormText
        label={'邀请码类型，1 - 渠道邀请，2 - 渠道裂变，3 -会员邀请'}
        name={'codeType'}
      />
    </ModalForm>
  );
};

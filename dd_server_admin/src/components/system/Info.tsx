import React, { useEffect } from 'react';
import useOsWebSocket, { MemoryInfo } from '@/models/useOsWebSocket';
import { Badge, Card, Progress, Spin } from 'antd';
import { ReadyState } from 'react-use-websocket';

const InfoItem: React.FC<{ title: string, value: string }> = (props) => {
  return <div className={'flex flex-row gap-2'}>
    <div className={'font-bold w-32'}>{props.title}</div>
    <div>{props.value}</div>
  </div>;
};
const SystemInfoCard: React.FC = () => {
  const { lastJsonMessage, sendMessage, readyText, data, loading, readyState } = useOsWebSocket();

  useEffect(() => {
    if (readyState === ReadyState.OPEN) {
      setInterval(() => {
        sendMessage('Memory');
      }, 2000);
    }
  }, [readyState]);

  let obj = (lastJsonMessage ?? data) as (MemoryInfo | undefined);

  return <Card title={'内存监听'} loading={!obj} extra={<span>{loading && <Spin />}{readyState === ReadyState.OPEN &&
    <Badge status="success" />} {readyText}</span>}>
    {
      obj && <div className={'flex flex-col gap-2 relative'}>
        <InfoItem title={'总内存'} value={obj.totalString} />
        <InfoItem title={'已使用'} value={obj.useString} />
        <InfoItem title={'可用'} value={obj.availableString} />
        <InfoItem title={'使用率'} value={Math.floor(obj.usageRate * 100) + '%'} />
        <div className={'absolute right-2 overflow-y-hidden bg-green h-full'}>
          <Progress percent={Math.floor(obj.usageRate * 100)} type={'dashboard'} />
        </div>
      </div>
    }
  </Card>;
};


export default SystemInfoCard;
import MinioUpdateComponent from '@/pages/minio/UpdateConfig';
import { getMinioConfig } from '@/services/minio/MinioService';
import { useRequest } from '@umijs/max';
import {Card, Descriptions} from 'antd';
import React from 'react';
import { useMount } from 'react-use';
import DesensitizationTextWidget from "@/components/widget/DesensitizationText";
import {ProDescriptions} from "@ant-design/pro-components";

const MinioInfoWidget: React.FC = () => {
  const { data, run, refresh } = useRequest(getMinioConfig, {
    manual: true,
  });

  useMount(() => {
    run();
  });

  return (
    <Card title='minio配置' extra={<MinioUpdateComponent onUpdated={refresh} />}>
        <ProDescriptions >
          <ProDescriptions.Item label={'host'}>{data?.endpoint}</ProDescriptions.Item>
          <ProDescriptions.Item label={'accessKey'}>
            <DesensitizationTextWidget text={data?.accessKey} />
          </ProDescriptions.Item>
          <ProDescriptions.Item label={'secretKey'}>
            <DesensitizationTextWidget text={data?.secretKey}/>
          </ProDescriptions.Item>
          <ProDescriptions.Item label={'bucket'}>
            {data?.bucketName}
          </ProDescriptions.Item>
        </ProDescriptions>
    </Card>
  );
};
export default MinioInfoWidget;

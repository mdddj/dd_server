import ChangeZheConfigComponent from '@/components/zhe/ChangeZheConfigComponent';
import { MailConfigShow } from '@/pages/Email/EmailConfigSetting';
import MinioInfoWidget from '@/pages/minio/MinioInfoWidget';
import { PageContainer } from '@ant-design/pro-components';
import { useModel } from '@umijs/max';
import React from 'react';

const HomePage: React.FC = () => {
  const { name } = useModel('global');
  return (
    <PageContainer ghost>
      <div className={'grid  gap-4 grid-cols-1'}>
        <MinioInfoWidget />
        <ChangeZheConfigComponent />
        <MailConfigShow />
      </div>
    </PageContainer>
  );
};

export default HomePage;

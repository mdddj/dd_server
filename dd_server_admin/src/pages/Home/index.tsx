import { PageContainer } from '@ant-design/pro-components';
import React from 'react';
import SystemInfoCard from '@/components/system/Info';
import SystemJavaInfoCard from '@/components/SystemJavaInfoCard';
const HomePage: React.FC = () => {
  return (
    <PageContainer ghost>
      <div className={'grid gap-4 grid-cols-2'}>
        <SystemInfoCard />
        <SystemJavaInfoCard/>
      </div>
    </PageContainer>
  );
};

export default HomePage;

import { PageContainer } from '@ant-design/pro-components';
import { useModel } from '@umijs/max';
import React from 'react';

const HomePage: React.FC = () => {
  const { name } = useModel('global');
  return (
    <PageContainer ghost>
      <div className={'grid  gap-4 grid-cols-1'}>
      
      </div>
    </PageContainer>
  );
};

export default HomePage;

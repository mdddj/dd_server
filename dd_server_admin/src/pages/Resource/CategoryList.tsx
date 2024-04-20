import CreateOrUpdateCategory from '@/pages/Resource/CreateOrUpdateCategory';
import { MyApiWithResourceCategoryList } from '@/services/resource/apis';
import { ResourcesCategory } from '@/services/resource/types';
import { PageContainer, ProTable } from '@ant-design/pro-components';
import { Card } from 'antd';

export default function Page() {
  return (
    <PageContainer
      title={'分类列表'}
      extra={[<CreateOrUpdateCategory key={'create'} />]}
    >
      <Card>
        <ProTable<ResourcesCategory>
          columns={[
            {
              key: 'id',
              dataIndex: 'id',
              title: 'id',
            },
            {
              key: 'name',
              dataIndex: 'name',
              title: 'name',
            },
            {
              key: 'logo',
              dataIndex: 'logo',
              title: 'logo',
            },
            {
              key: 'description',
              dataIndex: 'description',
              title: 'description',
            },
            {
              key: 'type',
              dataIndex: 'type',
              title: 'type',
            },
            {
              key: 'navJsonString',
              dataIndex: 'navJsonString',
              title: 'navJsonString',
            },
            {
              key: 'parentNode',
              dataIndex: 'parentNode',
              title: 'parentNode',
            },
            {
              key: 'level',
              dataIndex: 'level',
              title: 'level',
            },
          ]}
          request={async (params) => {
            let r = await MyApiWithResourceCategoryList({
              ...params,
              remove: 1,
            });
            return {
              success: r.success,
              data: r.data.list,
              total: r.data.page.total,
            };
          }}
          search={false}
        />
      </Card>
    </PageContainer>
  );
}

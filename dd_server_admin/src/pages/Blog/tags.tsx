import { ActionType, PageContainer, ProTable } from '@ant-design/pro-components';
import React, { useRef } from 'react';
import { request } from '@umijs/max';
import { Result } from '@/types/result';
import { BlogTag } from '@/types/blog';
import { Button, Popconfirm, Space } from 'antd';


export async function getAllTags(): Promise<Result<BlogTag[]>> {
  return request('/api/auth/tag/all', {
    method: 'GET',
  });
}

export async function deleteTag(id: number) {
  return request('/api/auth/tag/delete', {
    method: 'DELETE',
    params: { id },
  });
}

//博客标签页面
const BlogTagsPage: React.FC = () => {

  const ref = useRef<ActionType>();
  return <PageContainer>
    <ProTable<BlogTag>
      actionRef={ref}
      columns={[
        {
          'key': 'id',
          'dataIndex': 'id',
          'title': 'id',
        },
        {
          'key': 'name',
          'dataIndex': 'name',
          'title': '名称',
        },
        {
          'key': 'action',
          render: (dom, entity) => {
            return <Space>
              <Popconfirm title={'确定删除吗'} onConfirm={async () => {
                await deleteTag(entity.id);
                ref.current?.reload();
              }}>
                <Button>删除</Button>
              </Popconfirm>
            </Space>;
          },
        },
      ]}
      request={async () => {
        let r = await getAllTags();
        return {
          success: r.success,
          data: r.data,
          total: r.data.length,
        };
      }}>

    </ProTable>
  </PageContainer>;
};
export default BlogTagsPage;

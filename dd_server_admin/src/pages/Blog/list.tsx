import { GetBlogList } from '@/services/blog/BlogController';
import { Blog } from '@/types/blog';
import { useNavigate } from '@@/exports';
import { ActionType, PageContainer, ProList } from '@ant-design/pro-components';
import { Avatar, Button, Card, Popconfirm, Popover, Space, Tag, Typography } from 'antd';
import React, { useRef } from 'react';
import { request } from '@umijs/max';
import { Result } from '@/types/result';
import { TagOutlined } from '@ant-design/icons';


export async function deleteBlog(id: number) {
  return request('/api/auth/blog-delete', {
    method: 'DELETE',
    data: { id },
  });
}

async function updateDescFromAi(id: number): Promise<Result<boolean>> {
  return request<Result<boolean>>('/api/auth/ai-generate-desc', {
    method: 'POST',
    data: {
      id,
    },
  });
}

async function invalidateBlogsCache() {
  request("/api/auth/invalidate-blog-cache",{method: 'POST',})
}

const Page: React.FC = () => {
  const nav = useNavigate();
  const ref = useRef<ActionType>(undefined);
  return (
    <PageContainer>
      <Card extra={<Space>

        <Button onClick={async ()=>{
          await invalidateBlogsCache()
        }}>使博客缓存失效</Button>
      </Space>}>
        <ProList<Blog>
          actionRef={ref}
          pagination={{}}
          request={async (params) => {
            let data = await GetBlogList({ ...params });
            return {
              data: data.data.list,
              total: data.data.page.total,
              success: data.success,
            };
          }}
          metas={{
            avatar: {
              render: (_, entity) => {
                return <Avatar>{entity.id}</Avatar>;
              },
            },
            title: {
              dataIndex: 'title',
            },
            subTitle: {
              render: (_, entity) => {
                return (
                  <Space>
                    {entity.tags.map((value) => (
                      <Tag icon={<TagOutlined />} key={value.id}>{value.name}</Tag>
                    ))}
                    {entity.description?.length &&  <Popover content={entity.description} title="AI总结">
                      <Tag color={'blue'}>AI总结</Tag>
                    </Popover>}
                  </Space>
                );
              },
            },
            description: {
              render: (_, entity) => {
                return (
                  <Space >
                    <a>{entity.author}</a>
                    <span>发布于</span>
                    <a>{entity.createTime}</a>
                    {entity.html === '' && (
                      <Typography.Text type={'warning'}>
                        未生成html
                      </Typography.Text>
                    )}
                  </Space>
                );
              },
            },
            actions: {
              render: (_, entity) => {
                let id = entity.id;
                return (
                  <Space>
                    {id && <Button
                      onClick={() => {
                        nav('/blog/add?update=' + id);
                      }}
                    >
                      编辑
                    </Button>}
                    {id && <Button onClick={async () => {
                      try {
                        await updateDescFromAi(id);
                        ref.current?.reload();
                      } catch (e) {
                      }
                    }}>
                      AI总结
                    </Button>}
                    <Popconfirm title={'确定删除吗? '} onConfirm={async () => {
                      if (id != null) {
                        await deleteBlog(id);
                        ref.current?.reload();
                      }
                    }}>
                      <Button type={'dashed'}>删除</Button>
                    </Popconfirm>
                  </Space>
                );
              },
            },
          }}
        />
      </Card>
    </PageContainer>
  );
};
export default Page;

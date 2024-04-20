import { GetBlogList } from '@/services/blog/BlogController';
import { Blog } from '@/types/blog';
import { useNavigate } from '@@/exports';
import { PageContainer, ProList } from '@ant-design/pro-components';
import { Avatar, Button, Card, Space, Tag, Typography } from 'antd';
import React from 'react';

const Page: React.FC = () => {
  const nav = useNavigate();

  return (
    <PageContainer>
      <Card>
        <ProList<Blog>
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
                      <Tag key={value.id}>{value.name}</Tag>
                    ))}
                  </Space>
                );
              },
            },
            description: {
              render: (_, entity) => {
                return (
                  <Space>
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
                return (
                  <Space>
                    <Button
                      onClick={() => {
                        nav('/blog/add?update=' + entity.id);
                      }}
                    >
                      编辑
                    </Button>
                    <Button type={'dashed'}>删除</Button>
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

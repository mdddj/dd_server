import { DeleteFileById, GetAllFile } from '@/services/file/FileController';
import { FileInfo } from '@/services/file/type';
import { PageContainer, ProList } from '@ant-design/pro-components';
import {Avatar, Button, Card, Popconfirm, Space, message, Typography,Tooltip} from 'antd';

export default function Page() {
  return (
    <PageContainer>
      <Card>
        <ProList<FileInfo>
          request={async (params) => {
            let result = await GetAllFile({ ...params, remove: 1 });
            return {
              success: true,
              data: result.data.list,
            };
          }}
          pagination={{}}
          rowKey={'id'}
          metas={{
            title: {
              dataIndex: 'fileName',
                render: (dom, entity) => {
                  return <Tooltip title={entity.url}><Typography.Paragraph copyable={{text: entity.url}}>{dom}</Typography.Paragraph></Tooltip>
                }
            },
            avatar: {
              dataIndex: 'url',
              render: (_dom, entity) => (
                <Space>
                  <Avatar src={entity.url} />
                </Space>
              ),
            },
            subTitle: {
              dataIndex: 'createDate',
              render: (dom) => {
                return <>上传时间:{dom}</>;
              },
            },
            actions: {
              render(_, entity, __, action) {
                return (
                  <Space>
                    <Popconfirm
                      title={'确定删除吗？'}
                      onConfirm={async () => {
                        let hide = message.loading('正在删除');
                        await DeleteFileById(entity.id);
                        hide();
                        action?.reload();
                      }}
                    >
                      <a>删除</a>
                    </Popconfirm>
                  </Space>
                );
              },
            },
          }}
          headerTitle="列表"
          toolBarRender={() => {
            return [
              <Button type="primary" key={'upload'}>
                上传图片
              </Button>,
            ];
          }}
        ></ProList>
      </Card>
    </PageContainer>
  );
}

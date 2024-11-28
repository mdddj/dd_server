import React, { useRef } from 'react';
import { ActionType, PageContainer, ProTable } from '@ant-design/pro-components';
import { request } from '@umijs/max';
import { Result } from '@/types/result';
import { Button, Popconfirm, Space } from 'antd';

interface DocDto {
  id: number,
  name: string
}

async function findAll() {
  return request<Result<DocDto[]>>('/api/admin/doc/all', {
    method: 'GET',
  });
}

async function deleteById(name: string) {
  return request<Result<boolean>>(`/api/admin/doc/${name}`, {
    method: 'DELETE',
  });
}

export default function Page() {

  const actionRef = useRef<ActionType>()


  return (
    <PageContainer title={'idea文档列表'}>
      <ProTable<DocDto>
        actionRef={actionRef}
        rowKey={'id'}
        request={async () => {
          const result = await findAll();
          return {
            success: true,
            data: result.data,
            total: result.data.length,
          };
        }}
        columns={[{
          'key': 'id',
          'dataIndex': 'id',
          'title': 'id',
        },
          {
            'key': 'name',
            'dataIndex': 'name',
            'title': '名称',
          }, {
            key: 'action',
            render: (_, entity) => {
              return <Space>
                <Popconfirm title={'确定删除吗?'} onConfirm={async () => {
                  const result = await deleteById(entity.name);
                  if(result.success) {
                    actionRef.current?.reload()
                  }
                }}>
                  <Button>删除</Button>
                </Popconfirm>
              </Space>;
            },
          }]}>

      </ProTable>
    </PageContainer>
  );
}

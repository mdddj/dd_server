import { Project } from '@/types/project';
import { JpaPage, Result } from '@/types/result';
import { PageContainer, ProTable } from '@ant-design/pro-components';
import {request, useNavigate} from '@umijs/max';
import {Button, Image, Popconfirm, Space} from 'antd';

async function findBy(params: any): Promise<Result<JpaPage<Project>>> {
  return request('/api/project/findBy', {
    method: 'GET',
    params: params,
  });
}

async function deleteById(id: number) {
  return request('/api/project/delete', {
    method: 'DELETE',
    params: { id },
  });
}

export default function Page() {
  const nav = useNavigate()
  return (
    <PageContainer>
      <ProTable<Project>
        request={async (params, filters) => {
          let r = await findBy({ ...params, ...filters, remove: 1 });
          return {
            data: r.data.content,
            total: r.data.totalElements,
            success: r.success,
          };
        }}
        columns={[
          {
            key: 'id',
            dataIndex: 'id',
            title: '项目主键',
          },
          {
            key: 'name',
            dataIndex: 'name',
            title: '项目名称',
          },
          {
            key: 'description',
            dataIndex: 'description',
            title: '项目介绍',
          },
          {
            key: 'logo',
            dataIndex: 'logo',
            title: '项目logo',
            render: (__, entity) => <>{ entity.logo!== '' && <Image src={entity.logo} width={44} height={44} />}</>
          },
          {
            key: 'github',
            dataIndex: 'github',
            title: '源码仓库',
          },
          {
            key: 'downloadUrl',
            dataIndex: 'downloadUrl',
            title: '下载地址',
          },
          {
            key: 'previewUrl',
            dataIndex: 'previewUrl',
            title: '预览地址',
          },
          {
            title: '操作',
            render: (__, entity, _, action) => {
              return (
                <Space>
                  <Popconfirm
                    title={'确定删除吗? '}
                    onConfirm={async () => {
                      await deleteById(entity.id!!);
                      action?.reload();
                    }}
                  >
                    <Button size={'small'} type={'link'}>
                      删除
                    </Button>
                  </Popconfirm>
                  <Button size={'small'} type={'link'} onClick={()=>{
                    nav('/project/new?id='+entity.id)
                  }}>编辑</Button>
                </Space>
              );
            },
          },
        ]}
        rowKey={'id'}
      />
    </PageContainer>
  );
}

import CreateOrUpdateCategory from '@/pages/Resource/CreateOrUpdateCategory';
import {MyApiWithResourceCategoryList} from '@/services/resource/apis';
import {ResourcesCategory} from '@/services/resource/types';
import {ActionType, PageContainer, ProTable} from '@ant-design/pro-components';
import { request } from '@umijs/max';
import {Card, Space, Button, Popconfirm} from 'antd';
import { useRef } from 'react';

export default function Page() {



    const tableRef = useRef<ActionType>()

    ///删除某个分类
   async function onDeleteItem(entity: ResourcesCategory) {
       await request("/api/res/delete",{
            method: 'DELETE',
            params: {
                id: entity.id
            }
        })
        tableRef.current?.reload()
    }

    return (
        <PageContainer
            title={'分类列表'}
            extra={[<CreateOrUpdateCategory key={'create'}/>]}
        >
            <Card>
                <ProTable<ResourcesCategory>

actionRef={tableRef}
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
                        {
                            key: "actions",
                            render: (_,entity) => <Space>
                                <Popconfirm title={"确定删除吗?"} onConfirm={()=>onDeleteItem(entity)}>
                                <Button size={'small'} type={'dashed'}>删除</Button>
                                </Popconfirm>
                            </Space>
                        }
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

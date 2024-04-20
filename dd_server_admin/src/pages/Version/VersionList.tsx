import ApiTable from '@/components/Apitable';
import { Version } from '@/models/Version';
import { MyVersionDeleteApi } from '@/services/version/VersionService';
import { CoverToPageData, Result } from '@/types/result';
import { request, useNavigate } from '@umijs/max';
import { Button, Card, Checkbox, Modal, Space } from 'antd';

async function list(params: any): Promise<Result<CoverToPageData<Version>>> {
    return request<Result<CoverToPageData<Version>>>('/api/version/list', {
        method: 'GET',
        params: params,
    });
}

const Page = () => {
    const nav = useNavigate();

    const fetchData = (params: any) => {
        return list(params);
    };

    return (
        <Card>
            <ApiTable<Version>
                api={fetchData}
                rowKey={'id'}
                columns={[
                    {
                        key: 'id',
                        dataIndex: 'id',
                        title: '主键',
                    },
                    {
                        key: 'title',
                        dataIndex: 'title',
                        title: '标题',
                    },
                    {
                        key: 'description',
                        dataIndex: 'description',
                        title: '更新记录',
                    },
                    {
                        key: 'downloadUrl',
                        dataIndex: 'downloadUrl',
                        title: '下载地址(直链)',
                        render: (_, model) => {
                            let url = model.downloadUrl;
                            if (!url || url.length === 0) {
                                return '未设置';
                            }
                            return (
                                <Button
                                    size={'small'}
                                    onClick={() => {
                                        Modal.info({
                                            content: url,
                                        });
                                    }}
                                >
                                    查看
                                </Button>
                            );
                        },
                    },
                    {
                        key: 'platform',
                        dataIndex: 'platform',
                        title: '平台',
                    },
                    {
                        key: 'createDate',
                        dataIndex: 'createDate',
                        title: '创建时间',
                    },
                    {
                        key: 'htmlViewPage',
                        dataIndex: 'htmlViewPage',
                        title: '第三方下载地址',
                        render: (_, model) => {
                            let url = model.htmlViewPage;
                            if (!url || url.length === 0) {
                                return '未设置';
                            }
                            return (
                                <Button
                                    size={'small'}
                                    onClick={() => {
                                        Modal.info({
                                            content: url,
                                        });
                                    }}
                                >
                                    查看
                                </Button>
                            );
                        },
                    },
                    {
                        key: 'packageSize',
                        dataIndex: 'packageSize',
                        title: '包大小',
                    },
                    {
                        key: 'enable',
                        dataIndex: 'enable',
                        title: '是否有效',
                        render(dom, entity, index, action, schema) {
                            return <Checkbox checked={entity.enable} />;
                        },
                    },
                    {
                        key: 'versionNumber',
                        dataIndex: 'versionNumber',
                        title: '版本号',
                    },
                    {
                        key: 'projectName',
                        dataIndex: 'projectName',
                        title: '项目名称',
                        render: (_, model) => {
                            return (
                                <Space>
                                    <span>{model?.project?.name}</span>
                                </Space>
                            );
                        },
                    },
                    {
                        title: '操作',
                        render: (_, model, __, action) => {
                            return (
                                <Space>
                                    <Button
                                        size={'small'}
                                        onClick={() => {
                                            nav(`/version/add?id=${model.id}`);
                                        }}
                                    >
                                        编辑
                                    </Button>
                                    <Button
                                        type={'dashed'}
                                        size={'small'}
                                        onClick={async () => {
                                            if (model.id != null) {
                                                const { success } = await MyVersionDeleteApi(model.id);
                                                if (success) {
                                                    action?.reload();
                                                }
                                            }
                                        }}
                                    >
                                        删除
                                    </Button>
                                </Space>
                            );
                        },
                    },
                ]}
            />
        </Card>
    );
};
export default Page;

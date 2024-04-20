import MarkdownEditor from '@/components/MarkdownEditor';
import { TextModel } from '@/models/Text';
import { JpaPage, Result } from '@/types/result';
import {
  ActionType,
  ModalForm,
  PageContainer,
  ProFormCheckbox,
  ProFormItem,
  ProFormText,
  ProTable,
} from '@ant-design/pro-components';
import { request } from '@umijs/max';
import { Button, Popconfirm, Space } from 'antd';
import React, { ReactElement, useState } from 'react';

/**
 * 查询
 * @param params
 */
async function getList(params: any): Promise<Result<JpaPage<TextModel>>> {
  return request('/api/text/list', {
    method: 'GET',
    params,
  });
}

/**
 * 新增或者修改
 * @param data
 */
async function save(data: TextModel): Promise<Result<TextModel>> {
  return request('/api/text/save', {
    method: 'POST',
    data: data,
  });
}

/**
 * 删除
 * @param id
 */
export async function deleteItem(id: number): Promise<Result<boolean>> {
  return request('/api/text/delete', {
    method: 'DELETE',
    params: { id },
  });
}

export default function Page() {
  const actionRef = React.useRef<ActionType>();
  return (
    <PageContainer
      extra={[
        <TextForm
          trigger={<Button>添加字典</Button>}
          onSuccess={() => actionRef?.current?.reload()}
        />,
      ]}
    >
      <ProTable
        actionRef={actionRef}
        request={async (params, _, filter) => {
          const response = await getList({ ...params, ...filter, remove: 1 });
          return {
            success: response.success,
            data: response.data.content,
            total: response.data.totalElements,
          };
        }}
        columns={[
          {
            key: 'id',
            dataIndex: 'id',
            title: '主键ID',
          },
          {
            key: 'name',
            dataIndex: 'name',
            title: '查询关键字',
          },
          {
            key: 'intro',
            dataIndex: 'intro',
            title: '介绍',
          },
          {
            key: 'context',
            dataIndex: 'context',
            title: '正文内容',
          },
          {
            key: 'isEncryptionText',
            dataIndex: 'isEncryptionText',
            title: '是否加密',
          },
          {
            key: 'viewPassword',
            dataIndex: 'viewPassword',
            title: '查看密码',
          },
          {
            key: 'originPassword',
            dataIndex: 'originPassword',
            title: '原始查看密码',
          },
          {
            title: '操作',
            render: (_, entity, __, action) => {
              return (
                <Space>
                  <TextForm
                    trigger={<Button size={'small'}>修改</Button>}
                    initValue={entity}
                    onSuccess={() => action?.reload()}
                  />
                  <Popconfirm
                    title={'确定删除吗?'}
                    onConfirm={async () => {
                      const { id } = entity;
                      const response = id && (await deleteItem(id));
                      if (response && response.success) {
                        action?.reload();
                      }
                    }}
                  >
                    <Button size={'small'} type={'dashed'}>
                      删除
                    </Button>
                  </Popconfirm>
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

/**
 * 新增或者编辑表单
 */
type Props = {
  trigger?: ReactElement;
  onSuccess?: () => void;
  initValue?: TextModel;
};
const TextForm: React.FC<Props> = ({ trigger, onSuccess, initValue }) => {
  const [content, setContent] = useState(initValue?.context??'');
  return (
    <ModalForm<TextModel>
      trigger={trigger}
      onFinish={async (values: TextModel) => {
        const response = await save({ ...values, context: content });
        response.success && onSuccess?.();
        return response.success;
      }}
      initialValues={initValue}
    >
      <ProFormText name={'id'} label={'主键ID'} disabled={true} />

      <ProFormText name={'name'} label={'查询关键字'} />

      <ProFormText name={'intro'} label={'介绍'} />

      <ProFormItem label={'正文内容'}>
        <MarkdownEditor onChange={setContent} value={content} />
      </ProFormItem>

      <ProFormCheckbox name={'isEncryptionText'}>是否加密</ProFormCheckbox>

      <ProFormText name={'viewPassword'} label={'查看密码'} />

      <ProFormText name={'originPassword'} label={'原始查看密码'} />
    </ModalForm>
  );
};

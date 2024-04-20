import FileSelectWidget from '@/components/file/FileSelectComponent';
import { Project } from '@/types/project';
import { Result } from '@/types/result';
import {
  PageContainer,
  ProForm,
  ProFormInstance,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-components';
import { request, useNavigate, useSearchParams } from '@umijs/max';
import { Card } from 'antd';
import { useEffect, useRef } from 'react';

async function save(params: any): Promise<Result<Project>> {
  return request('/api/project/save', {
    method: 'POST',
    data: params,
  });
}

async function findById(id: string): Promise<Result<Project>> {
  return request('/api/project/findById', {
    method: 'GET',
    params: { id },
  });
}

export default function Page() {
  const formRef = useRef<ProFormInstance>();
  const [searchParma] = useSearchParams();
  const id = searchParma.get('id');
  const nav = useNavigate();
  useEffect(() => {
    if (id) {
      findById(id).then((value) => formRef.current?.setFieldsValue(value.data));
    }
  }, [id]);
  return (
    <PageContainer title={'添加新项目'}>
      <Card>
        <ProForm
          formRef={formRef}
          onFinish={async (formData) => {
            let r = await save(formData);
            if (r.success) {
              nav('/project/list');
            }
            return r.success;
          }}
        >
          <ProFormText
            name={'id'}
            label={'Id'}
            hidden={!id}
            disabled={id !== undefined}
          />

          <ProFormText name={'name'} label={'项目名称'} />

          <ProFormTextArea name={'description'} label={'项目介绍'} />

          <FileSelectWidget
            onFileSelect={(fileInfo) =>
              formRef.current?.setFieldValue('logo', fileInfo.url)
            }
          >
            <ProFormText name={'logo'} label={'项目logo'} />
          </FileSelectWidget>

          <ProFormText name={'github'} label={'源码仓库'} />

          <ProFormText name={'downloadUrl'} label={'下载地址'} />

          <ProFormText name={'previewUrl'} label={'预览地址'} />
        </ProForm>
      </Card>
    </PageContainer>
  );
}

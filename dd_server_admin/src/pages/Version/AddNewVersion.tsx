import FileSelectWidget from '@/components/file/FileSelectComponent';
import { Version } from '@/models/Version';
import { MyVersionSaveApi } from '@/services/version/VersionService';
import { Project } from '@/types/project';
import { Result } from '@/types/result';
import { isValidHttpUrl } from '@/utils/core';
import {
  PageContainer,
  ProForm,
  ProFormCheckbox,
  ProFormInstance,
  ProFormSelect,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-components';
import { request } from '@umijs/max';
import { Card } from 'antd';
import React, { useRef } from 'react';
import { useMount, useSearchParam } from 'react-use';

///获取全部项目列表
async function findAllProject(): Promise<Result<Project[]>> {
  return request<Promise<Result<Project[]>>>('/api/project/all', {
    method: 'GET',
  });
}

///查找对象
export async function findById(id: number): Promise<Result<Version>> {
  return request('/api/admin/version/get', {
    method: 'GET',
    params: { id },
  });
}

const AddNewVersionForm: React.FC = () => {
  const formKey = useRef<ProFormInstance<Version>>();
  let id: string | null = useSearchParam('id');

  ///提交数据
  const onFinish = async (values: Version) => {
    console.log(values);
    const result = await MyVersionSaveApi({
      ...values,
      project: { id: values.projectId },
    });
    return result.success;
  };

  async function _fetchVersionInfo() {
    if (id) {
      let idNumber = parseInt(id);
      let { success, data } = await findById(idNumber);
      if (success) {
        formKey.current?.setFieldsValue(data);
        formKey.current?.setFieldValue('projectId', data.project?.id);
        formKey.current?.setFieldValue('enable', data.enable ?? false);
      }
    }
  }

  useMount(() => {
    id && _fetchVersionInfo();
  });

  return (
    <PageContainer title={'发布新版本'}>
      <Card>
        <ProForm<Version> onFinish={onFinish} formRef={formKey}>
          <ProFormSelect
            label={'关联项目'}
            name={'projectId'}
            request={async () => {
              let r = await findAllProject();
              return r.data.map((value) => ({
                label: value.name,
                value: value.id,
              }));
            }}
            required={true}
          />

          <ProFormText name={'id'} label={'主键'} hidden={true} />

          <ProFormText name={'title'} label={'标题'} />

          <ProFormTextArea
            name={'description'}
            label={'更新记录'}
            rules={[{ required: true, message: '请输入更新记录' }]}
          />

          <FileSelectWidget
            onFileSelect={(fileInfo) => {
              formKey.current?.setFieldValue('downloadUrl', fileInfo.url);
              formKey.current?.setFieldValue('packageSize', fileInfo.fileSize);
              formKey.current?.setFieldValue('isDirectLink', true);
            }}
          >
            <ProFormTextArea
              fieldProps={{ rows: 5 }}
              name={'downloadUrl'}
              label={'下载地址(直链)'}
              rules={[
                ({}) => ({
                  validator(_, value: string, callback) {
                    if (
                      value &&
                      value.trim().length > 0 &&
                      !isValidHttpUrl(value)
                    ) {
                      callback('请输入有效的下载地址');
                    } else {
                      callback(undefined);
                    }
                  },
                }),
              ]}
            />
          </FileSelectWidget>

          <ProFormSelect
            name={'platform'}
            label={'平台'}
            required={true}
            options={[
              {
                label: '苹果',
                value: 'Ios',
              },
              {
                label: '安卓',
                value: 'Android',
              },
              {
                label: 'Windows',
                value: 'Windows',
              },
              {
                label: 'Macos',
                value: 'Macos',
              },
              {
                label: 'Linux',
                value: 'Linux',
              },
            ]}
          />

          <ProFormTextArea
            help={'多个请换行'}
            name={'htmlViewPage'}
            label={'第三方下载地址'}
            rules={[
              ({}) => ({
                validator(_, value: string, callback) {
                  if (
                    value &&
                    value.trim().length > 0 &&
                    !isValidHttpUrl(value)
                  ) {
                    callback('请输入有效的下载地址');
                  } else {
                    callback(undefined);
                  }
                },
              }),
            ]}
          />

          <ProFormText name={'packageSize'} label={'包大小'} />

          <ProFormCheckbox name={'enable'} label={'是否有效'} />
          <ProFormCheckbox
            name={'disabled'}
            label={'是否有效'}
            hidden={true}
            initialValue={true}
          />

          <ProFormText
            name={'versionNumber'}
            label={'版本号'}
            rules={[
              {
                required: true,
                message: '请输入版本号',
              },
            ]}
          />
        </ProForm>
      </Card>
    </PageContainer>
  );
};
export default AddNewVersionForm;

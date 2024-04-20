import FileSelectWidget from '@/components/file/FileSelectComponent';
import ResourceCategoryTypeSelect from '@/components/selects/ResourceCategoryType';
import { MyApiWithResourceCategoryUpdateAndSave } from '@/services/resource/apis';
import { ResourcesCategory } from '@/services/resource/types';
import {
  ModalForm,
  ProFormInstance,
  ProFormText,
} from '@ant-design/pro-components';
import { Button } from 'antd';
import { useRef } from 'react';

export default function CreateOrUpdateCategory() {
  const formRef = useRef<ProFormInstance>();
  return (
    <ModalForm<ResourcesCategory>
      width={300}
      formRef={formRef}
      title={'新增一个分类'}
      modalProps={{
        destroyOnClose: true,
      }}
      trigger={<Button type={'primary'}>新增</Button>}
      onFinish={async (params) => {
        let result = await MyApiWithResourceCategoryUpdateAndSave(params);
        return result.success;
      }}
    >
      <ProFormText name={'name'} label={'名称'} />
      <FileSelectWidget
        onFileSelect={(file) =>
          formRef.current?.setFieldValue('logo', file.url)
        }
      >
        <ProFormText name={'logo'} label={'图标'} />
      </FileSelectWidget>
      <ResourceCategoryTypeSelect name={'type'} label={'类型'} />
    </ModalForm>
  );
}

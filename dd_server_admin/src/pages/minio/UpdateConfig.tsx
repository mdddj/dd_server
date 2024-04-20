import { MinioDetailModel } from '@/services/minio/MinioModel';
import {
  getMinioConfig,
  updateMinioConfig,
} from '@/services/minio/MinioService';
import { ModalForm, ProFormText } from '@ant-design/pro-components';
import { useRequest } from '@umijs/max';
import { Button } from 'antd';



export default function MinioUpdateComponent(props:{onUpdated: () => void}) {
  const { data, loading } = useRequest(() => getMinioConfig());

  let onSubmitUpdate = async (values: MinioDetailModel): Promise<boolean> => {
    let result = await updateMinioConfig(values);
    console.log(result);
    let isSuccess= result.success
    if(isSuccess){
      props.onUpdated()
    }
    return isSuccess;
  };

  return (
    <ModalForm
      loading={loading}
      title={'编辑 Minio 配置'}
      initialValues={data}
      onFinish={onSubmitUpdate}
      trigger={<Button size={'small'} type={'primary'}>修改</Button>}
    >
      <ProFormText
        name={'endpoint'}
        label={'api端点'}
        rules={[
          {
            message: '请输入字段内容',
            required: true,
          },
        ]}
      />

      <ProFormText
        name={'accessKey'}
        label={'accessKey'}
        rules={[
          {
            message: '请输入字段内容',
            required: true,
          },
        ]}
      />

      <ProFormText
        name={'secretKey'}
        label={'secretKey'}
        rules={[
          {
            message: '请输入字段内容',
            required: true,
          },
        ]}
      />

      <ProFormText
        name={'bucketName'}
        label={'bucketName'}
        rules={[
          {
            message: '请输入字段内容',
            required: true,
          },
        ]}
      />
    </ModalForm>
  );
}

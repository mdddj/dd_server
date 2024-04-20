import * as EnterpriseController from '@/services/enterprise/EnterpriseController';
import {
  PageContainer,
  ProForm,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-components';
import { Card, message } from 'antd';

export default function Create() {
  return (
    <PageContainer title={'注册企业'}>
      <Card>
        <ProForm
          onFinish={async (formData) => {
            let hide = message.loading('正在创建');
            let result =
              await EnterpriseController.ApiEnterpriseCreate(formData);
            hide();
            message.success(result.message);
            return true;
          }}
        >
          <ProFormText name={'name'} label={'企业名称'} />
          <ProFormTextArea name={'profile'} label={'介绍'} />
          <ProFormText name={'phone'} label={'手机号'} tooltip={'仅联系使用'} />
          <ProFormText name={'qq'} label={'QQ'} tooltip={'仅联系使用'} />
          <ProFormText name={'wechat'} label={'微信'} tooltip={'仅联系使用'} />
        </ProForm>
      </Card>
    </PageContainer>
  );
}

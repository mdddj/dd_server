import { HOST_NAME } from '@/constants';
import { getAuthorizationHeader } from '@/utils/auth';
import { UploadOutlined } from '@ant-design/icons';
import { PageContainer } from '@ant-design/pro-components';
import { Button, Card, Upload } from 'antd';

export default function Page() {
  return (
    <PageContainer>
      <Card>
        <Upload
          name="file"
          action={HOST_NAME + '/api/storage/upload'}
          headers={getAuthorizationHeader()}
        >
          <Button icon={<UploadOutlined />}>选择文件后会自动上传</Button>
        </Upload>
      </Card>
    </PageContainer>
  );
}

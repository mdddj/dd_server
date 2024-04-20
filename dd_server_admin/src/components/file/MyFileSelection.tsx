import { OpenModalSelection } from '@/components/file/FileSelectorComponent';
import { GetAllFile } from '@/services/file/FileController';
import { FileInfo } from '@/services/file/type';
import { UploadOutlined } from '@ant-design/icons';

import { HOST_NAME } from '@/constants';
import { getAuthorizationHeader } from '@/utils/auth';
import { Image } from '@nextui-org/react';
import { Button, Modal, ModalProps, Upload } from 'antd';
import React, { useState } from 'react';
import { useMount } from 'react-use';

export type MyFileSelectionProp = {
  onFileSelect: (fileInfo: FileInfo) => void;
};
const FileInfoCardLayout: React.FC<{ file: FileInfo; onClick: () => void }> = ({
  file,
  onClick,
}) => {
  return (
    <Image
      onClick={onClick}
      isZoomed
      alt={file.fileName}
      src={file.url}
      className={'object-contain w-full aspect-square bg-secondary-50'}
    />
  );
};

///文件列表
const MyFileSelection: React.FC<MyFileSelectionProp & ModalProps> = ({
  onFileSelect,
  ...props
}) => {
  const [files, setFiles] = useState<FileInfo[]>([]);
  const getFiles = async () => {
    let result = await GetAllFile({ current: 1, pageSize: 20, remove: 1 });
    setFiles(result.data.list);
  };
  useMount(async () => {
    await getFiles();
  });
  return (
    <Modal title={'选择文件'} {...props}>
      <div style={{ marginBottom: 12 }}>
        <Upload
          name="file"
          action={HOST_NAME + '/api/storage/upload'}
          headers={getAuthorizationHeader()}
          onChange={async (info) => {
            if (info.file.status === 'done') {
              await getFiles();
            }
          }}
        >
          <Button type={'primary'} icon={<UploadOutlined />}>
            新.
          </Button>
        </Upload>
      </div>
      <div className={'grid grid-cols-4 gap-2'}>
        {files.map((value) => {
          return (
            <FileInfoCardLayout
              file={value}
              key={value.id}
              onClick={() => onFileSelect(value)}
            />
          );
        })}
      </div>
    </Modal>
  );
};

export function OpenFileSelectionModal(props: MyFileSelectionProp) {
  OpenModalSelection(<MyFileSelection {...props} />, props);
}

export { MyFileSelection };

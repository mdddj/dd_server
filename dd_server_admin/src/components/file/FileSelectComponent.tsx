import { CloudOutlined } from '@ant-design/icons';
import { Tooltip } from 'antd';
import React, { PropsWithChildren } from 'react';
import { MyFileSelectionProp, OpenFileSelectionModal } from './MyFileSelection';

type FileSelectWidgetProps = {
  top?: number;
  right?: number;
};

const FileSelectWidget: React.FC<
  PropsWithChildren<MyFileSelectionProp> & FileSelectWidgetProps
> = (props) => {
  return (
    <div
      style={{
        position: 'relative',
      }}
    >
      <div style={{ flex: 1, marginRight: 2 }}>{props.children}</div>
      <Tooltip title={'从文件中选择图像'}>
        <CloudOutlined
          style={{
            position: 'absolute',
            top: props.top ?? 6,
            right: props.right ?? 12,
          }}
          onClick={() => {
            OpenFileSelectionModal(props);
          }}
        />
      </Tooltip>
    </div>
  );
};

export default FileSelectWidget;

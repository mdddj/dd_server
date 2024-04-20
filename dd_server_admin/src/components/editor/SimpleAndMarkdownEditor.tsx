import {
  ProFormItem,
  ProFormItemProps,
  ProFormTextArea,
} from '@ant-design/pro-components';
import { Switch } from 'antd';
import React, { useState } from 'react';
/**
 * 属性
 */
type SimpleAndMarkdownEditorProp = {
  value?: string;
  onChange?: (value: string, html: string) => void;
  name?: string;
  label?: string;
};

/**
 * 编辑器
 * @param props
 * @constructor
 */
const SimpleAndMarkdownEditor: React.FC<
  SimpleAndMarkdownEditorProp & ProFormItemProps
> = (props) => {
  const [mk, setMk] = useState(false);
  const s = () => (
    <>
      <Switch checked={mk} onChange={setMk} size={'small'} /> Markdown编辑器
    </>
  );
  return (
    <>
      {mk && (
        <ProFormItem
          label={props.label}
          name={props.name}
          extra={s()}
          {...props}
        >

        </ProFormItem>
      )}
      {!mk && (
        <ProFormTextArea
          initialValue={props.value}
          name={props.name}
          label={props.label}
          fieldProps={{
            rows: 8,
          }}
          extra={s()}
          {...props}
        />
      )}
    </>
  );
};

export default SimpleAndMarkdownEditor;

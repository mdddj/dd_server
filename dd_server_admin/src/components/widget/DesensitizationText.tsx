import { EyeInvisibleOutlined, EyeOutlined } from '@ant-design/icons';
import React, { useState } from 'react';

type DesensitizationTextWidgetProp = {
  text?: string;
};

///脱敏字符串
const DesensitizationTextWidget: React.FC<DesensitizationTextWidgetProp> = ({
  text,
}) => {
  const [show, setShow] = useState(false);

  const changeText = (val?: string) => {
    if (val) {
      if (val.length) {
        const len = val.length;
        if (len === 1) {
          return '*';
        } else if (len === 2) {
          return val[0] + '*';
        } else if (len === 3) {
          return val[0] + '*' + val[2];
        } else if (len > 3 && len < 6) {
          const newVal = val.substring(2, len - 1);
          let newValChange = [];
          for (let i = 0; i < newVal.length; i++) {
            newValChange.push('*');
          }
          return val[0] + val[1] + newValChange.join('') + val[len - 1];
        } else if (len >= 6 && len < 8) {
          const newVal = val.substring(2, len - 2);
          let newValChange = [];
          for (let i = 0; i < newVal.length; i++) {
            newValChange.push('*');
          }
          return (
            val[0] +
            val[1] +
            newValChange.join('') +
            val[len - 2] +
            val[len - 1]
          );
        } else {
          const newVal = val.substring(3, len - 3);
          let newValChange = [];
          for (let i = 0; i < newVal.length; i++) {
            newValChange.push('*');
          }
          return (
            val[0] +
            val[1] +
            val[2] +
            newValChange.join('') +
            val[len - 3] +
            val[len - 2] +
            val[len - 1]
          );
        }
      }
    }
  };
  if(text === undefined || text.length === 0) return <span></span>;
  return (
    <span>
       {show ? text : changeText(text)}
      {show ? (
        <EyeInvisibleOutlined
          className={'ml-2'}
          onClick={() => setShow(false)}
          style={{ marginLeft: 2 }}
        />
      ) : (
        <EyeOutlined
          className={'ml-2'}
          onClick={() => setShow(true)}
          style={{ marginLeft: 2 }}
        />
      )}

    </span>
  );
};

export default DesensitizationTextWidget;

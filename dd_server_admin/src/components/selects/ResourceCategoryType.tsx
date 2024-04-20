import { Result } from '@/types/result';
import { ProFormSelect, ProFormSelectProps } from '@ant-design/pro-components';
import { request, useRequest } from '@umijs/max';
import { Input } from 'antd';
import { useState } from 'react';

interface TypeImpl {
  count: number;
  type: string;
}
async function getTypeList(): Promise<Result<TypeImpl[]>> {
  return request('/api/rc/types');
}

const ResourceCategoryTypeSelect: React.FC<ProFormSelectProps> = (props) => {
  const [inputValue, setInputValue] = useState('');
  const { data, loading } = useRequest(() => getTypeList());
  return (
    <ProFormSelect
      {...props}
      options={[
        ...(data?.map((v) => ({ label: v.type, value: v.type })) ?? []),
        inputValue !== '' && {
          label: inputValue,
          value: inputValue,
        },
      ]}
      fieldProps={{
        loading: loading,
        dropdownRender(menu) {
          return (
            <>
              {menu}
              <Input onChange={(e) => setInputValue(e.target.value)} />
            </>
          );
        },
      }}
    />
  );
};
export default ResourceCategoryTypeSelect;

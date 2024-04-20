import { MyApiWithResourceCategoryListAll } from '@/services/resource/apis';
import { ResourcesCategory } from '@/services/resource/types';
import { ProFormSelect } from '@ant-design/pro-components';
import { useRequest } from '@umijs/max';
import React from 'react';

type ResourceCategorySelectProps = {
  render: (
    list: ResourcesCategory[],
    loading: boolean,
    error: Error | undefined,
  ) => React.ReactNode;
};
/**
 * 资源分类选择器
 * @constructor
 */
const ResourceCategorySelect: React.FC<ResourceCategorySelectProps> = ({
  render,
}) => {
  const { data, loading, error } = useRequest(() =>
    MyApiWithResourceCategoryListAll(),
  );
  return <>{render(data ?? [], loading, error)}</>;
};

/**
 *  pro 表单
 * @param name
 * @param label
 * @constructor
 */
const ResourceCategorySelectProForm: React.FC<{
  name?: string;
  label?: string;
}> = ({ name, label }) => {
  return (
    <ResourceCategorySelect
      render={(list, loading) => {
        return (
          <ProFormSelect
            width={'md'}
            name={name}
            label={label}
            fieldProps={{
              loading: loading,
            }}
            options={[
              ...list.map((v) => {
                return {
                  label: v.name,
                  value: v.id,
                };
              }),
            ]}
          />
        );
      }}
    />
  );
};

export { ResourceCategorySelect, ResourceCategorySelectProForm };

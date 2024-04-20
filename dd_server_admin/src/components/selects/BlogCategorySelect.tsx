import { GetBlogCategorys } from '@/services/blog/BlogController';
import { BlogCategory } from '@/types/blog';
import { useRequest } from '@umijs/max';
import { Select, Spin } from 'antd';
import React from 'react';

type BlogCategorySelectProps = {
  onSelect?: (category: BlogCategory) => void;
  value?: BlogCategory | undefined;
};

const BlogCategorySelect: React.FC<BlogCategorySelectProps> = ({
  onSelect,
  value,
}) => {
  const { data, error, loading } = useRequest(() => GetBlogCategorys());

  if (loading) {
    return <Spin />;
  }

  if (error) {
    return <>error: {error}</>;
  }

  if (!data) {
    return <>无数据</>;
  }

  return (
    <Select<number>>
      {data.map((v) => (
        <Select.Option key={v.id} value={v.id}>
          {v.name}
        </Select.Option>
      ))}
    </Select>
  );
};

export default BlogCategorySelect;

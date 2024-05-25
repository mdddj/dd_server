import MarkdownEditor from '@/components/MarkdownEditor';
import FileSelectWidget from '@/components/file/FileSelectComponent';
import TagSelect from '@/components/tag/BlogTagSelect';
import {
  GetBlogById,
  GetBlogCategorys,
  PushOneBlog,
} from '@/services/blog/BlogController';
import { Blog, BlogCategory } from '@/types/blog';
import { useSearchParams } from '@@/exports';
import { PageContainer } from '@ant-design/pro-components';
import markdown from '@wcj/markdown-to-html';
import { Button, Card, Form, Input, Select, message } from 'antd';
import React, { useEffect, useState } from 'react';

const Page: React.FC = () => {
  const [form] = Form.useForm();
  const [searchParams] = useSearchParams();
  const [categoryList, setCategoryList] = useState<BlogCategory[]>([]);
  const [tags, setTags] = useState<string[]>([]);
  const [blog, setBlog] = useState<Blog | undefined>();

  const [content, setContent] = useState('');

  let updateId = searchParams.get('update');
    const fetchBlogCategory = async () => {
    const result = await GetBlogCategorys();
    setCategoryList(result.data);
  };

  const updateBlog = async () => {
    if(!updateId){
      return
    }
    let result = await GetBlogById(updateId);
    setBlog(result.data);
    setTags(result.data.tags.map((value) => value.name));
    form.setFieldsValue({
      categoryId: result.data.category.id,
      title: result.data.title,
      alias: result.data.aliasString,
      thumbnail: result.data.thumbnail,
      id: result.data.id,
    });
    setContent(result.data.content);
  };

  useEffect(() => {
    if (categoryList.length === 0) {
      fetchBlogCategory().then();
      form.setFieldsValue({
        tags: tags,
      });
    }

    if (updateId && !blog) {
      updateBlog().then();
    }
  }, [tags, updateId]);

  const push = async (values: Blog) => {
    const hide = message.loading('正在发布');
    const html = markdown(values.content);
    if (typeof html === 'string') {
      values.html = html;
    }
    const result = await PushOneBlog({
      ...values,
      content: content,
      tags: tags,
    });
    message.success(result.message);
    hide();
  };

  return (
    <PageContainer title="发布文章">
      <Card>
        <Form form={form} layout="vertical" onFinish={push}>
          <Form.Item hidden={true} name={'id'}>
            <Input />
          </Form.Item>

          <Form.Item
            label={'标题'}
            name={'title'}
            rules={[
              {
                required: true,
                message: '请输入标题',
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label={'分类'}
            name={'categoryId'}
            style={{ display: 'inline-block', width: 'calc(30% - 8px)' }}
            rules={[
              {
                required: true,
                message: '请选择分类',
              },
            ]}
          >
            <Select>
              {categoryList.map((value) => (
                <Select.Option key={value.id} value={value.id}>
                  {value.name}
                </Select.Option>
              ))}
            </Select>
          </Form.Item>
          <Form.Item label={'正文'}>
            <MarkdownEditor onChange={setContent} value={content} />
          </Form.Item>
          <Form.Item label={'别名'} name={'alias'}>
            <Input />
          </Form.Item>
          <Form.Item label={'主图'} name={'thumbnail'}>
            <FileSelectWidget
              onFileSelect={(fileInfo) =>
                form.setFieldValue('thumbnail', fileInfo.url)
              }
            >
              <Input />
            </FileSelectWidget>
          </Form.Item>
          <Form.Item label={'标签'} name={'tags'}>
            <TagSelect tags={tags} onChange={setTags} />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">
              发布博客
            </Button>
          </Form.Item>
        </Form>
      </Card>
    </PageContainer>
  );
};
export default Page;

import SimpleAndMarkdownEditor from '@/components/editor/SimpleAndMarkdownEditor';
import FileSelectWidget from '@/components/file/FileSelectComponent';
import { ResourceCategorySelectProForm } from '@/components/selects/ResourceCategorySelect';
import ResourceCategoryTypeSelect from '@/components/selects/ResourceCategoryType';
import {
  MyResourceAddPostApi,
  findResourceByIdApi,
} from '@/services/resource/apis';
import { MyResources } from '@/types/resource';
import {
  PageContainer,
  ProForm,
  ProFormDatePicker,
  ProFormInstance,
  ProFormText,
  ProFormUploadButton,
} from '@ant-design/pro-components';
import { useSearchParams } from '@umijs/max';
import { Card, message } from 'antd';

import { useEffect, useRef, useState } from 'react';

export default function Page() {
  const formRef = useRef<ProFormInstance<MyResources>>();
  const [updateModel, setUpdateModel] = useState<MyResources>();
  const [params] = useSearchParams();
  let updateId = params.get('update'); //修改的ID

  const initUpdateModel = () => {
    console.log(updateId);
    findResourceByIdApi(updateId).then((v) => {
      console.log(v);
      let data = v.data;
      let curr = formRef.current;
      if (data && curr) {
        curr.setFieldValue('title', data.title);
        curr.setFieldValue('content', data.content);
        curr.setFieldValue('categoryId', data.category?.id);
        curr.setFieldValue('description', data.description);
        curr.setFieldValue('createDate', data.createDate);
        curr.setFieldValue('updateDate', data.updateDate);
        curr.setFieldValue('label', data.label);
        curr.setFieldValue('thumbnailImage', data.thumbnailImage);
        curr.setFieldValue('links', data.links);
        curr.setFieldValue('type', data.type);
        curr.setFieldValue('authority', data.authority);
        curr.setFieldValue('clickCount', data.clickCount);
        curr.setFieldValue('browserUrl', data.browserUrl);
        setUpdateModel(data);
      }
    });
  };

  useEffect(() => {
    if (updateId) {
      initUpdateModel();
    }
  }, [updateId]);

  /**
   * 添加动态
   * @param values
   */
  const submit = async (values: any): Promise<boolean> => {
    let hide = message.loading('请稍等');
    let pics = values.pictures;
    if (pics && (pics as any[])) {
      values.pictures = (pics as any[]).map((v) => v.originFileObj);
    }
    let data = await MyResourceAddPostApi(values);
    hide();
    message.info(data.message);
    return data.success;
  };

  return (
    <PageContainer title={'添加动态'}>
      <Card>
        <ProForm<MyResources>
          onFinish={submit}
          initialValues={{
            clickCount: 0,
          }}
          formRef={formRef}
        >
          <ProFormText name={'id'} label={'主键'} hidden={true} />
          <ProForm.Group>
            <ResourceCategorySelectProForm name={'categoryId'} label={'分类'} />
            <ProFormText name={'title'} label={'标题'} width={'lg'} />
          </ProForm.Group>
          <ProFormText name={'description'} label={'描述'} />
          <SimpleAndMarkdownEditor
            name={'content'}
            label={'正文内容'}
            rules={[
              {
                required: true,
                message: '请输入正文内容',
              },
            ]}
          />
          <ProForm.Group>
            <ProFormDatePicker name={'createDate'} label={'发布时间'} />
            <ProFormDatePicker name={'updateDate'} label={'修改时间'} />
          </ProForm.Group>
          <ProForm.Group>
            <ProFormText name={'label'} label={'标签'} />
            <FileSelectWidget
              onFileSelect={(fileInfo) => {
                formRef.current?.setFieldValue('thumbnailImage', fileInfo.url);
              }}
            >
              <ProFormText
                name={'thumbnailImage'}
                label={'缩略图'}
                rules={[
                  {
                    type: 'url',
                    message: '请输入URL',
                  },
                ]}
              />
            </FileSelectWidget>
            <ProFormText
              name={'links'}
              label={'链接'}
              rules={[
                {
                  type: 'url',
                  message: '请输入URL',
                },
              ]}
            />
            <ResourceCategoryTypeSelect name={'type'} label={'类型'} />
          </ProForm.Group>
          <ProForm.Group>
            <ProFormText
              name={'authority'}
              label={'权限'}
              rules={[
                {
                  type: 'number',
                  message: '请输入数字',
                },
              ]}
            />
            <ProFormText
              name={'clickCount'}
              label={'点击量'}
              rules={[
                {
                  type: 'number',
                  message: '请输入数字',
                },
              ]}
            />
            <ProFormText name={'mianji'} label={'面基专用'} />
            <ProFormText
              name={'browserUrl'}
              label={'url'}
              rules={[
                {
                  type: 'url',
                  message: '请输入URL',
                },
              ]}
            />
          </ProForm.Group>
          <ProFormUploadButton
            name="pictures"
            label="图片"
            max={9}
            fieldProps={{
              name: 'pictures',
              listType: 'picture-card',
              multiple: true,
              defaultFileList: [
                ...(updateModel?.images?.map((v) => {
                  return {
                    uid: `${v.id}`,
                    url: v.url,
                    name: v.fileName,
                  };
                }) ?? []),
              ],
            }}
          />
        </ProForm>
      </Card>
    </PageContainer>
  );
}

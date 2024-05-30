import {ProForm, ProFormText, ProFormTextArea} from '@ant-design/pro-components';
import React from 'react';
import {request} from "@@/exports";
import {Result} from "@/types/result";
import FileSelectWidget from "@/components/file/FileSelectComponent";
import {Form} from "antd";
import TextProFormSelect from "@/components/selects/TextProFormSelect";

export interface MiniAppConfigModel {
    nickName: string,
    gender: string,
    address: string,
    job: string,
    codeStack: string,
    descriptionText: string,
    moreText: string,
    moreTextKey: string,
    btnOneTitle: string,
    btnOneKey: string,
    btnTwoTitle: string,
    btnTwoKey: string,
    avatar: string,
}

type ModelDef = MiniAppConfigModel;

interface Props {
    trigger?: React.JSX.Element;
    onSuccess?: () => void;
    initialValue?: ModelDef;
}

async function updateConfig(data: ModelDef): Promise<Result<ModelDef>> {
    return request('/admin/miniapp/config/update', {
        method: 'POST',
        data
    })
}


async function miniAppConfig(): Promise<Result<MiniAppConfigModel>> {
    return request('/admin/miniapp/config/get', {
        method: 'GET'
    })
}

const MiniAppConfigModelForm: React.FC<Props> = ({
                                                     onSuccess,
                                                 }) => {
    const [form] = Form.useForm();


    /// ToDo实现保存/修改请求
    const onSubmit = async (values: ModelDef): Promise<boolean> => {
        const r = await updateConfig(values)
        return r.success;
    };

    ///提交数据
    const onFinish = async (values: ModelDef): Promise<boolean> => {
        let r = await onSubmit(values);
        if (r) {
            onSuccess?.();
            form.setFieldsValue(values)
        }
        return r;
    };

    return (
        <>
            <ProForm<MiniAppConfigModel>
                form={form}
                onFinish={onFinish}
                title={'修改小程序配置'}
                request={async () => {
                    const r = await miniAppConfig()
                    return r.data
                }}
            >
                <ProForm.Group>
                    <ProFormText name={'nickName'} label={'姓名'} rules={[
                        {
                            "message": "请输入字段内容",
                            "required": true
                        }
                    ]}/>
                    <ProFormText name={'gender'} label={'性别'} rules={[
                        {
                            "message": "请输入字段内容",
                            "required": true
                        }
                    ]}/>
                </ProForm.Group>
                <ProForm.Group>
                    <ProFormText name={'address'} label={'地址'} rules={[
                        {
                            "message": "请输入字段内容",
                            "required": true
                        }
                    ]}/>
                    <ProFormText name={'job'} label={'职业'} rules={[
                        {
                            "message": "请输入字段内容",
                            "required": true
                        }
                    ]}/>
                </ProForm.Group>
                <ProFormText name={'codeStack'} label={'技术栈'} rules={[
                    {
                        "message": "请输入字段内容",
                        "required": true
                    }
                ]}/>
                <ProFormTextArea fieldProps={{rows: 3}} name={'descriptionText'} label={'个人描述'} rules={[
                    {
                        "message": "请输入字段内容",
                        "required": true
                    }
                ]}/>
                <ProForm.Group>
                    <ProFormText name={'moreText'} label={'底部按钮'} rules={[
                        {
                            "message": "请输入字段内容",
                            "required": true
                        }
                    ]}/>
                    <TextProFormSelect name={'moreTextKey'} label={'底部按钮的字典key'} rules={[
                        {
                            "message": "请输入字段内容",
                            "required": true
                        }
                    ]}/>
                </ProForm.Group>
                <ProForm.Group>
                    <ProFormText name={'btnOneTitle'} label={'按钮1标题'} rules={[
                        {
                            "message": "请输入字段内容",
                            "required": true
                        }
                    ]}/>
                    <TextProFormSelect name={'btnOneKey'} label={'按钮1字典key'} rules={[
                        {
                            "message": "请输入字段内容",
                            "required": true
                        }
                    ]}/>
                </ProForm.Group>
                <ProForm.Group>
                    <ProFormText name={'btnTwoTitle'} label={'按钮2标题'} rules={[
                        {
                            "message": "请输入字段内容",
                            "required": true
                        }
                    ]}/>
                    <TextProFormSelect name={'btnTwoKey'} label={'按钮2字典key'} rules={[
                        {
                            "message": "请输入字段内容",
                            "required": true
                        }
                    ]}/>
                </ProForm.Group>
                <FileSelectWidget
                    onFileSelect={(fileInfo) =>
                        form.setFieldValue('avatar', fileInfo.url)
                    }
                >
                    <ProFormText name={'avatar'} label={'头像'} rules={[
                        {
                            "message": "请输入字段内容",
                            "required": true
                        }
                    ]}/>
                </FileSelectWidget>


            </ProForm>
        </>
    );
};


export default MiniAppConfigModelForm
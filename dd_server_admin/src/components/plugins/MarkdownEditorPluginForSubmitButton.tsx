import { PluginComponent } from 'react-markdown-editor-lite';
import { Button } from 'antd';
import React from 'react';
import { SaveOutlined } from '@ant-design/icons';

//在 markdown编辑器里面植入一个发布按钮,
export default class MarkdownEditorPluginForSubmitButton extends PluginComponent<{ loading: boolean }, any> {
  static pluginName = 'MarkdownEditorPluginForSubmitButton';
  static align = 'right';

  constructor(props: any) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      loading: false,
    };
  }

  async handleSubmit() {
    const editor: any = this.editor;
    if (editor && editor.props.onSubmit) {
      this.setState({
        loading: true,
      });
      try {
        await editor.props.onSubmit();
      } finally {
        this.setState({
          loading: false,
        });
      }
    } else {
      console.log('onSubmit props not found');
    }
  }

  render() {
    return (
      <Button className={'align-center items-center self-center ml-1'}  type={'primary'} icon={<SaveOutlined />} size={'small'} loading={this.state.loading} onClick={this.handleSubmit}>
        发布
      </Button>
    );
  }
}

import { uploadSimpleFile } from '@/services/file/FileController';
import hljs from 'highlight.js';
import MarkdownIt from 'markdown-it';
import React from 'react';
import MdEditor from 'react-markdown-editor-lite';

import 'github-markdown-css/github-markdown-light.css';
import 'highlight.js/styles/github.css';
import 'react-markdown-editor-lite/lib/index.css';
import Editor from 'react-markdown-editor-lite';
import KbdMDPlugin from '@/components/plugins/KbdMDPlugin';
import MarkdownEditorPluginForSubmitButton from '@/components/plugins/MarkdownEditorPluginForSubmitButton';

const mdParser = new MarkdownIt({
  highlight: (str, lang) => {
    let code: any = mdParser.utils.escapeHtml(str);
    if (lang && hljs.getLanguage(lang)) {
      code = hljs.highlight(lang, str, true).value;
    }
    return `<pre class="hljs"><code>${code}</code></pre>`;
  },
  html: true,
});

Editor.use(KbdMDPlugin);
Editor.use(MarkdownEditorPluginForSubmitButton);

type Props = {
  onChange: (text: string) => void;
  value: string;
  onSubmit?: () => Promise<void>;
};

/**
 * markdown富文本剪辑器
 * @constructor
 */
const MarkdownEditor: React.FC<Props> = ({ onChange, value, onSubmit }) => {

  return (
    <MdEditor
      className={'h-96 markdown-body'}
      value={value}
      onChange={({ text }) => onChange(text)}
      renderHTML={(text) => mdParser.render(text)}
      onImageUpload={uploadSimpleFile}
      // @ts-ignore
      onSubmit={onSubmit}
    ></MdEditor>
  );
};

export default MarkdownEditor;
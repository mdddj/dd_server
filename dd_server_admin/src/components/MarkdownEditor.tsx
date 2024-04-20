import { uploadSimpleFile } from '@/services/file/FileController';
import hljs from 'highlight.js';
import MarkdownIt from 'markdown-it';
import React from 'react';
import MdEditor from 'react-markdown-editor-lite';

import 'github-markdown-css/github-markdown-light.css';
import 'highlight.js/styles/github.css';
import 'react-markdown-editor-lite/lib/index.css';

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

type Props = {
  onChange: (text: string) => void;
  value: string;
};

/**
 * markdown富文本剪辑器
 * @constructor
 */
const MarkdownEditor: React.FC<Props> = ({ onChange, value }) => {
  return (
    <MdEditor
      className={'h-96 markdown-body'}
      value={value}
      onChange={({ text }) => onChange(text)}
      renderHTML={(text) => mdParser.render(text)}
      onImageUpload={uploadSimpleFile}
    ></MdEditor>
  );
};

export default MarkdownEditor
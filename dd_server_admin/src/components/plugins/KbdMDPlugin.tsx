import { PluginComponent } from 'react-markdown-editor-lite';

export default class KbdMDPlugin extends PluginComponent {
  static pluginName = 'KbdMDPlugin';
  static align = 'left';

  handleClick() {
    let selection = this.editor.getSelection();
    console.log(selection);
    let text = selection.text;
    let newText = '<kbd>' + text + '</kbd>';
    let startPos = selection.start
    let endPos =  selection.start + text.length + 11
    this.editor.insertText(newText, true);
    this.editor.setSelection({
      start: startPos,
      end: endPos,
    })

  }

  constructor(props: any) {
    super(props);
    this.handleClick = this.handleClick.bind(this);
  }

  render() {
    return (
      <span className={'button'} title={'键盘'} onClick={this.handleClick}>
        kbd
      </span>
    );
  }
}

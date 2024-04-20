import { Modal, ModalProps } from 'antd';
import React from 'react';
import { createRoot } from 'react-dom/client';
import { MyFileSelectionProp } from './MyFileSelection';

export const FileSelectorComponent: React.FC = (props: any) => {
  return <Modal {...props}></Modal>;
};
export function OpenModalSelection(
  ModalElement: React.ReactElement,
  props?: ModalProps & MyFileSelectionProp,
) {
  const div = document.createElement('div');
  div.id = 'select-file';
  const modal = createRoot(div);
  let b = true;
  const clone = React.cloneElement(ModalElement, {
    open: b,
    getContainer: false,
    onCancel: () => {
      const c = React.cloneElement(clone, { open: false } as ModalProps);
      modal.render(c);
      console.log('关闭');
      setTimeout(() => {
        modal.unmount();
        div.remove();
      }, 300);
    },
    ...props,
    onFileSelect: (fileInfo) => {
      props?.onFileSelect(fileInfo);
      const c = React.cloneElement(clone, { open: false } as ModalProps);
      modal.render(c);
      setTimeout(() => {
        modal.unmount();
        div.remove();
      }, 300);
    },
  } as ModalProps & MyFileSelectionProp);
  modal.render(clone);
  document.body.appendChild(div);
}

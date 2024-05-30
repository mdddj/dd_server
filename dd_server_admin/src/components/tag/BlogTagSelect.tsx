import { getAllTags } from '@/services/blog/BlogController';
import { PlusOutlined } from '@ant-design/icons';
import { useRequest } from '@umijs/max';
import {Button, InputRef} from 'antd';
import { Input, Spin, Tag, theme } from 'antd';
import { TweenOneGroup } from 'rc-tween-one';
import React, { useEffect, useRef, useState } from 'react';
type TagSelectProps = {
  tags: string[];
  onChange: (tags: string[]) => void;
};
const TagSelect: React.FC<TagSelectProps> = ({ onChange, tags }) => {
  const { token } = theme.useToken();
  const [inputVisible, setInputVisible] = useState(false);
  const [inputValue, setInputValue] = useState('');
  const inputRef = useRef<InputRef>(null);

  const {data: serverTags, loading: tagsLoading} = useRequest(()=>getAllTags())

  useEffect(() => {
    if (inputVisible) {
      inputRef.current?.focus();
    }
  }, [inputVisible]);

  const handleClose = (removedTag: string) => {
    const newTags = tags.filter((tag) => tag !== removedTag);
    console.log(newTags);
    onChange(newTags);
  };

  const showInput = () => {
    setInputVisible(true);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
  };

  const handleInputConfirm = () => {
    handleTagClick(inputValue)
  };

  const handleTagClick = (tag: string) => {
    if (tag && tags.indexOf(tag) === -1) {
      onChange([...tags, tag]);
    }
    setInputVisible(false);
    setInputValue('');
  }

  const forMap = (tag: string) => {
    const tagElem = (
      <Tag
        closable
        onClose={(e) => {
          e.preventDefault();
          handleClose(tag);
        }}
      >
        {tag}
      </Tag>
    );
    return (
      <span key={tag} style={{ display: 'inline-block' }}>
        {tagElem}
      </span>
    );
  };

  const tagChild = tags.map(forMap);

  const tagPlusStyle = {
    background: token.colorBgContainer,
    borderStyle: 'dashed',
  };

  return (
    <>

      {tagsLoading && <Spin />}

      {
        serverTags?.length !== 0 && <div className='mb-2 card shadow'>
          <div className={'card-body '}>
            <h3 className={'font-bold text-lg'}>从已有标签中选择</h3>
            <div className={'flex flex-wrap gap-2'}>
              {
                serverTags?.map(item=><Button disabled={tags.indexOf(item.name)!==-1} size={'small'} key={item.id} onClick={()=>{
                  handleTagClick(item.name)
                }}>{item.name}</Button>)
              }
            </div>
          </div>
        </div>
      }

      <div style={{ marginBottom: 16 }}>
        <TweenOneGroup
          enter={{
            scale: 0.8,
            opacity: 0,
            type: 'from',
            duration: 100,
          }}
          onEnd={(e) => {
            if (e.type === 'appear' || e.type === 'enter') {
              (e.target as any).style = 'display: inline-block';
            }
          }}
          leave={{ opacity: 0, width: 0, scale: 0, duration: 200 }}
          appear={false}
        >
          {tagChild}
        </TweenOneGroup>
      </div>
      {inputVisible ? (
        <Input
          ref={inputRef}
          type="text"
          size="small"
          style={{ width: 78 }}
          value={inputValue}
          onChange={handleInputChange}
          onBlur={handleInputConfirm}
          onPressEnter={handleInputConfirm}
        />
      ) : (
        <Tag onClick={showInput} style={tagPlusStyle}>
          <PlusOutlined /> 添加标签
        </Tag>
      )}
    </>
  );
};

export default TagSelect;

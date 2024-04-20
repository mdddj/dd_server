import { AppInitialStateModel } from '@/app';
import { removeJwtToken } from '@/utils/cache';
import { SettingOutlined } from '@ant-design/icons';
import { useNavigate } from '@umijs/max';
import { Avatar, Dropdown, MenuProps } from 'antd';
import React from 'react';

const items: MenuProps['items'] = [
  {
    key: 1,
    label: '个人设置',
  },
  {
    key: 2,
    label: '退出登录',
  },
];
const GlobalAppBar: React.FC<{ initState: AppInitialStateModel }> = ({
  initState,
}) => {
  const nav = useNavigate();
  return (
    <div
      style={{
        display: 'flex',
        alignItems: 'center',
        width: '100%',
      }}
    >
      {initState.user && (
        <>
          <div style={{ flexGrow: 1 }}>
            <Avatar
              style={{ marginRight: '6px' }}
              src={initState.user?.picture}
            />
            <span>{initState?.user?.nickName}</span>
          </div>
          <div style={{ flexShrink: 0 }}>
            <Dropdown
              menu={{
                items,
                onClick: (pro) => {
                  if (pro.key === '1') {
                  } else if (pro.key === '2') {
                    removeJwtToken();
                    nav('/login');
                  }
                },
              }}
            >
              <a onClick={(e) => e.preventDefault()}>
                <SettingOutlined />
              </a>
            </Dropdown>
          </div>
        </>
      )}
    </div>
  );
};

export default GlobalAppBar;

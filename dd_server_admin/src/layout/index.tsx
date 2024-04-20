import { Outlet } from '@/.umi/exports';
import { App } from 'antd';

export default function Layout() {
  return (
    <App>
      <Outlet />
    </App>
  );
}

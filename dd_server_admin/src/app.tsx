import { HOST_NAME } from '@/constants';
import { getJwtToken, removeJwtToken } from '@/utils/cache';
import { history } from '@@/core/history';
import {
  AxiosError,
  AxiosResponse,
  RequestConfig,
  RequestError,
  RequestOptions,
} from '@@/plugin-request/request';
import { StyleProvider } from '@ant-design/cssinjs';
import { Modal, message } from 'antd';
import dayjs from 'dayjs';
import 'dayjs/locale/zh-cn';

import GlobalAppBar from '@/components/GlobalAppBar';
import { ApiGetCurrentUser } from '@/services/user/UserController';
import { User } from '@/types/user';
import { NextUIProvider } from '@nextui-org/react';
import updateLocale from 'dayjs/plugin/updateLocale';
import React from 'react';
import { Result, ToastType } from './types/result';

dayjs.extend(updateLocale);
dayjs.updateLocale('zh-cn', {
  weekStart: 0,
});

interface ResponseStructure {
  state: number;
  data: any;
  message: string;
  success: boolean;
  type: ToastType;
}

class ApiError extends Error {
  info: ResponseStructure;

  constructor(res: ResponseStructure) {
    super(res.message);
    super.name = 'BizError';
    this.info = res;
  }
}

export interface AppInitialStateModel {
  user?: User;
}

export async function getInitialState(): Promise<AppInitialStateModel> {
  let token = getJwtToken() ?? '';
  if (token !== '') {
    let result = await ApiGetCurrentUser();
    return { user: result.data };
  }
  return {};
}

export const layout: () => {
  logo: string;
  menu: { locale: boolean };
  rightRender: (initialState: AppInitialStateModel) => React.ReactNode;
} = () => {
  return {
    logo: 'https://i.postimg.cc/bv7RTyvs/avatar-apple.png',
    menu: {
      locale: false,
    },
    rightRender: (initialState: AppInitialStateModel | undefined) => {
      return <>{initialState && <GlobalAppBar initState={initialState} />}</>;
    },
  };
};

export function onRouteChange({ location }: any) {
  let token = getJwtToken();
  if (token === null && location.pathname !== '/login') {
    history.push('/login'); // 重定向到登录页面
  }
}

export const request: RequestConfig = {
  baseURL: HOST_NAME,
  requestInterceptors: [
    (config: RequestConfig) => {
      let token = getJwtToken() ?? '';
      if (token !== '') {
        if (config.headers) {
          config.headers['Authorization'] = token;
        } else {
          config.headers = {
            Authorization: token,
          };
        }
      }
      return config;
    },
  ],
  responseInterceptors: [
    (response: AxiosResponse) => {
      if (response.status === 200) {
        let data = response.data as Result<any>;
        if (data.type === ToastType.FinnalToast) {
          message.success(data.message).then();
        } else if (data.type === ToastType.FinnalDialog) {
          Modal.success({
            content: data.message,
          });
        }
      }
      return response;
    },
  ],
  errorConfig: {
    errorHandler(error: RequestError, opts: RequestOptions) {
      if (opts?.skipErrorHandler) throw error;
      if (error instanceof ApiError) {
        let result = error.info;
        if (result.state === 401) {
          removeJwtToken();
          location.href = '/login';
          return;
        }
        const errorInfo: ResponseStructure = error.info;
        if (errorInfo) {
          switch (errorInfo.type) {
            case ToastType.Toast:
              message.error(errorInfo.message).then();
              break;
            case ToastType.Dialog:
              Modal.error({ content: errorInfo.message, title: '操作失败' });
              break;
            case ToastType.None:
            case ToastType.Notice:
          }
        }
      } else {
        const axiosError = error as AxiosError;
        message.error(axiosError.message).then();
      }
    },
    errorThrower(res: ResponseStructure) {
      if (!res.success) {
        throw new ApiError(res);
      }
    },
  },
};

export function rootContainer(
  container:
    | string
    | number
    | boolean
    | React.ReactElement<any, string | React.JSXElementConstructor<any>>
    | Iterable<React.ReactNode>
    | React.ReactPortal
    | null
    | undefined,
) {
  return (
    <StyleProvider hashPriority="high">
      <NextUIProvider>{container}</NextUIProvider>
    </StyleProvider>
  );
}

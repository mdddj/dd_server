import { HOST_NAME } from '@/constants';
import { getJwtToken, removeJwtToken } from '@/utils/cache';
import { history } from '@@/core/history';
import { AxiosResponse, RequestConfig, RequestError, RequestOptions, } from '@@/plugin-request/request';
import { StyleProvider } from '@ant-design/cssinjs';
import { message, Modal } from 'antd';
import dayjs from 'dayjs';
import 'dayjs/locale/zh-cn';

import GlobalAppBar from '@/components/GlobalAppBar';
import { ApiGetCurrentUser } from '@/services/user/UserController';
import { User } from '@/types/user';
import { NextUIProvider } from '@nextui-org/react';
import updateLocale from 'dayjs/plugin/updateLocale';
import React from 'react';
import { ToastType } from './types/result';
import type { AxiosError } from "axios";

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
function isAxiosError(error: RequestError): error is AxiosError {
  return (error as AxiosError).isAxiosError !== undefined;
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
      if (response.data) {
        let { type, success, message: msg } = response.data as ResponseStructure;
        if (msg.trim() !== "") {
          if (type === ToastType.FinnalToast) {
            if (success) {
              message.success(msg).then();
            } else {
              message.error(msg).then();
            }

          } else if (type === ToastType.FinnalDialog) {
            if (success) {
              Modal.success({
                content: msg,
              });
            } else {
              Modal.error({
                content: msg,
              });
            }

          } else if (type == ToastType.Toast) {
            if (success) {
              message.success(msg).then()
            } else {
              message.error(msg).then()
            }

          }
        }
      }
      return response;
    },
  ],
  errorConfig: {
    errorHandler(error: RequestError, opts: RequestOptions) {
      if (opts?.skipErrorHandler) throw error;
      if (isAxiosError(error)) {
        const { response } = error
        const status = response?.status
        if (status === 401 || status === 403) {
          removeJwtToken();
          location.href = '/login';
          return;
        }
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

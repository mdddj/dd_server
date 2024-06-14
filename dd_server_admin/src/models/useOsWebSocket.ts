import useWebSocket, { ReadyState } from 'react-use-websocket';
import { WS_URL } from '@/constants';
import { getJwtToken } from '@/utils/cache';
import { request, useRequest } from '@umijs/max';
import { Result } from '@/types/result';

async function getMemoryInfo(): Promise<Result<MemoryInfo>> {
  return request('/system/info/memory-get', {
    method: 'GET',
  });
}

export interface MemoryInfo {
  available: number;
  availableString: string;
  total: number;
  totalString: string;
  usageRate: number;
  use: number;
  useString: string;
}

const useOsWebSocket = () => {
  const { data,loading } = useRequest(() => getMemoryInfo());
  const { readyState, sendMessage, lastJsonMessage } = useWebSocket<MemoryInfo>(WS_URL + '/os', {
    heartbeat: {
      message: 'ping',
      returnMessage: 'ping',
      timeout: 30000,
      interval: 5000,
    },
    onOpen: event => {
      console.log('onOpen', event);
    },
    onError: event => {
      console.log('onError', event);
    },
    queryParams: {
      'token': getJwtToken(),
    },
  });
  let readyText = '';
  switch (readyState) {
    case ReadyState.UNINSTANTIATED:
      readyText = '准备中';
      break;
    case ReadyState.CONNECTING:
      readyText = '正在连接...';
      break;
    case ReadyState.OPEN:
      readyText = '已连接';
      break;
    case ReadyState.CLOSING:
      readyText = '关闭连接中...';
      break;
    case ReadyState.CLOSED:
      readyText = '已关闭连接';
      break;

  }
  return { readyState, sendMessage, lastJsonMessage, readyText, data, loading };
};

export default useOsWebSocket;
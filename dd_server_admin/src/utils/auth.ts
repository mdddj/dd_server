import { getJwtToken } from '@/utils/cache';

/**
 * 获取jwt token
 */
export function getAuthorizationHeader(): Record<string, string> {
  let token = getJwtToken() ?? '';
  if (token !== '') {
    return {
      Authorization: token,
    };
  }
  return {};
}

/**
 * form类型的参数请求头
 */
export function formDataHeader(): Record<string, any> {
  return {
    'Content-Type': 'multipart/form-data',
  };
}

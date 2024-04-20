import { LoginType } from '@/pages/Login';
import { CoverToPageData, Result } from '@/types/result';
import { User } from '@/types/user';
import { request } from '@umijs/max';

/**
 * 登录接口
 * @param data
 * @param loginType 登录类型
 * @constructor
 */
export async function ApiLogin(data: any, loginType: LoginType) {
  let url =
    loginType === 'email'
      ? '/api/user-public/login-by-email'
      : '/api/user-public/login';
  return request(url, {
    method: 'POST',
    data,
  });
}

/**
 * 获取当前登录的用户信息
 * @constructor
 */
export async function ApiGetCurrentUser(): Promise<Result<User>> {
  return request('/api/get-user-by-token');
}

/**
 * 查询用户列表
 * @param pageParam
 * @constructor
 */
export async function ApiQueryUserList(
  pageParam: any,
): Promise<Result<CoverToPageData<User>>> {
  return request('/api/user/list', {
    params: pageParam,
  });
}

/**
 * 修改用户资料
 * @param params
 * @constructor
 */
export async function ApiUpdateUserInfo(params: any) {
  return request<Result<User>>('/api/user/update', {
    method: 'POST',
    data: params,
  });
}

/**
 * 修改用户密码
 * @param params
 * @constructor
 */
export async function ApiUpdateUserPassword(params: {
  currentPass: string;
  rePassword: string;
}) {
  return request<Result<string>>('/api/auth/user-update-pass', {
    method: 'POST',
    data: params,
  });
}

/**
 * 删除用户
 * @param params
 * @constructor
 */
export async function ApiDeleteUser(params: { id: number }) {
  return request('/api/auth/deleteUser', {
    method: 'DELETE',
    params,
  });
}

///是否注册过管理员账号
export async function ApiIsRegisterAdminAccount() {
  return request<Result<boolean>>('/api/public/has-admin', {
    method: 'GET',
  });
}

///创建初始管理员账号
export async function ApiCreateAdminAccount(params: any) {
  return request<Result<User>>('/api/public/create-admin', {
    method: 'POST',
    data: params,
  });
}

///查找全部用户
export async function ApiUserFindAll(): Promise<Result<User[]>> {
  return request<Result<User[]>>('/api/user/all-user');
}

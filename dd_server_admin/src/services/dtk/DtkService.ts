import {
  DTKDetail,
  DtkVerifyParam,
  SetDefaultDtkAccountParam,
} from '@/models/tdk';
import { JpaPage, Result } from '@/types/result';
import { request } from '@umijs/max';

///添加账号或者修改
export async function MyDtkServiceAddApi(
  params: DTKDetail,
): Promise<Result<DTKDetail>> {
  return request('/api/v1/dtk/detail/add', {
    method: 'POST',
    data: params,
  });
}

/**
 *
 * @returns 获取默认使用账号
 */
export async function MyDtkServiceFindDefaultApi(): Promise<Result<DTKDetail>> {
  return request('/api/v1/dtk/detail/set-default', {
    method: 'GET',
  });
}

/**
 *
 * @returns 更改大淘客状态
 */
export async function MyDtkServiceChangeDefaultAccountApi(
  param: SetDefaultDtkAccountParam,
): Promise<Result<boolean>> {
  return request('/api/v1/dtk/detail/update-default', {
    method: 'POST',
    data: param,
  });
}

/**
 *
 * @returns 获取大淘客账号列表
 */
export async function MyDtkServiceGetAllApi(
  params?: any,
): Promise<Result<JpaPage<DTKDetail>>> {
  return request('/api/v1/dtk/detail/all', {
    method: 'GET',
    params,
  });
}

/**
 * 验证大淘客账号
 * @param param
 * @constructor
 */
export async function MyDtkServiceVerifyDtkAccountApi(
  param: DtkVerifyParam,
): Promise<Result<boolean>> {
  return request('/api/v1/dtk/detail/verify', {
    method: 'POST',
    data: param,
  });
}

/**
 * 删除大淘客账号
 * @param id 主键
 * @constructor
 */
export async function MyDtkServiceDeleteApi(
  id: number,
): Promise<Result<boolean>> {
  return request('/api/v1/dtk/detail/delete', {
    method: 'DELETE',
    params: { id },
  });
}

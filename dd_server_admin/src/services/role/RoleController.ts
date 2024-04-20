import { CoverToPageData, Result } from '@/types/result';
import { request } from '@@/plugin-request';
import {RoleModel} from "@/services/role/types";

/**
 * 获取全部权限定义
 * @constructor
 */
export async function GetRoleListApi() {
  return request<Result<RoleModel[]>>('/api/role/list');
}

/**
 * 分页获取权限列表
 * @param params
 * @constructor
 */
export async function GetRoleListApiByPage(
  params: any,
): Promise<Result<CoverToPageData<RoleModel>>> {
  return request<Result<CoverToPageData<RoleModel>>>('/api/role/list-page', {
    params,
  });
}

/**
 * 保存或者修改权限数据
 * @param params 参数
 * @returns 保存或者修改后的数据
 */
export async function SaveOrUpdateRole(
  params: RoleModel,
): Promise<Result<RoleModel>> {
  return request<Result<RoleModel>>('/api/role/save', {
    method: 'POST',
    data: params,
  });
}

/**
 *
 * 删除权限
 * @param id 权限ID
 * @returns
 */
export async function DeleteRoleById(id: number) {
  return request('/api/role/delete', {
    method: 'DELETE',
    params: {
      id,
    },
  });
}

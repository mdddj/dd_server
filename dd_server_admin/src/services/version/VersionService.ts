import { Version } from '@/services/version/VersionDataType';
import { JpaPage, Result } from '@/types/result';
import { request } from '@umijs/max';

/**
 * 版本列表
 * @param params
 * @constructor
 */
export async function MyVersionListApi(
  params: any,
): Promise<Result<JpaPage<Version>>> {
  return request('/api/version/list', {
    method: 'GET',
    params: params,
  });
}

/**
 * 新增一个版本
 * @param params
 * @constructor
 */
export async function MyVersionSaveApi(params: any): Promise<Result<Version>> {
  return request('/api/admin/version/save', {
    method: 'POST',
    data: params,
  });
}

/**
 * 删除一个版本
 * @param id
 * @constructor
 */
export async function MyVersionDeleteApi(id: number) : Promise<Result<boolean>> {
  return request('/api/admin/version/delete', {
    method: 'DELETE',
    params: {
      id: id,
    },
  });
}

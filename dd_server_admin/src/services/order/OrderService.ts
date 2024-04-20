import {
  DtkOrderDto,
  DtkOrderResult,
  DtkOrderSelectParam,
  OrderSelectParam,
  UserOrder,
} from '@/models/order';
import { JpaPage, Result } from '@/types/result';
import { request } from '@umijs/max';

///大淘客官方订单查询
export async function MySelectDataokeOrderApi(
  params: DtkOrderSelectParam,
): Promise<Result<DtkOrderResult>> {
  return request<Result<DtkOrderResult>>('/api/user/order/select-by-dtk', {
    method: 'get',
    params: params,
  });
}

///订单写入
export async function MyUserOrderWirteApi(
  params: DtkOrderDto,
): Promise<Result<UserOrder>> {
  return request('/api/user/order/write-order-by-dtk', {
    method: 'post',
    data: params,
  });
}

///数据库订单查询 /api/user/order/list
export async function MyOrdersSelectApi(
  params: OrderSelectParam,
): Promise<Result<JpaPage<UserOrder>>> {
  return request('/api/user/order/list', {
    method: 'get',
    params: params,
  });
}

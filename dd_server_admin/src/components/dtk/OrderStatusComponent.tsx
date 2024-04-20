import { TkStatus } from '@/models/order';
import { Tag } from 'antd';
import React from 'react';

const OrderStatusComponent: React.FC<{
  status?: TkStatus;
}> = ({ status }) => {
  return (
    <>
      {status && (
        <Tag color={status === TkStatus.订单结算 ? 'success' : undefined}>
          {TkStatus[status].toString()}
        </Tag>
      )}
    </>
  );
};

export default OrderStatusComponent;

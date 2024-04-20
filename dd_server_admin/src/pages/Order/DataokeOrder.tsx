import OrderStatusComponent from '@/components/dtk/OrderStatusComponent';
import { OrderSelectParam, UserOrder } from '@/models/order';
import { MyOrdersSelectApi } from '@/services/order/OrderService';
import { PageContainer, ProTable } from '@ant-design/pro-components';
import { Image } from 'antd';

export default function Page() {
  return (
    <PageContainer title={'用户订单'}>
      <ProTable<UserOrder, OrderSelectParam>
        columns={[
          {
            key: 'id',
            dataIndex: 'id',
            title: 'id',
          },
          {
            dataIndex: 'itemImg',
            title: '图片',
            render: (_, entity) => {
              return <Image src={entity.itemImg} width={68} />;
            },
          },
          {
            dataIndex: 'itemTitle',
            title: '标题',
          },
          {
            dataIndex: 'alipayTotalPrice',
            title: '付款金额',
          },
          {
            dataIndex: 'pubShareFee',
            title: '佣金',
          },
          {
            dataIndex: 'pubSharePreFee',
            title: '预计佣金',
          },
          {
            dataIndex: 'tkStatus',
            title: '状态',
            render: (_, entity) => {
              return <OrderStatusComponent status={entity.tkStatus} />;
            },
          },
        ]}
        rowKey={'id'}
        request={async (params) => {
          params.remove = 1;
          let result = await MyOrdersSelectApi(params);
          return {
            success: result.success,
            total: result.data.totalElements,
            data: result.data.content,
          };
        }}
      />
    </PageContainer>
  );
}

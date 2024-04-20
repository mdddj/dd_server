import OrderStatusComponent from '@/components/dtk/OrderStatusComponent';
import UserSelectModal from '@/components/users/form/UserSelectModal';
import SizedBox from '@/components/widget/SizedBox';
import {
  DtkOrderDto,
  DtkOrderResult,
  DtkOrderSelectParam,
} from '@/models/order';
import {
  MySelectDataokeOrderApi,
  MyUserOrderWirteApi,
} from '@/services/order/OrderService';
import {
  PageContainer,
  ProFormDateTimeRangePicker,
  ProFormSelect,
  ProFormText,
  QueryFilter,
} from '@ant-design/pro-components';
import {
  Button,
  Card,
  Image,
  Space,
  Table,
  Tabs,
  Typography,
  message,
} from 'antd';
import dayjs from 'dayjs';
import { useState } from 'react';

export default function Page() {
  const [data, setData] = useState<DtkOrderResult | undefined>(undefined);

  const startStartSearch = async (values: DtkOrderSelectParam) => {
    const hide = message.loading('正在查询中...');
    const result = await MySelectDataokeOrderApi(values);
    hide();
    message.success(result.message);
    if (result.success) {
      setData(result.data);
    }
    return true;
  };
  return (
    <PageContainer title="大淘客订单查询工具">
      <Card>
        <QueryFilter<DtkOrderSelectParam>
          layout={'vertical'}
          onFinish={startStartSearch}
          initialValues={{}}
        >
          <ProFormSelect
            name={'queryType'}
            label={'查询时间类型'}
            rules={[
              {
                required: true,
                message: '请选择查询时间类型',
              },
            ]}
            valueEnum={{
              1: '按照订单淘客创建时间查询',
              2: '按照订单淘客付款时间查询',
              3: '按照订单淘客结算时间查询',
              4: '按照订单更新时间',
            }}
          />
          <ProFormDateTimeRangePicker
            name="datetimeRanger"
            label="查询时间"
            rules={[
              {
                required: true,
                message: '请输入查询时间',
              },
            ]}
            transform={(v) => {
              return {
                endTime: dayjs(v[1]).format('YYYY-MM-DD HH:mm:ss'),
                startTime: dayjs(v[0]).format('YYYY-MM-DD HH:mm:ss'),
              };
            }}
          />

          <ProFormText
            name={'positionIndex'}
            label={'位点'}
            tooltip={
              '位点，第一页数据返回里面有个这个字段，查第二页的数据的时候就传过去'
            }
          />
          <ProFormText name={'pageSize'} label="页大小" />
          <ProFormSelect
            name={'memberType'}
            label={'推广者角色类型'}
            valueEnum={{
              2: '二方',
              3: '三方',
            }}
          />
          <ProFormSelect
            name={'tkStatus'}
            label="淘客订单状态"
            valueEnum={{
              12: '付款',
              13: '关闭',
              14: '确认收货',
              3: '结算成功',
            }}
          />
          <ProFormSelect
            name={'jumpType'}
            label="跳转类型"
            valueEnum={{
              '-1': '向前翻页',
              '1': '向后翻页',
            }}
          />
          <ProFormText name={'pageNo'} label="页码" />
          {/* 场景订单场景类型，1:常规订单，2:渠道订单，3:会员运营订单，默认为1 */}
          <ProFormSelect
            name={'orderScene'}
            label={'场景订单场景类型'}
            valueEnum={{
              1: '常规订单',
              2: '渠道订单',
              3: '会员运营订单',
            }}
          />
        </QueryFilter>
      </Card>
      <SizedBox />
      <Card>
        {data && (
          <Tabs
            defaultActiveKey="1"
            items={[
              {
                key: '1',
                label: '查询结果',
                children: (
                  <Table<DtkOrderDto>
                    rowKey={'tradeParentid'}
                    dataSource={data.data?.results?.publisherOrderDto}
                    columns={[
                      {
                        dataIndex: 'itemImg',
                        title: '产品',
                        render: (_, entity) => {
                          return (
                            <Image
                              src={'https:' + entity.itemImg}
                              width={50}
                            ></Image>
                          );
                        },
                      },
                      {
                        dataIndex: 'tbPaidTime',
                        title: '下单时间',
                      },
                      {
                        dataIndex: 'itemTitle',
                        title: '标题',
                      },
                      {
                        dataIndex: 'itemPrice',
                        title: '单价',
                      },
                      {
                        dataIndex: 'pubShareFee',
                        title: '佣金',
                      },
                      {
                        dataIndex: 'pubSharePreFee',
                        title: '预估佣金',
                      },
                      {
                        dataIndex: 'relationId',
                        title: '渠道ID',
                      },
                      {
                        dataIndex: 'tradeParentid',
                        title: '订单编号',
                        key: 'tradeParentid',
                        render: (_dom, entity) => {
                          return (
                            <Typography.Paragraph
                              copyable={{ text: entity.tradeParentid }}
                            >
                              {entity.tradeParentid}
                            </Typography.Paragraph>
                          );
                        },
                      },
                      {
                        dataIndex: 'tkStatus',
                        title: '状态',
                        render: (_, entity) => {
                          return (
                            <OrderStatusComponent status={entity.tkStatus} />
                          );
                        },
                      },
                      {
                        title: '操作',
                        key: 'action',
                        render: (_, entity) => {
                          return (
                            <Space>
                              <UserSelectModal
                                trigger={<Button size={'small'}>关联</Button>}
                                onFinish={async (_, userId: number) => {
                                  console.log(userId);
                                  entity.userId = userId;
                                  let result =
                                    await MyUserOrderWirteApi(entity);
                                  return result.success;
                                }}
                              />
                            </Space>
                          );
                        },
                      },
                    ]}
                  />
                ),
              },
            ]}
          ></Tabs>
        )}
      </Card>
    </PageContainer>
  );
}

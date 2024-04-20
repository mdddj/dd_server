export interface UserOrder {
  adzoneName: string;
  adzoneid: null;
  alimamaRate: string;
  alimamaShareFee: string;
  alipayTotalPrice: string;
  clickTime: Date;
  depositPrice: string;
  flowSource: string;
  id: number;
  incomeRate: string;
  itemCategoryName: string;
  itemImg: string;
  itemLink: string;
  itemNum: number;
  itemPrice: string;
  itemTitle: string;
  itemid: string;
  orderType: string;
  pubShareFee: string;
  pubSharePreFee: string;
  pubShareRate: string;
  pubid: number;
  refundTag: number;
  sellerNick: string;
  sellerShopTitle: string;
  siteName: string;
  siteid: number;
  subsidyFee: string;
  subsidyRate: string;
  subsidyType: string;
  tbDepositTime: string;
  tbPaidTime: Date;
  terminalType: string;
  tkCommissionFeeForMediaPlatform: string;
  tkCommissionPreFeeForMediaPlatform: string;
  tkCommissionRateForMediaPlatform: string;
  tkCreateTime: Date;
  tkDepositTime: string;
  tkOrderRole: number;
  tkPaidTime: Date;
  tkStatus: TkStatus;
  tkTotalRate: string;
  totalCommissionFee: string;
  totalCommissionRate: string;
  tradeParentid: string;
  tradeid: string;
  zheAccount: string;
  zheActId: string;
  zheAppKey: string;
  zheCode: string;
  zheCustomerIdZtk: string;
  zheIsJieSuan: string;
  zheJieSuanProfit: string;
  zheJieSuanTime: string;
  zheOrderId: string;
  zhePayPrice: string;
  zhePayTime: string;
  zhePlatformZtk: string;
  zheProfit: string;
  zheRefundPrice: string;
  zheRefundTime: string;
  zheSanPingTai: string;
  zheSanPingTaiId: string;
  zheSid: string;
  zheSidZtk: string;
  zheSmsTitle: string;
  zheStatus: string;
  zheType: string;
  zheUpdateTime: string;
}

export interface DtkOrderSelectParam {
  /**
   *
   * 订单查询结束时间，订单开始时间至订单结束时间，中间时间段日常要求不超过3个小时，但如618、双11、年货节等大促期间预估时间段不可超过20分钟，超过会提示错误，调用时请务必注意时间段的选择，以保证亲能正常调用！
   * 时间格式：YYYY-MM-DD HH:MM:SS
   */
  endTime: string;
  /**
   * 跳转类型，当向前或者向后翻页必须提供,-1: 向前翻页,1：向后翻页
   */
  jumpType?: string;
  /**
   * 推广者角色类型,2:二方，3:三方，不传，表示所有角色
   */
  memberType?: string;
  /**
   * 场景订单场景类型，1:常规订单，2:渠道订单，3:会员运营订单，默认为1
   */
  orderScene?: string;
  /**
   * 第几页，默认1，1~100
   */
  pageNo?: string;
  /**
   * 页大小，默认20，1~100
   */
  pageSize?: string;
  /**
   * 位点，第一页数据返回里面有个这个字段，查第二页的数据的时候就传过去
   */
  positionIndex?: string;
  /**
   * 查询时间类型，1：按照订单淘客创建时间查询，2:按照订单淘客付款时间查询，3:按照订单淘客结算时间查询，4：按照订单更新时间（5.27新增字段）
   */
  queryType: string;
  /**
   * 订单查询开始时间。时间格式：YYYY-MM-DD HH:MM:SS
   */
  startTime: string;
  /**
   * 淘客订单状态，12-付款，13-关闭，14-确认收货，3-结算成功;不传，表示所有状态
   */
  tkStatus?: string;
}

/**
 * DtkOrderResult
 */
export interface DtkOrderResult {
  cache?: boolean;
  code?: number;
  data?: DtkOrderData;
  msg?: string;
  requestId?: string;
  time?: number;
}

/**
 * DtkOrderData
 */
export interface DtkOrderData {
  hasNext?: boolean;
  hasPre?: boolean;
  pageNo?: number;
  pageSize?: number;
  positionIndex?: string;
  results?: DtkOrderDataResults;
}

/**
 * DtkOrderDataResults
 */
export interface DtkOrderDataResults {
  publisherOrderDto?: DtkOrderDto[];
}

/**
 * DtkOrderDto
 */
export interface DtkOrderDto {
  adzoneId?: number;
  adzoneName?: string;
  alimamaRate?: string;
  alimamaShareFee?: string;
  alipayTotalPrice?: string;
  clickTime?: string;
  depositPrice?: string;
  flowSource?: string;
  incomeRate?: string;
  itemCategoryName?: string;
  itemid?: string;
  itemImg?: string;
  itemLink?: string;
  itemNum?: number;
  itemPrice?: string;
  itemTitle?: string;
  marketingType?: string;
  modifiedTime?: string;
  orderType?: string;
  payPrice?: string;
  pubid?: number;
  pubShareFee?: string;
  pubShareFeeForCommission?: number;
  pubShareFeeForSdy?: number;
  pubSharePreFee?: string;
  pubSharePreFeeForCommission?: number;
  pubSharePreFeeForSdy?: number;
  pubShareRate?: string;
  pubShareRateForSdy?: number;
  refundTag?: number;
  sellerNick?: string;
  sellerShopTitle?: string;
  siteid?: number;
  siteName?: string;
  subsidyFee?: string;
  subsidyRate?: string;
  subsidyType?: string;
  tbDepositTime?: string;
  tbPaidTime?: string;
  terminalType?: string;
  tkCreateTime?: string;
  tkDepositTime?: string;
  tkEarningTime?: string;
  tkOrderRole?: number;
  tkPaidTime?: string;
  tkStatus?: TkStatus;
  tkTotalRate?: string;
  tkTotalRateForSdy?: number;
  totalCommissionFee?: string;
  totalCommissionRate?: string;
  tradeid?: string;
  tradeParentid?: string;
  userId?: number;
}

export enum TkStatus {
  '订单付款' = 12,
  '订单结算' = 3,
  '订单失效' = 13,
  '订单成功' = 14,
}

export interface OrderSelectParam {
  adzoneid?: number;
  adzoneName?: string;
  alimamaRate?: string;
  alimamaShareFee?: string;
  alipayTotalPrice?: string;
  clickTime?: string;
  current?: number;
  depositPrice?: string;
  flowSource?: string;
  /**
   * 订单ID
   */
  id?: number;
  incomeRate?: string;
  itemCategoryName?: string;
  itemid?: string;
  itemImg?: string;
  itemLink?: string;
  itemNum?: number;
  itemPrice?: string;
  itemTitle?: string;
  orderType?: string;
  page?: number;
  pageSize: number;
  pubid?: number;
  pubShareFee?: string;
  pubSharePreFee?: string;
  pubShareRate?: string;
  refundTag?: number;
  remove?: number;
  sellerNick?: string;
  sellerShopTitle?: string;
  siteid?: number;
  siteName?: string;
  subsidyFee?: string;
  subsidyRate?: string;
  subsidyType?: string;
  tbDepositTime?: string;
  tbPaidTime?: string;
  terminalType?: string;
  tkCommissionFeeForMediaPlatform?: string;
  tkCommissionPreFeeForMediaPlatform?: string;
  tkCommissionRateForMediaPlatform?: string;
  tkCreateTime?: string;
  tkDepositTime?: string;
  tkOrderRole?: number;
  tkPaidTime?: string;
  tkStatus?: number;
  tkTotalRate?: string;
  totalCommissionFee?: string;
  totalCommissionRate?: string;
  tradeid?: string;
  tradeParentid?: string;
  user?: string;
  /**
   * 折淘客账号
   */
  zheAccount?: string;
  /**
   * 活动id
   */
  zheActId?: string;
  zheAppKey?: string;
  /**
   * 折淘客自动增长列
   */
  zheCode?: string;
  /**
   * 推广者自定义编号
   */
  zheCustomerIdZtk?: string;
  /**
   * 折淘客是否结算，0未结算，1已结算
   */
  zheIsJieSuan?: string;
  /**
   * 折淘客结算金额
   */
  zheJieSuanProfit?: string;
  /**
   * 折淘客结算时间
   */
  zheJieSuanTime?: string;
  /**
   * 订单编号
   */
  zheOrderId?: string;
  /**
   * 订单实际支付金额
   */
  zhePayPrice?: string;
  /**
   * 订单支付时间
   */
  zhePayTime?: string;
  /**
   * 推广者平台类型，默认zhetaoke
   */
  zhePlatformZtk?: string;
  /**
   * 预估佣金
   */
  zheProfit?: string;
  /**
   * 退款金额
   */
  zheRefundPrice?: string;
  /**
   * 退款时间
   */
  zheRefundTime?: string;
  /**
   * 订单所属平台,考拉、美团、苏宁、淘宝、京东、拼多多、唯品会、饿了么等
   */
  zheSanPingTai?: string;
  /**
   * 订单所属平台id，1美团、2考拉、3苏宁、4淘宝、5京东、6拼多多、7唯品会、8饿了么
   */
  zheSanPingTaiId?: string;
  /**
   * 原始推广位sid
   */
  zheSid?: string;
  /**
   * 折淘客授权sid
   */
  zheSidZtk?: string;
  /**
   * 订单标题
   */
  zheSmsTitle?: string;
  /**
   * 订单状态，1已付款，8已完成，9已退款或风控
   */
  zheStatus?: string;
  /**
   * 订单类型
   */
  zheType?: string;
  /**
   * 数据最后更新时间
   */
  zheUpdateTime?: string;
}

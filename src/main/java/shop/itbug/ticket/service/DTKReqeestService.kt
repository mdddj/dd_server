package shop.itbug.ticket.service

import org.springframework.stereotype.Service
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.model.params.*
import shop.itbug.ticket.utils.Result


typealias DaTaokeStringToModel<T> = (jsonString: String) -> T

fun <T> Result<String>.daTaoKeStringCovert(func: DaTaokeStringToModel<T>) : T {
    this.data ?: throw BizException("转换失败")
    try{
        return func(this.data!!)
    }catch (e:Exception){
        e.printStackTrace()
        log().error("$e")
        throw BizException("转换数据失败")
    }
}
/**
 * 注意,这里返回的String 均是淘客的data字段转成json数据
 */
@Service
interface DTKReqeestService {
    /**
     * 通用数据返回
     *
     * @param response json字符串
     * @return 返回前端的数据
     */
    fun getData(response: String): Result<String>

    /**
     * 大淘客分类接口
     *
     * @return json字符串
     */
    val category: String
    fun getProductList(goodsListParams: GoodsListParams): String

    /**
     * 该接口返回大淘客所有分类下精选品牌以及对应推荐的热门商品
     *
     * @param paramsModel 参数
     * @return 大淘客data json数据字符串
     */
    fun getBrandList(paramsModel: BrandListParamsModel): String

    /**
     * 该接口返回某个具体品牌的详细信息和推荐商品
     *
     * @param brandId  品牌id
     * @param pageId   分页id：常规分页方式，请直接传入对应页码（比如：1,2,3……） 第一页时会携带品牌信息数据
     * @param pageSize 每页记录条数（每页支持最大记录条数100）
     * @return 大淘客data json数据字符串
     */
    fun getBrandDetail(brandId: String, pageId: String, pageSize: String): String

    /**
     * 商品评价接口
     * 该接口返回某个具体商品的评价内容，目前每个商品最多返回200条评论（仅限大淘客商品）
     *
     * @param paramsModel 参数列表
     * @return 大淘客data json数据字符串
     */
    fun getCommentList(paramsModel: CommentParamsModel): String

    /**
     * 店铺转链
     *
     * @param params 参数
     * @return json数据
     */
    fun shopConvert(params: ShopConvertParamsModel): String

    /**
     * 热搜关键字
     *
     * @return json字符串
     */
    val searchWorlds: String

    /**
     * 获取短视频分类
     *
     * @return json
     */
    val videoCategory: String

    /**
     * 获取短视频的列表
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getVideoGoodsList(paramsModel: VideoGoodsParamsModel): String

    /**
     * 细分类目榜
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getSubdivisionIdGoodsList(paramsModel: SubdivisionParamsModel): String

    /**
     * 获取折上折商品列表
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getDiscountGoodsList(paramsModel: DiscountParamsModel): String

    /**
     * 获取每日半价商品
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getHalfDayGoodsList(paramsModel: HalfDayParamsModel): String

    /**
     * 获取商品的历史价格
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getGoodsHistory(paramsModel: GoodsHistoryParamsModel): String

    /**
     * 获取直播的商品
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getLiveDatas(paramsModel: LiveParamsModel): String

    /**
     * 获取每日爆品商品
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getHotDayGoodsList(paramsModel: HotDayParamsModel): String

    /**
     * 获取9.9商品列表
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getNineNineGoodsList(paramsModel: NineNineParamsModel): String

    /**
     * 咚咚抢
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getDddGoodsList(paramsModel: DdqParamsModel): String

    /**
     * 获取大淘客独家券
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getExclusiveGoodsList(paramsModel: ExclusiveParamsModel): String

    /**
     * 各大榜单
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getTopGoodsList(paramsModel: TopParamsModel): String

    /**
     * 朋友圈文案
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getWechatList(paramsModel: WeChatParamsModel): String

    /**
     * 获取精选专题
     *
     * @return json
     */
    val topicList: String



    /**
     * 相似推荐
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getSimilarList(paramsModel: SimilarParamsModel): String

    /**
     * 获取热门活动
     *
     * @return json
     */
    val activity: String

    /**
     * 活动商品列表
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getActivityGoods(paramsModel: ActivityGoodsParamsModel): String

    /**
     * 单品详情
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getDetail(paramsModel: DetailParamsModel): String

    /**
     * 别人的淘口令转成自己的淘口令
     *
     * @param paramsModel 参数
     * @return json
     */
    fun getTklConvertTkl(paramsModel: TklConvertTklParamsModel): String

    /**
     * 创建淘口令
     *
     * @param params 参数
     * @return json
     */
    fun createTkl(params: CreateTklParams): String

    /**
     * 高效转链接口
     *
     * @param params 参数
     * @return json
     */
    fun getPrivilegeLink(params: PrivilegeLinkParams): String


    /**
     * 获取淘宝官方活动列表（淘宝1元购）
     *
     * @param param 参数
     * @return json
     */
    fun getTaobaoOnePriceProducts(param: TaobaoOnePriceProductsParam): String

    /**
     * 超级搜索
     *
     * @param param 参数
     * @return json
     */
    fun superSearch(param: LianmengSearchParam): String

    /**
     * 线报
     *
     * @param param 参数
     * @return json
     */
    fun getSpeiderProduct(param: SpeiderProductParam): String

    /**
     * 获取热搜关键字
     *
     * @return json
     */
    val hotSearchWorlds: String

    /**
     * 商品精推素材
     *
     * @param param 参数
     * @return json
     */
    fun getProductMaterial(param: ProductMaterialParam): String

    /**
     * 高佣精选
     *
     * @param param 参数
     * @return json
     */
    fun getHighCommission(param: HighCommissionParam): String

    /**
     * 细分类目合集
     *
     * @param param 参数
     * @return json
     */
    fun getSubCategory(param: SubCategoryParam): String

    /**
     * 官方活动会场转链
     *
     * @param param 参数
     * @return json
     */
    fun getActivityProducts(param: ActivityParam?): String

    /**
     * 京东商品批量转链
     * (这个是批量的)
     *
     * @param promotionUnionConvert 参数
     * @return json
     */
    fun jdGetPromotionUnionConvert(promotionUnionConvert: JDPromotionUnionConvert?): String

    /**
     * 京东链接解析
     *
     * @param url 链接
     * @return json
     */
    fun jdParseUrl(url: String): String

    /**
     * 京东商品转链
     *
     * @param params 参数
     * @return json
     */
    fun jdPromotionUnionConvertWithOne(params: JDPromotionUnionConvertWithOneParams?): String

    /**
     * 拼多多商品转链
     *
     * @param param 参数
     * @return json
     */
    fun pddGoodsPromGenerate(param: PDDGoodsPromGenerateParam?): String

    /**
     * 淘宝订单查询
     *
     * @param param 参数
     * @return  json数据
     */
    fun getOrderDetails(param: OrderDetailParam): String

    /**
     * 淘系万能解析
     *
     * @return 结果
     */
    fun getTbParseContent(content: String): String

    /**
     * 淘口令解析
     *
     * @param content  解析文本
     * @return  json
     */
    fun getTbParseTaokouling(content: String): String

    /**
     * 获取京东成交订单
     * @param param 参数
     * @return  json
     */
    fun getJdOfficiaOrderList(param: GetOfficialOrderListParam?): String

    /**
     * 优惠券查询
     * @param content   二合一链接，淘口令，或同时输入商品+优惠券链接
     * @return  json
     */
    fun getTbCouponInfo(content: String): String

    /**
     * 超值买返转链
     * @param param  参数
     * @return  json
     */
    fun getTbCzmf(param: CzmfParam?): String

    /**
     * 拼多多搜索
     * @param param 参数
     * @return  json
     */
    fun pddSearch(param: PddSearchParam?): String

    /**
     * 淘礼金创建
     * @param param 入参
     * @return  json
     */
    fun tljCreate(param: TaolijinCreateParam?): String

    /**
     * 获取淘礼金商品
     * @param param 入参
     * @return  json
     */
    fun tljProducts(param: TaolijinProductsParam?): String

    /**
     * 京东9块9包邮
     * @param pageId    页码
     * @param pageSize  页数量
     * @param sort  排序 0-综合排序；1-价格升序；2-价格降序
     * @return  json
     */
    fun jdNinesList(pageId: Int, pageSize: Int, sort: Int): String

    /**
     * 获取京东的商品详情
     * @param sku   sku
     * @return  json
     */
    fun jdProductDetail(sku: String): String

    /**
     * 京东排行榜
     * @param page  第几页
     * @param pageSize  每页多少条数据
     * @return  json
     */
    fun jdPhb(page: Int, pageSize: Int): String

    /**
     * 京东大牌折扣
     * @param page  第几页
     * @param pageSize  每页多少条数据
     * @return  json
     */
    fun jdDpzk(page: Int, pageSize: Int): String

    /**
     * 获取去平多多的分类数据
     * @param parentId 父亲分类id , 一级分类设置成0
     * @return  json
     */
    fun getPddCategory(parentId: String): String

    /**
     * 获取京东的分类数据
     * @return json
     */
    fun getJdCategory(jsonObject: JdCategoryParam?): String

    /**
     * 京东联盟搜索
     * @param params    请求参数
     * @return  json
     */
    fun jdSearch(params: MutableMap<String, Any>): String

    /**
     * 淘客统一请求
     * @param url 请求链接
     * @param version 版本号
     * @param params 参数
     * @return json
     */
    fun requestUrl(url: String, version: String="v1.0.0", params: MutableMap<String, Any>): String
}
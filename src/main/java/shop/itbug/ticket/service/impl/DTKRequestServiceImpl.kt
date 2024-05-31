package shop.itbug.ticket.service.impl

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import jakarta.annotation.Resource
import org.apache.commons.lang3.StringUtils
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import shop.itbug.ticket.constant.DTKApiConstant
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.exception.ResultDialogType
import shop.itbug.ticket.model.params.*
import shop.itbug.ticket.service.DTKApiService
import shop.itbug.ticket.service.DTKReqeestService
import shop.itbug.ticket.service.redis.RedisKeys
import shop.itbug.ticket.service.util.ParamUtil
import shop.itbug.ticket.utils.Result
import java.util.*


fun TreeMap<*, *>.toMutableMap(): MutableMap<String, Any> {
    val map = mutableMapOf<String, Any>()
    forEach { (any, any2) ->
        run {
            if (any2 != null && any2.toString().trim().isNotBlank()) {
                map[any.toString()] = any2
            }
        }
    }
    return map
}


fun <T> T.toMapParams(): MutableMap<String, Any> {
    try {
        val treeMap = JSON.parseObject(JSONObject.toJSONString(this), TreeMap::class.java)
        return treeMap.toMutableMap()
    } catch (e: Exception) {
        throw BizException("转换模型失败:$e")
    }
}

/**
 *
 */
@Service
class DTKRequestServiceImpl : DTKReqeestService {
    @Resource
    private lateinit var dtkApiService: DTKApiService


    /**
     * 通用数据返回
     *
     * @param response json字符串
     * @return 返回前端的数据
     */
    override fun getData(response: String): Result<String> {
        return dtkApiService.handleResponse(response)
    }

    /**
     * 大淘客分类接口
     *
     * @return json字符串
     */
    @get:Cacheable(RedisKeys.DTK_SERVICE + "category#24")
    override val category: String
        get() {
            val host = "https://openapi.dataoke.com/api/category/get-super-category"
            return dtkApiService.request(host, mutableMapOf(), DTKApiConstant.CATEGORY_VERSION)
        }

    /**
     * 商品列表
     *
     * @param goodsListParams 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE + "productList#20#m", key = "#goodsListParams")
    override fun getProductList(goodsListParams: GoodsListParams): String {
        val url = "https://openapi.dataoke.com/api/goods/get-goods-list"
        return dtkApiService.request(url, goodsListParams.toMapParams(), DTKApiConstant.PRODUCT_LIST_VERSION)
    }

    /**
     * 该接口返回大淘客所有分类下精选品牌以及对应推荐的热门商品
     *
     * @param paramsModel 参数
     * @return 大淘客data json数据字符串
     */
    @Cacheable(RedisKeys.DTK_SERVICE + "getBrandList#24", key = "#paramsModel")
    override fun getBrandList(paramsModel: BrandListParamsModel): String {
        if (StringUtils.isBlank(paramsModel.pageId) || StringUtils.isBlank(paramsModel.pageSize) || StringUtils.isBlank(
                paramsModel.cid
            )
        ) throw BizException(CommonEnum.PARAMS_ERROR)
        val url = "https://openapi.dataoke.com/api/delanys/brand/get-column-list"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.BRAND_LIST_VERSION)
    }

    /**
     * 该接口返回某个具体品牌的详细信息和推荐商品
     *
     * @param brandId  品牌id
     * @param pageId   分页id：常规分页方式，请直接传入对应页码（比如：1,2,3……） 第一页时会携带品牌信息数据
     * @param pageSize 每页记录条数（每页支持最大记录条数100）
     * @return 大淘客data json数据字符串
     */
    @Cacheable(RedisKeys.DTK_SERVICE + "getBrandDetail#24", key = "#brandId+'_'+#pageId+'_'+#pageSize")
    override fun getBrandDetail(brandId: String, pageId: String, pageSize: String): String {

        val url = "https://openapi.dataoke.com/api/delanys/brand/get-goods-list"
        val initParamMap = mutableMapOf<String, Any>()
        initParamMap["brandId"] = brandId
        initParamMap["pageId"] = pageId
        initParamMap["pageSize"] = pageSize
        return dtkApiService.request(url, initParamMap, DTKApiConstant.BRAND_DETAIL_VERSION)
    }

    /**
     * 商品评价接口
     * 该接口返回某个具体商品的评价内容，目前每个商品最多返回200条评论（仅限大淘客商品）
     *
     * @param paramsModel 参数列表
     * @return 大淘客data json数据字符串
     */
    @Cacheable(RedisKeys.DTK_SERVICE + "getCommentList#24", key = "#paramsModel")
    override fun getCommentList(paramsModel: CommentParamsModel): String {
        if (StringUtils.isBlank(paramsModel.id) && StringUtils.isBlank(paramsModel.goodsId)) throw BizException(
            CommonEnum.PARAMS_ERROR
        )
        val url = "https://openapi.dataoke.com/api/comment/get-comment-list"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.COMMENT_VERSION)
    }

    /**
     * 店铺转链
     *
     * @param params 参数
     * @return json数据
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"shopConvert#1", key = "#params")
    override fun shopConvert(params: ShopConvertParamsModel): String {
        if (StringUtils.isBlank(params.pid) || StringUtils.isBlank(params.sellerId) || StringUtils.isBlank(params.shopName)) throw BizException(
            CommonEnum.PARAMS_ERROR
        )
        val url = "https://openapi.dataoke.com/api/dels/shop/convert"
        return dtkApiService.request(url, params.toMapParams(), DTKApiConstant.SHOP_CONVERT_VERSION)
    }

    /**
     * 热搜记录
     *
     *
     * 应用场景：
     * 可用于搜索页面的热搜词榜单，为用户提供搜索推荐
     * 接口说明：
     * 该接口返回昨日大淘客CMS端采集统计的前100名搜索热词
     *
     *
     * 文档地址:[http://www.dataoke.com/kfpt/api-d.html?id=4]
     *
     * @return json字符串
     */
    @get:Cacheable(RedisKeys.DTK_SERVICE + "searchWorlds#1")
    override val searchWorlds: String
        get() {
            val url = "https://openapi.dataoke.com/api/category/get-top100"
            return dtkApiService.request(url, mutableMapOf(), DTKApiConstant.SEARCH_WORLDS_VERSION)
        }

    /**
     * 获取短视频分类
     *
     * @return json
     */
    @get:Cacheable(RedisKeys.DTK_SERVICE + "videoCategory#1")
    override val videoCategory: String
        get() {
            val url = "https://openapi.dataoke.com/api/delanys/media/video/get-category-list"
            return dtkApiService.request(url, mutableMapOf(), DTKApiConstant.VIDEO_CATEGORY_VERSION)
        }

    /**
     * 获取短视频的列表
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getVideoGoodsList#1", key = "#paramsModel")
    override fun getVideoGoodsList(paramsModel: VideoGoodsParamsModel): String {
        if (StringUtils.isBlank(paramsModel.cid) || StringUtils.isBlank(paramsModel.pageId) || StringUtils.isBlank(
                paramsModel.pageSize
            )
        ) throw BizException(CommonEnum.PARAMS_ERROR)
        val url = "https://openapi.dataoke.com/api/delanys/media/video/get-goods-list"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.VIDEO_GOODS_VERSION)
    }

    /**
     * 细分类目榜
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getSubdivisionIdGoodsList#2", key = "#paramsModel")
    override fun getSubdivisionIdGoodsList(paramsModel: SubdivisionParamsModel): String {
        if (StringUtils.isBlank(paramsModel.subdivisionId)) throw BizException(CommonEnum.PARAMS_ERROR)
        val url = "https://openapi.dataoke.com/api/subdivision/get-rank-list"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.SUBDIVISION_VERSION)
    }

    /**
     * 折上折
     *
     *
     * 应用场景：
     * 可用于搭建导购网站、APP、小程序等应用的特色栏目或社群分发推广
     * 接口说明：
     * 该接口返回全网最新的折上折商品，以更丰富的形式提高转化
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getDiscountGoodsList#2",key = "#paramsModel")
    override fun getDiscountGoodsList(paramsModel: DiscountParamsModel): String {
        if (StringUtils.isBlank(paramsModel.pageId) || StringUtils.isBlank(paramsModel.pageSize) || StringUtils.isBlank(
                paramsModel.sort
            )
        ) throw BizException(CommonEnum.PARAMS_ERROR)
        val url = "https://openapi.dataoke.com/api/goods/super-discount-goods"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.DISCOUNT_VERSION)
    }

    /**
     * 获取每日半价商品
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getHalfDayGoodsList#2", key = "#paramsModel")
    override fun getHalfDayGoodsList(paramsModel: HalfDayParamsModel): String {
        val url = "https://openapi.dataoke.com/api/goods/get-half-price-day"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.HALF_DAY_VERSION)
    }

    /**
     * 获取商品的历史价格
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getGoodsHistory#2", key = "#paramsModel")
    override fun getGoodsHistory(paramsModel: GoodsHistoryParamsModel): String {
        if (StringUtils.isBlank(paramsModel.id) && StringUtils.isBlank(paramsModel.goodsId)) throw BizException(
            CommonEnum.PARAMS_ERROR
        )
        val url = "https://openapi.dataoke.com/api/goods/price-trend"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.GOODS_HISTORY_VERSION)
    }

    /**
     * 获取直播的商品
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getLiveDatas#2", key = "#paramsModel")
    override fun getLiveDatas(paramsModel: LiveParamsModel): String {
        val url = "https://openapi.dataoke.com/api/goods/liveMaterial-goods-list"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.LIVE_VERSION)
    }

    /**
     * 获取每日爆品商品
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getHotDayGoodsList#2", key = "#paramsModel")
    override fun getHotDayGoodsList(paramsModel: HotDayParamsModel): String {
        if (StringUtils.isBlank(paramsModel.pageId) || StringUtils.isBlank(paramsModel.pageSize)) throw BizException(
            CommonEnum.PARAMS_ERROR
        )
        val url = "https://openapi.dataoke.com/api/goods/explosive-goods-list"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.HOT_DAY_VERSION)
    }

    /**
     * 获取9.9商品列表
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getNineNineGoodsList#2", key = "#paramsModel")
    override fun getNineNineGoodsList(paramsModel: NineNineParamsModel): String {
        if (StringUtils.isBlank(paramsModel.nineCid) || StringUtils.isBlank(paramsModel.pageId) || StringUtils.isBlank(
                paramsModel.pageSize
            )
        ) throw BizException(CommonEnum.PARAMS_ERROR)
        val url = "https://openapi.dataoke.com/api/goods/nine/op-goods-list"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.NINE_NINE_VERSION)
    }

    /**
     * 咚咚抢
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getDddGoodsList#2", key = "#paramsModel")
    override fun getDddGoodsList(paramsModel: DdqParamsModel): String {
        val url = "https://openapi.dataoke.com/api/category/ddq-goods-list"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.DDQ_VERSION)
    }

    /**
     * 获取大淘客独家券
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getExclusiveGoodsList#2", key = "#paramsModel")
    override fun getExclusiveGoodsList(paramsModel: ExclusiveParamsModel): String {
        if (StringUtils.isBlank(paramsModel.pageId) || StringUtils.isBlank(paramsModel.pageSize)) throw BizException(
            CommonEnum.PARAMS_ERROR
        )
        val url = "https://openapi.dataoke.com/api/goods/exclusive-goods-list"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.EXCLUSIVE_VERSION)
    }

    /**
     * 各大榜单
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getTopGoodsList#2", key = "#paramsModel")
    override fun getTopGoodsList(paramsModel: TopParamsModel): String {
        val url = "https://openapi.dataoke.com/api/goods/get-ranking-list"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.TOP_VERSION)
    }

    /**
     * 朋友圈文案
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getWechatList#2", key = "#paramsModel")
    override fun getWechatList(paramsModel: WeChatParamsModel): String {
        if (StringUtils.isBlank(paramsModel.pageId)) throw BizException(CommonEnum.PARAMS_ERROR, "缺少pageId")
        val url = "https://openapi.dataoke.com/api/goods/friends-circle-list"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.WECHAT_VERSION)
    }

    /**
     * 获取精选专题
     *
     * @return json
     */
    @get:Cacheable(RedisKeys.DTK_SERVICE + "topicList#2")
    override val topicList: String
        get() {
            val url = "https://openapi.dataoke.com/api/goods/topic/catalogue"
            return dtkApiService.request(url, mutableMapOf(), DTKApiConstant.TOPIC_VERSION)
        }


    /**
     * 相似推荐
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getSimilarList#2", key = "#paramsModel")
    override fun getSimilarList(paramsModel: SimilarParamsModel): String {
        if (StringUtils.isBlank(paramsModel.id)) throw BizException(CommonEnum.PARAMS_ERROR)
        val url = "https://openapi.dataoke.com/api/goods/list-similer-goods-by-open"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.SIMILAR_VERSION)
    }

    /**
     * 获取热门活动
     *
     * @return json
     */
    @get:Cacheable(RedisKeys.DTK_SERVICE + "activity#2")
    override val activity: String
        get() {
            val url = "https://openapi.dataoke.com/api/goods/activity/catalogue"
            return dtkApiService.request(url, mutableMapOf(), DTKApiConstant.ACTIVITY_VERSION)
        }

    /**
     * 活动商品列表
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getActivityGoods#2", key = "#paramsModel")
    override fun getActivityGoods(paramsModel: ActivityGoodsParamsModel): String {
        if (StringUtils.isBlank(paramsModel.activityId)) throw BizException(CommonEnum.PARAMS_ERROR)
        val url = "https://openapi.dataoke.com/api/goods/activity/goods-list"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.ACTIVITY_GOODS_VERSION)
    }

    /**
     * 单品详情
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getDetail#2", key = "#paramsModel")
    override fun getDetail(paramsModel: DetailParamsModel): String {
        if (StringUtils.isBlank(paramsModel.goodsId) && StringUtils.isBlank(paramsModel.id)) throw BizException(
            CommonEnum.PARAMS_ERROR
        )
        val url = "https://openapi.dataoke.com/api/goods/get-goods-details"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.DETAIL_VERSION)
    }

    /**
     * 别人的淘口令转成自己的淘口令
     *
     * @param paramsModel 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getTklConvertTkl#2", key = "#paramsModel")
    override fun getTklConvertTkl(paramsModel: TklConvertTklParamsModel): String {
        if (StringUtils.isBlank(paramsModel.content)) throw BizException(CommonEnum.PARAMS_ERROR)
        val url = "https://openapi.dataoke.com/api/tb-service/twd-to-twd"
        return dtkApiService.request(url, paramsModel.toMapParams(), DTKApiConstant.DKL_TO_TKL_VERSION)
    }

    /**
     * 创建淘口令
     *
     * @param params 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"createTkl#2", key = "#params")
    override fun createTkl(params: CreateTklParams): String {
        val url = "https://openapi.dataoke.com/api/tb-service/creat-taokouling"
        if (StringUtils.isBlank(params.text) || StringUtils.isBlank(params.url)) {
            throw BizException(CommonEnum.PARAMS_ERROR)
        }
        return dtkApiService.request(url, params.toMapParams(), DTKApiConstant.CREATE_TKL_VERSION)
    }

    /**
     * 高效转链接口
     *
     * @param params 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getPrivilegeLink#2", key = "#params")
    override fun getPrivilegeLink(params: PrivilegeLinkParams): String {
        if (StringUtils.isBlank(params.goodsId)) throw BizException(CommonEnum.PARAMS_ERROR)
        val url = "https://openapi.dataoke.com/api/tb-service/get-privilege-link"
        return dtkApiService.request(url, params.toMapParams(), DTKApiConstant.PRIVILELE_VERSION)
    }

    /**
     * 获取淘宝官方活动列表（淘宝1元购)
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getTaobaoPriceProducts#2", key = "#param")
    override fun getTaobaoOnePriceProducts(param: TaobaoOnePriceProductsParam): String {
        val url = "https://openapi.dataoke.com/api/category/get-tb-topic-list"
        ParamUtil.verifyParam(param.pageId)
        return dtkApiService.request(url, param.toMapParams(), "v1.2.0")
    }

    /**
     * 超级搜索
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"superSearch#2", key = "#param")
    override fun superSearch(param: LianmengSearchParam): String {
        val url = "https://openapi.dataoke.com/api/goods/list-super-goods"
        ParamUtil.verifyParam(param.pageId, param.pageSize, param.type, param.keyWords)
        return dtkApiService.request(url, param.toMapParams(), "v1.3.0")
    }

    /**
     * 线报
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getSpeiderProduct#2", key = "#param")
    override fun getSpeiderProduct(param: SpeiderProductParam): String {
        val url = "https://openapi.dataoke.com/api/dels/spider/list-tip-off"
        return dtkApiService.request(url, param.toMapParams(), "v2.0.0")
    }

    /**
     * 热搜榜
     *
     *
     * 应用场景：
     * 可用于搜索页面的热搜榜单，为用户提供搜索推荐，提高搜索转化
     * 接口说明：
     * 该接口返回近期热搜话题相关商品的合集
     *
     * @return json
     */
    @get:Cacheable(RedisKeys.DTK_SERVICE + "getHotSearchWorlds#2")
    override val hotSearchWorlds: String
        get() {
            val url = "https://openapi.dataoke.com/api/etc/search/list-hot-words"
            return dtkApiService.request(url, mutableMapOf(), "v1.0.0")
        }

    /**
     * 商品精推素材
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getProductMaterial#2", key = "#param")
    override fun getProductMaterial(param: ProductMaterialParam): String {
        val url = "https://openapi.dataoke.com/api/goods/material/list"
        ParamUtil.verifyParam(param.id)
        return dtkApiService.request(url, param.toMapParams(), "v1.0.0")
    }

    /**
     * 高佣精选
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getHighCommission#2", key = "#param")
    override fun getHighCommission(param: HighCommissionParam): String {
        val url = "https://openapi.dataoke.com/api/goods/singlePage/list-height-commission"
        ParamUtil.verifyParam(param.pageId, param.pageSize)
        return dtkApiService.request(url, param.toMapParams(), "v1.0.0")
    }

    /**
     * 细分类目合集
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getSubCategory#2", key = "#param")
    override fun getSubCategory(param: SubCategoryParam): String {
        val url = "https://openapi.dataoke.com/api/subdivision/get-list"
        ParamUtil.verifyParam(param.cid, param.pageSize, param.pageId)
        return dtkApiService.request(url, param.toMapParams(), "v1.0.0")
    }

    /**
     * 官方活动会场转链
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getActivityProducts#2", key = "#param")
    override fun getActivityProducts(param: ActivityParam?): String {
        val url = "https://openapi.dataoke.com/api/tb-service/activity-link"
        return dtkApiService.request(url, param.toMapParams(), "v1.0.0")
    }

    /**
     * 京东商品批量转链
     *
     * @param promotionUnionConvert 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"jdGetPromotionUnionConvert#2", key = "#promotionUnionConvert")
    override fun jdGetPromotionUnionConvert(promotionUnionConvert: JDPromotionUnionConvert?): String {
        val url = "https://openapi.dataoke.com/api/dels/jd/kit/content/promotion-union-convert"
        return dtkApiService.request(url, promotionUnionConvert.toMapParams(), "v1.0.0")
    }

    /**
     * 京东链接解析
     *
     * @param url 链接
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"jdParseUrl#2", key = "#url")
    override fun jdParseUrl(url: String): String {
        val apiUrl = "https://openapi.dataoke.com/api/dels/jd/kit/parseUrl"
        val initParamMap = mutableMapOf<String, Any>()
        initParamMap["url"] = url
        return dtkApiService.request(apiUrl, initParamMap, "v1.0.0")
    }

    /**
     * 京东商品转链
     *
     * @param params 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"jdPromotionUnionConvertWithOne#2", key = "#params")
    override fun jdPromotionUnionConvertWithOne(params: JDPromotionUnionConvertWithOneParams?): String {
        val url = "https://openapi.dataoke.com/api/dels/jd/kit/promotion-union-convert"
        return dtkApiService.request(url, params.toMapParams(), "v1.0.0")
    }

    /**
     * 拼多多商品转链
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"pddGoodsPromGenerate#2", key = "#param")
    override fun pddGoodsPromGenerate(param: PDDGoodsPromGenerateParam?): String {
        val url = "https://openapi.dataoke.com/api/dels/pdd/kit/goods-prom-generate"
        return dtkApiService.request(url, param.toMapParams(), "v1.0.0")
    }

    /**
     * 淘宝订单查询
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getOrderDetails#5#s", key = "#param")
    override fun getOrderDetails(param: OrderDetailParam): String {
        val url = "https://openapi.dataoke.com/api/tb-service/get-order-details"
        val string = dtkApiService.request(url, param.toMapParams(), "v1.0.0")

        //进行判断
        val json = JSONObject.parse(string)
        if (json.getIntValue("code") == 0) {
            val tbJson = json.getJSONObject("data")
            if (tbJson.containsKey("msg") && tbJson.getString("msg") != "ok") {
                throw BizException(
                    errorMsg = tbJson.getString("sub_msg"),
                    data = tbJson,
                    toastType = ResultDialogType.Dialog
                )
            }
        }
        return string
    }

    /**
     * 淘系万能解析
     *
     * @param content 解析文本
     * @return 结果
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getTbParseContent#2", key = "#content")
    override fun getTbParseContent(content: String): String {
        val url = "https://openapi.dataoke.com/api/tb-service/parse-content"
        val initParamMap = mutableMapOf<String, Any>()
        initParamMap["content"] = content
        return dtkApiService.request(url, initParamMap, "v1.0.0")
    }

    /**
     * 淘口令解析
     *
     * @param content 解析文本
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getTbParseTaokouling#2", key = "#content")
    override fun getTbParseTaokouling(content: String): String {
        val url = "https://openapi.dataoke.com/api/tb-service/parse-taokouling"
        val initParamMap = mutableMapOf<String, Any>()
        initParamMap["content"] = content
        return dtkApiService.request(url, initParamMap, "v1.0.0")
    }

    /**
     * 获取京东成交订单
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getJdOfficiaOrderList#2", key = "#param", unless = "param==null")
    override fun getJdOfficiaOrderList(param: GetOfficialOrderListParam?): String {
        val url = "https://openapi.dataoke.com/api/dels/jd/order/get-official-order-list"
        return dtkApiService.request(url, param.toMapParams(), "v1.0.0")
    }

    /**
     * 优惠券查询
     *
     * @param content 二合一链接，淘口令，或同时输入商品+优惠券链接
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getTbCouponInfo#2", key = "#content")
    override fun getTbCouponInfo(content: String): String {
        val url = "https://openapi.dataoke.com/api/dels/taobao/kit/coupon/get-coupon-info"
        val initParamMap = mutableMapOf<String, Any>()
        initParamMap["content"] = content
        return dtkApiService.request(url, initParamMap, "v1.0.0")
    }

    /**
     * 超值买返转链
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getTbCzmf#2", key = "#param")
    override fun getTbCzmf(param: CzmfParam?): String {
        val url = "https://openapi.dataoke.com/api/dels/taobao/kit/turnLink/czmf"
        return dtkApiService.request(url, param.toMapParams(), "v1.0.0")
    }

    /**
     * 拼多多搜索
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"pddSearch#2", key = "#param")
    override fun pddSearch(param: PddSearchParam?): String {
        val url = "https://openapi.dataoke.com/api/dels/pdd/goods/search"
        val treeMap = JSONObject.parseObject(JSONObject.toJSONString(param), TreeMap::class.java)
        return dtkApiService.request(url, treeMap.toMutableMap(), "v1.0.0")
    }

    /**
     * 淘礼金创建
     *
     * @param param 入参
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"tljCreate#2", key = "#param")
    override fun tljCreate(param: TaolijinCreateParam?): String {
        val url = "https://openapi.dataoke.com/api/dels/taobao/kit/create-tlj"

        val treeMap = JSONObject.parseObject(JSONObject.toJSONString(param), TreeMap::class.java)
        return dtkApiService.request(url, treeMap.toMutableMap(), "v1.0.0")
    }

    /**
     * 获取淘礼金商品
     *
     * @param param 入参
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"tljProducts#2", key = "#param")
    override fun tljProducts(param: TaolijinProductsParam?): String {
        val url = "https://openapi.dataoke.com/api/goods/first-order-gift-money"
        val treeMap = JSONObject.parseObject(JSONObject.toJSONString(param), TreeMap::class.java)
        return dtkApiService.request(url, treeMap.toMutableMap(), "v1.0.0")
    }

    /**
     * 京东9块9包邮
     *
     * @param pageId   页码
     * @param pageSize 页数量
     * @param sort     排序 0-综合排序；1-价格升序；2-价格降序
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"jdNinesList#2", key = "#pageId+'_'+#pageSize+'_'+#sort")
    override fun jdNinesList(pageId: Int, pageSize: Int, sort: Int): String {
        val url = "https://openapi.dataoke.com/api/dels/jd/column/list-nines"
        val initParamMap = mutableMapOf<String, Any>()
        initParamMap["pageId"] = pageId.toString()
        initParamMap["pageSize"] = pageSize.toString()
        initParamMap["sort"] = sort.toString()
        return dtkApiService.request(url, initParamMap, "v1.0.0")
    }

    /**
     * 获取京东的商品详情
     *
     * @param sku sku
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"jdProductDetail#2", key = "#sku")
    override fun jdProductDetail(sku: String): String {
        val url = "https://openapi.dataoke.com/api/dels/jd/goods/get-details"
        val initParamMap = mutableMapOf<String, Any>()
        initParamMap["skuIds"] = sku
        return dtkApiService.request(url, initParamMap, "v1.0.0")
    }

    /**
     * 京东排行榜
     *
     * @param page     第几页
     * @param pageSize 每页多少条数据
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"jdPhb#2", key = "#page+'_'+#pageSize")
    override fun jdPhb(page: Int, pageSize: Int): String {
        val url = "https://openapi.dataoke.com/api/dels/jd/column/list-real-ranks"
        val initParamMap = mutableMapOf<String, Any>()
        initParamMap["pageId"] = page.toString()
        initParamMap["pageSize"] = pageSize.toString()
        return dtkApiService.request(url, initParamMap, "v1.0.0")
    }

    /**
     * 京东大牌折扣
     *
     * @param page     第几页
     * @param pageSize 每页多少条数据
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"jdDpzk#2", key = "#page+'_'+#pageSize")
    override fun jdDpzk(page: Int, pageSize: Int): String {
        val url = "https://openapi.dataoke.com/api/dels/jd/column/list-discount-brand"
        val initParamMap = mutableMapOf<String, Any>()
        initParamMap["pageId"] = page.toString()
        initParamMap["pageSize"] = pageSize.toString()
        return dtkApiService.request(url, initParamMap, "v1.0.0")
    }

    /**
     * 获取去平多多的分类数据
     *
     * @param parentId 父亲分类id , 一级分类设置成0
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getPddCategory#2", key = "#parentId")
    override fun getPddCategory(parentId: String): String {
        val url = "https://openapi.dataoke.com/api/dels/pdd/category/search"
        val initParamMap = mutableMapOf<String, Any>()
        initParamMap["parentId"] = parentId
        return dtkApiService.request(url, initParamMap, "v1.0.0")
    }

    /**
     * 获取京东的分类数据
     *
     * @param jsonObject 参数
     * @return json
     */
    @Cacheable(RedisKeys.DTK_SERVICE+"getJdCategory#2", key = "#jsonObject", unless = "jsonObject==null")
    override fun getJdCategory(jsonObject: JdCategoryParam?): String {
        val url = "https://openapi.dataoke.com/api/dels/jd/category/search"
        val treeMap = JSONObject.parseObject(JSONObject.toJSONString(jsonObject), TreeMap::class.java)
        return dtkApiService.request(url, if (jsonObject == null) mutableMapOf() else treeMap.toMutableMap(), "v1.0.0")
    }

    /**
     * 京东联盟搜索
     *
     * @param params 请求参数
     * @return json
     */

    @Cacheable(RedisKeys.DTK_SERVICE+"jdSearch#2", key = "#params")
    override fun jdSearch(params: MutableMap<String, Any>): String {
        val url = "https://openapi.dataoke.com/api/dels/jd/goods/search"
        return dtkApiService.request(url, params, "v1.0.0")
    }

    /**
     * 淘客统一请求
     *
     * @param url     请求链接
     * @param version 版本号
     * @param params  参数
     * @return json
     */
    override fun requestUrl(url: String, version: String, params: MutableMap<String, Any>): String {
        return dtkApiService.request(url, params, version)
    }
}
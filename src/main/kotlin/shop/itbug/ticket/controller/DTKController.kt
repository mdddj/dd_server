package shop.itbug.ticket.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import kotlinx.serialization.json.Json
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.model.dtk.*
import shop.itbug.ticket.model.params.*
import shop.itbug.ticket.service.DTKReqeestService
import shop.itbug.ticket.service.daTaoKeStringCovert
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.successResult
import shop.itbug.ticket.utils.verify


/**
 * 大淘客的接口使用
 *
 */
@RestController
@RequestMapping("/tkapi/api/v1/dtk/apis")
@Tag(name = "大淘客接口")
class DTKController {
    @Resource
    private lateinit var dtkReqeestService: DTKReqeestService

    /**
     * 超级分类
     *
     *
     * 应用场景：
     * 用于搭建网站或app的分类筛选功能
     * 接口说明：
     * 该接口返回大淘客所有的一级分类和二级分类，且提供一级分类图标及二级分类图标素材
     * 接口版本号：v1.1.0
     *
     * @return json
     */
    @Operation(summary = "超级分类")
    @GetMapping("/categorys")
    fun getDTKCategoryJson(): Result<List<DtkCategory>> =
        dtkReqeestService.getData(dtkReqeestService.category).daTaoKeStringCovert {
            Json.decodeFromString<List<DtkCategory>>(it)
        }.successResult("获取成功")

    /**
     * 商品列表
     *
     *
     * 应用场景：
     * 构建商品库，就首次将所有商品全量入库，后续可通过实定时拉取、失效商品、商品更新等接口保证商品增量和商品的信息同步
     * 接口说明：
     * 该接口返回筛选条件内的所有商品列表和商品信息
     * 接口版本号：v1.2.4
     *
     * @param params 请求参数
     * @return json
     */
    @GetMapping("/goods")
    @Operation(summary = "商品列表")
    fun getDTKProductList(params: GoodsListParams): Result<ProductListResultModel> {
        val response = dtkReqeestService.getProductList(params)
        return dtkReqeestService.getData(response).daTaoKeStringCovert {
            Json.decodeFromString(ProductListResultModel.serializer(),it)
        }.successResult("获取产品列表成功")
    }

    /**
     * 品牌栏目
     *
     *
     * 应用场景：
     * 可用于搭建品牌特色栏目
     * 接口说明：
     * 该接口返回大淘客所有分类下精选品牌以及对应推荐的热门商品
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 请求参数
     * @return json
     */
    @GetMapping("/brand-list")
    @Operation(summary = "品牌栏目")
    fun getBrandList(paramsModel: BrandListParamsModel): Result<String> {
        val response = dtkReqeestService.getBrandList(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 单个品牌详情
     *
     *
     * 应用场景：
     * 用于展示单个品牌相关信息以及品牌商品
     * 接口说明：
     * 该接口返回某个具体品牌的详细信息和推荐商品
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 请求参数
     * @return json
     */
    @GetMapping("/brand-detail")
    @Operation(summary = "单个品牌详情")
    fun getBrandDetail(paramsModel: BrandDetailParamsModel): Result<String> {
        val response = dtkReqeestService.getBrandDetail(paramsModel.brandId, paramsModel.pageId, paramsModel.pageSize)
        return dtkReqeestService.getData(response)
    }

    /**
     * 商品评论
     *
     *
     * 应用场景：
     * 可用于商品详情页中，展示该商品在淘宝/天猫的评论情况
     * 接口说明：
     * 该接口返回某个具体商品的评价内容，目前每个商品最多返回200条评论（仅限大淘客商品）
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 参数
     * @return json数据
     */
    @GetMapping("/comment")
    @Operation(summary = "商品评论")
    fun getComment(paramsModel: CommentParamsModel): Result<String> {
        val response = dtkReqeestService.getCommentList(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 店铺转链
     *
     *
     * 应用场景：
     * 可用于推广某个店铺内的所有商品。一键推广，省时省力，推广更高效
     * 接口说明：
     * 该接口返回某个店铺的推广链接和推广淘口令
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/shop-convert")
    @Operation(summary = "店铺转链")
    fun shopConvert(paramsModel: ShopConvertParamsModel): Result<String> {
        val response = dtkReqeestService.shopConvert(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 热搜记录
     *
     *
     * 应用场景：
     * 可用于搜索页面的热搜词榜单，为用户提供搜索推荐
     * 接口说明：
     * 该接口返回昨日大淘客CMS端采集统计的前100名搜索热词
     * 接口版本号：v1.0.1
     *
     * @return json
     */
    @GetMapping("/search-worlds")
    @Operation(summary = "热搜记录")
    fun getSearchWorlds(): Result<HotSearchKeyWords> =
        dtkReqeestService.getData(dtkReqeestService.searchWorlds).daTaoKeStringCovert {
            Json.decodeFromString(HotSearchKeyWords.serializer(), it)
        }.successResult("获取成功")

    /**
     * 短视频分类
     *
     * @return json数据
     */
    @GetMapping("/video-category")
    @Operation(summary = "短视频分类")
    fun getVideoCategory(): Result<List<VideoCategory>> =
        dtkReqeestService.getData(dtkReqeestService.videoCategory).daTaoKeStringCovert {
            Json.decodeFromString<List<VideoCategory>>(it)
        }.successResult("获取成功")

    /**
     * 获取短视频的商品列表
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/video-goods")
    @Operation(summary = "获取短视频的商品列表")
    fun getVideoGoodsList(paramsModel: VideoGoodsParamsModel): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.getVideoGoodsList(paramsModel))
    }

    /**
     * 细分类目榜
     *
     *
     * 应用场景：
     * 可用于搜索展示某个商品，在细分类目中的销量排行；也可用于单品详情，展示该商品细分类目的排名。帮助用户购买决策，增加购买选择。
     * 接口说明：
     * 该接口返回某个具体细分类目榜单前20名商品数据。
     *
     *
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/subdivision-goods")
    @Operation(summary = "细分类目榜")
    fun getSubDivisionGoodsList(paramsModel: SubdivisionParamsModel): Result<String> {
        val response = dtkReqeestService.getSubdivisionIdGoodsList(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 折上折
     *
     *
     * 应用场景：
     * 可用于搭建导购网站、APP、小程序等应用的特色栏目或社群分发推广
     * 接口说明：
     * 该接口返回全网最新的折上折商品，以更丰富的形式提高转化
     *
     *
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/discount-goods")
    @Operation(summary = "折上折")
    fun getDiscountGoodsList(paramsModel: DiscountParamsModel): Result<String> {
        val response = dtkReqeestService.getDiscountGoodsList(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 每日半价
     *
     *
     * 应用场景：
     * 可用于特色营销栏目搭建或社群分发推广
     * 接口说明：
     * 该接口返回每日半价活动商品，可用于吸引用户下单，打造半价爆款商品，提高流量变现！
     *
     *
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/halfday-goods")
    @Operation(summary = "每日半价")
    fun getHalfDayGoodsList(paramsModel: HalfDayParamsModel): Result<String> {
        val response = dtkReqeestService.getHalfDayGoodsList(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 商品历史券后价
     *
     *
     * 应用场景：
     * 可用于商品详情展示该商品的价格变化
     * 接口说明：
     * 该接口返回某个商品在大淘客平台的历史券后价，通过价格的变化，帮助用户购买决策
     *
     *
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/goods-history")
    @Operation(summary = "商品历史券后价")
    fun getGoodsHistory(paramsModel: GoodsHistoryParamsModel): Result<String> {
        val response = dtkReqeestService.getGoodsHistory(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 直播好货
     *
     *
     * 应用场景：
     * 此接口可搭建商品精推素材直播栏目，以更丰富的展现形式推广商品，打造爆款
     * 接口说明：
     * 该接口返回官网新版咚咚抢好货直播商品数据，严格筛选，品质保障
     *
     *
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/live-data")
    @Operation(summary = "直播好货")
    fun getLiveData(paramsModel: LiveParamsModel): Result<String> {
        val response = dtkReqeestService.getLiveDatas(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 每日爆品
     *
     *
     * 应用场景：
     * 可用于特色栏目专区搭建，或者社群分发推广
     * 接口说明：
     * 该接口返回每天满足联盟爆品规则的商品合集，单价10~20元商品当日销量超过3000单，20~40元商品当日销量超过1000单，大于等于40元的销量超过500单。 （因为统计的是商品当天的销量，所以早上可能存在数量很少的情况。10分钟会刷新一次）
     *
     *
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/hot-day")
    @Operation(summary = "每日爆品")
    fun getHotDayGoodsList(paramsModel: HotDayParamsModel): Result<String> {
        val response = dtkReqeestService.getHotDayGoodsList(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 9.9包邮精选
     *
     *
     * 应用场景：
     * 可用于搭建淘客导购软件：网站，cms，公众号，小程序等应用的特色栏目
     * 接口说明：
     * 该接口返回的物美价廉的精选商品。注：该接口返回都是价格9.9左右区间的商品
     *
     *
     * 接口版本号：v2.0.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/nine-nine-goods")
    @Operation(summary = "9.9包邮精选")
    fun getNineNineGoodsList(paramsModel: NineNineParamsModel): Result<NineNineResultModel> {
        val response = dtkReqeestService.getNineNineGoodsList(paramsModel)
        return dtkReqeestService.getData(response).daTaoKeStringCovert {
            Json.decodeFromString<NineNineResultModel>(it)
        }.successResult("获取成功")
    }

    /**
     * 咚咚抢
     *
     *
     * 应用场景：
     * 该接口可用于搭建营销活动的板块内容
     * 接口说明：
     * 接口返回每天参与整点抢购活动的高性价比商品列表。精品中的极品，只为性价比而生
     *
     *
     * 接口版本号：v1.2.1
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/ddq")
    @Operation(summary = "咚咚抢")
    fun getDdqGoodsList(paramsModel: DdqParamsModel): Result<String> {
        val response = dtkReqeestService.getDddGoodsList(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 大淘客独家券商品
     *
     *
     * 应用场景：
     * 大淘客平台独家优惠券，可用于创建营销栏目。
     * 接口说明：
     * 该接口返回大淘客独家的优惠券商品资源，拥有独家优势。助力淘客推广
     *
     *
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/exclusive-goods")
    @Operation(summary = "独家券商品")
    fun getExclusiveGoodsList(paramsModel: ExclusiveParamsModel): Result<String> {
        val response = dtkReqeestService.getExclusiveGoodsList(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 各大榜单
     *
     *
     * 应用场景：
     * 可用于搭建自己的特色榜单，帮助用户快速决策购买优质的商品，提升选品体验
     * 接口说明：
     * 该接口返回的是大淘客平台实时榜、热推榜、全天销量榜、复购榜的前200条商品数据
     *
     *
     * 接口版本号：v1.3.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/top")
    @Operation(summary = "各大榜单")
    fun getTopGoodsList(@Validated paramsModel: TopParamsModel,bindingResult: BindingResult): Result<List<DtkProduct>> {
        bindingResult.verify()
        val response = dtkReqeestService.getTopGoodsList(paramsModel)
        return dtkReqeestService.getData(response).daTaoKeStringCovert {
            Json.decodeFromString<List<DtkProduct>>(it)
        }.successResult("获取成功")
    }

    /**
     * 朋友圈文案
     *
     * 应用场景：
     * 可用于自动发单软件，app推广等场景，提供商品转化
     * 接口说明：
     * 该接口返回有朋友圈文案素材的商品列表
     *
     * 接口版本号：v1.3.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/wechat")
    @Operation(summary = "朋友圈文案")
    fun getWechatList(@Validated paramsModel: WeChatParamsModel,bindingResult: BindingResult): Result<WeChatModel> {
        bindingResult.verify()
        val response = dtkReqeestService.getWechatList(paramsModel)
        val dtkData = dtkReqeestService.getData(response)
        return Json.decodeFromString(WeChatModel.serializer(), dtkData.data!!).successResult()
    }

    /**
     * 精选专题
     *
     *
     * 应用场景：
     * 可用于导购平台的营销专区
     * 接口说明：
     * 由大淘客数据+人工甄选的优质商品，以主题的方式开放给开发者，您可通过精选专题接口获取专辑ID，再通过专题商品接口获取该专辑下的所有商品
     *
     *
     * 接口版本号：v1.1.0
     *
     * @return json
     */
    @GetMapping("/topic")
    @Operation(summary = "精选专题")
    fun getTopicList(): Result<String> = dtkReqeestService.getData(dtkReqeestService.topicList)



    /**
     * 猜你喜欢
     *
     *
     * 应用场景：
     * 可用于用户查看某个商品详情后相关商品的推荐或首页内容推荐
     * 接口说明：
     * 该接口返回基于用户的搜索、查看等行为推荐的类似相关商品，提升用户的停留时间和点击率
     *
     *
     * 接口版本号：v1.2.2
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/similar")
    @Operation(summary = "猜你喜欢")
    fun getSimilarList(paramsModel: SimilarParamsModel): Result<List<DtaokeBaseProduct>> {
        val response = dtkReqeestService.getSimilarList(paramsModel)
        return dtkReqeestService.getData(response).daTaoKeStringCovert {
            Json.decodeFromString<List<DtaokeBaseProduct>>(it)
        }.successResult("获取猜你喜欢产品成功")
    }

    /**
     * 热门活动
     *
     *
     * 应用场景：
     * 可用于导购平台的活动推广专区
     * 接口说明：
     * 该接口返回淘宝官方活动或大淘客官方活动的ID，通过获得的活动ID，使用活动商品接口获取对应的活动商品
     *
     *
     * 接口版本号：v1.1.0
     *
     * @return json
     */
    @GetMapping("/activity")
    @Operation(summary = "热门活动")
    fun getActivity(): Result<String> = dtkReqeestService.getData(dtkReqeestService.activity)

    /**
     * 活动商品
     *
     *
     * 应用场景：
     * 可用于导购平台的活动推广专区查看活动商品
     * 接口说明：
     * 该接口通过热门活动id获取对应的活动商品
     *
     *
     * 接口版本号：v1.2.2
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/activity-goods")
    @Operation(summary = "活动商品")
    fun getActivityGoods(paramsModel: ActivityGoodsParamsModel): Result<String> {
        val response = dtkReqeestService.getActivityGoods(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 单品详情
     *
     *
     * 应用场景：
     * 用于查看某个具体商品的详细信息，帮助用户购买决策。可构建单品详情页
     * 接口说明：
     * 通过大淘客商品id或淘宝商品id查询商品详细信息
     *
     *
     * 接口版本号：v1.2.3
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/detail")
    @Operation(summary = "单品详情")
    fun getDetail(paramsModel: DetailParamsModel): Result<DtaokeBaseProduct> {
        val response = dtkReqeestService.getDetail(paramsModel)
        return dtkReqeestService.getData(response).daTaoKeStringCovert {
            Json.decodeFromString(DtaokeBaseProduct.serializer(),it)
        }.successResult("查找产品成功")
    }

    /**
     * 淘口令转淘口令
     *
     * 应用场景：
     * 当用户从其他渠道采集到商品口令，想要自己推广，可以将此转为自己的淘口令
     * 接口说明：
     * 此接口将其他人的口令解析后，再转化为自己的口令。
     *
     * 接口版本号：v1.0.0
     *
     * @param paramsModel 参数
     * @return json
     */
    @GetMapping("/covert")
    @Operation(summary = "淘口令转淘口令")
    fun tklConvert(paramsModel: TklConvertTklParamsModel): Result<String> {
        val response = dtkReqeestService.getTklConvertTkl(paramsModel)
        return dtkReqeestService.getData(response)
    }

    /**
     * 淘口令生成
     *
     *
     * 应用场景：
     * 针对导购或返利平台，分享时生成商品淘口令，对于素材圈的商品复制口令；同时可以满足用户提供淘口令生成工具
     * 接口说明：
     * 该接口可以将二合一链接、长链接、短链接等各种淘宝高佣链接，生成淘口令
     *
     *
     * 接口版本号：v1.0.0
     *
     * @param params 参数
     * @return json
     */
    @GetMapping("/create-tkl")
    @Operation(summary = "淘口令生成")
    fun createTkl(params: CreateTklParams): Result<String> {
        val response = dtkReqeestService.createTkl(params)
        return dtkReqeestService.getData(response)
    }

    /**
     * 高效转链
     *
     *
     * 应用场景：
     * 可用于导购平台的高佣转链工具
     * 接口说明：
     * 高效转链接口将您的pid信息、商品地址及优惠券信息进行转链，转链后的结果可进行推广或完成订单，接口支持授权淘宝账号下所有PID进行转链。由于接口特殊性，请适量缓存已转链的链接，以达最佳效率。（接联盟通知，v1.2.0官方比价订单佣金调整正式生效时间推迟至7月22日）
     *
     *
     * 接口版本号：v1.3.1
     *
     * @param params 参数
     * @return json
     */
    @GetMapping("/privilege-link")
    @Operation(summary = "高效转链")
    fun getPrivilegeLink(params: PrivilegeLinkParams): Result<PrivilegeLinkModel> {
        val response = dtkReqeestService.getPrivilegeLink(params)
        return dtkReqeestService.getData(response).daTaoKeStringCovert {
            Json.decodeFromString(PrivilegeLinkModel.serializer(),it)
        }.successResult("转链成功")
    }


    /**
     * 官方活动（淘宝1元购）
     *
     *
     * 应用场景：
     * 可用于导购平台的活动推广专区
     * 接口说明：
     * 接口返回联盟最新官方活动推广精品内容，成品页面适配，快速接入，不错过每一次精彩活动。淘宝1元购活动ID：20150318020003158
     *
     *
     * 接口版本号：v1.2.0
     *
     * @param param 参数
     * @return json
     */
    @GetMapping("/taobao-oneprice_product")
    @Operation(summary = "官方活动（淘宝1元购）")
    fun getTaobaoOnePriceProducts(param: TaobaoOnePriceProductsParam): Result<String> {
        val response = dtkReqeestService.getTaobaoOnePriceProducts(param)
        return dtkReqeestService.getData(response)
    }

    /**
     * 超级搜索
     *
     *
     * 应用场景：
     * 基于大淘客和淘宝联盟的商品两个平台的商品进行搜索，优先搜索大淘客优质商品（字段展示更全面）数据不够的情况会再请求联盟做商品补充。
     * 接口说明：
     * 通过关键词搜索返回对应的商品列表。目前返回大淘客200条商品和联盟的300条相关商品。搜索建议：可按销量、佣金比例等维度降序排列。优先展示高质量的商品，提高转化。
     *
     *
     * 接口版本号：v1.3.0
     *
     * @param param 参数
     * @return json
     */
    @GetMapping("/super-search")
    @Operation(summary = "超级搜索")
    fun superSearch(param: LianmengSearchParam): Result<SuperSearchResultModel> {
        val response = dtkReqeestService.superSearch(param)
        return dtkReqeestService.getData(response).daTaoKeStringCovert {
            Json.decodeFromString(SuperSearchResultModel.serializer(),it)
        }.successResult("搜索成功")
    }

    /**
     * 线报
     *
     *
     * 应用场景：
     * 可用于app、自动发单、社群等场景，提前透出商品优惠信息，便于拉新促活，提高转化，锁佣快人一步
     * 接口说明：
     * 该接口返回全网（京东，淘宝，天猫）最全的商品优惠信息（半价，秒杀…）拉新促活好帮手
     *
     *
     * 接口版本号：v1.0.0
     *
     * @param param 参数
     * @return json
     */
    @GetMapping("/speider")
    @Operation(summary = "线报")
    fun getSpeiderList(param: SpeiderProductParam): Result<SpeiderResultModel> {
        val response = dtkReqeestService.getSpeiderProduct(param)
        return dtkReqeestService.getData(response).daTaoKeStringCovert {
            Json.decodeFromString(SpeiderResultModel.serializer(),it)
        }.successResult("获取线报成功")
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
     *
     * 接口版本号：v1.0.0
     *
     * @return json
     */
    @GetMapping("/hot-search-worlds")
    @Operation(summary = "热搜榜")
    fun getHotSearchWorlds(): Result<List<HotSearchKeyWordsItemElement>> =
        dtkReqeestService.getData(dtkReqeestService.hotSearchWorlds).daTaoKeStringCovert {
            Json.decodeFromString<List<HotSearchKeyWordsItemElement>>(it)
        }.successResult("获取成功")

    /**
     * 商品精推素材
     *
     *
     * 应用场景：
     * 可以用于社群或其他渠道进行推广，精选素材保证转化率
     * 接口说明：
     * 该接口返回大淘客从各大优质采集群采集的精推素材数据，易于推广，转化率高
     *
     *
     * 接口版本号：v1.0.0
     *
     * @return json
     */
    @GetMapping("/product-material")
    @Operation(summary = "商品精推素材")
    fun getProductMaterial(param: ProductMaterialParam): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.getProductMaterial(param))
    }

    /**
     * 高佣精选
     *
     *
     * 应用场景：
     * 可用于搭建导购网站、APP、小程序等应用的特色栏目或社群分发推广
     * 接口说明：
     * 该接口返回大淘客高佣商品合集，高佣金高收益（佣金比例大于等于40%）
     *
     *
     * 接口版本号：v1.0.0
     *
     * @param param 参数
     * @return json
     */
    @GetMapping("/high-commission")
    @Operation(summary = "高佣精选")
    fun getHighCommission(param: HighCommissionParam): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.getHighCommission(param))
    }


    data class DetailAllDataResult(val detail: DtaokeBaseProduct,val  similarList: List<DtaokeBaseProduct>,val privilegeLink:PrivilegeLinkModel )
    /**
     * 详情页面全局所需数据
     *
     * @param productId 商品id
     * @return json
     */
    @GetMapping("/product-detail-all/{productId}")
    @Operation(summary = "详情页所需数据")
    fun getDetailAllData(@PathVariable(name = "productId") productId: String,relationId: String?): Result<DetailAllDataResult> {
        val detail = getDetail(DetailParamsModel(productId)).data ?: throw BizException(CommonEnum.NOT_FOUND)
        val param = SimilarParamsModel().apply {
            id = productId
            size = "10"
        }
        val similarList = getSimilarList(param).data
        val goodsId = detail.goodsId!!
        val privilegeLink = getPrivilegeLink(PrivilegeLinkParams(goodsId = goodsId).apply {
            channelId = relationId
        }).data
        return DetailAllDataResult(detail,similarList!!,privilegeLink!!).successResult("获取成功")
    }

    /**
     * 细分类目合集
     *
     * @param param 参数
     * @return json
     */
    @GetMapping("/sub-category")
    @Operation(summary = "细分类目合集")
    fun getSucCategory(param: SubCategoryParam): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.getSubCategory(param))
    }

    /**
     * 官方活动会场转链
     *
     * @param param         参数
     * @param bindingResult 参数验证结果
     * @return json
     */
    @GetMapping("/activity-link")
    @Operation(summary = "官方活动会场转链")
    fun getActivityProduct(param: @Valid ActivityParam?, bindingResult: BindingResult): Result<String> {
        bindingResult.verify()
        return dtkReqeestService.getData(dtkReqeestService.getActivityProducts(param))
    }

    /**
     * 京东商品批量转链
     *
     * @param promotionUnionConvert 参数
     * @param bindingResult         验证
     * @return json
     */
    @GetMapping("/jd-promotion-union-convert")
    @Operation(summary = "京东商品批量转链")
    fun jdGetPromotionUnionConvert(
        promotionUnionConvert: @Valid JDPromotionUnionConvert?,
        bindingResult: BindingResult
    ): Result<String> {
        bindingResult.verify()
        return  dtkReqeestService.getData(dtkReqeestService.jdGetPromotionUnionConvert(promotionUnionConvert))
    }

    /**
     * 京东链接解析
     *
     * @param url           转链url
     * @param bindingResult 验证
     * @return json
     */
    @GetMapping("/jd-parse-url")
    @Operation(summary = "京东链接解析")
    fun jdParseUrl(
        @RequestParam("url") @NotBlank(message = "请输入转链url") url:  String,
        bindingResult: BindingResult
    ): Result<String> {
        bindingResult.verify()
        return dtkReqeestService.getData(dtkReqeestService.jdParseUrl(url))
    }

    /**
     * 京东商品转链 (单个)
     *
     * @param params 参数
     * @return json
     */
    @GetMapping("/jd-promotion-union-convert-with-one")
    @Operation(summary = "京东商品转链 (单个)")
    fun jdPromotionUnionConvertWithOne(
        params: @Valid JDPromotionUnionConvertWithOneParams?,
        bindingResult: BindingResult
    ): Result<String> {
        bindingResult.verify()
        return dtkReqeestService.getData(
            dtkReqeestService.jdPromotionUnionConvertWithOne(
                params
            )
        )
    }

    /**
     * 拼多多商品转链
     *
     * @param param         参数
     * @param bindingResult 验证
     * @return json
     */
    @GetMapping("/pdd-goods-prom-generate")
    @Operation(summary = "拼多多商品转链")
    fun pddGoodsPromGenerate(param: @Valid PDDGoodsPromGenerateParam?, bindingResult: BindingResult): Result<String> {
        bindingResult.verify()
        return dtkReqeestService.getData(dtkReqeestService.pddGoodsPromGenerate(param))
    }

    /**
     * 淘宝订单查询
     *
     * @param param         参数
     * @param bindingResult 验证
     * @return json
     */
    @GetMapping("/tb-order-detail")
    @Operation(summary = "淘宝订单查询")
    fun getOrderDetails(param: @Valid OrderDetailParam?, bindingResult: BindingResult): Result<String> {
        bindingResult.verify()
        return dtkReqeestService.getData(dtkReqeestService.getOrderDetails(param!!))
    }

    /**
     * 淘系万能解析
     *
     * @param content 解析文本
     * @return json
     */
    @GetMapping("/tb-parse-content")
    @Operation(summary = "淘系万能解析")
    fun getTbParseContent(@RequestParam("content") content: String): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.getTbParseContent(content))
    }

    /**
     * 淘系万能解析
     *
     * @param content 解析文本
     * @return json
     */
    @GetMapping("/tb-parse-taokoulin")
    @Operation(summary = "淘系万能解析2")
    fun getTbParseTaokou(@RequestParam("content") content: String): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.getTbParseTaokouling(content))
    }

    /**
     * 获取京东成交订单
     *
     * @param param         参数
     * @param bindingResult 验证
     * @return json
     */
    @GetMapping("/get-official-order-list")
    @Operation(summary = "获取京东成交订单")
    fun getJdOfficiaOrderList(param: @Valid GetOfficialOrderListParam?, bindingResult: BindingResult): Result<String> {
        bindingResult.verify()
        return dtkReqeestService.getData(dtkReqeestService.getJdOfficiaOrderList(param))
    }

    /**
     * 优惠券查询
     *
     * @param content 二合一链接，淘口令，或同时输入商品+优惠券链接
     * @return json
     */
    @GetMapping("/get-tb-coupon-info")
    @Operation(summary = "优惠券查询")
    fun getTbCouponInfo(@RequestParam("content") content: String): Result<String> {

        return  dtkReqeestService.getData(dtkReqeestService.getTbCouponInfo(content))
    }

    /**
     * 超值买返转链
     *
     * @param param         参数
     * @param bindingResult 验证信息
     * @return json
     */
    @GetMapping("/czmf")
    @Operation(summary = "超值买返转链")
    fun getTbCzmf(param: @Valid CzmfParam?, bindingResult: BindingResult): Result<String> {
        bindingResult.verify()
        return dtkReqeestService.getData(dtkReqeestService.getTbCzmf(param))
    }

    /**
     * 拼多多搜索
     *
     * @param param 入参
     * @return json
     */
    @GetMapping("/pdd-search")
    @Operation(summary = "拼多多搜索")
    fun pddSearch(param: PddSearchParam?): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.pddSearch(param))
    }

    /**
     * 淘礼金创建
     *
     * @param param         参数
     * @param bindingResult 验证信息
     * @return json
     */
    @GetMapping("/tlj-create")
    @Operation(summary = "淘礼金创建")
    fun tljCreate(@Validated param: TaolijinCreateParam?, bindingResult: BindingResult): Result<String> {
        bindingResult.verify()
        return dtkReqeestService.getData(dtkReqeestService.tljCreate(param))
    }

    /**
     * 淘礼金商品
     *
     * @param param         参数
     * @param bindingResult 验证信息
     * @return json
     */
    @GetMapping("/tlj-products")
    @Operation(summary = "淘礼金商品")
    fun tljProducts(@Validated param: TaolijinProductsParam?, bindingResult: BindingResult): Result<TljResultModel> {
        bindingResult.verify()
        return dtkReqeestService.getData(dtkReqeestService.tljProducts(param)).daTaoKeStringCovert { Json.decodeFromString(TljResultModel.serializer(),it) }.successResult()
    }

    /**
     * 京东9块9包邮
     *
     * @return json
     */
    @GetMapping("/jd-nines")
    @Operation(summary = "京东9块9包邮")
    fun jdNinesList(pageId: Int, pageSize: Int, sort: Int): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.jdNinesList(pageId, pageSize, sort))
    }

    /**
     * 京东的商品详情
     *
     * @param sku sku
     * @return json
     */
    @GetMapping("/jd-detail")
    @Operation(summary = "京东的商品详情")
    fun jdProductDetail(sku: String): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.jdProductDetail(sku))
    }

    /**
     * 获取京东排行榜商品
     *
     * @param page     页
     * @param pageSize 页数量
     * @return json
     */
    @GetMapping("/jd-phb")
    @Operation(summary = "京东排行榜商品")
    fun jdPhb(page: Int, pageSize: Int): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.jdPhb(page, pageSize))
    }

    /**
     * 获取京东大牌折扣商品
     *
     * @param page     页
     * @param pageSize 页数量
     * @return json
     */
    @GetMapping("/jd-dpzk")
    @Operation(summary = "京东大牌折扣")
    fun jdDpzk(page: Int, pageSize: Int): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.jdDpzk(page, pageSize))
    }

    /**
     * 获取平多多的分类数据
     * @param parentId 父分类
     * @return 结果
     */
    @GetMapping("/pdd-category")
    @Operation(summary = "拼多多的分类数据")
    fun pddCategory(parentId: String): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.getPddCategory(parentId))
    }

    /**
     * 获取京东的分类数据
     * @param jdCategoryParam 参数
     * @return  json
     */
    @GetMapping("/jd-category")
    @Operation(summary = "京东的分类数据")
    fun getJdCategory(jdCategoryParam: JdCategoryParam?): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.getJdCategory(jdCategoryParam))
    }

    /**
     * 京东联盟搜索
     * @param params    参数
     * @return  json
     */
    @GetMapping("/jd-search")
    @Operation(summary = "京东联盟搜索")
    fun jdSearch(@RequestParam params: MutableMap<String, Any>): Result<String> {
        return dtkReqeestService.getData(dtkReqeestService.jdSearch(params))
    }

}
package shop.itbug.ticket.exception

enum class CommonEnum(
    /**
     * 错误码
     */
    private val errCode: Int,
    /**
     * 错误描述
     */
    private val errMessage: String
) : BaseErrorInfoInterface {
    // 数据操作错误定义
    SUCCESS(200, "成功!"), BODY_NOT_MATCH(400, "请求的数据格式不符!"), SIGNATURE_NOT_MATCH(401, "请先登录"), NOT_FOUND(
        404,
        "未找到该资源!"
    ),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!"),
    PARAMS_ERROR(
        505,
        "请求参数错误,或者缺少请求参数"
    ),
    PARAMS_VIOLATION_EXCEPTION(508, "请求参数验证失败"), RESOURCES_REPETITION(504, "数据已存在"), NO_AUTHORIZATION(
        403,
        "没有权限访问此资源"
    ),
    NO_AUTHORIZATION_HEADER(401, "无法获取用户信息"),
    NO_AUTHORIZATION_INVALID(401, "token过期,请重新登录"),
    TOKEN_INVALID(506, "不是有效的token"),
    EXCEPTION(500,"网络繁忙,请稍后重试"),
    ORDER_NOTFOUND_BY_TASK(308,"没有查询到订单")
    ;

    /**
     * 错误码
     *
     * @return code
     */
    override fun errCode(): Int {
        return errCode
    }

    /**
     * 错误信息
     *
     * @return message
     */
    override fun errMessage(): String {
        return errMessage
    }
}
package shop.itbug.ticket.entry


open class DTKResultJson {
    open var time: Long? = null
    open var code: Int? = null
    open var msg: String? = null
    open var data: String? = null
    open val message: String? = null
}

fun DTKResultJson.getMsg() : String {
    if(msg!=null){
        return msg!!
    }
    if(message!=null){
        return message!!
    }
    return  "服务繁忙,请稍后重试"
}
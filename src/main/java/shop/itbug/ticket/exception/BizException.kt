package shop.itbug.ticket.exception

import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.ResultJSON
import java.io.Serial


fun Boolean.tureToNull(): Boolean? {
    if (this) {
        return null
    }
    return false
}

enum class ResultDialogType {
    Toast, Dialog, Notice,None,FinalToast,FinalDialog
}
/**
 * @author ldd
 */
class BizException : RuntimeException {
    var errCode = 0
    var errMessage: String? = null
    var data: Any? = null
    var type: ResultDialogType = ResultDialogType.Toast

    constructor() : super()

    constructor(errorMsg: String) {
        errCode = 10099
        errMessage = errorMsg
        type = ResultDialogType.FinalDialog
    }

    constructor(msg: String,resultDialogType: ResultDialogType) {
        errCode = 10099
        errMessage = msg
        type = resultDialogType
    }

    constructor(e: Exception) {
        errCode = 10098
        e.printStackTrace()
        errMessage = e.localizedMessage
        type = ResultDialogType.Dialog
    }


    constructor(errorMsg: String, data: Any) : this(ResultJSON(10099, errorMsg, data))
    constructor(errorMsg: String, data: Any,toastType: ResultDialogType = ResultDialogType.Toast) : this(ResultJSON(10099, errorMsg, data,toastType))
    constructor(result: Result<*>?) : super(result?.message) {
        if (result != null) {
            errCode = result.state
        }
        if (result != null) {
            errMessage = result.message
        }
        data = result?.data
        type = result?.type ?: ResultDialogType.Toast
    }

    constructor(commonEnum: CommonEnum) : super(commonEnum.errMessage()) {
        errCode = commonEnum.errCode()
        errMessage = commonEnum.errMessage()
    }
    constructor(commonEnum: CommonEnum,data: Any) : super(commonEnum.errMessage()) {
        errCode = commonEnum.errCode()
        errMessage = commonEnum.errMessage()
        this.data = data
    }

    constructor(errorCode: Int, errorMsg: String?) : super(errorMsg) {
        errCode = errorCode
        errMessage = errorMsg
    }

    companion object {
        @Serial
        private val serialVersionUID = 4068276900912868826L
    }

    override fun getLocalizedMessage(): String {
        return "code:$errCode,msg:$errMessage"
    }
}

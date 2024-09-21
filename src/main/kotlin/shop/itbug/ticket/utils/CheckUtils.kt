package shop.itbug.ticket.utils

/**
 * 密码验证合格程序
 *
 */
object CheckStrUtils {


    /**
     * 验证密码合法性
     *
     * @param password
     * @return
     */
    fun verifyPassword(password: String?): Boolean {
        // 长度超过8位
        if (password == null || password.length <= 8) {
            return false
        }
        return checkCharTypes(password) && checkRepeatSubstring(password)
    }

    /**
     * 包括大小写字母、数字、其它符号,以上四种至少三种
     *
     * @param password
     * @return
     */
    private fun checkCharTypes(password: String): Boolean {
        var upperCase = 0
        var lowerCase = 0
        var digit = 0
        var other = 0
        for (ch in password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upperCase = 1
            } else if (Character.isLowerCase(ch)) {
                lowerCase = 1
            } else if (Character.isDigit(ch)) {
                digit = 1
            } else {
                other = 1
            }
        }
        return upperCase + lowerCase + digit + other >= 3
    }

    /**
     * 不能有相同长度超2的子串重复
     *
     * @param password
     * @return
     */
    private fun checkRepeatSubstring(password: String): Boolean {
        for (i in 0 until password.length - 3) {
            val s = password.substring(i, i + 3)
            val tempStr = password.substring(i + 3, password.length)
            if (tempStr.contains(s)) {
                return false
            }
        }
        return true
    }
}

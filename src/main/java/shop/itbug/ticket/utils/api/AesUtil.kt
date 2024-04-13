package shop.itbug.ticket.utils.api

import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.io.UnsupportedEncodingException
import java.security.Security
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * aes 加解密工具
 */
object AesUtil {
    private const val ALGORITHM = "AES/CBC/PKCS5Padding"
    private const val CHAT_SET_NAME = "UTF-8"
    private const val KEY = "76CAA1C88F7F8D1D"
    private const val IV = "91129048100F0494"

    init {
        Security.addProvider(BouncyCastleProvider())
    }

    /**
     * aes加密
     *
     * @param content 要加密的内容
     * @return 加密后数据
     */
    fun encrypt(content: String): String? {
        return try {
            val cipher = Cipher.getInstance(ALGORITHM)
            val keySpec = SecretKeySpec(KEY.toByteArray(charset(CHAT_SET_NAME)), "AES")
            val paramSpec: AlgorithmParameterSpec = IvParameterSpec(IV.toByteArray())
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec)
            ParseSystemUtil.parseByte2HexStr(cipher.doFinal(content.toByteArray(charset(CHAT_SET_NAME))))
        } catch (e: Exception) {
            null
        }
    }

    /**
     * base64 编码
     *
     * @param str 字符串
     * @return 编码后的结果
     */
    fun base64Encode(str: String): String? {
        val asB64: String? = try {
            java.util.Base64.getEncoder().encodeToString(str.toByteArray(charset(CHAT_SET_NAME)))
        } catch (e: UnsupportedEncodingException) {
            return null
        }
        return asB64
    }
}
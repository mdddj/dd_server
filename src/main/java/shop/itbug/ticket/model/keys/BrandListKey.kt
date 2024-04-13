package shop.itbug.ticket.model.keys

import shop.itbug.ticket.entry.abs.RedisKey

// 品牌列表
class BrandListKey(key: String) : RedisKey(key) {
    companion object {
        const val KEY = "breand_list_key:"
    }
}
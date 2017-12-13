package lz.renatkaitmazov.juntotesttask.data.model.product

import com.google.gson.annotations.SerializedName

/**
 *
 * @author Renat Kaitmazov
 */
data class ProductResponse(@SerializedName("posts") val products: List<ProductEntity>)
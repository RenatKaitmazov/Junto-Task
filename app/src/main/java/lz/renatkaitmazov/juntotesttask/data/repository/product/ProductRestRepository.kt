package lz.renatkaitmazov.juntotesttask.data.repository.product

import io.reactivex.Flowable
import lz.renatkaitmazov.juntotesttask.data.model.product.Product

/**
 *
 * @author Renat Kaitmazov
 */
interface ProductRestRepository {

    fun getTodayProducts(topicSlug: String): Flowable<List<Product>>

    fun clearCache()
}
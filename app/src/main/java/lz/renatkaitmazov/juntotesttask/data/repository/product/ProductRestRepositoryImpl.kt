package lz.renatkaitmazov.juntotesttask.data.repository.product

import android.support.v4.util.LruCache
import io.reactivex.Flowable
import lz.renatkaitmazov.juntotesttask.data.model.product.Product
import lz.renatkaitmazov.juntotesttask.data.model.product.ProductMapper
import retrofit2.Retrofit

/**
 *
 * @author Renat Kaitmazov
 */
class ProductRestRepositoryImpl(retrofit: Retrofit,
                                private val productMapper: ProductMapper,
                                private val cache: LruCache<String, List<Product>>) : ProductRestRepository {

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    private val restApi = retrofit.create(ProductRestApi::class.java)

    /*------------------------------------------------------------------------*/
    /* ProductRestRepository implementation                                   */
    /*------------------------------------------------------------------------*/

    override fun getTodayProducts(topicSlug: String): Flowable<List<Product>> {
        val cachedProducts = cache.get(topicSlug)
        if (cachedProducts != null) {
            return Flowable.just(cachedProducts)
        }
        return restApi.getTodayProducts(topicSlug)
                .map { it.products }
                .map(productMapper::map)
                .doOnNext { cache.put(topicSlug, it)}
    }

    override fun refreshTodayProducts(topicSlug: String): Flowable<List<Product>> {
        cache.remove(topicSlug)
        return getTodayProducts(topicSlug)
    }
}
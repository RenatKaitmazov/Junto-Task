package lz.renatkaitmazov.juntotesttask.data.repository.product

import io.reactivex.Flowable
import lz.renatkaitmazov.juntotesttask.data.model.product.Product
import lz.renatkaitmazov.juntotesttask.data.model.product.ProductMapper
import retrofit2.Retrofit

/**
 *
 * @author Renat Kaitmazov
 */
class ProductRestRepositoryImpl(retrofit: Retrofit,
                                private val productMapper: ProductMapper) : ProductRestRepository {

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    private val restApi = retrofit.create(ProductRestApi::class.java)

    /*------------------------------------------------------------------------*/
    /* ProductRestRepository implementation                                   */
    /*------------------------------------------------------------------------*/

    override fun getTodayProducts(topicSlug: String): Flowable<List<Product>> {
        return restApi.getTodayProducts(topicSlug)
                .map { it.products }
                .map(productMapper::map)
    }

    override fun clearCache() {

    }
}
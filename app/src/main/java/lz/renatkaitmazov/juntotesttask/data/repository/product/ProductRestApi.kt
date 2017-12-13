package lz.renatkaitmazov.juntotesttask.data.repository.product

import io.reactivex.Flowable
import lz.renatkaitmazov.juntotesttask.data.model.product.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * @author Renat Kaitmazov
 */

interface ProductRestApi {

    @GET("/v1/categories/{topic_slug}/posts")
    fun getTodayProducts(@Path("topic_slug") topicSlug: String): Flowable<ProductResponse>
}
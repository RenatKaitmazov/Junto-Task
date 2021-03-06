package lz.renatkaitmazov.juntotesttask.data.repository.topic

import io.reactivex.Flowable
import lz.renatkaitmazov.juntotesttask.data.model.topic.TopicResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * @author Renat Kaitmazov
 */
interface TopicRestApi {

    @GET("/v1/topics")
    fun getTrendingTopics(@Query("search[trending]") isTrending: Boolean,
                          @Query("per_page") count: Int): Flowable<TopicResponse>
}
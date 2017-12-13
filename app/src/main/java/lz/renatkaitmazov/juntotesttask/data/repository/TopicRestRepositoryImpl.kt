package lz.renatkaitmazov.juntotesttask.data.repository

import io.reactivex.Flowable
import lz.renatkaitmazov.juntotesttask.data.model.Topic
import lz.renatkaitmazov.juntotesttask.data.model.TopicResponse
import retrofit2.Retrofit

/**
 *
 * @author Renat Kaitmazov
 */
class TopicRestRepositoryImpl(retrofit: Retrofit) : TopicRestRepository {

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    private val topicApi = retrofit.create(TopicRestApi::class.java)

    /*------------------------------------------------------------------------*/
    /* TopicRestRepository implementation                                     */
    /*------------------------------------------------------------------------*/

    override fun getTrendingTopics(): Flowable<List<Topic>> {
        return topicApi.getTrendingTopics(true, 10)
                .map(TopicResponse::topics)
    }

}
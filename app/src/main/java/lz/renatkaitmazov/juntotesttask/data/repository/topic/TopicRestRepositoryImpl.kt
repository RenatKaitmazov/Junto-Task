package lz.renatkaitmazov.juntotesttask.data.repository.topic

import io.reactivex.Flowable
import lz.renatkaitmazov.juntotesttask.data.model.topic.Topic
import lz.renatkaitmazov.juntotesttask.data.model.topic.TopicResponse
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
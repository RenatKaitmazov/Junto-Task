package lz.renatkaitmazov.juntotesttask.data.repository.topic

import android.support.v4.util.LruCache
import io.reactivex.Flowable
import lz.renatkaitmazov.juntotesttask.data.model.topic.Topic
import lz.renatkaitmazov.juntotesttask.data.model.topic.TopicResponse
import retrofit2.Retrofit

/**
 *
 * @author Renat Kaitmazov
 */
class TopicRestRepositoryImpl(retrofit: Retrofit,
                              private val cache: LruCache<String, List<Topic>>) : TopicRestRepository {

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    private val topicApi = retrofit.create(TopicRestApi::class.java)

    /*------------------------------------------------------------------------*/
    /* TopicRestRepository implementation                                     */
    /*------------------------------------------------------------------------*/

    override fun getTrendingTopics(): Flowable<List<Topic>> {
        val key = "trending_topics"
        val cachedTopics = cache.get(key)
        if (cachedTopics != null) {
            return Flowable.just(cachedTopics)
        }
        return topicApi.getTrendingTopics(true, 10)
                .map(TopicResponse::topics)
                .doOnNext { cache.put(key, it) }
    }

}
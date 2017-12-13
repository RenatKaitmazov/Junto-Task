package lz.renatkaitmazov.juntotesttask.data.repository.topic

import io.reactivex.Flowable
import lz.renatkaitmazov.juntotesttask.data.model.topic.Topic

/**
 *
 * @author Renat Kaitmazov
 */
interface TopicRestRepository {
    fun getTrendingTopics(): Flowable<List<Topic>>
}
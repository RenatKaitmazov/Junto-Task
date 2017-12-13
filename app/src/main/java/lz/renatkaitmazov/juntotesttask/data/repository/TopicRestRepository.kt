package lz.renatkaitmazov.juntotesttask.data.repository

import io.reactivex.Flowable
import lz.renatkaitmazov.juntotesttask.data.model.Topic

/**
 *
 * @author Renat Kaitmazov
 */
interface TopicRestRepository {
    fun getTrendingTopics(): Flowable<List<Topic>>
}
package lz.renatkaitmazov.juntotesttask.productlist

import lz.renatkaitmazov.juntotesttask.base.LoadingView
import lz.renatkaitmazov.juntotesttask.data.model.Topic

/**
 *
 * @author Renat Kaitmazov
 */
interface ProductListView : LoadingView {

    /**
     * Shows a list of trending topics.
     */
    fun showTrendingTopics(topics: List<Topic>)

    /**
     * Shows an error arisen when fetching data.
     */
    fun showError(error: Throwable)
}
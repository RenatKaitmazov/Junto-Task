package lz.renatkaitmazov.juntotesttask.productlist

import lz.renatkaitmazov.juntotesttask.base.BasePresenter

/**
 *
 * @author Renat Kaitmazov
 */
interface ProductListPresenter : BasePresenter<ProductListView> {

    /**
     * Returns a list of trending topics of size 10.
     */
    fun getTrendingTopics()

    /**
     * Returns a list of products by the specified topic posted today.
     * The default topic is "Tech".
     */
    fun getTodayProducts(topicSlug: String)

    /**
     * Clears cached data and makes a new request to the server to get
     * today's list of products.
     */
    fun refreshTodayProducts(topicSlug: String)
}
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
}
package lz.renatkaitmazov.juntotesttask.productlist

import lz.renatkaitmazov.juntotesttask.base.LoadingView
import lz.renatkaitmazov.juntotesttask.data.model.product.Product
import lz.renatkaitmazov.juntotesttask.data.model.topic.Topic

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
     * Shows a list of products for today.
     */
    fun showTodayProducts(products: List<Product>)

    /**
     * Shows an error arisen when fetching data.
     */
    fun showError(error: Throwable)
}
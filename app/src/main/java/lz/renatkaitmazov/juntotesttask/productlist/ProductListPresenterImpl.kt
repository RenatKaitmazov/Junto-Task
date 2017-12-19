package lz.renatkaitmazov.juntotesttask.productlist

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import lz.renatkaitmazov.juntotesttask.base.AbstractPresenter
import lz.renatkaitmazov.juntotesttask.data.model.product.Product
import lz.renatkaitmazov.juntotesttask.data.repository.product.ProductRestRepository
import lz.renatkaitmazov.juntotesttask.data.repository.topic.TopicRestRepository

/**
 *
 * @author Renat Kaitmazov
 */
class ProductListPresenterImpl(view: ProductListView,
                               private val topicRepository: TopicRestRepository,
                               private val productRestRepository: ProductRestRepository) :
        AbstractPresenter<ProductListView>(view),
        ProductListPresenter {

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    private val disposables = CompositeDisposable()

    /*------------------------------------------------------------------------*/
    /* ProductListPresenter implementation                                    */
    /*------------------------------------------------------------------------*/

    override fun getTrendingTopics() {
        view?.showProgress()
        val disposable = topicRepository.getTrendingTopics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view?.hideProgress() }
                .subscribe(
                        success@ { view?.showTrendingTopics(it) },
                        error@ { view?.showError(it) }
                )
        disposables.add(disposable)
    }

    override fun getTodayProducts(topicSlug: String) {
        getTodayProducts(topicSlug, productRestRepository::getTodayProducts)
    }

    override fun refreshTodayProducts(topicSlug: String) {
        getTodayProducts(topicSlug, productRestRepository::refreshTodayProducts)
    }

    /*------------------------------------------------------------------------*/
    // Overridden methods
    /*------------------------------------------------------------------------*/

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    /*------------------------------------------------------------------------*/
    /* Helper Methods                                                         */
    /*------------------------------------------------------------------------*/

    private fun getTodayProducts(topicSlug: String, task: (String) -> Flowable<List<Product>>) {
        // There is no need in showing additional progress view, because the SwipeRefreshLayout
        // has its own progress bar that will shown automatically.
        view?.showProgress()
        val disposable = task.invoke(topicSlug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view?.hideProgress() }
                .subscribe(
                        success@ { view?.showTodayProducts(it) },
                        error@ { view?.showError(it) }
                )
        disposables.add(disposable)
    }
}
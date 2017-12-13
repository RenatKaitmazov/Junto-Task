package lz.renatkaitmazov.juntotesttask.productlist

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import lz.renatkaitmazov.juntotesttask.base.AbstractPresenter
import lz.renatkaitmazov.juntotesttask.data.repository.TopicRestRepository

/**
 *
 * @author Renat Kaitmazov
 */
class ProductListPresenterImpl(view: ProductListView,
                               private val topicRepository: TopicRestRepository) :
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

    /*------------------------------------------------------------------------*/
    // Overridden methods
    /*------------------------------------------------------------------------*/

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}
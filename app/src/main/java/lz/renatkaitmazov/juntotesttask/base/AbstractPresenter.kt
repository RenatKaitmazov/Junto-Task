package lz.renatkaitmazov.juntotesttask.base

/**
 * An abstraction of a presenter.
 * Provides the very basic API which is destroying a view
 * associated with the presenter.
 * Any concrete presenter associated with a concrete view should extend this class.
 *
 * @author Renat Kaitmazov
 */

abstract class AbstractPresenter<V : MvpView>(view: V?) {

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    var view: V? = view
        private set

    /*------------------------------------------------------------------------*/
    /* API                                                                    */
    /*------------------------------------------------------------------------*/

    open fun onDestroy() {
        view = null
    }
}
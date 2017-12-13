package lz.renatkaitmazov.juntotesttask.base

/**
 * @author Renat Kaitmazov
 */
interface BasePresenter<V : MvpView> {

    /**
     * Perform clean-up.
     */
    fun onDestroy()
}
package lz.renatkaitmazov.juntotesttask.base

/**
 * Every MVP view which performs a long running operation should implement this interface.
 *
 * @author Renat Kaitmazov
 */
interface LoadingView : MvpView {
    /**
     * Show progress while fetching data to be shown on the screen.
     */
    fun showProgress()

    /**
     * Hide progress data is fetched and ready to be shown to the user.
     */
    fun hideProgress()
}
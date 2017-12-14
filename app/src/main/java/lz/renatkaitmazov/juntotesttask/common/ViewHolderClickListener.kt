package lz.renatkaitmazov.juntotesttask.common

import android.view.View

/**
 * A click listener for view holders used by recycler view adapters.
 *
 * @author Renat Kaitmazov
 */
interface ViewHolderClickListener {
    fun onViewHolderClicked(view: View, position: Int)
}
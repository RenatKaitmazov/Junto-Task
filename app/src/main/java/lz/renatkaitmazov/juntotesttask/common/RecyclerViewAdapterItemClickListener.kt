package lz.renatkaitmazov.juntotesttask.common

/**
 * A click listener for adapters used by recycler views.
 *
 * @author Renat Kaitmazov
 */
interface RecyclerViewAdapterItemClickListener<in T> {
    fun onAdapterItemClicked(item: T)
}
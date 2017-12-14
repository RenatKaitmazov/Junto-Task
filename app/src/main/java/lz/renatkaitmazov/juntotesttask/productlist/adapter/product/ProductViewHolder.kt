package lz.renatkaitmazov.juntotesttask.productlist.adapter.product

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_product.view.*
import lz.renatkaitmazov.juntotesttask.common.ViewHolderClickListener

/**
 *
 * @author Renat Kaitmazov
 */
class ProductViewHolder(view: View, private val listener: ViewHolderClickListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    val thumbnailImageView = view.productThumbnail
    val productNameTextView = view.productNameTextView
    val productDescriptionTextView = view.productDescriptionTextView
    val upVoteTextView = view.upVoteTextView

    /*------------------------------------------------------------------------*/
    /* Initialization                                                         */
    /*------------------------------------------------------------------------*/

    init {
        view.setOnClickListener(this)
    }

    /*------------------------------------------------------------------------*/
    /* View.OnClickListener implementation                                    */
    /*------------------------------------------------------------------------*/

    override fun onClick(view: View) {
        listener.onViewHolderClicked(view, adapterPosition)
    }
}
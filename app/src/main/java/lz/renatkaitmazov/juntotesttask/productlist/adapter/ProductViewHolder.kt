package lz.renatkaitmazov.juntotesttask.productlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_product.view.*

/**
 *
 * @author Renat Kaitmazov
 */
class ProductViewHolder(view: View, private val listener: ProductItemClickListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

    /*------------------------------------------------------------------------*/
    /* Interfaces                                                             */
    /*------------------------------------------------------------------------*/

    interface ProductItemClickListener {
        fun onProductItemClicked(view: View, position: Int)
    }

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    val thumbnailImageView = view.productThumbnail
    val productNameTextView = view.productNameTextView
    val productDescriptionTextView = view.productDescriptionTextView
    val upVoteTextView = view.upVoteTextView

    init {
        view.setOnClickListener(this)
    }

    /*------------------------------------------------------------------------*/
    /* View.OnClickListener implementation                                    */
    /*------------------------------------------------------------------------*/

    override fun onClick(view: View) {
        listener.onProductItemClicked(view, adapterPosition)
    }
}
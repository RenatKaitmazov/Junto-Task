package lz.renatkaitmazov.juntotesttask.productlist.adapter.topic

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_topic.view.*
import lz.renatkaitmazov.juntotesttask.common.ViewHolderClickListener

/**
 *
 * @author Renat Kaitmazov
 */
class TopicViewHolder(view: View, private val listener: ViewHolderClickListener) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener {

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    val thumbnail = view.topicThumbnail
    val nameTextView = view.topicNameTextView
    val description = view.topicDescriptionTextView
    val followersCount = view.followersCountTextView
    val postsCount = view.postsCountTextView

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
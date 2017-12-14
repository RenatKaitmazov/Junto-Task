package lz.renatkaitmazov.juntotesttask.productlist.adapter.topic

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import lz.renatkaitmazov.juntotesttask.R
import lz.renatkaitmazov.juntotesttask.common.RecyclerViewAdapterItemClickListener
import lz.renatkaitmazov.juntotesttask.common.ViewHolderClickListener
import lz.renatkaitmazov.juntotesttask.data.model.topic.Topic

/**
 *
 * @author Renat Kaitmazov
 */

class TopicAdapter(private val topics: ArrayList<Topic>,
                   private val listener: RecyclerViewAdapterItemClickListener<Topic>) :
        RecyclerView.Adapter<TopicViewHolder>(),
        ViewHolderClickListener {

    /*------------------------------------------------------------------------*/
    /* RecyclerView.Adapter implementation                                    */
    /*------------------------------------------------------------------------*/

    override fun getItemCount() = topics.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val ctx = parent.context
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_topic, parent, false)
        return TopicViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = topics[position]
        holder.nameTextView.text = topic.name
        holder.description.text = topic.description
        holder.followersCount.text = topic.followersCount
        holder.postsCount.text = topic.postsCount
        val ctx = holder.itemView.context.applicationContext
        Glide.with(ctx)
                .load(topic.imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.thumbnail)
    }

    /*------------------------------------------------------------------------*/
    /* ViewHolderClickListener implementation                                 */
    /*------------------------------------------------------------------------*/

    override fun onViewHolderClicked(view: View, position: Int) {
        val item = topics[position]
        listener.onAdapterItemClicked(item)
    }
}
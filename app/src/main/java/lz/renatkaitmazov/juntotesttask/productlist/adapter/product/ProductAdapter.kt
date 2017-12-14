package lz.renatkaitmazov.juntotesttask.productlist.adapter.product

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
import lz.renatkaitmazov.juntotesttask.data.model.product.Product

/**
 *
 * @author Renat Kaitmazov
 */
class ProductAdapter(private val listener: RecyclerViewAdapterItemClickListener<Product>) :
        RecyclerView.Adapter<ProductViewHolder>(),
        ViewHolderClickListener {

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    private val products: MutableList<Product> = ArrayList()

    /*------------------------------------------------------------------------*/
    /* RecyclerView.Adapter implementation                                    */
    /*------------------------------------------------------------------------*/

    override fun getItemCount() = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val ctx = parent.context
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.productNameTextView.text = product.name
        holder.productDescriptionTextView.text = product.description
        holder.upVoteTextView.text = product.upVotes

        val ctx = holder.itemView.context.applicationContext
        val thumbnailUrl = product.thumbnailUrl
        Glide.with(ctx)
                .load(thumbnailUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.thumbnailImageView)
    }

    /*------------------------------------------------------------------------*/
    /* RecyclerViewAdapterItemClickListener implementation                    */
    /*------------------------------------------------------------------------*/

    override fun onViewHolderClicked(view: View, position: Int) {
        val item = products[position]
        listener.onAdapterItemClicked(item)
    }

    /*------------------------------------------------------------------------*/
    /* API                                                                    */
    /*------------------------------------------------------------------------*/

    fun update(newProducts: List<Product>) {
        products.clear()
        products.addAll(newProducts)
        notifyDataSetChanged()
    }
}
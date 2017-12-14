package lz.renatkaitmazov.juntotesttask.productdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_product_detail.view.*
import lz.renatkaitmazov.juntotesttask.R
import lz.renatkaitmazov.juntotesttask.base.RetainableFragment
import lz.renatkaitmazov.juntotesttask.data.model.product.Product

/**
 *
 * @author Renat Kaitmazov
 */
class ProductDetailFragment : RetainableFragment() {

    /*------------------------------------------------------------------------*/
    /* Static                                                                 */
    /*------------------------------------------------------------------------*/

    companion object {
        /*------------------------------------------------------------------------*/
        /* Constants                                                              */
        /*------------------------------------------------------------------------*/

        private const val KEY_ARG_PRODUCT = "KEY_ARG_PRODUCT"

        /*------------------------------------------------------------------------*/
        /* API                                                                    */
        /*------------------------------------------------------------------------*/

        fun newInstance(product: Product): ProductDetailFragment {
            val args = Bundle(1)
            args.putParcelable(KEY_ARG_PRODUCT, product)
            val fragment = ProductDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    private lateinit var product: Product

    /*------------------------------------------------------------------------*/
    /* Lifecycle Events                                                       */
    /*------------------------------------------------------------------------*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments!!
        product = args.getParcelable(KEY_ARG_PRODUCT)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)!!
        view.productNameTextView.text = product.name
        view.upVoteTextView.text = product.upVotes
        view.productDescriptionTextView.text = product.description
        Glide.with(activity!!.applicationContext)
                .load(product.largeImgUrl)
                .into(view.productImageView)
        setUpGetButton(view.getProductButton)
        return view
    }

    /*------------------------------------------------------------------------*/
    /* Helper Methods                                                         */
    /*------------------------------------------------------------------------*/

    private fun setUpGetButton(button: Button) {
        val productUrl = product.productLink
        button.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(productUrl))
            val packageManager = activity!!.packageManager
            if (browserIntent.resolveActivity(packageManager) != null) {
                val chooserTitle = getString(R.string.chooser_title)
                val chooserIntent = Intent.createChooser(browserIntent, chooserTitle)
                startActivity(chooserIntent)
            } else {
                Toast.makeText(activity, R.string.error_view_link, Toast.LENGTH_LONG).show()
            }
        }
    }

    /*------------------------------------------------------------------------*/
    /* RetainableFragment implementation                                      */
    /*------------------------------------------------------------------------*/

    override fun getLayoutResId() = R.layout.fragment_product_detail
}
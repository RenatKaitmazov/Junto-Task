package lz.renatkaitmazov.juntotesttask.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import lz.renatkaitmazov.juntotesttask.base.SingleFragmentActivity
import lz.renatkaitmazov.juntotesttask.data.model.product.Product

/**
 *
 * @author Renat Kaitmazov
 */
class ProductDetailActivity : SingleFragmentActivity() {

    /*------------------------------------------------------------------------*/
    /* Static                                                                 */
    /*------------------------------------------------------------------------*/

    companion object {

        /*------------------------------------------------------------------------*/
        /* Constants                                                              */
        /*------------------------------------------------------------------------*/

        private const val KEY_EXTRA_PRODUCT = "KEY_EXTRA_PRODUCT"

        /*------------------------------------------------------------------------*/
        /* API                                                                    */
        /*------------------------------------------------------------------------*/

        fun newIntent(context: Context, product: Product): Intent {
            return Intent(context, ProductDetailActivity::class.java)
                    .putExtra(KEY_EXTRA_PRODUCT, product)
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
        // First, retrieve data which will be passed to the associated fragment.
        product = intent.getParcelableExtra(KEY_EXTRA_PRODUCT) as Product
        // Add the fragment onto the fragment manager.
        super.onCreate(savedInstanceState)
    }

    /*------------------------------------------------------------------------*/
    /* SingleFragmentActivity implementation                                  */
    /*------------------------------------------------------------------------*/

    override fun getFragment() = ProductDetailFragment.newInstance(product)
}
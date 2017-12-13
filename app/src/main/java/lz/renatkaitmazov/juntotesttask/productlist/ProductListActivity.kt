package lz.renatkaitmazov.juntotesttask.productlist

import lz.renatkaitmazov.juntotesttask.base.SingleFragmentActivity

class ProductListActivity : SingleFragmentActivity() {

    /*------------------------------------------------------------------------*/
    /* SingleFragmentActivity implementation                                  */
    /*------------------------------------------------------------------------*/

    override fun getFragment() = ProductListFragment.newInstance()
}

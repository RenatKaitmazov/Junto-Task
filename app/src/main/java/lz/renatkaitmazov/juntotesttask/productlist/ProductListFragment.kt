package lz.renatkaitmazov.juntotesttask.productlist

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.fragment_product_list.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import lz.renatkaitmazov.juntotesttask.JuntoApp
import lz.renatkaitmazov.juntotesttask.R
import lz.renatkaitmazov.juntotesttask.base.RetainableFragment
import lz.renatkaitmazov.juntotesttask.data.model.Topic
import lz.renatkaitmazov.juntotesttask.di.fragment.ProductListFragmentModule
import javax.inject.Inject

/**
 *
 * @author Renat Kaitmazov
 */
class ProductListFragment : RetainableFragment(), ProductListView {

    /*------------------------------------------------------------------------*/
    /* Static                                                                 */
    /*------------------------------------------------------------------------*/

    companion object {

        /*------------------------------------------------------------------------*/
        /* API                                                                    */
        /*------------------------------------------------------------------------*/

        fun newInstance() = ProductListFragment()
    }

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    lateinit var refreshLayout: SwipeRefreshLayout
        private set

    lateinit var productRecyclerView: RecyclerView
        private set

    lateinit var progressBar: ProgressBar
        private set

    @Inject
    @JvmField
    var presenter: ProductListPresenter? = null

    /*------------------------------------------------------------------------*/
    /* Lifecycle Events                                                       */
    /*------------------------------------------------------------------------*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        JuntoApp.get(activity as Context)
                .appComponent
                .plus(ProductListFragmentModule(this))
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)!!
        setUpToolbar(view.includedToolbar as Toolbar)
        refreshLayout = view.refreshLayout
        setUpSwipeRefreshLayout(refreshLayout)
        productRecyclerView = view.productRecyclerView
        setUpProductRecyclerView(productRecyclerView)
        progressBar = view.progressBar
        return view
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    /*------------------------------------------------------------------------*/
    /* RetainableFragment implementation                                      */
    /*------------------------------------------------------------------------*/

    override fun getLayoutResId() = R.layout.fragment_product_list

    /*------------------------------------------------------------------------*/
    /* ProductListView implementation                                         */
    /*------------------------------------------------------------------------*/

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showError(error: Throwable) {
        Log.e("ProductListFragment", error.message)
    }

    override fun showTrendingTopics(topics: List<Topic>) {
        Log.i("ProductListFragment", topics.toString())
    }

    /*------------------------------------------------------------------------*/
    /* Helper Methods                                                         */
    /*------------------------------------------------------------------------*/

    private fun setUpToolbar(toolbar: Toolbar) {
        val parentActivity = activity as AppCompatActivity
        parentActivity.setSupportActionBar(toolbar)
        // Reset the toolbar's intrinsic title, because we use a custom one.
        parentActivity.supportActionBar!!.title = null
        // Set our own clickable title.
        toolbar.toolbarTitle.setText(R.string.default_category)
        toolbar.toolbarTitle.setOnClickListener {
            if (!refreshLayout.isRefreshing) {
                presenter?.getTrendingTopics()
            }
        }
    }

    private fun setUpSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout) {
        val ctx = activity as Context
        val darkRed = ContextCompat.getColor(ctx, android.R.color.holo_red_dark)
        val darkGreen = ContextCompat.getColor(ctx, android.R.color.holo_green_light)
        val purple = ContextCompat.getColor(ctx, android.R.color.holo_purple)
        swipeRefreshLayout.setColorSchemeColors(darkRed, darkGreen, purple)
        swipeRefreshLayout.setOnRefreshListener {
            val isProgressBarRefreshing = progressBar.visibility == View.VISIBLE
            if (!swipeRefreshLayout.isRefreshing && !isProgressBarRefreshing) {
                swipeRefreshLayout.isRefreshing = true
                // TODO: fetch data from the server
            }
        }
    }

    private fun setUpProductRecyclerView(recyclerView: RecyclerView) {
        val linearLayoutManager = LinearLayoutManager(activity)
        // The size of the recycler view won't change. The flag improves performance.
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager
    }
}
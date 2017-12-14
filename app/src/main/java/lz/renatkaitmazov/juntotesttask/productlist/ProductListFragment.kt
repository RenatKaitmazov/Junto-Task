package lz.renatkaitmazov.juntotesttask.productlist

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_product_list.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import lz.renatkaitmazov.juntotesttask.JuntoApp
import lz.renatkaitmazov.juntotesttask.R
import lz.renatkaitmazov.juntotesttask.base.RetainableFragment
import lz.renatkaitmazov.juntotesttask.common.ItemDividerDecoration
import lz.renatkaitmazov.juntotesttask.common.RecyclerViewAdapterItemClickListener
import lz.renatkaitmazov.juntotesttask.data.model.product.Product
import lz.renatkaitmazov.juntotesttask.data.model.topic.Topic
import lz.renatkaitmazov.juntotesttask.di.fragment.ProductListFragmentModule
import lz.renatkaitmazov.juntotesttask.productdetail.ProductDetailActivity
import lz.renatkaitmazov.juntotesttask.productlist.adapter.product.ProductAdapter
import javax.inject.Inject

/**
 *
 * @author Renat Kaitmazov
 */
class ProductListFragment :
        RetainableFragment(),
        ProductListView,
        RecyclerViewAdapterItemClickListener<Product>,
        TopicListDialogFragment.OnTopicSelectedListener {

    /*------------------------------------------------------------------------*/
    /* Static                                                                 */
    /*------------------------------------------------------------------------*/

    companion object {

        /*------------------------------------------------------------------------*/
        /* Constants                                                              */
        /*------------------------------------------------------------------------*/

        private const val REQUEST_KEY = 100
        private const val TOPIC_LIST_FRAGMENT_TAG = "TopicListFragment"

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

    private lateinit var productRecyclerView: RecyclerView

    private lateinit var productAdapter: ProductAdapter

    lateinit var progressBar: ProgressBar
        private set

    @Inject
    @JvmField
    var presenter: ProductListPresenter? = null

    private lateinit var topicSlug: String

    private lateinit var topicName: String

    /*------------------------------------------------------------------------*/
    /* Lifecycle Events                                                       */
    /*------------------------------------------------------------------------*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topicSlug = getString(R.string.default_slug)
        topicName = getString(R.string.default_topic)
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
        // Fetch data as soon as all views are initialized
        presenter?.getTodayProducts(topicSlug)
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
        refreshLayout.isRefreshing = false
    }

    override fun showError(error: Throwable) {
        val ctx = activity as Context
        Toast.makeText(ctx, R.string.error_fetch_data, Toast.LENGTH_LONG).show()
    }

    override fun showTrendingTopics(topics: List<Topic>) {
        val topicListFragment = TopicListDialogFragment.newInstance(topics as ArrayList<Topic>)
        topicListFragment.setTargetFragment(this, REQUEST_KEY)
        val fragmentManager = activity!!.supportFragmentManager
        topicListFragment.show(fragmentManager, TOPIC_LIST_FRAGMENT_TAG)
    }

    override fun showTodayProducts(products: List<Product>) {
        productAdapter.update(products)
    }

    /*------------------------------------------------------------------------*/
    /* RecyclerViewAdapterItemClickListener implementation                    */
    /*------------------------------------------------------------------------*/

    override fun onAdapterItemClicked(item: Product) {
        val activityIntent = ProductDetailActivity.newIntent(activity!!, item)
        startActivity(activityIntent)
    }

    /*------------------------------------------------------------------------*/
    /* TopicListDialogFragment.OnTopicSelectedListener  implementation        */
    /*------------------------------------------------------------------------*/

    override fun onTopicSelected(topic: Topic) {
        topicName = topic.name
        topicSlug = topic.slug
        view?.toolbarTitle?.text = topicName
        presenter?.getTodayProducts(topicSlug)
    }

    /*------------------------------------------------------------------------*/
    /* Helper Methods                                                         */
    /*------------------------------------------------------------------------*/

    private fun isFetchingData() = progressBar.visibility == View.VISIBLE
            || refreshLayout.isRefreshing

    private fun setUpToolbar(toolbar: Toolbar) {
        val parentActivity = activity as AppCompatActivity
        parentActivity.setSupportActionBar(toolbar)
        // Reset the toolbar's intrinsic title, because we use a custom one.
        parentActivity.supportActionBar!!.title = null
        // Set our own clickable title.
        toolbar.toolbarTitle.text = topicName
        toolbar.toolbarTitle.setOnClickListener {
            if (!isFetchingData()) {
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
            if (progressBar.visibility != View.VISIBLE) {
                presenter?.refreshTodayProducts(topicSlug)
            }
        }
    }

    private fun setUpProductRecyclerView(recyclerView: RecyclerView) {
        productAdapter = ProductAdapter(this)
        val ctx = activity as Context
        val linearLayoutManager = LinearLayoutManager(ctx)
        val divider = ctx.getDrawable(R.drawable.item_divider)
        val endPadding = resources.getDimension(R.dimen.padding_small).toInt()
        val startPadding = endPadding shl 1 // Twice as big as the start padding.
        val itemDivider = ItemDividerDecoration(divider, startPadding, endPadding)
        // The size of the recycler view won't change. The flag improves performance.
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = productAdapter
        recyclerView.addItemDecoration(itemDivider)
    }
}
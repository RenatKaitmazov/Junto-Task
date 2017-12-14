package lz.renatkaitmazov.juntotesttask.productlist

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.fragment_topic_list.view.*
import lz.renatkaitmazov.juntotesttask.R
import lz.renatkaitmazov.juntotesttask.common.ItemDividerDecoration
import lz.renatkaitmazov.juntotesttask.common.RecyclerViewAdapterItemClickListener
import lz.renatkaitmazov.juntotesttask.data.model.topic.Topic
import lz.renatkaitmazov.juntotesttask.productlist.adapter.topic.TopicAdapter

/**
 *
 * @author Renat Kaitmazov
 */
class TopicListDialogFragment : DialogFragment(), RecyclerViewAdapterItemClickListener<Topic> {

    /*------------------------------------------------------------------------*/
    /* Interfaces                                                             */
    /*------------------------------------------------------------------------*/

    interface OnTopicSelectedListener {
        fun onTopicSelected(topic: Topic)
    }

    /*------------------------------------------------------------------------*/
    /* Static                                                                 */
    /*------------------------------------------------------------------------*/

    companion object {

        /*------------------------------------------------------------------------*/
        /* Constants                                                              */
        /*------------------------------------------------------------------------*/

        private const val KEY_ARG_TOPICS = "KEY_ARG_TOPICS"

        /*------------------------------------------------------------------------*/
        /* API                                                                    */
        /*------------------------------------------------------------------------*/

        fun newInstance(topics: ArrayList<Topic>): TopicListDialogFragment {
            val args = Bundle(1)
            args.putParcelableArrayList(KEY_ARG_TOPICS, topics)
            val fragment = TopicListDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    /*------------------------------------------------------------------------*/
    /* Lifecycle Events                                                       */
    /*------------------------------------------------------------------------*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_topic_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.topicRecyclerView
        setUpRecyclerView(recyclerView)
    }

    override fun onResume() {
        // Make the dialog occupy the entire screen.
        dialog?.apply {
            val params = window.attributes
            params.width = WindowManager.LayoutParams.MATCH_PARENT
            params.height = WindowManager.LayoutParams.MATCH_PARENT
            window.attributes = params
        }
        super.onResume()
    }

    override fun onDestroyView() {
        val dialog = dialog
        // Does not allow the dialog to disappear when a configuration change happens.
        // handles https://code.google.com/p/android/issues/detail?id=17423
        if (dialog != null && retainInstance) {
            dialog.setDismissMessage(null)
        }
        super.onDestroyView()
    }

    /*------------------------------------------------------------------------*/
    /* RecyclerViewAdapterItemClickListener implementation                    */
    /*------------------------------------------------------------------------*/

    override fun onAdapterItemClicked(item: Topic) {
        (targetFragment as? OnTopicSelectedListener)?.onTopicSelected(item)
        dismiss()
    }

    /*------------------------------------------------------------------------*/
    /* Helper Methods                                                         */
    /*------------------------------------------------------------------------*/

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
        val topics: ArrayList<Topic> = arguments!!.getParcelableArrayList(KEY_ARG_TOPICS)
        val adapter = TopicAdapter(topics, this)
        val ctx = activity as Context
        val linearLayoutManager = LinearLayoutManager(ctx)
        val divider = ctx.getDrawable(R.drawable.item_divider)
        val endPadding = resources.getDimension(R.dimen.padding_small).toInt()
        val startPadding = endPadding shl 1 // Twice as big as the start padding.
        val itemDivider = ItemDividerDecoration(divider, startPadding, endPadding)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(itemDivider)
    }
}
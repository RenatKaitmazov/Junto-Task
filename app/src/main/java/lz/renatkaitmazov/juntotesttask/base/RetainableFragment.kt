package lz.renatkaitmazov.juntotesttask.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A base support fragment class which retains its state when a configuration change happens.
 * If this is the behaviour you need, extend a fragment from this class.
 *
 * @author Renat Kaitmazov
 */
abstract class RetainableFragment : Fragment(), MvpView {

    /*------------------------------------------------------------------------*/
    /* Lifecycle Events                                                       */
    /*------------------------------------------------------------------------*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    /*------------------------------------------------------------------------*/
    // Abstract methods
    /*------------------------------------------------------------------------*/

    /**
     * A layout resource identifier which will be used to inflate a layout corresponding
     * to the given id.
     */
    @LayoutRes
    abstract fun getLayoutResId(): Int
}
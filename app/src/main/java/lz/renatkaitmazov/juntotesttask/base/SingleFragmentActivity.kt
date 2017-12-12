package lz.renatkaitmazov.juntotesttask.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import lz.renatkaitmazov.juntotesttask.R

/**
 * A base activity that has a place for inserting exactly one fragment.
 * The fragment occupies the entire screen except for the status bar. So if a toolbar is needed
 * it must be provided by the concrete class.
 * Activities with a single fragment should extend this class.
 *
 * @author Renat Kaitmazov
 */
abstract class SingleFragmentActivity : AppCompatActivity() {

    /*------------------------------------------------------------------------*/
    /* Lifecycle Events                                                       */
    /*------------------------------------------------------------------------*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)
        addSupportFragment(getFragment(), R.id.fragmentContainer)
    }

    /*------------------------------------------------------------------------*/
    // Abstract methods
    /*------------------------------------------------------------------------*/

    /**
     * Should be provided by the class extending this class.
     */
    abstract fun getFragment(): Fragment
}

/*------------------------------------------------------------------------*/
/*                          ACTIVITY EXTENSIONS                           */
/*------------------------------------------------------------------------*/

fun <T : Fragment> AppCompatActivity.addSupportFragment(fragment: T,
                                                        @LayoutRes containerId: Int) {
    val supportFragmentManager = supportFragmentManager
    val oldFragment = supportFragmentManager.findFragmentById(containerId)
    if (oldFragment == null) {
        supportFragmentManager.beginTransaction()
                .add(containerId, fragment)
                .commitNow()
    }
}
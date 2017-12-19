package lz.renatkaitmazov.juntotesttask

import android.app.Application
import android.content.Context
import android.os.Looper
import lz.renatkaitmazov.juntotesttask.di.app.AppComponent
import lz.renatkaitmazov.juntotesttask.di.app.AppModule
import lz.renatkaitmazov.juntotesttask.di.app.DaggerAppComponent
import lz.renatkaitmazov.juntotesttask.di.net.NetModule

/**
 *
 * @author Renat Kaitmazov
 */

class JuntoApp : Application() {

    /*------------------------------------------------------------------------*/
    /* Static                                                                 */
    /*------------------------------------------------------------------------*/

    companion object {

        /*------------------------------------------------------------------------*/
        /* API                                                                    */
        /*------------------------------------------------------------------------*/

        fun get(context: Context) = context.applicationContext as JuntoApp
    }

    /*------------------------------------------------------------------------*/
    /* Properties                                                             */
    /*------------------------------------------------------------------------*/

    val appComponent: AppComponent by lazy(LazyThreadSafetyMode.NONE) {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule())
                .build()
    }

    /*------------------------------------------------------------------------*/
    /* Lifecycle Events                                                       */
    /*------------------------------------------------------------------------*/

    override fun onCreate() {
        super.onCreate()
        val uiThread = Looper.getMainLooper().thread
        uiThread.priority = Thread.MAX_PRIORITY // Make UI thread as fast as possible.
    }
}
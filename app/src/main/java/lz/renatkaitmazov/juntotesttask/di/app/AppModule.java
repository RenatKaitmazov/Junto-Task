package lz.renatkaitmazov.juntotesttask.di.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * The topmost module that provides the application context.
 *
 * @author Renat Kaitmazov
 */

@Module
public final class AppModule {

    /*------------------------------------------------------------------------*/
    // Fields
    /*------------------------------------------------------------------------*/

    private final Context appContext;

    /*------------------------------------------------------------------------*/
    /* Constructors                                                           */
    /*------------------------------------------------------------------------*/

    public AppModule(Context appContext) {
        this.appContext = appContext;
    }

    /*------------------------------------------------------------------------*/
    /* Dependencies                                                           */
    /*------------------------------------------------------------------------*/

    @Provides
    @Singleton
    Context providesAppContext() {
        return appContext;
    }
}

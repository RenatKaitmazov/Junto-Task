package lz.renatkaitmazov.juntotesttask.di.net;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A module for dependencies related to networking.
 * All dependencies are singletons.
 *
 * @author Renat Kaitmazov
 */

@Module
public final class NetModule {

    /*------------------------------------------------------------------------*/
    /* Constants                                                              */
    /*------------------------------------------------------------------------*/

    private static final long TIMEOUT = 15L;

    /*------------------------------------------------------------------------*/
    /* Dependencies                                                           */
    /*------------------------------------------------------------------------*/

    @Provides
    @Singleton
    String provideBaseUrl() {
        return "https://api.producthunt.com/v1/";
    }

    @Provides
    @Singleton
    @Named("Headers")
    Interceptor provideHeadersInterceptor() {
        return new HeaderInterceptor();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@Named("Headers") Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient httpClient, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}

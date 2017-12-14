package lz.renatkaitmazov.juntotesttask.di.net;

import android.support.v4.util.LruCache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lz.renatkaitmazov.juntotesttask.data.Cache;
import lz.renatkaitmazov.juntotesttask.data.model.product.Product;
import lz.renatkaitmazov.juntotesttask.data.model.topic.Topic;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
    // The maximum number of entries in a cache (NOT THE NUMBER OF BYTES!!!)
    private static final int CACHE_SIZE = 100;

    /*------------------------------------------------------------------------*/
    /* Dependencies                                                           */
    /*------------------------------------------------------------------------*/

    @Provides
    @Singleton
    String provideBaseUrl() {
        return "https://api.producthunt.com/";
    }

    @Provides
    @Singleton
    @Named("Headers")
    Interceptor provideHeadersInterceptor() {
        return new HeaderInterceptor();
    }

    @Provides
    @Singleton
    @Named("Logging")
    Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@Named("Headers") Interceptor headersInterceptor,
                                     @Named("Logging") Interceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(headersInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient httpClient, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @Named("ProductCache")
    LruCache<String, List<Product>> provideProductCache() {
        return new Cache<>(CACHE_SIZE);
    }

    @Provides
    @Singleton
    @Named("TopicCache")
    LruCache<String, List<Topic>> provideTopicCache() {
        // There won't be more than 10 topic.
        return new Cache<>(10);
    }
}

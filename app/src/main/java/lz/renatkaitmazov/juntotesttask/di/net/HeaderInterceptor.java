package lz.renatkaitmazov.juntotesttask.di.net;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Renat Kaitmazov
 */

final class HeaderInterceptor implements Interceptor {

    /*------------------------------------------------------------------------*/
    /* Constants                                                              */
    /*------------------------------------------------------------------------*/

    private static final String KEY_ACCEPT = "Accept";
    private static final String VALUE_ACCEPT = "application/json";
    private static final String KEY_CONTENT_TYPE = "Content-Type";
    private static final String VALUE_CONTENT_TYPE = "application/json";
    private static final String KEY_AUTH = "Authorization";
    private static final String VALUE_AUTH = "Bearer " + Secret.ACCESS_TOKEN;

    /*------------------------------------------------------------------------*/
    /* Interceptor implementation                                             */
    /*------------------------------------------------------------------------*/

    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        final Request oldRequest = chain.request();
        final Request newRequest = new Request.Builder()
                .url(oldRequest.url())
                .addHeader(KEY_ACCEPT, VALUE_ACCEPT)
                .addHeader(KEY_CONTENT_TYPE, VALUE_CONTENT_TYPE)
                .addHeader(KEY_AUTH, VALUE_AUTH)
                .build();
        return chain.proceed(newRequest);
    }
}

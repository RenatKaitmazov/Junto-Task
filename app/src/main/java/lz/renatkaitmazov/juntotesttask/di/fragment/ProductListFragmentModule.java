package lz.renatkaitmazov.juntotesttask.di.fragment;

import android.support.v4.util.LruCache;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import lz.renatkaitmazov.juntotesttask.data.model.product.Product;
import lz.renatkaitmazov.juntotesttask.data.model.product.ProductMapper;
import lz.renatkaitmazov.juntotesttask.data.model.topic.Topic;
import lz.renatkaitmazov.juntotesttask.data.repository.product.ProductRestRepository;
import lz.renatkaitmazov.juntotesttask.data.repository.product.ProductRestRepositoryImpl;
import lz.renatkaitmazov.juntotesttask.data.repository.topic.TopicRestRepository;
import lz.renatkaitmazov.juntotesttask.data.repository.topic.TopicRestRepositoryImpl;
import lz.renatkaitmazov.juntotesttask.di.scope.FragmentScope;
import lz.renatkaitmazov.juntotesttask.productlist.ProductListFragment;
import lz.renatkaitmazov.juntotesttask.productlist.ProductListPresenter;
import lz.renatkaitmazov.juntotesttask.productlist.ProductListPresenterImpl;
import retrofit2.Retrofit;

/**
 * @author Renat Kaitmazov
 */

@Module
public final class ProductListFragmentModule {

    /*------------------------------------------------------------------------*/
    // Fields
    /*------------------------------------------------------------------------*/

    private final ProductListFragment productListFragment;

    /*------------------------------------------------------------------------*/
    /* Constructors                                                           */
    /*------------------------------------------------------------------------*/

    public ProductListFragmentModule(ProductListFragment productListFragment) {
        this.productListFragment = productListFragment;
    }

    /*------------------------------------------------------------------------*/
    /* Dependencies                                                           */
    /*------------------------------------------------------------------------*/

    @Provides
    @FragmentScope
    TopicRestRepository provideTopicRestRepository(Retrofit retrofit,
                                                   @Named("TopicCache") LruCache<String, List<Topic>> cache) {
        return new TopicRestRepositoryImpl(retrofit, cache);
    }

    @Provides
    @FragmentScope
    ProductMapper provideProductMapper() {
        return new ProductMapper();
    }

    @Provides
    @FragmentScope
    ProductRestRepository provideProductRestRepository(Retrofit retrofit,
                                                       ProductMapper mapper,
                                                       @Named("ProductCache") LruCache<String, List<Product>> cache) {
        return new ProductRestRepositoryImpl(retrofit, mapper, cache);
    }

    @Provides
    @FragmentScope
    ProductListPresenter provideProductListPresenter(TopicRestRepository topicRestRepository,
                                                     ProductRestRepository productRestRepository) {
        return new ProductListPresenterImpl(productListFragment,
                topicRestRepository,
                productRestRepository
        );
    }
}

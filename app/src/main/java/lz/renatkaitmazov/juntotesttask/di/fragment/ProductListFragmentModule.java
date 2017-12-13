package lz.renatkaitmazov.juntotesttask.di.fragment;

import dagger.Module;
import dagger.Provides;
import lz.renatkaitmazov.juntotesttask.data.repository.TopicRestRepository;
import lz.renatkaitmazov.juntotesttask.data.repository.TopicRestRepositoryImpl;
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
    TopicRestRepository provideTopicRestRepository(Retrofit retrofit) {
        return new TopicRestRepositoryImpl(retrofit);
    }

    @Provides
    @FragmentScope
    ProductListPresenter provideProductListPresenter(TopicRestRepository topicRestRepository) {
        return new ProductListPresenterImpl(productListFragment, topicRestRepository);
    }
}

package lz.renatkaitmazov.juntotesttask.di.fragment;

import dagger.Subcomponent;
import lz.renatkaitmazov.juntotesttask.di.scope.FragmentScope;
import lz.renatkaitmazov.juntotesttask.productlist.ProductListFragment;

/**
 * All modules which provide dependencies whose lifecycle lasts as long as the
 * fragment's lifecycle should be listed here.
 *
 * @author Renat Kaitmazov
 */

@FragmentScope
@Subcomponent(modules = {ProductListFragmentModule.class})
public interface FragmentSubcomponent {

    // Injection targets go here.
    void inject(ProductListFragment fragment);
}

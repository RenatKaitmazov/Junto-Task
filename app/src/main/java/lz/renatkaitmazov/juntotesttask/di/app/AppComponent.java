package lz.renatkaitmazov.juntotesttask.di.app;

import javax.inject.Singleton;

import dagger.Component;
import lz.renatkaitmazov.juntotesttask.di.fragment.FragmentSubcomponent;
import lz.renatkaitmazov.juntotesttask.di.fragment.ProductListFragmentModule;
import lz.renatkaitmazov.juntotesttask.di.net.NetModule;

/**
 * The topmost component for dependency injection.
 * All dependencies provided from modules related to this component are singletons.
 * Factory methods for dependent subcomponents are provided here.
 *
 * @author Renat Kaitmazov
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    // Factory methods for dependent subcomponents go here
    FragmentSubcomponent plus(ProductListFragmentModule module);

    // Injection targets go here
}

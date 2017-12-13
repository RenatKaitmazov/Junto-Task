package lz.renatkaitmazov.juntotesttask.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * This scope maps to the fragment's lifecycle.
 *
 * @author Renat Kaitmazov
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}

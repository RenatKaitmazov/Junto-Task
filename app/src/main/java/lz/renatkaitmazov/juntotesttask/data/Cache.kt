package lz.renatkaitmazov.juntotesttask.data

import android.support.v4.util.LruCache

/**
 *
 * @author Renat Kaitmazov
 */
class Cache<T>(maxSize: Int) : LruCache<String, T>(maxSize)
package lz.renatkaitmazov.juntotesttask.data.model.product

/**
 * A flattened data class that does not have nested structures.
 * It is assumed to be used in views.
 *
 * @author Renat Kaitmazov
 */

data class Product(val name: String,
                   val description: String,
                   val upVotes: String,
                   val productLink: String,
                   val thumbnailUrl: String,
                   val smallImgUrl: String,
                   val largeImgUrl: String)
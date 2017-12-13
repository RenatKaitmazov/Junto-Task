package lz.renatkaitmazov.juntotesttask.data.model.product

/**
 *
 * @author Renat Kaitmazov
 */
class ProductMapper {

    /*------------------------------------------------------------------------*/
    /* API                                                                    */
    /*------------------------------------------------------------------------*/

    fun map(entities: List<ProductEntity>): List<Product> {
        val products = ArrayList<Product>(entities.size)
        entities.map(this::map)
                .forEach(products::addItem)
        return products
    }

    /*------------------------------------------------------------------------*/
    /* Helper Methods                                                         */
    /*------------------------------------------------------------------------*/

    private fun map(entity: ProductEntity) = Product(entity.name,
            entity.description,
            entity.upVotes,
            entity.productLink,
            entity.thumbnail.url,
            entity.screenshot.smallImgUrl,
            entity.screenshot.largeImgUrl
    )
}

fun <T> ArrayList<T>.addItem(item: T) {
    add(item)
}
package lz.renatkaitmazov.juntotesttask.data.model.product

import android.os.Parcel
import android.os.Parcelable

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
                   val largeImgUrl: String) : Parcelable {

    /*------------------------------------------------------------------------*/
    /* Parcelable implementation                                              */
    /*------------------------------------------------------------------------*/

    companion object {
        @JvmField
        final val CREATOR = object : Parcelable.Creator<Product> {
            override fun newArray(size: Int): Array<Product?> = Array(size) { null }

            override fun createFromParcel(parcel: Parcel) = Product(parcel)
        }
    }


    private constructor(parcel: Parcel) : this(parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(name)
        parcel?.writeString(description)
        parcel?.writeString(upVotes)
        parcel?.writeString(productLink)
        parcel?.writeString(thumbnailUrl)
        parcel?.writeString(smallImgUrl)
        parcel?.writeString(largeImgUrl)
    }
}
package lz.renatkaitmazov.juntotesttask.data.model.topic

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 *
 * @author Renat Kaitmazov
 */

data class Topic(val name: String,
                 val slug: String,
                 val description: String,
                 @SerializedName("followers_count") val followersCount: String,
                 @SerializedName("posts_count") val postsCount: String,
                 @SerializedName("image") val imageUrl: String) : Parcelable {

    /*------------------------------------------------------------------------*/
    /* Parcelable implementation                                              */
    /*------------------------------------------------------------------------*/

    companion object {
        @JvmField
        final val CREATOR = object : Parcelable.Creator<Topic> {
            override fun newArray(size: Int): Array<Topic?> = Array(size) { null }

            override fun createFromParcel(parcel: Parcel) = Topic(parcel)
        }
    }

    private constructor(parcel: Parcel) : this(parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(name)
        parcel?.writeString(slug)
        parcel?.writeString(description)
        parcel?.writeString(followersCount)
        parcel?.writeString(postsCount)
        parcel?.writeString(imageUrl)
    }
}
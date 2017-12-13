package lz.renatkaitmazov.juntotesttask.data.model.product

import com.google.gson.annotations.SerializedName

/**
 * Raw data class that is used by Retrofit to convert JSON into a plain kotlin object.
 *
 * @author Renat Kaitmazov
 */
data class ProductEntity(val name: String,
                         val thumbnail: Thumbnail,
                         @SerializedName("screenshot_url") val screenshot: Screenshot,
                         @SerializedName("redirect_url") val productLink: String,
                         @SerializedName("tagline") val description: String,
                         @SerializedName("votes_count") val upVotes: String)

data class Thumbnail(@SerializedName("image_url") val url: String)

data class Screenshot(@SerializedName("300px") val smallImgUrl: String,
                      @SerializedName("850px") val largeImgUrl: String)
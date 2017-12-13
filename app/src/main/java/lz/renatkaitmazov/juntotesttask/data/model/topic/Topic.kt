package lz.renatkaitmazov.juntotesttask.data.model.topic

import com.google.gson.annotations.SerializedName

/**
 *
 * @author Renat Kaitmazov
 */

data class Topic(val name: String,
                 val slug: String,
                 val description: String,
                 @SerializedName("followers_count") val followersCount: Int,
                 @SerializedName("posts_count") val postsCount: Int,
                 @SerializedName("image") val imageUrl: String)
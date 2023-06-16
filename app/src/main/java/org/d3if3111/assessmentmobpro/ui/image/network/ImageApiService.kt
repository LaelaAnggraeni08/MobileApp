package org.d3if3111.assessmentmobpro.ui.image.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3111.assessmentmobpro.ui.image.model.Image
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "LaelaAnggraeni08/json-bangun-ruang/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ImageApiService {
    @GET("bangunruang.json")
    suspend fun getImage(): List<Image>
}

object ImageApi {
    val service: ImageApiService by lazy {
        retrofit.create(ImageApiService::class.java)
    }

    fun getImageUrl(imageResId: String): String {
        return "$BASE_URL$imageResId.png"
    }

    enum class ApiStatus { LOADING, SUCCESS, FAILED }
}

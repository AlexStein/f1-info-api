package ru.softmine.f1infoapi.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.softmine.f1infoapi.BuildConfig
import ru.softmine.f1infoapi.mvp.model.api.DataSource
import ru.softmine.f1infoapi.mvp.model.network.NetworkStatus
import ru.softmine.f1infoapi.ui.App
import ru.softmine.f1infoapi.ui.network.AndroidNetworkStatus
import javax.inject.Named
import javax.inject.Singleton

const val API_HOST = BuildConfig.RAPIDAPI_HOST
const val API_TOKEN = BuildConfig.RAPIDAPI_KEY

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://$API_HOST/"

    private var client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("x-rapidapi-key", API_TOKEN)
            .addHeader("x-rapidapi-host", API_HOST)
            .build()
        chain.proceed(newRequest)
    }.build()

    @Provides
    @Singleton
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): DataSource = Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(DataSource::class.java)


    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()


    @Provides
    @Singleton
    fun networkStatus(app: App): NetworkStatus = AndroidNetworkStatus(app)
}
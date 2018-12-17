package com.kwabenaberko.finito.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.kwabenaberko.finito.BuildConfig
import com.kwabenaberko.finito.model.Priority
import com.kwabenaberko.finito.model.repository.network.NoteService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.LocalDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val baseUrl: String) {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor{
        val loggingInterceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG){
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        else{
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideGson(): Gson{
        return GsonBuilder()
                .registerTypeAdapter(LocalDateTime::class.java, JsonDeserializer {
                    json, type, context ->
                    LocalDateTime.parse(json.asJsonPrimitive.asString)
                })
                .registerTypeAdapter(Priority::class.java, JsonDeserializer {
                    json, type, context ->
                    Priority.values().get(json.asJsonPrimitive.asInt)
                })
                .create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit{
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Singleton
    @Provides
    fun provideNotesApiService(retrofit: Retrofit): NoteService{
        return retrofit.create(NoteService::class.java)
    }
}
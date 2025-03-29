package com.bytebandits.sanjeevani.di

import android.content.Context
<<<<<<< HEAD
import com.bytebandits.sanjeevani.interfaces.NearbyHospitalsService
=======
>>>>>>> 4e42eac (Added ICP and Frontend)
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
<<<<<<< HEAD
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
=======
>>>>>>> 4e42eac (Added ICP and Frontend)
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
<<<<<<< HEAD
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }


    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://backend-url/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNearbyHospitalsService(retrofit: Retrofit): NearbyHospitalsService {
        return retrofit.create(NearbyHospitalsService::class.java)
    }
}
=======
    fun provideContext(@ApplicationContext context: Context): Context{
        return context
    }
}
>>>>>>> 4e42eac (Added ICP and Frontend)

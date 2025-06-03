package com.atm.skymessageapp.di

import android.content.Context
import androidx.room.Room
import com.atm.skymessageapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "SkyMessageDatabase"
        ).build()
    }

    @Provides
    fun provideMessageDao(db: AppDatabase) = db.messageDao()

}
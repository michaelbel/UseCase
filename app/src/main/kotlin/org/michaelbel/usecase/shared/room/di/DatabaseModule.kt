package org.michaelbel.usecase.shared.room.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import org.michaelbel.usecase.shared.room.AppDatabase
import org.michaelbel.usecase.shared.room.dao.NoteDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun appDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration(dropAllTables = true)
            .addCallback(object: RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL(
                        """
                        INSERT INTO notes (title, body)
                        VALUES ('Покупки', 'Сметанос')
                        """
                    )
                }
            })
            .build()
    }

    @Provides
    fun noteDao(database: AppDatabase): NoteDao = database.noteDao()
}

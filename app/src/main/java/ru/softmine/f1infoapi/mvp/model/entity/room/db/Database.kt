package ru.softmine.f1infoapi.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomCircuit
import ru.softmine.f1infoapi.mvp.model.entity.room.RoomImage
import ru.softmine.f1infoapi.mvp.model.entity.room.dao.CircuitDao
import ru.softmine.f1infoapi.mvp.model.entity.room.dao.ImageDao

@androidx.room.Database(
    entities = [
        RoomImage::class,
        RoomCircuit::class,
    ],
    version = 2
)
abstract class Database : RoomDatabase() {
    abstract val circuitDao: CircuitDao
    abstract val imageDao: ImageDao

    companion object {
        const val DB_NAME = "f1api_database.db"

        private var instance: Database? = null

        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .build()
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE `RoomImage` (`url` TEXT PRIMARY KEY NOT NULL, `fileName` TEXT NOT NULL)"
                )
            }
        }
    }
}

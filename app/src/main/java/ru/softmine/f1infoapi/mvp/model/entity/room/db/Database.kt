package ru.softmine.f1infoapi.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.softmine.f1infoapi.mvp.model.entity.room.*
import ru.softmine.f1infoapi.mvp.model.entity.room.dao.*

@androidx.room.Database(
    entities = [
        RoomCircuit::class,
        RoomCompetition::class,
        RoomDriver::class,
        RoomDriverRanking::class,
        RoomImage::class,
        RoomRace::class,
        RoomSeason::class,
        RoomTeam::class,
        RoomTeamRanking::class
    ],
    version = 4
)
abstract class Database : RoomDatabase() {
    abstract val circuitDao: CircuitDao
    abstract val driverDao: DriverDao
    abstract val driverRankingDao: DriverRankingDao
    abstract val imageDao: ImageDao
    abstract val raceDao: RaceDao
    abstract val seasonDao: SeasonDao
    abstract val teamDao: TeamDao
    abstract val teamRankingDao: TeamRankingDao

    companion object {
        const val DB_NAME = "f1api_database.db"

        private var instance: Database? = null

        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .addMigrations(MIGRATION_2_3)
                    .addMigrations(MIGRATION_3_4)
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

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """CREATE TABLE `RoomCompetition` (
                        |`id` INTEGER PRIMARY KEY NOT NULL, 
                        |`name` TEXT NOT NULL,
                        |`location_country` TEXT NOT NULL,
                        |`location_city` TEXT NOT NULL
                        |)
                    """.trimMargin()
                )

                database.execSQL(
                    """CREATE TABLE `RoomDriver` (
                        |`id` INTEGER PRIMARY KEY NOT NULL, 
                        |`name` TEXT NOT NULL,
                        |`image` TEXT NOT NULL,
                        |`nationality` TEXT NOT NULL,
                        |`birthdate` TEXT NOT NULL
                        |)
                    """.trimMargin()
                )

                database.execSQL(
                    """CREATE TABLE `RoomRace` (
                        |`id` INTEGER PRIMARY KEY NOT NULL, 
                        |`circuitId` INTEGER NOT NULL,
                        |`competitionId` INTEGER NOT NULL,
                        |`date` TEXT NOT NULL,
                        |`distance` INTEGER NOT NULL,
                        |`laps` INTEGER NOT NULL,
                        |`season` INTEGER NOT NULL,
                        |`status` TEXT NOT NULL,
                        |`type` TEXT NOT NULL,
                        |`weather` TEXT NOT NULL,
                        |)
                    """.trimMargin()
                )

                database.execSQL(
                    "CREATE TABLE `RoomSeason` (`id` INTEGER PRIMARY KEY NOT NULL)"
                )

                database.execSQL(
                    """CREATE TABLE `RoomTeam` (
                        |`id` INTEGER PRIMARY KEY NOT NULL, 
                        |`name` TEXT NOT NULL,
                        |`logo` TEXT NOT NULL,
                        |`president` TEXT NOT NULL,
                        |`director` TEXT NOT NULL,
                        |`technical_manager` TEXT NOT NULL,
                        |`engine` TEXT NOT NULL,
                        |`tyres` TEXT NOT NULL,
                        |)
                    """.trimMargin()
                )
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """CREATE TABLE `RoomDriverRanking` (
                        |`id` INTEGER PRIMARY KEY NOT NULL, 
                        |`driverId` INTEGER NOT NULL,
                        |`teamId` INTEGER NOT NULL,
                        |`seasonId` INTEGER NOT NULL,
                        |`position` INTEGER NOT NULL DEFAULT 0,
                        |`points` INTEGER NOT NULL DEFAULT 0,
                        |`wins` INTEGER NOT NULL DEFAULT 0,
                        |`behind` INTEGER NOT NULL DEFAULT 0
                        |)
                    """.trimMargin()
                )

                database.execSQL(
                    """CREATE TABLE `RoomTeamRanking` (
                        |`id` INTEGER PRIMARY KEY NOT NULL, 
                        |`teamId` INTEGER NOT NULL,
                        |`seasonId` INTEGER NOT NULL,
                        |`position` INTEGER NOT NULL DEFAULT 0,
                        |`points` INTEGER NOT NULL DEFAULT 0,
                        |)
                    """.trimMargin()
                )
            }
        }
    }
}

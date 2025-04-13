// Migrations.kt
package com.example.assignment1.data

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// Migration from version 1 (initial app with appointments) to 2 (pets table added).
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create the pets table with all columns, including petDOB.
        database.execSQL(
            "CREATE TABLE pets (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "name TEXT NOT NULL, " +
                    "species TEXT NOT NULL, " +
                    "gender TEXT NOT NULL, " +
                    "imageResId INTEGER NOT NULL, " +
                    "exerciseGoalHours REAL NOT NULL, " +
                    "exerciseProgressHours REAL NOT NULL, " +
                    "allergies TEXT NOT NULL, " +
                    "petDOB TEXT NOT NULL DEFAULT '1970-01-01'" + // Include petDOB here.
                    ")"
        )
    }
}
val MIGRATION_2_3 = object : Migration(2, 3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE `medical_info` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `petId` INTEGER NOT NULL, `date` INTEGER NOT NULL, `clinicName` TEXT NOT NULL, `vetName` TEXT NOT NULL, `description` TEXT NOT NULL)")
    }
}
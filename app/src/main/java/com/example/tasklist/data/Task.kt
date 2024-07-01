package com.example.tasklist.data

import android.provider.BaseColumns

data class Task(var id: Int, var name: String, var done: Boolean = false) {

    companion object {
        const val TABLE_NAME = "Tasks"
        const val COLUMN_NAME_TITLE = "name"
        const val COLUMN_NAME_DONE = "done"

        const val SQL_CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NAME_TITLE TEXT," +
                    "$COLUMN_NAME_DONE INTEGER)"

        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }

}

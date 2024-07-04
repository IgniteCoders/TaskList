package com.example.tasklist.data.entities

import com.example.tasklist.utils.DatabaseManager

data class Task(var id: Int, var name: String, var done: Boolean = false) {

    companion object {
        const val TABLE_NAME = "Tasks"
        const val COLUMN_NAME_TITLE = "name"
        const val COLUMN_NAME_DONE = "done"
        const val COLUMN_NAME_CATEGORY = "category_id"
        val COLUMN_NAMES = arrayOf(
            DatabaseManager.COLUMN_NAME_ID,
            COLUMN_NAME_TITLE,
            COLUMN_NAME_CATEGORY,
            COLUMN_NAME_DONE
        )

        const val SQL_CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME (" +
                    "${DatabaseManager.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NAME_TITLE TEXT," +
                    "$COLUMN_NAME_CATEGORY INTEGER," +
                    "$COLUMN_NAME_DONE BOOLEAN, " +
                    "CONSTRAINT fk_category " +
                    "FOREIGN KEY($COLUMN_NAME_CATEGORY) " +
                    "REFERENCES ${Category.TABLE_NAME}(${DatabaseManager.COLUMN_NAME_ID}) ON DELETE CASCADE)"

        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }

}

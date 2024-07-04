package com.example.tasklist.data.entities

import com.example.tasklist.utils.DatabaseManager

class Category (var id: Int, var name: String, var color: String, var icon: Int) {

    //var tasks: List<Task>? = null

    companion object {
        const val TABLE_NAME = "Categories"
        const val COLUMN_NAME_CATEGORY = "name"
        const val COLUMN_NAME_COLOR = "color"
        const val COLUMN_NAME_ICON = "icon"
        val COLUMN_NAMES = arrayOf(
            DatabaseManager.COLUMN_NAME_ID,
            COLUMN_NAME_CATEGORY,
            COLUMN_NAME_COLOR,
            COLUMN_NAME_ICON
        )

        const val SQL_CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME (" +
        "${DatabaseManager.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
        "$COLUMN_NAME_CATEGORY TEXT," +
        "$COLUMN_NAME_COLOR TEXT," +
        "$COLUMN_NAME_ICON INTEGER)"
    }

    override fun toString(): String {
        return "$id -> Task: $name - $color"
    }

    override fun equals(other: Any?): Boolean{
        if(other is Category){
            return id == other.id
        }
        return false;
    }
}
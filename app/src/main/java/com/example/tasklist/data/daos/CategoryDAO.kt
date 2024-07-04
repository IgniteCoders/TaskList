package com.example.tasklist.data.daos

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.tasklist.data.entities.Category
import com.example.tasklist.utils.DatabaseManager

class CategoryDAO (context: Context) {

    private var databaseManager: DatabaseManager = DatabaseManager(context)

    fun insert(category: Category): Category {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Category.COLUMN_NAME_CATEGORY, category.name)
        values.put(Category.COLUMN_NAME_COLOR, category.color)
        values.put(Category.COLUMN_NAME_ICON, category.icon)

        val newRowId = db.insert(Category.TABLE_NAME, null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        db.close()


        category.id = newRowId.toInt()
        return category
    }

    fun update(category: Category) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Category.COLUMN_NAME_CATEGORY, category.name)
        values.put(Category.COLUMN_NAME_COLOR, category.color)
        values.put(Category.COLUMN_NAME_ICON, category.icon)

        val updatedRows = db.update(Category.TABLE_NAME, values, "${DatabaseManager.COLUMN_NAME_ID} = ${category.id}", null)
        Log.i("DATABASE", "Updated records: $updatedRows")

        db.close()
    }

    fun delete(category: Category) {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Category.TABLE_NAME, "${DatabaseManager.COLUMN_NAME_ID} = ${category.id}", null)
        Log.i("DATABASE", "Deleted rows: $deletedRows")

        db.close()
    }

    @SuppressLint("Range")
    fun find(id: Int): Category? {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Category.TABLE_NAME,                         // The table to query
            Category.COLUMN_NAMES,       // The array of columns to return (pass null to get all)
            "${DatabaseManager.COLUMN_NAME_ID} = $id",                        // The columns for the WHERE clause
            null,                    // The values for the WHERE clause
            null,                        // don't group the rows
            null,                         // don't filter by row groups
            null                         // The sort order
        )

        var category: Category? = null

        if (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val name = cursor.getString(cursor.getColumnIndex(Category.COLUMN_NAME_CATEGORY))
            val color = cursor.getString(cursor.getColumnIndex(Category.COLUMN_NAME_COLOR))
            val icon = cursor.getInt(cursor.getColumnIndex(Category.COLUMN_NAME_ICON))
            //Log.i("DATABASE", "$id -> Category: $categoryName, Done: $done")

            category = Category(id, name, color, icon)
        }

        cursor.close()
        db.close()

        return category
    }

    @SuppressLint("Range")
    fun findAll(): List<Category> {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Category.TABLE_NAME,                 // The table to query
            Category.COLUMN_NAMES,     // The array of columns to return (pass null to get all)
            null,                // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        val list: MutableList<Category> = mutableListOf()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val name = cursor.getString(cursor.getColumnIndex(Category.COLUMN_NAME_CATEGORY))
            val color = cursor.getString(cursor.getColumnIndex(Category.COLUMN_NAME_COLOR))
            val icon = cursor.getInt(cursor.getColumnIndex(Category.COLUMN_NAME_ICON))
            //Log.i("DATABASE", "$id -> Category: $categoryName, Done: $done")

            val category = Category(id, name, color, icon)
            list.add(category)
        }

        cursor.close()
        db.close()

        return list
    }

}
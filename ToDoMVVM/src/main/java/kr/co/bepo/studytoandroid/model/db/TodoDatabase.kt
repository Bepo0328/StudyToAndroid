package kr.co.bepo.studytoandroid.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kr.co.bepo.studytoandroid.model.TodoModel
import kr.co.bepo.studytoandroid.model.db.dao.TodoDao

@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, TodoDatabase::class.java, "Todo.db")
                .build()
    }
}
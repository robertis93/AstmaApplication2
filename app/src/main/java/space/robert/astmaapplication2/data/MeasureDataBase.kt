package space.robert.astmaapplication2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import space.robert.astmaapplication2.model.Measure

@Database(entities = [Measure::class], version = 1, exportSchema = false)
abstract class MeasureDataBase : RoomDatabase() {

    abstract fun measureDao(): MeasureDao

    companion object{
        @Volatile
        private var INSTANCE: MeasureDataBase? = null

        fun getDaraBase(context : Context) : MeasureDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MeasureDataBase::class.java,
                    "measure_database",

                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
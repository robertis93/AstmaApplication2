package space.robert.astmaapplication2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "measure_day")
data class Measure(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val measureM : Int,
    val measureD : Int,
    val measureE : Int
)  : Parcelable{

}

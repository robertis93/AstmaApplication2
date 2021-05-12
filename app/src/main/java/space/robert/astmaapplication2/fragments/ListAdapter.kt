package space.robert.astmaapplication2.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_row.view.*
import space.robert.astmaapplication2.R
import space.robert.astmaapplication2.model.Measure

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var measureList = emptyList<Measure>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = measureList[position]
        holder.itemView.id_text.text = currentItem.dayOfMeasure

        holder.itemView.morningMeasure.text = currentItem.measureM.toString()
        holder.itemView.daygMeasure.text  = currentItem.measureD.toString()
        holder.itemView.eveningMeasure.text = currentItem.measureE.toString()
        holder.itemView.first_time.text = currentItem.firstTime.toString()
        holder.itemView.second_time.text = currentItem.secondTime.toString()
        holder.itemView.third_time.text = currentItem.thirdTime.toString()

        var mor = (holder.itemView.morningMeasure.text).toString().toInt()
        var day = (holder.itemView.daygMeasure.text).toString().toInt()
        var eve = (holder.itemView.eveningMeasure.text).toString().toInt()

        var sum =(mor + day + eve)/3
        if ((mor) < sum){
            holder.itemView.morningMeasure.setTextColor(R.color.red)
        }

        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }



    fun setData(measure: List<Measure>){
         this.measureList = measure
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return measureList.size
    }
}
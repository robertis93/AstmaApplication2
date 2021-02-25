package space.robert.astmaapplication2.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_row.view.*
import space.robert.astmaapplication2.R
import space.robert.astmaapplication2.model.Measure

class ListAdaptor: RecyclerView.Adapter<ListAdaptor.MyViewHolder>() {

    private var measureList = emptyList<Measure>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = measureList[position]
        holder.itemView.id_text.text = currentItem.id.toString()
        holder.itemView.first_time.text = currentItem.dayMeasure
        holder.itemView.morningMeasure.text = currentItem.measureM.toString()
        holder.itemView.daygMeasure.text = currentItem.measureD.toString()
        holder.itemView.eveningMeasure.text = currentItem.measureE.toString()

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
package space.robert.astmaapplication2.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.view.*
import space.robert.astmaapplication2.R
import space.robert.astmaapplication2.viewmodel.MeasureViewModel


class ListFragment : Fragment() {

    private lateinit var mMeasureViewModel: MeasureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //Recycler
        val adapter = ListAdaptor()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mMeasureViewModel = ViewModelProvider(this).get(MeasureViewModel::class.java)
        mMeasureViewModel.readAllData.observe(viewLifecycleOwner, Observer {measure ->
            adapter.setData(measure)
        })

        view.actionButtonToAddFragment.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        view.actionButtonToGraphFragment.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_graphFragment)
        }

        view.actionButtonToDozeFragment.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_dozeFragment)
        }

        view.actionButtonToAlarm.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_alarmFragment)
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
        deleteAllMeasures()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllMeasures() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mMeasureViewModel.deleteAllMeasure()
            Toast.makeText(requireContext(), "Successfully removed everething}", Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No"){_, _ ->
        }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }
}
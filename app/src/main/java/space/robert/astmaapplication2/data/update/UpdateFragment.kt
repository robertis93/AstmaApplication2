package space.robert.astmaapplication2.data.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import space.robert.astmaapplication2.R
import space.robert.astmaapplication2.model.Measure
import space.robert.astmaapplication2.viewmodel.MeasureViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mMeasureViewModel: MeasureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)


        mMeasureViewModel = ViewModelProvider(this).get(MeasureViewModel::class.java)

        view.editTextDate.setText(args.currentUser.dayMeasure)
        view.update_measureMorning.setText(args.currentUser.measureM.toString())
        view.update_measureDay.setText(args.currentUser.measureD.toString())
        view.update_measureEvening.setText(args.currentUser.measureE.toString())

        view.update_button.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem() {

        val measureDate = editTextDate.text.toString()
        val measureM = Integer.parseInt(update_measureMorning.text.toString())
        val measureD = Integer.parseInt(update_measureDay.text.toString())
        val measureE = Integer.parseInt(update_measureEvening.text.toString())

        if (inputCheck(update_measureMorning.text, update_measureDay.text, update_measureEvening.text)
        ) {
            val updateMeasure = Measure(args.currentUser.id, measureDate, measureM, measureD, measureE)

            mMeasureViewModel.updateMeasure(updateMeasure)
            Toast.makeText(requireContext(), "Updated Sujccessfull", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(morningM: Editable, dayM: Editable, evening: Editable): Boolean {
        return !(morningM.isEmpty() && dayM.isEmpty() && evening.isEmpty())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteMeasure()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteMeasure() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mMeasureViewModel.deleteMeasure(args.currentUser)
            Toast.makeText(requireContext(), "Successfully removed: ${args.currentUser.measureM}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                    }
        builder.setNegativeButton("No"){_, _ ->
                    }
        builder.setTitle("Delete ${args.currentUser.measureM}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.measureM}?")
        builder.create().show()
    }
}
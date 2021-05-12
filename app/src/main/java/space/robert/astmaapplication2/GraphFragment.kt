package space.robert.astmaapplication2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import space.robert.astmaapplication2.model.Measure
import space.robert.astmaapplication2.viewmodel.MeasureViewModel


class GraphFragment : Fragment() {

        lateinit var dataOfMeasure: LiveData<List<Measure>>

    private lateinit var mMeasureViewModel: MeasureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


//        val fM = dataOfMeasure.value?.elementAt(0)?.measureM.toString().toInt()

//        if (dataOfMeasure != null) {
//            dataOfMeasure.elementAt(0).measureM
//        }

        return inflater.inflate(R.layout.fragment_graph, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMeasureViewModel = ViewModelProvider(this).get(MeasureViewModel::class.java)
        dataOfMeasure = mMeasureViewModel.readAllData
        val aaChartView = getView()?.findViewById<AAChartView>(R.id.aa_chart_view)
        val aaChartModel: AAChartModel = AAChartModel()
            .chartType(AAChartType.Area)
            .title("Время")
            .subtitle("subtitle")
            .backgroundColor("#4b2b7f")
            .dataLabelsEnabled(true)
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Утренний замер")
                        .data(
                            arrayOf(
                                dataOfMeasure.value?.first()?.measureM?.toString()?.toInt() ?: -1,
                                6.9,
                                9.5,
                                14.5,
                                18.2,
                                21.5,
                                25.2,
                                26.5,
                                23.3,
                                18.3,
                                13.9,
                                9.6
                            )
                        ),
                    AASeriesElement()
                        .name("Дневной замер")
                        .data(
                            arrayOf(
                                0.2,
                                0.8,
                                5.7,
                                11.3,
                                17.0,
                                22.0,
                                24.8,
                                24.1,
                                20.1,
                                14.1,
                                8.6,
                                2.5
                            )
                        ),
                    AASeriesElement()
                        .name("Вечерний замер")
                        .data(
                            arrayOf(
                                0.9,
                                0.6,
                                3.5,
                                8.4,
                                13.5,
                                17.0,
                                18.6,
                                17.9,
                                14.3,
                                9.0,
                                3.9,
                                1.0
                            )
                        ),
                    AASeriesElement()
                        .name("Замер перед сном")
                        .data(
                            arrayOf(
                                3.9,
                                4.2,
                                5.7,
                                8.5,
                                11.9,
                                15.2,
                                17.0,
                                16.6,
                                14.2,
                                10.3,
                                6.6,
                                4.8
                            )
                        )
                )
            )
        if (aaChartView != null) {
            aaChartView.aa_drawChartWithChartModel(aaChartModel)
        }

    }

}
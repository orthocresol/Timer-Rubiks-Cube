package org.timerrubikscube.aatimer.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import org.timerrubikscube.R
import org.timerrubikscube.aatimer.nonactivityclass.Item
import java.util.*
import kotlin.collections.ArrayList

class RecordFragment : Fragment() {

    lateinit var mView: View
    lateinit var singleBestTV: TextView
    lateinit var mo3TV: TextView
    lateinit var ao5TV: TextView
    lateinit var ao12TV: TextView
    lateinit var mo50TV: TextView
    lateinit var mo100TV: TextView

    lateinit var csingleBestTV: TextView
    lateinit var cmo3TV: TextView
    lateinit var cao5TV: TextView
    lateinit var cao12TV: TextView
    lateinit var cmo50TV: TextView
    lateinit var cmo100TV: TextView

    val userID = FirebaseAuth.getInstance().currentUser?.uid
    val collectionRef = FirebaseFirestore.getInstance().collection("Solve $userID")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_record, container, false)
        initVariables(mView)
        getItems()

        return mView;
    }

    override fun onResume() {
        super.onResume()
        getItems()
    }

    private fun initVariables(mView: View) {
        singleBestTV = mView.findViewById(R.id.value_single_time)
        mo3TV = mView.findViewById(R.id.value_mean_of_3)
        ao5TV = mView.findViewById(R.id.value_avg_of_5)
        ao12TV = mView.findViewById(R.id.value_avg_of_12)
        mo50TV = mView.findViewById(R.id.value_mean_of_50)
        mo100TV = mView.findViewById(R.id.value_mean_of_100)
        csingleBestTV = mView.findViewById(R.id.value_single_time_c)
        cmo3TV = mView.findViewById(R.id.value_mean_of_3_c)
        cao5TV = mView.findViewById(R.id.value_avg_of_5_c)
        cao12TV = mView.findViewById(R.id.value_avg_of_12_c)
        cmo50TV = mView.findViewById(R.id.value_mean_of_50_c)
        cmo100TV = mView.findViewById(R.id.value_mean_of_100_c)
    }

    private fun getItems() {
        val itemList = ArrayList<Item>()
        collectionRef
            .orderBy("timeFromBeginning", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                for (documentSnapshot in queryDocumentSnapshots) {
                    val item = documentSnapshot.toObject(Item::class.java)
                    item.id = documentSnapshot.id
                    itemList.add(item)
                }
                findSingleBest(itemList)
                findmo3(itemList)
                findao5(itemList)
                findao12(itemList)
                findmo50(itemList)
                findmo100(itemList)

                findCurrentSingleBest(itemList)
                findCurrentmo3(itemList)
                findCurrentao5(itemList)
                findCurrentao12(itemList)
                findCurrentmo50(itemList)
                findCurrentmo100(itemList)

            }
    }


    private fun findmo100(itemList: ArrayList<Item>) {
        if (itemList.size < 100) {
            mo100TV.text = "DNF"
            return
        }
        val size = itemList.size
        var lowest = 0.00f
        var totalSolves = 100
        for (i in 0..99) {
            if (itemList[i].dnf) {
                totalSolves--
            } else if (itemList[i].plus2) {
                lowest += itemList[i].timing + 2.00f
            } else
                lowest += itemList[i].timing
        }
        var lowestSum = lowest
        lowest /= totalSolves
        for (i in 0..(size - 101)) {
            if (itemList[i].dnf) {

            } else if (itemList[i].plus2) {
                totalSolves--;
                lowestSum -= (itemList[i].timing + 2.00f)
            } else if (itemList[i].ok) {
                totalSolves--
                lowestSum -= (itemList[i].timing)
            }

            if (itemList[i + 100].dnf) {

            } else if (itemList[i + 100].plus2) {
                totalSolves++
                lowestSum += (itemList[i + 100].timing + 2.00f)
            } else if (itemList[i + 100].ok) {
                totalSolves++
                lowestSum += (itemList[i + 100].timing)
            }

            if ((lowestSum / totalSolves) < lowest) {
                lowest = (lowestSum / totalSolves)
            }
        }
        if (lowest > 0.05)
            mo100TV.text = String.format("%.2f", lowest)
        else
            mo100TV.text = "DNF"
    }

    private fun findCurrentmo100(itemList: ArrayList<Item>) {
        if (itemList.size < 100) {
            cmo100TV.text = "DNF"
            return
        }
        var lowest = 0.00f
        var totalSolves = 100
        for (i in 0..99) {
            if (itemList[i].dnf) {
                totalSolves--
            } else if (itemList[i].plus2) {
                lowest += itemList[i].timing + 2.00f
            } else
                lowest += itemList[i].timing
        }
        lowest /= totalSolves
        if (totalSolves != 0)
            cmo100TV.text = String.format("%.2f", lowest)
        else cmo100TV.text = "DNF"

    }

    private fun findmo50(itemList: ArrayList<Item>) {
        if (itemList.size < 50) {
            mo50TV.text = "DNF"
            return
        }
        val size = itemList.size
        var lowest = 0.00f
        var totalSolves = 50
        for (i in 0..49) {
            if (itemList[i].dnf) {
                totalSolves--
            } else if (itemList[i].plus2) {
                lowest += itemList[i].timing + 2.00f
            } else
                lowest += itemList[i].timing
        }
        var lowestSum = lowest
        lowest /= totalSolves
        for (i in 0..(size - 51)) {
            if (itemList[i].dnf) {

            } else if (itemList[i].plus2) {
                totalSolves--;
                lowestSum -= (itemList[i].timing + 2.00f)
            } else if (itemList[i].ok) {
                totalSolves--
                lowestSum -= (itemList[i].timing)
            }

            if (itemList[i + 50].dnf) {

            } else if (itemList[i + 50].plus2) {
                totalSolves++
                lowestSum += (itemList[i + 50].timing + 2.00f)
            } else if (itemList[i + 50].ok) {
                totalSolves++
                lowestSum += (itemList[i + 50].timing)
            }

            if ((lowestSum / totalSolves) < lowest) {
                lowest = (lowestSum / totalSolves)
            }
        }
        if (lowest > 0.05)
            mo50TV.text = String.format("%.2f", lowest)
        else
            mo50TV.text = "DNF"
    }

    private fun findCurrentmo50(itemList: ArrayList<Item>) {
        if (itemList.size < 50) {
            cmo50TV.text = "DNF"
            return
        }

        var lowest = 0.00f
        var totalSolves = 50
        for (i in 0..49) {
            if (itemList[i].dnf) {
                totalSolves--
            } else if (itemList[i].plus2) {
                lowest += itemList[i].timing + 2.00f
            } else
                lowest += itemList[i].timing
        }
        lowest /= totalSolves
        if (totalSolves != 0)
            cmo50TV.text = String.format("%.2f", lowest)
        else
            cmo50TV.text = "DNF"
    }

    private fun findao12(itemList: ArrayList<Item>) {
        if (itemList.size < 12) {
            ao12TV.text = "DNF"
            return
        }
        var lowest = 12000.00f
        val size = itemList.size
        for (i in 0..(size - 12)) {
            val toSort = ArrayList<Float>()
            var dnf = 0
            for (j in i..(i + 11)) {
                if (itemList[j].dnf) {
                    dnf++;
                    toSort.add(77000.00f)

                } else if (itemList[j].plus2) {
                    toSort.add(itemList[j].timing + 2.00f)

                } else if (itemList[j].ok) {
                    toSort.add(itemList[j].timing)
                }
            }
            Collections.sort(toSort)
            var aggregate = 0.00f
            for (j in 1..10) {
                aggregate += toSort[j]
            }
            if ((aggregate < lowest) && (dnf < 2)) {
                lowest = aggregate
            }
        }
        lowest /= 10
        if (lowest < 1000.00f)
            ao12TV.text = String.format("%.2f", lowest)
        else
            ao12TV.text = "DNF"
    }

    private fun findCurrentao12(itemList: ArrayList<Item>) {
        if (itemList.size < 12) {
            cao12TV.text = "DNF"
            return
        }
        val toSort = ArrayList<Float>()
        var dnf = 0
        for (i in 0..11) {
            if (itemList[i].dnf) {
                dnf++
                toSort.add(1000.00f)
            } else if (itemList[i].plus2) {
                toSort.add(itemList[i].timing + 2.00f)
            } else
                toSort.add(itemList[i].timing)
        }
        Collections.sort(toSort)
        var aggregate = 0.00f
        for (j in 1..10) {
            aggregate += toSort[j]
        }
        aggregate /= 10
        if (dnf < 2)
            cao12TV.text = String.format("%.2f", aggregate)
        else
            cao12TV.text = "DNF"
    }

    private fun findao5(itemList: ArrayList<Item>) {
        if (itemList.size < 5) {
            ao5TV.text = "DNF"
            return
        }
        var lowest = 1000.00f
        val size = itemList.size
        for (i in 0..(size - 5)) {
            val toSort = ArrayList<Float>()
            var dnf = 0
            for (j in i..(i + 4)) {
                if (itemList[j].dnf) {
                    dnf++;
                    toSort.add(7000.00f)

                } else if (itemList[j].plus2) {
                    toSort.add(itemList[j].timing + 2.00f)

                } else if (itemList[j].ok) {
                    toSort.add(itemList[j].timing)
                }
            }
            Collections.sort(toSort)
            var aggregate = 0.00f
            for (j in 1..3) {
                aggregate += toSort[j]
            }
            if ((aggregate < lowest) && (dnf < 2)) {
                lowest = aggregate
            }
        }
        lowest /= 3
        if (lowest < 330.00f)
            ao5TV.text = String.format("%.2f", lowest)
        else
            ao5TV.text = "DNF"

    }

    private fun findCurrentao5(itemList: ArrayList<Item>) {
        if (itemList.size < 5) {
            cao5TV.text = "DNF"
            return
        }
        val toSort = ArrayList<Float>()
        var dnf = 0
        for (i in 0..4) {
            if (itemList[i].dnf) {
                toSort.add(1000.00f)
                dnf++
            } else if (itemList[i].plus2) {
                toSort.add(itemList[i].timing + 2.00f)
            } else
                toSort.add(itemList[i].timing)
        }
        Collections.sort(toSort)
        var aggregate = 0.00f
        for (j in 1..3) {
            aggregate += toSort[j]
        }
        aggregate /= 3
        if (dnf < 2)
            cao5TV.text = String.format("%.2f", aggregate)
        else cao5TV.text = "DNF"
    }


    private fun findmo3(itemList: ArrayList<Item>) {
        if (itemList.size < 3) {
            mo3TV.text = "DNF"
            return
        }
        val size = itemList.size
        var solution = 3300.00f

        for (i in 0..(size - 3)) {
            var lowest = itemList[i].timing + itemList[i + 1].timing + itemList[i + 2].timing
            if (itemList[i].plus2) lowest += 2.00f
            if (itemList[i + 1].plus2) lowest += 2.00f
            if (itemList[i + 2].plus2) lowest += 2.00f

            if (itemList[i].dnf || itemList[i + 1].dnf || itemList[i + 2].dnf) {
                // ignore
            } else {
                if (lowest < solution) solution = lowest
            }
        }

        solution /= 3
        if (solution < 1000.00f)
            mo3TV.text = String.format("%.2f", solution)
        else
            mo3TV.text = "DNF"

    }

    private fun findCurrentmo3(itemList: ArrayList<Item>) {
        if (itemList.size < 3) {
            cmo3TV.text = "DNF"
            return
        }
        var temp = (itemList[0].timing + itemList[1].timing + itemList[2].timing)

        if (itemList[0].plus2) temp += 2.00f
        if (itemList[1].plus2) temp += 2.00f
        if (itemList[2].plus2) temp += 2.00f

        temp /= 3

        if (!itemList[0].dnf && !itemList[1].dnf && !itemList[2].dnf)
            cmo3TV.text = String.format("%.2f", temp)
        else cmo3TV.text = "DNF"
    }

    private fun findSingleBest(itemList: ArrayList<Item>) {
        if (itemList.size < 1) {
            singleBestTV.text = "DNF"
            return
        }

        var lowest = 1000.00f
        for (item in itemList) {
            var temp: Float = item.getTiming()
            if (item.plus2) temp += 2.00f
            if (temp < lowest && !item.dnf) {
                lowest = temp
            }
        }
        if (lowest < 999.00f)
            singleBestTV.text = String.format("%.2f", lowest)
        else singleBestTV.text = "DNF"
    }

    private fun findCurrentSingleBest(itemList: ArrayList<Item>) {
        if (itemList.size < 1) {
            csingleBestTV.text = "DNF"
            return
        }
        if (itemList[0].ok)
            csingleBestTV.text = String.format("%.2f", itemList[0].timing)
        else if (itemList[0].plus2) {
            val temp: Float = itemList[0].getTiming() + 2.00f
            csingleBestTV.text = (String.format("%.2f", temp) + "+")
        } else if (itemList[0].dnf) {
            csingleBestTV.text = "DNF"
        }
    }

}
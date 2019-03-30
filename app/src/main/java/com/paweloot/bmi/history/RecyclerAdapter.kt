package com.paweloot.bmi.history

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paweloot.bmi.R
import com.paweloot.bmi.main.BmiConstants
import com.paweloot.bmi.main.BmiConstants.BMI_NORMAL_UPPER_BOUND
import com.paweloot.bmi.main.BmiConstants.BMI_OBESE_UPPER_BOUND
import com.paweloot.bmi.main.BmiConstants.BMI_OVERWEIGHT_UPPER_BOUND
import com.paweloot.bmi.main.BmiConstants.BMI_UNDERWEIGHT_UPPER_BOUND
import com.paweloot.bmi.main.BmiConstants.IMPERIAL_UNITS
import com.paweloot.bmi.main.BmiConstants.METRIC_UNITS
import com.paweloot.bmi.main.logic.BmiForKgCm
import com.paweloot.bmi.main.logic.BmiForLbFtIn
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import org.json.JSONArray

class RecyclerAdapter(private val historyData: JSONArray) :
    RecyclerView.Adapter<RecyclerAdapter.HistoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.HistoryViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false) as View

        return HistoryViewHolder(inflatedView, parent.context)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val record = historyData.getJSONObject(position)

        val units: Int
        val mass: String
        val height: String
        val date: String
        val heightIn: String
        val bmiResult: String

        record.apply {
            units = getInt("units")
            mass = getString("mass")
            height = getString("height")
            date = getString("date")
        }

        holder.apply {
            setUnits(units)
            setMass(mass)
            setHeight(height)
            setDate(date)
        }

        if (units == METRIC_UNITS) {
            bmiResult = "%.2f".format(BmiForKgCm(mass.toInt(), height.toInt()).calcBmi())
        } else {
            heightIn = record.getString("heightIn")
            holder.setHeightIn(heightIn)
            bmiResult = "%.2f".format(BmiForLbFtIn(mass.toInt(), height.toInt(), heightIn.toInt()).calcBmi())
        }

        holder.setBmiResult(bmiResult)
    }

    override fun getItemCount() = historyData.length()

    class HistoryViewHolder(val view: View, private val context: Context) : RecyclerView.ViewHolder(view) {

        fun setUnits(units: Int) {
            if (units == METRIC_UNITS) {
                view.history_mass.text = appendColon(context.getString(R.string.bmi_main_mass_kg))
                view.history_height.text = appendColon(context.getString(R.string.bmi_main_height_cm))
                view.history_height_in.visibility = View.GONE
                view.history_height_in_value.visibility = View.GONE
            } else {
                view.history_mass.text = appendColon(context.getString(R.string.bmi_main_mass_lb))
                view.history_height.text = appendColon(context.getString(R.string.bmi_main_height_ft))
                view.history_height_in.text = appendColon(context.getString(R.string.bmi_main_height_in))
                view.history_height_in.visibility = View.VISIBLE
                view.history_height_in_value.visibility = View.VISIBLE
            }
        }

        fun setMass(mass: String) {
            view.history_mass_value.text = mass
        }

        fun setHeight(height: String) {
            view.history_height_value.text = height
        }

        fun setHeightIn(heightIn: String) {
            view.history_height_in_value.text = heightIn
        }

        fun setDate(date: String) {
            view.history_date.text = date
        }

        fun setBmiResult(bmiResult: String) {
            view.history_bmi_result.text = bmiResult
            view.history_bmi_result.setTextColor(mapBmiToColor(bmiResult.toDouble()))
        }

        private fun appendColon(s: String): String {
            return "$s:"
        }

        private fun mapBmiToColor(bmiResult: Double): Int {
            return context.getColor(
                when {
                    bmiResult < BMI_UNDERWEIGHT_UPPER_BOUND -> R.color.bmi_underweight
                    bmiResult < BMI_NORMAL_UPPER_BOUND -> R.color.bmi_normal
                    bmiResult < BMI_OVERWEIGHT_UPPER_BOUND -> R.color.bmi_overweight
                    bmiResult < BMI_OBESE_UPPER_BOUND -> R.color.bmi_obese
                    else -> R.color.bmi_extremely_obese
                }
            )
        }
    }
}

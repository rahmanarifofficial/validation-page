package com.rahmanarifofficial.validationpage.customview

import android.app.Activity
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.rahmanarifofficial.validationpage.R

@Suppress("DEPRECATION")
class SpinnerAdapter(
    private val activity: Activity,
    layoutResourceId: Int,
    private val code: List<String>,
    private val sortTitle: List<String>
) : ArrayAdapter<String>(activity, layoutResourceId, sortTitle) {

    private val inflater: LayoutInflater =
        this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var currentSelectedPosition: Int? = -1

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        var cView = convertView

        if (convertView ==
            null
        ) {
            cView = inflater.inflate(R.layout.list_item_spinner_show, parent, false)
        }

        val showTitleTxt = cView!!.findViewById<TextView>(R.id.titleTxt)
        val codeTxt = cView.findViewById<TextView>(R.id.codeTxt)
        val relativelyt = cView.findViewById<LinearLayout>(R.id.relativelyt)
        val codeTitle = code[position]
        val title = Html.fromHtml(sortTitle[position]).toString()

        if (currentSelectedPosition == position) {
            relativelyt.setBackgroundResource(R.drawable.bg_active_spinner)
            showTitleTxt.setTextColor(ContextCompat.getColor(context, R.color.blue))
        } else {
            relativelyt.setBackgroundResource(R.drawable.bg_transparent)
            showTitleTxt.setTextColor(ContextCompat.getColor(context, R.color.grey))
        }

        showTitleTxt.text = title
        codeTxt.text = codeTitle

        if (codeTitle.isEmpty()) {
            showTitleTxt.visibility = View.GONE
        } else
            showTitleTxt.visibility = View.VISIBLE
        return cView
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var cView = convertView
        if (cView == null) {
            cView = inflater.inflate(R.layout.list_item_spinner_hide, parent, false)
        }

        val showTitleTxt = cView!!.findViewById<TextView>(R.id.titleTxt)
        val codeTxt = cView.findViewById<TextView>(R.id.codeTxt)

        val codeTitle = code[position]
        val title = Html.fromHtml(sortTitle[position]).toString()

        showTitleTxt.text = title
        codeTxt.text = codeTitle

        if (codeTitle.isEmpty()) {
            showTitleTxt.setTextColor(ContextCompat.getColor(context, R.color.grey))
        }
        return cView
    }
}
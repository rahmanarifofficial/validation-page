package com.rahmanarifofficial.validationpage.view

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import com.rahmanarifofficial.validationpage.*
import com.rahmanarifofficial.validationpage.customview.SpinnerAdapter
import com.rahmanarifofficial.validationpage.model.DataDiri
import com.rahmanarifofficial.validationpage.preferences.UserPreference
import kotlinx.android.synthetic.main.activity_data_diri.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*


class DataDiriActivity : BaseActivity(), ErrorHandling {

    //===== EDUCATION SPINNER =====/
    private lateinit var educationAdapter: SpinnerAdapter
    private val educationIdList = ArrayList<String>()
    private val educationNameList = ArrayList<String>()
    //===== EDUCATION SPINNER =====/

    private lateinit var pref: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_diri)
        initObject()
        initUI()
        eventUI()
    }

    override fun initObject() {
        pref = UserPreference(this)

        educationIdList.add("0")
        educationNameList.add(" - Pendidikan Terakhir - ")
        enumValues<DataDiri.Education>().forEach {
            educationIdList.add(it.id.toString())
            educationNameList.add(it.educationTitle)
        }
        educationAdapter = SpinnerAdapter(
            this,
            android.R.layout.simple_list_item_1,
            educationIdList,
            educationNameList
        )
    }

    override fun initUI() {
        eduSpinner?.adapter = educationAdapter
        etKtp?.setText(pref.ktp)
        etName?.setText(pref.name)
        etNorek?.setText(pref.rekening)
        eduSpinner?.setSelection(educationNameList.indexOf(pref.education))
        etBirthday?.setText(pref.birthday)
    }

    override fun eventUI() {
        etNorek?.checkMinChar()

        etBirthday?.setOnClickListener {
            showDatePickerDialog()
        }
        deleteBtn?.setOnClickListener {
            etKtp?.setText("")
            etName?.setText("")
            etNorek?.setText("")
            eduSpinner?.setSelection(0)
            etBirthday?.setText("")

            pref.ktp = ""
            pref.name = ""
            pref.rekening = ""
            pref.education = ""
            pref.birthday = ""
        }
        submitBtn?.setOnClickListener {
            if (!checkErrorRequired()) {
                return@setOnClickListener
            }
            pref.ktp = etKtp?.text.toString()
            pref.name = etName?.text.toString()
            pref.rekening = etNorek?.text.toString()
            pref.education =
                educationAdapter.getItem(eduSpinner?.selectedItemPosition!!).toString()
            pref.birthday = etBirthday?.text.toString()
            startActivity<AlamatKTPActivity>()
            finish()
        }
    }

    private fun checkErrorRequired(): Boolean {
        return etKtp?.wasFilled(this, getString(R.string.error_ktp))!!
                //TODO: ISSUE NANE
                && etName?.wasFilled(this, getString(R.string.error_nama))!!
                && etNorek?.wasFilled(this, getString(R.string.error_norek))!!
                && eduSpinner?.wasChoosen(this, getString(R.string.error_pendidikan))!!
                && etBirthday?.wasFilled(this, getString(R.string.error_tgl_lahir))!!
    }

    private fun showDatePickerDialog() {
        val formatConfig = "dd-MM-YYYY"
        val sdf = SimpleDateFormat(formatConfig, Locale.US)

        //TODO: DatePicker
/*
        val selectedDateString = etBirthday?.text.toString()
        val formatToDate = SimpleDateFormat(formatConfig, Locale.US)
        val parseToDate = formatToDate.parse(selectedDateString)
        val selectedDate = sdf.format(parseToDate!!)
*/

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            OnDateSetListener { view, mYear, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, mYear)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                etBirthday?.setText(sdf.format(calendar.time))
            }, year, month, day
        )
        datePickerDialog.show()
    }

    override fun showSnackbar(message: String) {
        val view = window.decorView.rootView
        Utils.showSnackbar(view, message)
    }
}

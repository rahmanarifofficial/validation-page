package com.rahmanarifofficial.validationpage

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.rahmanarifofficial.validationpage.customview.SpinnerAdapter
import com.rahmanarifofficial.validationpage.model.DataDiri
import com.rahmanarifofficial.validationpage.preferences.UserPreference
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class DataDiriActivity : BaseActivity() {

    //===== EDUCATION SPINNER =====/
    private lateinit var educationAdapter: SpinnerAdapter
    private val educationIdList = ArrayList<String>()
    private val educationNameList = ArrayList<String>()
    //===== EDUCATION SPINNER =====/

    private lateinit var pref: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObject()
        initUI()
        eventUI()
    }

    override fun initObject() {
        pref = UserPreference(this)

        educationIdList.add("")
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
        etBirthday?.setOnClickListener {
            showDatePickerDialog()
        }
        submitBtn?.setOnClickListener {
            Log.d("EmptyDate", "Err "+checkErrorRequired().toString())
            if (!checkErrorRequired()) {
                pref.ktp = etKtp?.text.toString()
                pref.name = etName?.text.toString()
                pref.rekening = etNorek?.text.toString()
                pref.education =
                    educationAdapter.getItem(eduSpinner?.selectedItemPosition!!).toString()
                pref.birthday = etBirthday?.text.toString()
            }
        }
    }

    private fun checkErrorRequired(): Boolean {
        Log.d("EmptyDate", TextUtils.isEmpty(etBirthday?.text).toString())
        return etKtp?.isRequired("KTP Harus Diisi")!!
                //TODO: ISSUE NANE
                //TODO: Check Error
                || etName?.isRequired("Nama Harus Diisi")!!
                || etNorek?.isRequired("Nomor Rekening Harus Diisi")!!
                || etBirthday?.isRequired("Harus Diisi")!!
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
}

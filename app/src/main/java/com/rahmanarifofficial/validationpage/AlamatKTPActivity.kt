package com.rahmanarifofficial.validationpage

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rahmanarifofficial.validationpage.customview.SpinnerAdapter
import com.rahmanarifofficial.validationpage.model.Alamat
import com.rahmanarifofficial.validationpage.preferences.UserPreference
import com.rahmanarifofficial.validationpage.viewmodel.ProvinsiViewModel
import kotlinx.android.synthetic.main.activity_alamat_ktp.*

class AlamatKTPActivity : BaseActivity() {

    //===== TIPE RUMAH SPINNER =====/
    private lateinit var tipeRumahAdapter: SpinnerAdapter
    private val rumahIdList = ArrayList<String>()
    private val rumahNameList = ArrayList<String>()
    //===== TIPE RUMAH SPINNER =====/
    //===== PROVINSI SPINNER =====/
    private lateinit var provinsiAdapter: SpinnerAdapter
    private val provinsiIdList = ArrayList<String>()
    private val provinsiList = ArrayList<String>()
    //===== PROVINSI SPINNER =====/

    private lateinit var vm: ProvinsiViewModel

    private lateinit var pref: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alamat_ktp)
        initObject()
        initUI()
        eventUI()
    }

    override fun initObject() {
        pref = UserPreference(this)
        vm = ViewModelProvider(this).get(ProvinsiViewModel::class.java)

        rumahIdList.add("")
        rumahNameList.add(" - Tipe Rumah - ")
        enumValues<Alamat.TipeRumah>().forEach {
            rumahIdList.add(it.id.toString())
            rumahNameList.add(it.tipeRumah)
        }
        tipeRumahAdapter = SpinnerAdapter(
            this,
            android.R.layout.simple_list_item_1,
            rumahIdList,
            rumahNameList
        )

        loadProvinsi()
        provinsiAdapter = SpinnerAdapter(
            this,
            android.R.layout.simple_list_item_1,
            provinsiIdList,
            provinsiList
        )
    }

    override fun initUI() {
        rumahSpinner?.adapter = tipeRumahAdapter
        provinsiSpinner?.adapter = provinsiAdapter
        etAlamatKTP?.setText(pref.alamatKTP)
        etNoBlok?.setText(pref.noBlok)
        rumahSpinner?.setSelection(rumahNameList.indexOf(pref.education))
        provinsiSpinner?.setSelection(provinsiList.indexOf(pref.education))
    }

    override fun eventUI() {

    }

    private fun loadProvinsi() {
        Log.d("DATAPRO", "LOAD PROVINSI")
        vm.getListProvinsi().observe(this, Observer {
            it?.let { res ->
                Log.d("DATAPRO", res.errorStatus.toString())
                Log.d("DATAPRO", res.errorMessage.toString())
                if (!res.errorStatus) {
                    Log.d("DATAPRO", res.semuaProvinsi.toString())
                    res.semuaProvinsi?.let { data ->
                        for (index in data.indices) {
                            provinsiIdList.add("")
                            provinsiList.add(" - Pilih Provinsi - ")
                            provinsiIdList.add(data[index].id ?: "")
                            provinsiList.add(data[index].provinsi ?: "")
                            provinsiAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }
}

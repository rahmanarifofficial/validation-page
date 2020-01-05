package com.rahmanarifofficial.validationpage.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rahmanarifofficial.validationpage.*
import com.rahmanarifofficial.validationpage.Utils.Companion.hideLoadingDialog
import com.rahmanarifofficial.validationpage.Utils.Companion.showLoadingDialog
import com.rahmanarifofficial.validationpage.customview.SpinnerAdapter
import com.rahmanarifofficial.validationpage.model.Alamat
import com.rahmanarifofficial.validationpage.preferences.UserPreference
import com.rahmanarifofficial.validationpage.viewmodel.ProvinsiViewModel
import kotlinx.android.synthetic.main.activity_alamat_ktp.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class AlamatKTPActivity : BaseActivity(), ErrorHandling {

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

        rumahIdList.add("0")
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
        rumahSpinner?.setSelection(rumahNameList.indexOf(pref.homeType))
        provinsiSpinner?.setSelection(provinsiList.indexOf(pref.provinsi))
    }

    override fun eventUI() {
        backBtn?.onClick {
            startActivity<DataDiriActivity>()
            finish()
        }
        deleteBtn?.onClick {
            etAlamatKTP?.setText("")
            etNoBlok?.setText("")
            rumahSpinner?.setSelection(0)
            provinsiSpinner?.setSelection(0)

            pref.alamatKTP = ""
            pref.noBlok = ""
            pref.homeType = ""
            pref.provinsi = ""
        }
        submitBtn?.onClick {
            if (!checkErrorRequired()){
                return@onClick
            }

            pref.alamatKTP = etAlamatKTP?.text.toString()
            pref.noBlok = etNoBlok?.text.toString()
            pref.homeType =
                tipeRumahAdapter.getItem(rumahSpinner?.selectedItemPosition ?: 0).toString()
            pref.provinsi =
                provinsiAdapter.getItem(provinsiSpinner?.selectedItemPosition ?: 0).toString()
            startActivity<ReviewActivity>()
            finish()
        }
    }

    private fun checkErrorRequired(): Boolean {
        return etAlamatKTP?.wasFilled(this, getString(R.string.error_alamat_ktp))!!
                && etNoBlok?.wasFilled(this, getString(R.string.error_no_blok))!!
                && rumahSpinner?.wasChoosen(this, getString(R.string.error_jenis_tinggal))!!
                && provinsiSpinner?.wasChoosen(this, getString(R.string.error_provinsi))!!
    }

    private fun loadProvinsi() {
        showLoadingDialog(this)
        vm.getListProvinsi().observe(this, Observer {
            it?.let { res ->
                if (!res.errorStatus) {
                    res.semuaProvinsi?.let { data ->
                        provinsiIdList.clear()
                        provinsiList.clear()
                        provinsiIdList.add("0")
                        provinsiList.add(" - Pilih Provinsi - ")
                        for (index in data.indices) {
                            provinsiIdList.add(data[index].id ?: "")
                            provinsiList.add(data[index].provinsi ?: "")
                            provinsiAdapter.notifyDataSetChanged()
                        }
                    }
                    hideLoadingDialog()
                }
            }
        })
    }

    override fun showSnackbar(message: String) {
        val view = window.decorView.rootView
        Utils.showSnackbar(view, message)
    }

}

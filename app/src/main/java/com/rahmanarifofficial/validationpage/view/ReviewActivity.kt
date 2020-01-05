package com.rahmanarifofficial.validationpage.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rahmanarifofficial.validationpage.R
import com.rahmanarifofficial.validationpage.preferences.UserPreference
import kotlinx.android.synthetic.main.activity_review.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class ReviewActivity : BaseActivity() {

    private lateinit var pref: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        initObject()
        initUI()
        eventUI()
    }

    override fun initObject() {
        pref = UserPreference(this)
    }

    override fun initUI() {
        tvKTP.text = pref.ktp
        tvName.text = pref.name
        tvAlamatKTP.text = pref.alamatKTP
        tvBirthday.text = pref.birthday
        tvNoBlok.text = pref.noBlok
        tvPendidikan.text = pref.education
        tvProvinsi.text = pref.provinsi
        tvRekening.text = pref.rekening
        tvTipeRumah.text = pref.homeType
    }

    override fun eventUI() {
        backBtn?.onClick {
            startActivity<AlamatKTPActivity>()
            finish()
        }
        deleteBtn?.onClick {
            tvKTP?.text = ""
            tvName?.text = ""
            tvNoBlok?.text = ""
            tvTipeRumah?.text = ""
            tvBirthday?.text = ""
            tvAlamatKTP?.text = ""
            tvPendidikan?.text = ""
            tvProvinsi?.text = ""
            tvRekening?.text = ""

            pref.ktp = ""
            pref.name = ""
            pref.rekening = ""
            pref.education = ""
            pref.birthday = ""
            pref.alamatKTP = ""
            pref.noBlok = ""
            pref.homeType = ""
            pref.provinsi = ""
        }
    }

}

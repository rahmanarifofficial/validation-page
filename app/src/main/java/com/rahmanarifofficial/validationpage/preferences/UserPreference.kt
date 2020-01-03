package com.rahmanarifofficial.validationpage.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.rahmanarifofficial.validationpage.BuildConfig
import com.rahmanarifofficial.validationpage.constant.SharedPreferencesConstant

class UserPreference(context: Context) {
    private val preffName: String = BuildConfig.APP_NAME
    private var pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(preffName, 0)
    }

    var ktp: String
        get() = pref.getString(SharedPreferencesConstant.KTP, "") ?: ""
        set(value) {
            pref.edit {
                putString(SharedPreferencesConstant.KTP, value)
            }
        }

    var name: String
        get() = pref.getString(SharedPreferencesConstant.NAME, "") ?: ""
        set(value) {
            pref.edit {
                putString(SharedPreferencesConstant.NAME, value)
            }
        }

    var rekening: String
        get() = pref.getString(SharedPreferencesConstant.REK, "") ?: ""
        set(value) {
            pref.edit {
                putString(SharedPreferencesConstant.REK, value)
            }
        }

    var education: String
        get() = pref.getString(SharedPreferencesConstant.EDUCATION, "") ?: ""
        set(value) {
            pref.edit {
                putString(SharedPreferencesConstant.EDUCATION, value)
            }
        }

    var birthday: String
        get() = pref.getString(SharedPreferencesConstant.BIRTHDAY, "") ?: ""
        set(value) {
            pref.edit {
                putString(SharedPreferencesConstant.BIRTHDAY, value)
            }
        }

    var alamatKTP: String
        get() = pref.getString(SharedPreferencesConstant.ALAMAT_KTP, "") ?: ""
        set(value) {
            pref.edit {
                putString(SharedPreferencesConstant.ALAMAT_KTP, value)
            }
        }

    var homeType: String
        get() = pref.getString(SharedPreferencesConstant.HOME_TYPE, "") ?: ""
        set(value) {
            pref.edit {
                putString(SharedPreferencesConstant.HOME_TYPE, value)
            }
        }

    var noBlok: String
        get() = pref.getString(SharedPreferencesConstant.NO_BLOK, "") ?: ""
        set(value) {
            pref.edit {
                putString(SharedPreferencesConstant.NO_BLOK, value)
            }
        }

    var provinsi: String
        get() = pref.getString(SharedPreferencesConstant.PROVINSI, "") ?: ""
        set(value) {
            pref.edit {
                putString(SharedPreferencesConstant.PROVINSI, value)
            }
        }

}
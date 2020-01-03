package com.rahmanarifofficial.validationpage.model

import com.google.gson.annotations.SerializedName

data class Alamat(
    var alamat: String,
    var tipeRumah: String,
    var blok: String,
    var provinsi: String
) {
    enum class TipeRumah(val id: Int, val tipeRumah: String) {
        RUMAH(1, "Rumah"),
        KANTOR(2, "Kantor")
    }

    data class Provinsi(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("nama")
        var provinsi: String? = null
    )

    data class ResponseProvinsi(
        @SerializedName("error")
        var errorStatus: Boolean = false,
        @SerializedName("message")
        var errorMessage: String? = null,
        @SerializedName("semuaprovinsi")
        var semuaProvinsi: List<Provinsi>? = null
    )
}
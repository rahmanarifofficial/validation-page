package com.rahmanarifofficial.validationpage.model

data class DataDiri(
    var nomorKtp: String,
    var nama: String? = null,
    var norek: String,
    var education: String,
    var tanggalLahir: String
){
    enum class Education (val id: Int, val educationTitle: String){
        SD(1, "SD"),
        SMP(2, "SMP"),
        SMA(3, "SMA"),
        S1(4, "S1 (Sarjana)"),
        S2(5, "S2 (Magister)"),
        S3(6, "S3 (Doktoral)")
    }
}
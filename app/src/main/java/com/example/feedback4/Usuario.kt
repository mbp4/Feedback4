package com.example.feedback4

data class Usuario(val mail: String, val password: String, val modo: Boolean){
    constructor() : this("","",false)
}
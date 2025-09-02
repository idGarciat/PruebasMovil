package com.example.pruebas1

class Now {

    fun espera1(numero1 : Int, numero2 : Int) : Int {
        return numero1 + numero2
    }

    fun espera2(resultado1: Int) : Int {
        return resultado1 * 2
    }

}

fun main() {
    val now = Now()
    val resultado1 = now.espera1(3, 4) //Esto tendrian que ser inputs
    //Tiempo de por medio
    val resultado2 = now.espera2(resultado1)
    println("$resultado2")
}
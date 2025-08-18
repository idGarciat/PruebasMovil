package com.example.pruebas1

class Complejos {
    //Sumas
    fun suma(a: Int, b: Int, c: Int, d: Int): String {
        val pReal = a + c
        val pImaginario = b + d

        if (pImaginario > 0 && pReal == 0) {
            return "${pImaginario}i"
        }
        else  if (pReal > 0 && pImaginario == 0) {
            return "$pReal"
        }
        else if (pImaginario == 0 && pReal == 0) {
            return "0"
        }
        else if (pReal < 0 && pImaginario < 0) {
            return "$pReal - ${-pImaginario}i"
        }
        else {
            return "$pReal + ${pImaginario}i"
        }



    }
    //Multiplicaciones
    fun multiplicacion(a: Int, b: Int, c: Int, d: Int): String {
        val pReal = (a * c) - (b * d)
        val pImaginario = (a * d) + (b * c)

        if (pImaginario > 0 && pReal == 0) {
            return "${pImaginario}i"
        }
        else  if (pReal > 0 && pImaginario == 0) {
            return "$pReal"
        }
        else if (pImaginario == 0 && pReal == 0) {
            return "0"
        }
        else if (pReal < 0 && pImaginario < 0) {
            return "$pReal - ${-pImaginario}i"
        }
        else {
            return "$pReal + ${pImaginario}i"
        }
    }

    fun division(a: Int, b: Int, c: Int, d: Int): String {
        val denominador = (c * c) + (d * d)
        val pReal = ((a * c) + (b * d)).toDouble() / denominador.toDouble()
        val pImaginario = ((b * c) - (a * d)).toDouble() / denominador.toDouble()

        if (pImaginario > 0 && pReal == 0.0) {
            return "${pImaginario}i"
        }
        else  if (pReal > 0 && pImaginario == 0.0) {
            return "$pReal"
        }
        else if (pImaginario == 0.0 && pReal == 0.0) {
            return "0"
        }
        else if (pReal < 0 && pImaginario < 0) {
            return "$pReal - ${-pImaginario}i"
        }
        else {
            return "$pReal + ${pImaginario}i"
        }
    }
}
package com.example.pruebas1

class Recursividad {


    fun Factorial(a: Int): Int {
        if(a == 0) {
            return 1
        } else if (a > 0){
            return a * Factorial(a - 1)
        } else {
            return 123456
        }
    }

    fun Potencia(a: Int, b: Int): Int {

        if(b == 0) {
            return 1
        } else if (b > 0){
            return a * Potencia(a, b - 1)
        }
        else return 123456
    }

    fun MultiRecursiva(a: Int, b: Int): Int {
        if(b == 0) {
            return 0
        } else if(b > 0) {
            return a * (MultiRecursiva(a, b - 1))
        } else {
            return 123456
        }
    }

    fun SumaRecursiva(a: Int, b: Int): Int {
        if(b == 0) {
            return a
        } else if (b > 0){
            return a + SumaRecursiva(a, b - 1)
        } else {
            return 123456
        }
    }
}
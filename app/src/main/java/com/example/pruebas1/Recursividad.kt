package com.example.pruebas1

class Recursividad {


    fun factorial(a: Int): Int {
        if(a == 0) {
            return 1
        } else{
            return a * factorial(a - 1)
        }
    }

    fun potencia(a: Int, b: Int): Int {

        if(b == 0) {
            return 1
        } else{
            return a * potencia(a, b - 1)
        }
    }

    fun multiRecursiva(a: Int, b: Int): Int {
        if(b == 0) {
            return 0
        } else {
            return a + (multiRecursiva(a, b - 1))
        }
    }

    fun sumaRecursiva(a: Int, b: Int): Int {
        if(b == 0) {
            return a
        } else{
            return sumaRecursiva(a+1, b - 1)
        }
    }
}
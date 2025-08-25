package com.example.pruebas1

import android.widget.TextView

class Recursividad {

//    fun factorial(a: Int): Int {
//        if(a == 0) {
//            return 1
//        } else{
//            return a * factorial(a - 1)
//        }
//    }

    fun factorialConProceso(n: Int, pasos: MutableList<String>): Int {
        return if (n == 0) {
            pasos.add("0! = 1")
            1
        } else {
            pasos.add("$n! = $n * (${n-1})!")
            val resultado = n * factorialConProceso(n - 1, pasos)
            pasos.add("$n! = $resultado")
            resultado
        }
    }

//    fun potencia(a: Int, b: Int): Int {
//
//        if(b == 0) {
//            return 1
//        } else{
//            return a * potencia(a, b - 1)
//        }
//    }

    fun potenciaConProceso(a: Int, b: Int, pasos: MutableList<String>): Int {
        return if (b == 0) {
            pasos.add("${a}^0 = 1")
            1
        } else {
            pasos.add("${a}^$b = ${a} * ${a}^${b - 1}")
            val resultado = a * potenciaConProceso(a, b - 1, pasos)
            pasos.add("${a}^$b = $resultado")
            resultado
        }
    }

//    fun multiRecursiva(a: Int, b: Int): Int {
//        if(b == 0) {
//            return 0
//        } else {
//
//            return a + (multiRecursiva(a, b - 1))
//        }
//    }

    fun multiplicacionConProceso(a: Int, b: Int, pasos: MutableList<String>): Int {
        return if (b == 0) {
            pasos.add("${a} * 0 = 0")
            0
        } else {
            pasos.add("${a} * $b = $a + (${a} * ${b - 1})")
            val resultado = a + multiplicacionConProceso(a, b - 1, pasos)
            pasos.add("${a} * $b = $resultado")
            resultado
        }
    }


//    fun sumaRecursiva(a: Int, b: Int): Int {
//        if(b == 0) {
//            return a
//        } else{
//            return sumaRecursiva(a+1, b - 1)
//        }
//    }

    fun sumaConProceso(a: Int, b: Int, pasos: MutableList<String>): Int {
        return if (b == 0) {
            pasos.add("$a + 0 = $a")
            a
        } else {
            pasos.add("$a + $b = (${a}+1) + (${b-1})")
            val resultado = sumaConProceso(a + 1, b - 1, pasos)
            pasos.add("${a}+${b} = $resultado")
            resultado
        }
    }

    fun fractal(paso: Int, inicio: Double = 0.0, fin: Double = 1.0): List<Pair<Double, Double>> {
        if (paso == 0) {
            return listOf(Pair(inicio, fin))
        }

        val ter = (fin - inicio) / 3.0
        val izq = fractal(paso - 1, inicio, inicio + ter)
        val der = fractal(paso - 1, fin - ter, fin)

        return izq + der
    }

    fun fractalconproceso(paso: Int): String {
        val texto = StringBuilder()

        for (i in 0..paso) {
            val intervalo = fractal(i)
            texto.append("C$i = ")
            intervalo.forEachIndexed { index, interval ->
                texto.append("[${interval.first}, ${interval.second}]")
                if (index != intervalo.size - 1) texto.append(" ∪ ")
            }
            texto.append("\n")
        }

        return texto.toString()
    }




}
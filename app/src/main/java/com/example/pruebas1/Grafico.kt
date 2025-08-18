package com.example.pruebas1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View

class Grafico (context: Context) : View(context){

    private val paint = Paint()
    private val TAG: String = "Grafico1"

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.strokeWidth = 5f

        val ancho = canvas.width
        val alto = canvas.height
        Log.d(TAG, "Ancho: $ancho, Alto: $alto")

        canvas.drawLine(0f, alto/2f, ancho.toFloat(), alto/2f, paint)
        canvas.drawLine(ancho/2f, 0f, ancho/2f, alto.toFloat(), paint)

        paint.color = Color.BLUE

        val limInfx = -20f
        val limSupx = 20f
        val limInfy = -20f
        val limSupy = 20f

        var x = limInfx
        while (x <= limSupx) {
            val y = 1/Math.tan(x.toDouble())

            val xt = (x - limInfx) / (limSupx - limInfx) * ancho
            val yt = alto - (y - limInfy) / (limSupy - limInfy) * alto
            canvas.drawCircle(xt, yt.toFloat(), 5f, paint)
            Log.d(TAG, "x: $x, y: $y, xt: $xt, yt: $yt")


            x = x + 0.1f


        }


    }
}
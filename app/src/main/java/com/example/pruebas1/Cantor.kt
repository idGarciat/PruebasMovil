package com.example.pruebas1
import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View


class GraficoCantor (context: Context) : View(context){



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paint = Paint()

        paint.color = Color.RED
        paint.strokeWidth = 5f


        var nivel = 10
        val ancho = canvas.width

        var rojo = 227
        var verde = 103
        var azul = 162

        val y = 100

        paint.color = Color.rgb(rojo, verde, azul)

        fun dibujar(startx: Int, endx: Int,y: Int, nivel: Int) {
            if ( nivel == 0) return


            rojo = rojo - 10
            verde = verde + 10
            azul = azul + 10

            paint.color = Color.rgb(rojo, verde, azul)

            canvas.drawLine(startx.toFloat(), y.toFloat()-15, endx.toFloat(), y.toFloat()-15, paint)


            var t = (endx - startx) / 3
            var newy = y + 60

            dibujar(startx, startx + t, newy, nivel - 1)


            rojo = rojo - 10
            verde = verde + 10
            azul = azul + 10

            paint.color = Color.rgb(rojo, verde, azul)

            dibujar(startx + 2 * t, endx, newy, nivel - 1)

        }


        dibujar(0, ancho,  y, nivel)

    }



}
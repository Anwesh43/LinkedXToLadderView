package com.anwesh.uiprojects.xtoladderview

/**
 * Created by anweshmishra on 01/02/19.
 */

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 4
val xlines : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.8f
val foreColor : Int = Color.parseColor("#01579B")
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + scaleFactor() * b.inverse()
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawXTLNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val xGap : Float = size * Math.cos(Math.PI/4).toFloat()
    val yGap : Float = (2 * size) / lines
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.color = foreColor
    save()
    translate(w / 2, gap * (i + 1))
    for (j in 0..(xlines - 1)) {
        val sc : Float = sc1.divideScale(j, xlines)
        save()
        rotate(45f * (1f - 2 * j) * sc)
        drawLine(0f, -size, 0f, size, paint)
        restore()
    }
    for (j in 0..(lines - 1)) {
        val sc : Float = sc2.divideScale(j, lines)
        val p1 : Int = j / 2
        val xSize : Float = (xGap - xGap/2 * j) * p1 + (xGap - xGap/2 * (lines-1 - j)) * (1 - p1)
        save()
        translate(0f, yGap * j)
        drawLine(-xSize * sc, 0f, xSize * sc, 0f, paint)
        restore()
    }
    restore()
}
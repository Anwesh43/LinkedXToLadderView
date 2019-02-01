package com.anwesh.uiprojects.linkedxtoladderview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.xtoladderview.XToLadderView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view : XToLadderView = XToLadderView.create(this)
    }
}

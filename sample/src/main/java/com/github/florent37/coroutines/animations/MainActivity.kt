package com.github.florent37.coroutines.animations

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.florent37.coroutineanimations.experimental.animation
import com.github.florent37.coroutineanimations.experimental.centerY
import com.github.florent37.coroutineanimations.experimental.floatAnimation
import florent37.github.com.kotlinanimation.R
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    lateinit var avatar: View
    lateinit var kotlin: View
    lateinit var coroutine: View
    lateinit var follow: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kotlin = findViewById<View>(R.id.kotlin)
        coroutine = findViewById<View>(R.id.coroutine)
        avatar = findViewById<View>(R.id.avatar)
        follow = findViewById<View>(R.id.follow)

        follow.setOnClickListener { performAnimation() }
    }

    fun performAnimation() = launch(UI) {
        animation(avatar, startDelay = 1000L) { top = 0f }.join()

        //wait until animation finished
        floatAnimation(avatar, 1f, 0.5f) { view, value ->
            view.alpha = value
        }.join()

        //wait until all of these animation finished
        mutableListOf(
                animation(follow) {
                    top = avatar.y + avatar.height + 16f
                },
                animation(kotlin) {
                    left = avatar.x - kotlin.width - 16f
                    centerY = avatar.centerY()
                },
                animation(coroutine) {
                    left = avatar.x + avatar.width + 16f
                    centerY = avatar.centerY()
                }
        ).forEach { it.join() } // wait for all animations to complete
    }

}
package com.example.laptopsellingg

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 7000L // Waktu tampilan splash screen dalam milidetik

    private lateinit var gifImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        gifImageView = findViewById(R.id.gifImageView)

        Glide.with(this)
            .asGif()
            .load(R.drawable.logoku)
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.setLoopCount(1) // Atur jumlah pengulangan ke 1
                    return false
                }
            })
            .into(object : CustomTarget<GifDrawable>() {
                override fun onResourceReady(
                    resource: GifDrawable,
                    transition: Transition<in GifDrawable>?
                ) {
                    gifImageView.setImageDrawable(resource)
                    resource.start()
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })

        // Handler untuk menjalankan kode setelah tampilan splash screen selesai
        Handler().postDelayed({
            // Kode ini akan dijalankan setelah SPLASH_TIME_OUT
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Menutup activity splash screen agar tidak bisa dikembalikan dengan tombol back
        }, SPLASH_TIME_OUT)
    }
}

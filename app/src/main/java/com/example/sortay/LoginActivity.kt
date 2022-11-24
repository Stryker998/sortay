package com.example.sortay

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sortay.databinding.ActivityLoginBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.komputing.khex.extensions.toNoPrefixHexString
import org.walletconnect.Session
import org.walletconnect.impls.*
import org.walletconnect.nullOnThrow
import java.io.File
import java.util.*


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    companion object {
        private lateinit var client: OkHttpClient
        private lateinit var moshi: Moshi
        private lateinit var storage: WCSessionStore
        private lateinit var config: Session.Config
        private lateinit var session: Session

        private val bridgeUrl = "https://bridge.walletconnect.org"
        private val metaData = Session.PeerMeta(
            name = "metamask",
            url = "metamask.io",
            description = "Sort your assets"
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        client = OkHttpClient.Builder().build()
        moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        storage = FileWCSessionStore(
            File(
                applicationContext.cacheDir,
                "session_store.json"
            ).apply { createNewFile() }, moshi
        )


        resetSession(
            object : Session.Callback {
                override fun onMethodCall(call: Session.MethodCall) {

                }

                override fun onStatus(status: Session.Status) {
                    /*when(status) {
                        Session.Status.Connected -> {
                            val i = Intent(applicationContext, StatsActivity::class.java)
                            startActivity(i)
                        }
                    }*/
                    if (status == Session.Status.Approved) {
                        val i = Intent(applicationContext, StatsActivity::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(i)
                        finishAffinity()
                    }
                }
            }
        )

    }

    fun resetSession(callback: Session.Callback) {
        nullOnThrow { session }?.clearCallbacks()
        val key = ByteArray(32).also { Random().nextBytes(it) }.toNoPrefixHexString()
        config = Session.Config(UUID.randomUUID().toString(), bridgeUrl, key)
        session = WCSession(
            config.toFullyQualifiedConfig(),
            MoshiPayloadAdapter(moshi),
            storage,
            OkHttpTransport.Builder(client, moshi),
            metaData
        )
        session.addCallback(callback)
        session.offer()
        signIn()
    }



    private fun signIn() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(config.toWCUri()))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
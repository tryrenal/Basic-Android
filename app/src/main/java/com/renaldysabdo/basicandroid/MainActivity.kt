package com.renaldysabdo.basicandroid

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class MainActivity : AppCompatActivity() {

    var fingerPrintManager: FingerprintManager? = null
    var keyguardManager: KeyguardManager? = null

    var keyStore : KeyStore? = null
    var keyGenerator : KeyGenerator? = null

    var KEY_NAME = "my_key"

    var cipher : Cipher? = null
    var cryptoObject : FingerprintManager.CryptoObject? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        fingerPrintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager

        if (keyguardManager?.isKeyguardSecure != true){
            Toast.makeText(this, "Lock Screen Security not enabled in settings", Toast.LENGTH_SHORT).show()
            return
        }

        if (fingerPrintManager?.hasEnrolledFingerprints() != true){
            Toast.makeText(this, "Register atleast one fingerprint in setting", Toast.LENGTH_SHORT).show()
            return
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.USE_FINGERPRINT), 111)
        } else {
            validatedFingerPrint()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            validatedFingerPrint()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun validatedFingerPrint(){
        //generating key
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
            keyStore?.load(null)
            keyGenerator?.init(KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setUserAuthenticationRequired(true)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .build())
            keyGenerator?.generateKey()
        }catch (e: Exception){
            e.printStackTrace()
        }

        //initialization of cryptography
        if (initCipher()){
            cipher?.let {
                cryptoObject = FingerprintManager.CryptoObject(it)
            }
        }

        var helper = FingerPrintHelper(this)
        if (fingerPrintManager != null && cryptoObject != null){
            helper.startAuth(fingerPrintManager!!, cryptoObject!!)
        }
    }

    private fun initCipher(): Boolean {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC +"/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7)
        }catch (e: Exception){
            e.printStackTrace()
        }

        try {
            keyStore?.load(null)
            val key = keyStore?.getKey(KEY_NAME, null) as SecretKey
            cipher?.init(Cipher.ENCRYPT_MODE, key)
            return true
        }catch (e: Exception){
            e.printStackTrace()
            return false
        }
    }
}
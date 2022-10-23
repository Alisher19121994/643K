package com.example.a643k

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    var launcher: ActivityResultLauncher<Array<String>>? = null
    private var isReadPermissionGranted = false
    private var isLocationPermissionGranted = false
    private var isCameraPermissionGranted = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {

        launcher = registerForActivityResult(RequestMultiplePermissions()) { it ->

            if (it[Manifest.permission.ACCESS_FINE_LOCATION] != null) {
                isReadPermissionGranted = it[Manifest.permission.ACCESS_FINE_LOCATION]!!

            }
            if (it[Manifest.permission.CAMERA] != null) {
                isReadPermissionGranted = it[Manifest.permission.CAMERA]!!

            }
        }

        requestPermission()
    }


    private fun requestPermission() {
        isLocationPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

        isCameraPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

        val permissionRequest: MutableList<String> = ArrayList()

        if (!isLocationPermissionGranted) {
            permissionRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!isCameraPermissionGranted) {
            permissionRequest.add(Manifest.permission.CAMERA)
        }
        if (permissionRequest.isNotEmpty()) {
            launcher!!.launch(permissionRequest.toTypedArray())
        }
    }
}
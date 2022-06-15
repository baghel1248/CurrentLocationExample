package com.example.currentlocationexample

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(),LocationListener {
    lateinit var textview1:TextView
    lateinit var textView2:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview1=findViewById(R.id.textview1)
        textView2=findViewById(R.id.textview2)

        var locationManager:LocationManager= getSystemService(LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,this)
    }

    override fun onLocationChanged(p0: Location) {
        var lat=p0.latitude
        var longi=p0.longitude
        textview1.setText("$lat \n $longi")

        val latitu = (lat * 10000.0).roundToInt() / 10000.0
        val longitu = (longi * 10000.0).roundToInt() / 10000.0

        if ((latitu>=28.5797 && latitu<=28.5799)&&(longitu>=77.3145 && longitu<=77.3149)){
            textView2.setText("You are Enter in Ducat")
        }
        else{
            textView2.setText("Out of Ducat")

            val birthdaySong: MediaPlayer = MediaPlayer.create(applicationContext,R.raw.song)
            birthdaySong.start()
//            var mobNumber:String="7388182148"
//            var intent:Intent=Intent(Intent.ACTION_CALL)
//            intent.setData(Uri.parse("tel:"+mobNumber))
//            startActivity(intent)

        }
    }
}
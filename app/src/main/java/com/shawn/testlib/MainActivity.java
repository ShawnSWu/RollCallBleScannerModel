package com.shawn.testlib;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tedpark.tedpermission.rx2.TedRx2Permission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RollCallBLEScanner rollCallBLEScanner = new RollCallBLEScanner
                .Builder()
                .build(this);


        TedRx2Permission.with(this)
                .setRationaleTitle("Title")
                .setRationaleMessage("Message") // "we need permission for read contact and find your location"
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .request()
                .subscribe(tedPermissionResult -> {
                    if (tedPermissionResult.isGranted()) {
                        //Permission to apply successfully
                        rollCallBLEScanner.startScan();
                    } else {
                        //Permission to apply failure
                    }
                }, throwable -> {
                }, () -> {
                });


        /*
          find Ble device.
         */
        //rollCallBLEScanner.startScan();

        /*
          stop find Ble device.
         */
        //rollCallBLEScanner.stopScan();

        /*
          continue scan after stop.
          deviceList will not clear.
         */
        //rollCallBLEScanner.continueScan();

    }
}

package com.shawn.testlib;

import android.Manifest;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.support.annotation.RequiresPermission;

import java.util.List;

/**
 * Created by Shawn.Wu on 2017/10/17.
 */

public class RollCallBLEScanner extends Scanner {

    private static ScanSettings scanSettings = null;

    private static List<ScanFilter> scanFilter = null;

    private RollCallBLEScanner(Context context) {
        final BluetoothManager bluetoothManager =
                (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothLeScanner = bluetoothManager.getAdapter().getBluetoothLeScanner();
    }

    private ScanCallback callback() {
        scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);

                BleDeviceItem bluetoothDevice = new BleDeviceItem(result.getDevice());
                String deviceAddress = bluetoothDevice.getAddress();

                if(!deviceAddressList.contains(deviceAddress)) {
                    deviceAddressList.add(deviceAddress);
                    deviceItemList.add(bluetoothDevice);

                }
            }
        };
        return scanCallback;
    }

    private void scan() {
        if(!isscaning) {
            if(scanSettings != null) {
                bluetoothLeScanner.startScan(scanFilter,scanSettings,callback());
            }else {
                bluetoothLeScanner.startScan(callback());
            }
            isscaning = true;
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_ADMIN)
    @Override
    public void startScan() {
        if(!isscaning){
            scan();
            deviceItemList.clear();
        }

    }

    @Override
    public void stopScan() {
        if(isscaning) {
            bluetoothLeScanner.stopScan(callback());
        }
    }

    @Override
    public void continueScan() {
        if(!isscaning) {
            scan();
        }
    }

    @Override
    public List<BleDeviceItem> getDeviceList() {return deviceItemList;}


    public static class Builder {

        Builder setScanSettings(ScanSettings scanSettings) {
            RollCallBLEScanner.scanSettings = scanSettings;
            return this;
        }

        Builder setScanFilter(List<ScanFilter> scanFilter) {
            RollCallBLEScanner.scanFilter = scanFilter;
            return this;
        }

        RollCallBLEScanner build(Context context) {
            return new RollCallBLEScanner(context);
        }

    }

}

# RollCall Scanner Model Demo


## Function :
<br>

|       name       |       description         |
|    -----------   |    -------------------    |
|   startScan()   |  start finding Ble device.|
|     stopScan()    |  stop find Ble device.    |
|    continueScan() |  continue scan after stop,deviceList will not clear.|
|   getDeviceList() |  get device List         |



## Example :

      RollCallBLEScanner rollCallBLEScanner = new RollCallBLEScanner
                .Builder()
                .build(this);
                
                
                

you also can set setScanSettings :

     RollCallBLEScanner rollCallBLEScanner = new RollCallBLEScanner
                .Builder()
                .setScanSettings(new ScanSettings())
                .build(this);

and set setScanFilter :

    List<ScanFilter> scanFilter = new ArrayList<>(); // whatever you want
     RollCallBLEScanner rollCallBLEScanner = new RollCallBLEScanner
                .Builder()
                .setScanSettings(new ScanSettings())
                .setScanFilter(scanFilter)
                .build(this);

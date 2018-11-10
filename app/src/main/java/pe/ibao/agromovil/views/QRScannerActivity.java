package pe.ibao.agromovil.views;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import pe.ibao.agromovil.R;

public class QRScannerActivity extends AppCompatActivity {

    TextView txtResult;
    SurfaceView cameraPreview;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    Camera camera;
    final int RequestCameraPermissionID = 1001;
    ConstraintLayout qRScannerLayout;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {


                        return;
                    }
                    try {
                        cameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);


        Display display = getWindowManager().getDefaultDisplay();
        int anchoQRScanner = display.getWidth();
        int  altoQRScaner= 4*(anchoQRScanner/3);
        qRScannerLayout = (ConstraintLayout)findViewById(R.id.qr_scanner_layout);
        qRScannerLayout.setMinWidth(anchoQRScanner);
        qRScannerLayout.setMinHeight(altoQRScaner);

        cameraPreview = (SurfaceView) findViewById(R.id.surfaceView);
        txtResult = (TextView) findViewById(R.id.qr_messagge);
        barcodeDetector = new BarcodeDetector
                .Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();
        //addEvent

        cameraPreview.setFocusable(true);
        cameraPreview.setFocusableInTouchMode(true);
        cameraPreview.requestFocus();
        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //pedida de permisos
                    ActivityCompat.requestPermissions(QRScannerActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            RequestCameraPermissionID);
                    return;
                }
                try {
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();
                if(qrCodes.size() != 0){
                    txtResult.post(new Runnable() {
                        @Override
                        public void run() {

                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                txtResult.setText(qrCodes.valueAt(0).displayValue);
                                vibrator.vibrate(500);
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra(newTestActivity.QR_RESULT,txtResult.getText());
                            setResult(Activity.RESULT_OK,returnIntent);
                            finish();
                        }
                    });
                }
            }
        });

    }
}

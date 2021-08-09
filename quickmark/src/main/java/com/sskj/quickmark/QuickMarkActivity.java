package com.sskj.quickmark;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.sskj.common.base.App;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.presenter.BasePresenter;
import com.sskj.quickmark.camera.CameraManager;
import com.sskj.quickmark.decoding.CaptureActivityHandler;
import com.sskj.quickmark.decoding.InactivityTimer;
import com.sskj.quickmark.view.ViewfinderView;

import java.io.IOException;
import java.util.Vector;


/**
 * (二维码扫描)
 */
@Route(path = RConfig.QUICKMARK_QRCODE)
public class QuickMarkActivity extends BaseActivity<BasePresenter> implements Callback {

    public static final int PERMISSION_REQUEST_CAMERA = 2000;
    private CaptureActivityHandler handler; //y

    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats; //y
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;  // 滴滴声
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
    /**
     * 闪光灯标记
     */
    private boolean isOpen = false;
    private boolean mIsDelivery;

    ViewfinderView viewfinderView;

    SurfaceView mSurfaceView;


    @Override
    public void onResume() {
        super.onResume();
        SurfaceHolder surfaceHolder = mSurfaceView.getHolder();
        //必须要判断 hasSurface 当surFaceView 创建成功后才能开启相机
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    /**
     * 闪光灯开关
     */
    public void mask(View v) {
        if (!isOpen) {
            CameraManager.get().enableFlash();
            isOpen = true;
        } else {  // 关灯
            CameraManager.get().disableFlash();
            isOpen = false;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.quickmark_activity_main;
    }

    @Override
    public BasePresenter getPresenter() {
        return new BasePresenter();
    }

    @Override
    protected void initView() {
        setTitle(App.INSTANCE.getString(R.string.quickmarkquickMarkActivity1));
        viewfinderView = findViewById(R.id.viewfinder_view);
        mSurfaceView = findViewById(R.id.preview_view);
        CameraManager.init(this);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
        requestPermission();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (inactivityTimer != null) {
            inactivityTimer.shutdown();
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String resultString = result.getText();
        if (TextUtils.isEmpty(resultString)) {
            restartPreviewAndDecode();
            Toast.makeText(this, getString(R.string.quickmark_quickMarkActivity1_j), Toast.LENGTH_SHORT).show();
        } else {
            //二维码扫描结果回调
//			ToastUtil.showLong(App.INSTANCE.getString(R.string.strQuickMarkActivity201) + resultString);
            Intent intent = new Intent();
            intent.putExtra("scan_result", resultString);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    /**
     * 扫描过程中的滴滴声
     */
    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            if (!mIsDelivery) {
                mediaPlayer.start();
            }
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    /**
     * 开启二次扫描
     */
    void restartPreviewAndDecode() {
        if (handler != null) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (handler != null) {
                        handler.restartPreviewAndDecode();
                    }
                }
            }, 1000);

        }
    }

    /**
     * 请求权限
     *
     * @return
     */
    boolean requestPermission() {
        if (ContextCompat.checkSelfPermission(QuickMarkActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);

            return false;
        }
    }

    /**
     * 权限请求回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != PERMISSION_REQUEST_CAMERA) {
            return;
        }

        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//开通权限

        } else {
            Toast.makeText(QuickMarkActivity.this, getString(R.string.quickmark_quickMarkActivity2_j), Toast.LENGTH_LONG).show();
        }
    }

}
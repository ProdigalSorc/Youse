package br.com.youse.redditfeed.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

import java.util.concurrent.CountDownLatch;

public class SplashActivity extends AppCompatActivity {

    private final CountDownLatch timeoutLatch = new CountDownLatch(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        final Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }

                timeoutLatch.countDown();
            }
        });
        thread.start();
        goLogin();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        timeoutLatch.countDown();
        return true;
    }

    private void goAfterSplashTimeout(final Intent intent) {
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    timeoutLatch.await();
                } catch (InterruptedException e) {
                }

                SplashActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
        thread.start();
    }

    protected void goLogin() {
        goAfterSplashTimeout(new Intent(this, PostsActivity.class));
    }

}

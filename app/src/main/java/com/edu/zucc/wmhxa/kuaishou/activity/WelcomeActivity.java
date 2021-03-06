package com.edu.zucc.wmhxa.kuaishou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.activity.login.LoginActivity;
import com.edu.zucc.wmhxa.kuaishou.model.BeanUsers;
import com.edu.zucc.wmhxa.kuaishou.util.SysApplication;

import java.util.Timer;
import java.util.TimerTask;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Administrator on 2017/7/19.
 */

public class WelcomeActivity extends Activity {

    private Intent intent ;
    private final Timer timer = new Timer();
    private TimerTask task;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 要做的事情
            if (BeanUsers.localUser != null) {
                intent = new Intent(WelcomeActivity.this, HomeActivity.class);
            } else {
                intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        SysApplication.getInstance().addActivity(this);

        //初始化计时器任务
        task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer.schedule(task, 3 * 1000);

    }
}

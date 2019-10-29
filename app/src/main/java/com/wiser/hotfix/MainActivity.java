package com.wiser.hotfix;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wiser.hotfix.tool.FileTool;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    String content = "666 / 0 = ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv_calculator);
    }

    //计算
    public void calculator(View view) {
        tv.setText(content + 666 / 0);
    }

    //修复
    public void fixBug(View view) {
        tv.setText(content);
        // 服务器下载的修复后的dex文件 这里模拟已经下载完 存在了SD卡中了
        File sourceFile = new File(Environment.getExternalStorageDirectory(),"classes2.dex");

        // 目标文件
        File targetFile = new File(getDir("odex", Context.MODE_PRIVATE).getAbsolutePath() + File.separator + "classes2.dex");

        try {
            FileTool.copyFile(sourceFile,targetFile);

            Toast.makeText(MainActivity.this,"复制完成",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

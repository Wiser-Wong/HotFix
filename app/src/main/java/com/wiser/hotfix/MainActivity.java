package com.wiser.hotfix;

import java.io.File;

import com.wiser.hotfix.tool.AssetTools;
import com.wiser.hotfix.tool.FileTool;
import com.wiser.hotfix.tool.FixDexTools;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	TextView	tv;

	String		content	= "666 / 0 = ";

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = findViewById(R.id.tv_calculator);

		tv.setText(content);
	}

	// 计算
	public void calculator(View view) {
		tv.setText(content + 666 / 0);
	}

	// 修复
	public void fixBug(View view) {

		// 模拟从服务端下载的dex文件，这里是从assets中获取文件保存到本地
		AssetTools.copyAssetToLocalFile(this, "classes2.dex", FileTool.DEX_FILE_SAVE_PATH, "classes2.dex");

		FixDexTools.loadFixedDex(this, new File(FileTool.DEX_FILE_SAVE_PATH));

		Toast.makeText(MainActivity.this, "修复完成，必须冷启动才会生效", Toast.LENGTH_SHORT).show();

	}

}

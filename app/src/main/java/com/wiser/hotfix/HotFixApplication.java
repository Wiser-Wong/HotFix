package com.wiser.hotfix;

import java.io.File;

import com.wiser.hotfix.tool.FileTool;
import com.wiser.hotfix.tool.FixDexTools;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class HotFixApplication extends MultiDexApplication {

	@Override protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(base);
		FixDexTools.loadFixedDex(base, new File(FileTool.DEX_FILE_SAVE_PATH));
	}
}

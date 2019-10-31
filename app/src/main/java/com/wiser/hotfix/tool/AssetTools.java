package com.wiser.hotfix.tool;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author Wiser
 * 
 *         Assets 工具
 */
public class AssetTools {

	/**
	 * 拷贝Assets文件到本地
	 * 
	 * @param mContext
	 *            上下文
	 * @param assetName
	 *            assets 中的文件名字
	 * @param savePath
	 *            保存路径
	 * @param saveName
	 *            保存名称
	 */
	public static void copyAssetToLocalFile(Context mContext, String assetName, String savePath, String saveName) {
		String filename = savePath + "/" + saveName;

		File dir = new File(savePath);
		// 如果目录不中存在，创建这个目录
		if (!dir.exists()) dir.mkdir();
		try {
			if (!(new File(filename)).exists()) {
				InputStream is = mContext.getResources().getAssets().open(assetName);
				FileOutputStream fos = new FileOutputStream(filename);
				byte[] buffer = new byte[2048];
				int count;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

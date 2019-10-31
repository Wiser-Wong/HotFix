package com.wiser.hotfix.tool;

import android.os.Environment;

import com.wiser.hotfix.IConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Wiser
 *
 *         文件工具
 */
public class FileTool {

	// 修复的dex文件保存路径
	public static final String DEX_FILE_SAVE_PATH = Environment.getExternalStorageDirectory() + File.separator + IConstant.OPT_NAME;

	// 文件copy
	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		if (!sourceFile.exists()) {
			throw new IOException("源文件不存在，无法复制");
		}

		FileInputStream fileInputStream = new FileInputStream(sourceFile);
		FileOutputStream fileOutputStream = new FileOutputStream(targetFile);

		byte[] buffer = new byte[1024];
		int byteRead;
		while (-1 != (byteRead = fileInputStream.read(buffer))) {
			fileOutputStream.write(buffer, 0, byteRead);
		}
		fileInputStream.close();
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	/**
	 * 清空文件夹
	 *
	 * @param dirPath
	 */
	public static void clearFolder(String dirPath) {
		File dir = new File(dirPath);// 清空文件夹
		File[] files = dir.listFiles();
		if (null != files && files.length > 0) {
			for (File file : files) {
				file.delete();
			}
		}
	}

}

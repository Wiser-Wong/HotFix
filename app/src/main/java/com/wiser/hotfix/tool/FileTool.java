package com.wiser.hotfix.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

	// 文件copy
	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		if (targetFile.exists()) targetFile.delete();

		FileInputStream fis = new FileInputStream(sourceFile);
		BufferedInputStream bis = new BufferedInputStream(fis);

		FileOutputStream fos = new FileOutputStream(targetFile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		byte[] buffers = new byte[1024];
		int length;
		while ((length = bis.read(buffers)) != -1) {
			bos.write(buffers, 0, length);
		}
		fis.close();
		bis.close();
		fos.close();
		fos.flush();
		bos.close();
		bos.flush();
	}

}

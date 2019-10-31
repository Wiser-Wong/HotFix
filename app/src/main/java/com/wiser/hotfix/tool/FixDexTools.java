package com.wiser.hotfix.tool;

import java.io.File;
import java.util.HashSet;

import com.wiser.hotfix.IConstant;

import android.content.Context;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * @author Wiser
 *
 *         修复dex工具
 */
public class FixDexTools {

	// 修复dex集合
	private static HashSet<File> dexs = new HashSet<>();

	static {
		dexs.clear();
	}

	/**
	 * 加载修复包
	 *
	 * @param context
	 */
	public static void loadFixedDex(Context context, File fileDir) {
		if (context == null || fileDir == null || !fileDir.exists()) return;

		File[] files = fileDir.listFiles();
		for (File file : files) {
			if (file.getName().endsWith(IConstant.END_WITH) && !"classes.dex".equals(file.getName())) {
				dexs.add(file);
			}
		}

		// 创建类加载器
		createDexClassLoader(context, fileDir);
	}

	private static void createDexClassLoader(Context context, File fileDir) {
		// 临时解压目录
		// String optimizedDirectory = fileDir.getAbsolutePath() + File.separator +
		// IConstant.OPT_NAME;
		// File fileOpt = new File(optimizedDirectory);
		// if (!fileOpt.exists()) fileOpt.mkdirs();
		String optimizedDirectory = context.getDir(IConstant.OPT_NAME, Context.MODE_PRIVATE).getAbsolutePath();
		DexClassLoader myClassLoader;
		for (File dex : dexs) {
			// String dexPath, String optimizedDirectory, String librarySearchPath,
			// ClassLoader parent
			myClassLoader = new DexClassLoader(dex.getAbsolutePath(), optimizedDirectory, dex.getAbsolutePath(), context.getClassLoader());

			// 热修复开始
			try {
				hotFix(myClassLoader, context);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static void hotFix(DexClassLoader myClassLoader, Context context) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
		// 系统类加载器
		PathClassLoader sysClassLoader = (PathClassLoader) context.getClassLoader();

		// 自己的dex数组
		Object myDexElements = ReflectTools.getDexElements(ReflectTools.getPathList(myClassLoader));

		// 系统的dex数值
		Object sysDexElements = ReflectTools.getDexElements(ReflectTools.getPathList(sysClassLoader));

		// 合并自己的dex和系统的dex数组
		Object dexElements = JavaTools.combineArray(myDexElements, sysDexElements);

		// 拿到系统的DexPathList对象
		Object sysPathList = ReflectTools.getPathList(sysClassLoader);

		// 给系统的DexPathList类中dexElements数组重新赋值
		ReflectTools.setField(sysPathList, sysPathList.getClass(), dexElements);
	}

}

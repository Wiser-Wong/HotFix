package com.wiser.hotfix.tool;

import java.lang.reflect.Array;

/**
 * @author Wiser
 * 
 *         Java工具
 */
public class JavaTools {

	/**
	 * 合并数组
	 * 
	 * @param arrOne
	 *            插队数组
	 * @param arrTwo
	 *            已有数组
	 * @return 合并数组
	 */
	public static Object combineArray(Object arrOne, Object arrTwo) {
		// 获取一个数组Class对象，通过Array.newInstance()可以反射生成数组对象
		Class<?> localClass = arrOne.getClass().getComponentType();
		// 前数组长度
		int i = Array.getLength(arrOne);
		// 新数组长度 = 前数组长度 + 后数组长度
		int j = i + Array.getLength(arrTwo);
		// 生成数组对象
		Object result = Array.newInstance(localClass, j);
		for (int k = 0; k < j; ++k) {
			// 先把自己放入数组
			if (k < i) {
				// 从0开始变量，如果前数组有值，添加到新数组的第一个位置
				Array.set(result, k, Array.get(arrOne, k));
			} else {
				// 添加完前数组，再添加后数组，合并完成
				Array.set(result, k, Array.get(arrTwo, k - i));
			}
		}
		return result;
	}

}

package com.wiser.hotfix.tool;

import java.lang.reflect.Field;

/**
 * @author Wiser
 * 
 *         反射工具类
 */
public class ReflectTools {

	/**
	 * 通过反射获取成员变量
	 * 
	 * @param obj
	 * @param clazz
	 * @param field
	 * @return
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	private static Object getField(Object obj, Class<?> clazz, String field) throws NoSuchFieldException, IllegalAccessException {
		Field localField = clazz.getDeclaredField(field);
		localField.setAccessible(true);
		return localField.get(obj);
	}

	/**
	 * 给系统DexPathList 类 中的成员变量dexElements数组赋予新值
	 * 
	 * @param obj
	 * @param clazz
	 * @param value
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	public static void setField(Object obj, Class<?> clazz, Object value) throws NoSuchFieldException, IllegalAccessException {
		Field localField = clazz.getDeclaredField("dexElements");
		localField.setAccessible(true);
		localField.set(obj, value);
	}

	/**
	 * 通过反射获取系统的DexPathList对象pathList
	 * 
	 * @param baseDexClassLoader
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	public static Object getPathList(Object baseDexClassLoader) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
		return getField(baseDexClassLoader, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
	}

    /**
     * 获取系统的DexPathList类中的dexElements数值对象¬
     * @param paramObject
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
	public static Object getDexElements(Object paramObject) throws NoSuchFieldException, IllegalAccessException {
		return getField(paramObject, paramObject.getClass(), "dexElements");
	}

}

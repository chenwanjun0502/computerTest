package com.answertest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class OpenSharedPerferences {
	/**
	 * д�뱾��
	 * @param context
	 * @param file_name
	 * @param map
	 */
	public static void write(Context context,String  file_name,Map<String,Object>  map){
		SharedPreferences  sharedPreferences=context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
		SharedPreferences.Editor  editor=sharedPreferences.edit();
		for (String  key:map.keySet()) {
			editor.putString(key, map.get(key).toString());
		}
		editor.commit();
	}  
	/**
	 *  ��ȡ
	 * @param context
	 * @param file_name
	 * @param key
	 * @return
	 */
	public static String read(Context context,String  file_name,String key){
		String  result="";
		SharedPreferences  sharedPreferences=context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
		result=sharedPreferences.getString(key, "");
		return result;
	}  
}

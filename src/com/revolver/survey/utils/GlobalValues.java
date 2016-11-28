package com.revolver.survey.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 题型初始化类
 * 
 * @author REVOLVER
 * 
 */
public class GlobalValues {
	public static final Map<Integer, String> QUESTION_TYPES = new HashMap<Integer, String>();
	public static final int SURVEY_LIST_PAGE_SIZE = 10;
	public static final int SURVEY_PAGE_SIZE = 30;
	public static final int RES_PAGE_SIZE = 100;
	public static final int AUTH_PAGE_SIZE = 10;
	public static final int ADMIN_PAGE_SIZE = 20;
	public static final int GENERATE_SIZE = 10;
	public static final int LOG_PAGE_SIZE = 20;
	static {
		QUESTION_TYPES.put(0, "单选题");
		QUESTION_TYPES.put(1, "多选题");
		QUESTION_TYPES.put(2, "下拉列表选择题");
		QUESTION_TYPES.put(3, "简答题");
		QUESTION_TYPES.put(4, "矩阵单选题");
		QUESTION_TYPES.put(5, "矩阵多选题");
		QUESTION_TYPES.put(6, "矩阵下拉列表选择题");
	}

	/**
	 * 单选题 多选题 下拉列表选择题 简答题 矩阵单选题 矩阵多选题 矩阵下拉列表选择题
	 */

}

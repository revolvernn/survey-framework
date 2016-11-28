package com.revolver.survey.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revolver.survey.guest.entity.Bag;

/**
 * 验证工具
 * 
 * @author REVOLVER
 * 
 */
public class ValidateUtils {

	/**
	 * 验证路径
	 * 
	 * @param logoPath
	 * @return
	 */
	public static boolean stringValidate(String source) {
		return source != null && source.length() > 0;
	}

	public static boolean validateIngeter(Integer source) {
		return source != null;
	}

	@SuppressWarnings("rawtypes")
	public static boolean validateCollection(Collection collections) {
		return collections != null && collections.size() > 0;
	}

	public static boolean validateList(List<Bag> bagList) {

		Set<Integer> bagOrderSet = new HashSet<Integer>();

		for (Bag bag : bagList) {
			bagOrderSet.add(bag.getBagOrder());
		}

		return bagList.size() == bagOrderSet.size();
	}

	public static boolean validateMap(Map<String, String[]> parameters) {
		return parameters != null && parameters.size() > 0;
	}

	public static boolean validateArray(Object[] paramArr) {
		return paramArr != null && paramArr.length > 0;
	}
}

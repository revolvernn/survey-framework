package com.revolver.survey.utils;

/**
 * 
 * @author REVOLVER
 * 
 */
public class HTMLTagUtil {
	/**
	 * 单选题 <input id="questionId01" type="radio" name="question5" value="0"><label for="questionId01">选项01</label><br>
	 * 
	 * @param idStr
	 * @param name
	 * @param value
	 * @return
	 */
	public static String generateRadio(String idStr, String name, String value, String label, String checkedStr, boolean isBr) {

		return "<input id='" + idStr + "' type='radio' name='" + name + "' value='" + value + "' " + checkedStr + " /><label for='" + idStr + "' >" + label + "</label>" + (isBr ? "<br>" : "");
	}

	/**
	 * 多选题 <input id="idStr" type="checkbox" name="name" value="value"><label for="idStr">选项01</label><br>
	 * 
	 * @param idStr
	 * @param name
	 * @param value
	 * @return
	 */
	public static String generateCheckBox(String idStr, String name, String value, String label, String checkedStr, boolean isBr) {

		return "<input id='" + idStr + "' type='checkbox' name='" + name + "' value='" + value + "' " + checkedStr + " /><label for='" + idStr + "' >" + label + "</label>" + (isBr ? "<br>" : "");
	}

	/**
	 * 下拉Options <option value="value" selected="selected">label</option>
	 * 
	 * @param value
	 * @param label
	 * @param selectedStr
	 * @return
	 */
	public static String generateOption(String value, String label, String selected) {

		return "<option value='" + value + "' " + selected + ">" + label + "</option>";
	}

	/**
	 * 下拉列表 <select name="name"> <option value="value" selected="selected">label</option> .... <option value="value" selected="selected">label</option> </select>
	 * 
	 * @param name
	 * @param allOptionStr
	 * @return
	 */
	public static String generateSelect(String name, String allOptionStr) {

		return "<select name='" + name + "'>" + allOptionStr + "</select>";
	}

	/**
	 * 文本框 <input type="text" name="name" value="value"/>其它
	 * 
	 * @param name
	 * @param value
	 * @param otherText
	 * @return
	 */
	public static String generateText(String name, String value, boolean isOtherText) {

		return "<input type='text' name='" + name + "' value='" + value + "'/>" + (isOtherText ? "其它" : "");
	}

	// ------------------generteTable----------------------
	/**
	 * "<td>"+content+"</td>"
	 * 
	 * @param content
	 * @return
	 */
	public static String generateTd(String content) {

		return "<td>" + content + "</td>";
	}

	/**
	 * "
	 * <tr>
	 * "+tds+"
	 * </tr>
	 * "
	 * 
	 * @param tdStrs
	 * @return
	 */
	public static String generateTr(String tds) {

		return "<tr>" + tds + "</tr>";
	}

	/**
	 * "
	 * <table class='dashedTable'>
	 * "+trs+"
	 * </table>
	 * ";
	 * 
	 * @param trs
	 * @return
	 */
	public static String generateTable(String trs) {

		return "<table class='dashedTable'>" + trs + "</table>";
	}
}

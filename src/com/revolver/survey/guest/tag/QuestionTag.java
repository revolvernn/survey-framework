package com.revolver.survey.guest.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.revolver.survey.guest.entity.Question;
import com.revolver.survey.utils.GlobalNames;
import com.revolver.survey.utils.HTMLTagUtil;
import com.revolver.survey.utils.ValidateUtils;

/**
 * 
 * @author REVOLVER
 * 
 */
public class QuestionTag extends SimpleTagSupport {

	private String currentBagIdOGNL;

	@SuppressWarnings("unchecked")
	@Override
	public void doTag() throws JspException, IOException {

		PageContext pageContext = (PageContext) getJspContext();

		JspWriter out = pageContext.getOut();

		StringBuilder finalBuilder = new StringBuilder();

		Map<Integer, Map<String, String[]>> allBagMap = (Map<Integer, Map<String, String[]>>) pageContext.getSession().getAttribute(GlobalNames.ALL_BAG_MAP);

		Integer currentBagId = parseBagId(currentBagIdOGNL);

		Map<String, String[]> parameters = allBagMap.get(currentBagId);
		// if(!ValidateUtils.validateMap(parameters))
		// 获取Question对象
		Question question = getQuestion();
		Integer questionId = question.getQuestionId();
		int questionType = question.getQuestionType();
		// 判断题型
		// 单选题
		if (questionType == 0) {

			String[] optionsArray = question.getOptionsArray();

			for (int i = 0; i < optionsArray.length; i++) {

				// 准备数据
				String idStr = "radio" + questionId + i;
				String name = "question" + questionId;
				String value = "" + i;
				String label = optionsArray[i];

				boolean reDisplay = checkedReDisplay(parameters, name, value);

				String checkedStr = reDisplay ? "checked='checked'" : "";
				boolean isBr = question.isBr();
				// 生成标签
				String radio = HTMLTagUtil.generateRadio(idStr, name, value, label, checkedStr, isBr);

				finalBuilder.append(radio);
			}
			// 是否有其它项
			radioHasOther(finalBuilder, parameters, question, questionId);

		}

		// 多选题
		if (questionType == 1) {
			String[] optionsArray = question.getOptionsArray();

			for (int i = 0; i < optionsArray.length; i++) {

				// 准备数据
				String idStr = "checkbox" + questionId + i;
				String name = "question" + questionId;
				String value = "" + i;
				String label = optionsArray[i];

				boolean reDisplay = checkedReDisplay(parameters, name, value);

				String checkedStr = reDisplay ? "checked='checked'" : "";
				boolean isBr = question.isBr();
				// 生成标签
				String checkbox = HTMLTagUtil.generateCheckBox(idStr, name, value, label, checkedStr, isBr);

				finalBuilder.append(checkbox);
			}
			// 是否有其它项
			checkboxHasOther(finalBuilder, parameters, question, questionId);
		}
		// 下拉列表选择题
		if (questionType == 2) {
			String[] optionsArray = question.getOptionsArray();
			StringBuilder optionsBuilder = new StringBuilder();

			String name = "question" + questionId;

			for (int i = 0; i < optionsArray.length; i++) {
				String value = "" + i;
				String label = optionsArray[i];

				boolean reDisplay = checkedReDisplay(parameters, name, value);

				String selected = reDisplay ? "selected='selected'" : "";

				String option = HTMLTagUtil.generateOption(value, label, selected);

				optionsBuilder.append(option);
			}

			String select = HTMLTagUtil.generateSelect(name, optionsBuilder.toString());

			finalBuilder.append(select);
		}
		// 简答题
		if (questionType == 3) {
			String name = "question" + questionId;
			String value = textReDisplay(parameters, name);
			String text = HTMLTagUtil.generateText(name, value, false);

			finalBuilder.append(text);
		}
		// 矩阵单选题
		// 矩阵多选题
		// 矩阵下拉列表选择题

		if (questionType == 4 || questionType == 5 || questionType == 6) {

			StringBuilder tdBuilder = new StringBuilder();

			String tdStr = HTMLTagUtil.generateTd("&nbsp;");

			tdBuilder.append(tdStr);
			String[] matrixColTitlesArray = question.getMatrixColTitlesArray();
			for (int i = 0; i < matrixColTitlesArray.length; i++) {

				String colTitles = matrixColTitlesArray[i];
				tdStr = HTMLTagUtil.generateTd(colTitles);
				tdBuilder.append(tdStr);
			}

			String allTdStr = tdBuilder.toString();
			String trStr = HTMLTagUtil.generateTr(allTdStr);
			StringBuilder trBuilder = new StringBuilder();
			trBuilder.append(trStr);

			tdBuilder.delete(0, tdBuilder.length());

			String[] matrixRowTitlesArray = question.getMatrixRowTitlesArray();

			for (int i = 0; i < matrixRowTitlesArray.length; i++) {
				String rowTiles = matrixRowTitlesArray[i];
				tdStr = HTMLTagUtil.generateTd(rowTiles);
				tdBuilder.append(tdStr);

				String cellContnet = null;

				for (int j = 0; j < matrixColTitlesArray.length; j++) {
					if (questionType == 4) {

						String idStr = "matrix" + questionId + i + j;
						String name = "question" + questionId + "_" + i;
						// 0_0,0_1,...
						String value = i + "_" + j;
						String label = "点我";

						boolean redisplay = checkedReDisplay(parameters, name, value);

						String checkedStr = redisplay ? "checked='checked'" : "";

						cellContnet = HTMLTagUtil.generateRadio(idStr, name, value, label, checkedStr, false);

					}

					// 如果是矩阵多选题
					if (questionType == 5) {

						String idStr = "matrix" + questionId + i + j;
						String label = "点我";

						// question23_2
						String name = "question" + questionId;

						// 0_0,0_1,...
						String value = i + "_" + j;

						boolean redisplay = checkedReDisplay(parameters, name, value);

						String checkedStr = redisplay ? "checked='checked'" : "";

						cellContnet = HTMLTagUtil.generateCheckBox(idStr, name, value, label, checkedStr, false);
					}

					// 如果是下拉列表选择题
					if (questionType == 6) {

						// question23_2
						String name = "question" + questionId;

						// 0_0_0,0_0_1,...
						StringBuilder optionBuilder = new StringBuilder();
						String[] optionArray = question.getMatrixOptionsArray();
						for (int k = 0; k < optionArray.length; k++) {
							// <option value='xxx'>xxx</option>
							String value = i + "_" + j + "_" + k;
							String label = optionArray[k];

							boolean redisplay = checkedReDisplay(parameters, name, value);

							String selected = redisplay ? "selected='selected'" : "";

							String option = HTMLTagUtil.generateOption(value, label, selected);
							optionBuilder.append(option);
						}

						cellContnet = HTMLTagUtil.generateSelect(name, optionBuilder.toString());
					}

					tdStr = HTMLTagUtil.generateTd(cellContnet);

					tdBuilder.append(tdStr);
				}

				trStr = HTMLTagUtil.generateTr(tdBuilder.toString());
				trBuilder.append(trStr);
				tdBuilder.delete(0, tdBuilder.length());

			}

			String allTrStr = trBuilder.toString();
			String table = HTMLTagUtil.generateTable(allTrStr);
			finalBuilder.append(table);

		}
		out.println(finalBuilder.toString());
	}

	private void checkboxHasOther(StringBuilder finalBuilder, Map<String, String[]> parameters, Question question, Integer questionId) {
		// 其它项
		if (question.isHasOther()) {
			int otherType = question.getOtherType();

			// 和主题型一致
			if (otherType == 0) {
				String idStr = "other" + questionId;
				String name = "question" + questionId;
				String value = "other";

				boolean reDisplay = checkedReDisplay(parameters, name, value);

				String checkedStr = reDisplay ? "checked='checked'" : "";
				boolean isBr = question.isBr();

				String otherRadio = HTMLTagUtil.generateCheckBox(idStr, name, value, "其它", checkedStr, isBr);
				finalBuilder.append(otherRadio);
			}

			// 文本框
			if (otherType == 1) {

				String name = "question" + questionId + "other";
				String value = textReDisplay(parameters, name);

				String generateText = HTMLTagUtil.generateText(name, value, true);

				finalBuilder.append(generateText);
			}
		}
	}

	private void radioHasOther(StringBuilder finalBuilder, Map<String, String[]> parameters, Question question, Integer questionId) {
		// 其它项
		if (question.isHasOther()) {
			int otherType = question.getOtherType();

			// 和主题型一致
			if (otherType == 0) {
				String idStr = "other" + questionId;
				String name = "question" + questionId;
				String value = "other";

				boolean reDisplay = checkedReDisplay(parameters, name, value);

				String checkedStr = reDisplay ? "checked='checked'" : "";
				boolean isBr = question.isBr();

				String otherRadio = HTMLTagUtil.generateRadio(idStr, name, value, "其它", checkedStr, isBr);
				finalBuilder.append(otherRadio);
			}

			// 文本框
			if (otherType == 1) {

				String name = "question" + questionId + "other";
				String value = textReDisplay(parameters, name);

				String generateText = HTMLTagUtil.generateText(name, value, true);

				finalBuilder.append(generateText);
			}
		}
	}

	// 解析bagId
	public Integer parseBagId(String currentBagIdOGNL) {
		return (Integer) ActionContext.getContext().getValueStack().findValue(currentBagIdOGNL);
	}

	// 判断是否要回显
	public boolean checkedReDisplay(Map<String, String[]> parameters, String name, String currentValue) {

		if (!ValidateUtils.validateMap(parameters))
			return false;

		String[] paramArr = parameters.get(name);

		if (!ValidateUtils.validateArray(paramArr))
			return false;

		for (String paramValue : paramArr) {
			if (paramValue.equals(currentValue)) {
				return true;
			}
		}
		return false;
	}

	// 判断是否要回显
	public String textReDisplay(Map<String, String[]> parameters, String name) {

		if (!ValidateUtils.validateMap(parameters))
			return "";

		String[] paramArr = parameters.get(name);

		if (!ValidateUtils.validateArray(paramArr))
			return "";

		return paramArr[0];
	}

	public Question getQuestion() {

		// 获取值栈对象
		ValueStack valueStack = ActionContext.getContext().getValueStack();

		Object object = valueStack.getRoot().get(0);

		return (Question) object;
	}

	public void setCurrentBagIdOGNL(String currentBagIdOGNL) {
		this.currentBagIdOGNL = currentBagIdOGNL;
	}
}

package com.revolver.survey.guest.entity;

import java.io.Serializable;

import com.revolver.survey.utils.DataProcessUtils;

/**
 * 问题类
 * 
 * @author REVOLVER
 * 
 */
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	// OID
	private Integer questionId;

	// 包裹
	private Bag bag;

	// 题干
	private String questionName;

	// 题型
	private int questionType;

	// 是否换行
	private boolean br;

	// 是否设置其他项
	private boolean hasOther;

	// 其他项形式
	// 0：和主题型一致
	// 1：文本框
	private int otherType;

	public Question() {
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Bag getBag() {
		return bag;
	}

	public void setBag(Bag bag) {
		this.bag = bag;
	}

	public boolean isBr() {
		return br;
	}

	public void setBr(boolean br) {
		this.br = br;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public boolean isHasOther() {
		return hasOther;
	}

	public void setHasOther(boolean hasOther) {
		this.hasOther = hasOther;
	}

	public int getOtherType() {
		return otherType;
	}

	public void setOtherType(int otherType) {
		this.otherType = otherType;
	}

	// ----------需要特殊处理的属性---------------
	// 单选/多选/下拉列选
	private String options;

	// 矩阵式问题的行标题组
	private String matrixRowTitles;

	// 矩阵式问题的列标题组
	private String matrixColTitles;

	// 矩阵式下拉列表问题的选项
	private String matrixOptions;

	// ----------需要特殊处理的属性---------------

	// ==========需要特殊处理的getXxx()setXxx()================

	// ------------options 单选项 多选项组--------------------------------
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = DataProcessUtils.toStrBrReComma(options);
	}

	public String getOptionsForShow() {
		return DataProcessUtils.toStrCommaReBr(options);
	}

	// 获取 options 数组
	public String[] getOptionsArray() {
		return DataProcessUtils.toArrayStrOffComma(options);
	}

	// ------------matrixRowTitles 行标签组--------------------------------
	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	// 保存将 \r\n 换成 , 的字符串
	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = DataProcessUtils.toStrBrReComma(matrixRowTitles);
	}

	// 获取将 , 换成 \r\n 的字符串
	public String getMatrixRowTitlesForShow() {
		return DataProcessUtils.toStrCommaReBr(matrixRowTitles);
	}

	// 获取 matrixRowTitles 数组
	public String[] getMatrixRowTitlesArray() {
		return DataProcessUtils.toArrayStrOffComma(matrixRowTitles);
	}

	// ------------matrixColTitles 列标签组--------------------------------
	public String getMatrixColTitles() {
		return matrixColTitles;
	}

	// 保存将 \r\n 换成 , 的字符串
	public void setMatrixColTitles(String matrixColTitles) {
		this.matrixColTitles = DataProcessUtils.toStrBrReComma(matrixColTitles);
	}

	// 获取将 , 换成 \r\n 的字符串
	public String getMatrixColTitlesForShow() {
		return DataProcessUtils.toStrCommaReBr(matrixColTitles);
	}

	// 获取 matrixColTitles 数组
	public String[] getMatrixColTitlesArray() {
		return DataProcessUtils.toArrayStrOffComma(matrixColTitles);
	}

	// ------------matrixOptions 下拉列表集合--------------------------------
	public String getMatrixOptions() {
		return matrixOptions;
	}

	// 保存将 \r\n 换成 , 的字符串
	public void setMatrixOptions(String matrixOptions) {
		this.matrixOptions = DataProcessUtils.toStrBrReComma(matrixOptions);
	}

	// 获取将 , 换成 \r\n 的字符串
	public String getMatrixOptionsForShow() {
		return DataProcessUtils.toStrCommaReBr(matrixOptions);
	}

	// 获取 matrixOptions 数组
	public String[] getMatrixOptionsArray() {
		return DataProcessUtils.toArrayStrOffComma(matrixOptions);
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionName=" + questionName + ", questionType=" + questionType + "]";
	}
}

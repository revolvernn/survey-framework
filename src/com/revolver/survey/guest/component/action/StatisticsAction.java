package com.revolver.survey.guest.component.action;

import java.util.List;

import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.revolver.survey.base.m.BaseAction;
import com.revolver.survey.guest.component.service.i.QuestionService;
import com.revolver.survey.guest.component.service.i.StatisticsService;
import com.revolver.survey.guest.component.service.i.SurveyService;
import com.revolver.survey.guest.entity.Question;
import com.revolver.survey.guest.entity.Survey;
import com.revolver.survey.guest.model.OptionStatisticModel;
import com.revolver.survey.guest.model.QuestionStatisticModel;
import com.revolver.survey.utils.DataProcessUtils;
import com.revolver.survey.utils.GlobalNames;

/**
 * 
 * @author REVOLVER
 * 
 */

@Controller
@Scope("prototype")
public class StatisticsAction extends BaseAction<Survey> {
	// -------------------属性--------------------------
	private static final long serialVersionUID = 1L;
	private int height;
	private int width;

	private JFreeChart chart;
	@Autowired
	private SurveyService surveyService;
	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private QuestionService questionService;

	private Integer questionId;

	private Integer rowNo;
	private Integer colNo;

	// -------------------Action方法--------------------------
	public String showTextList() {

		List<String> textList = statisticsService.getTextList(questionId);
		reqMap.put(GlobalNames.TEXT_LIST, textList);

		return "toTextListPage";
	}

	public String showOtherTextList() {

		List<String> textList = statisticsService.getOtherTextList(questionId);
		reqMap.put(GlobalNames.TEXT_LIST, textList);

		return "toTextListPage";
	}

	// -----------------生成图表方法----------------------------------------

	public String showOptionMatrix() throws Exception {
		
		Question question = questionService.getEntityById(questionId);

		reqMap.put(GlobalNames.QUESTION, question);

		return "toNormalMatrixPage";
	}

	public String showOptionMatrixChart() throws Exception {
		List<OptionStatisticModel> osmList = statisticsService.getOsmList(questionId, rowNo, colNo);

		this.chart = DataProcessUtils.generateChartByOsmList(osmList);

		this.width = 300;
		this.height = 200;
		return "toChartResult";
	}

	public String showNormalMatrixChart() throws Exception {
		List<OptionStatisticModel> osmList = statisticsService.getOsmList(questionId, rowNo);

		this.chart = DataProcessUtils.generateChartByOsmList(osmList);

		this.width = 300;
		this.height = 200;
		return "toChartResult";
	}

	public String showNormalMatrix() throws Exception {
		
		Question question = questionService.getEntityById(questionId);

		reqMap.put(GlobalNames.QUESTION, question);

		return "toNormalMatrixPage";
	}

	public String showNormalChart() {
		
		QuestionStatisticModel qsm = statisticsService.getQsm(questionId);

		this.chart = DataProcessUtils.generateChartByOsm(qsm);
		this.width = 1024;
		this.height = 768;
		return "toChartResult";
	} 

	public void prepareShowSummary() throws Exception {
		this.t = surveyService.getEntityById(surveyId);
	}

	public String showSummary() {

		return "toShowSummary";
	}

	// -------------------getXxx()/setXxx()方法-----------------------------
	
	public JFreeChart getChart() {
		return chart;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}

	public void setColNo(Integer colNo) {
		this.colNo = colNo;
	}
}

package com.revolver.survey.guest.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.revolver.survey.base.m.BaseAction;
import com.revolver.survey.guest.component.service.i.QuestionService;
import com.revolver.survey.guest.entity.Question;
import com.revolver.survey.utils.ValidateUtils;

/**
 * 
 * @author REVOLVER
 * 
 */
@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {

	// *******************成员变量区********************
	private static final long serialVersionUID = 1L;
	@Autowired
	private QuestionService questionService;

	private Integer questionId;

	// ******************Action()区********************
	// -----------------Question CRUD---------------------
	/**
	 * 删除问题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String removeQuestion() throws Exception {
		questionService.deleteEntity(t);
		return "toDesignPage";
	}

	/**
	 * 更新和保存问题
	 * 
	 * @throws Exception
	 */
	public void prepareSaveOrUpdate() throws Exception {
		if (ValidateUtils.validateIngeter(questionId)) {
			this.t = questionService.getEntityById(questionId);
		}
	}

	public String saveOrUpdate() {

		questionService.saveOrUpdateQuestion(t);

		return "toDesignPage";
	}

	// -----------------跳转页面---------------------

	/**
	 * 转到选择题型页面
	 * 
	 * @return
	 */
	public void prepareEditQuestion() throws Exception {
		this.t = questionService.getEntityById(questionId);
	}

	public String editQuestion() {

		return "toTypeChosenPage";
	}

	/**
	 * 去选择题型页面
	 * 
	 * @return
	 * @throws Exception
	 */

	public String toTypeChosen() {
		return "toTypeChosenPage";
	}

	/**
	 * 到问题设计页面
	 * 
	 * @throws Exception
	 */
	public void prepareToQuestionDesign() throws Exception {
		if (ValidateUtils.validateIngeter(questionId)) {
			this.t = questionService.getEntityById(questionId);
		}
	}

	public String toQuestionDesign() {

		return "toQuestionDesignPage";
	}

	// **************getXxx()/setXxx()区***************

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
}

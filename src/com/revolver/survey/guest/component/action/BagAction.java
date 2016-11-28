package com.revolver.survey.guest.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.revolver.survey.base.m.BaseAction;
import com.revolver.survey.e.CatchDelBagException;
import com.revolver.survey.guest.component.service.i.BagService;
import com.revolver.survey.guest.entity.Bag;
import com.revolver.survey.guest.entity.Survey;

/**
 * 
 * @author REVOLVER
 * 
 */
@Controller
@Scope("prototype")
public class BagAction extends BaseAction<Bag> {
	private static final long serialVersionUID = 1L;
	// *******************成员变量区********************
	@Autowired
	private BagService bagService;

	// ******************Action()区********************
	// -----------------bag CRUD---------------------

	public String copyBagToSurvey() {
		bagService.copyBagToSurvey(bagId, surveyId);
		addActionMessage("包裹复制成功!!!");
		return "toCopyOrMoveMessagePage";
	}

	public String moveBagToSurvey() {
		bagService.moveBagToSurvey(bagId, surveyId);
		addActionMessage("包裹移动成功!!!");
		return "toCopyOrMoveMessagePage";
	}

	/**
	 * 保存包裹
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		Survey survey = new Survey();
		survey.setSurveyId(surveyId);

		this.t.setSurvey(survey);

		bagService.saveEntity(t);

		return "toDesignPage";
	}

	/**
	 * 删除包裹
	 * 
	 * @return
	 */
	public String removeBag() {

		try {
			bagService.deleteEntity(t);
		} catch (Exception e) {
			throw new CatchDelBagException();
		}
		return "toDesignPage";
	}

	/**
	 * 更新包裹
	 * 
	 * @throws Exception
	 */
	public void prepareUpdate() throws Exception {
		this.t = bagService.getEntityById(bagId);
	}

	public String update() throws Exception {
		Survey survey = new Survey();
		survey.setSurveyId(surveyId);

		this.t.setSurvey(survey);

		bagService.updateEntity(t);

		return "toDesignPage";
	}

	// -----------------跳转页面---------------------

	public void prepareEditBag() throws Exception {
		this.t = bagService.getEntityById(bagId);
	}

	public String editBag() {
		return "toEditBagPage";
	}

	// **************getXxx()/setXxx()区***************

}

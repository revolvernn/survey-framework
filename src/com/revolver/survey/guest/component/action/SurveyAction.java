package com.revolver.survey.guest.component.action;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.revolver.survey.base.i.UserAware;
import com.revolver.survey.base.m.BaseAction;
import com.revolver.survey.e.CatchDelSurveyException;
import com.revolver.survey.guest.component.service.i.BagService;
import com.revolver.survey.guest.component.service.i.SurveyService;
import com.revolver.survey.guest.entity.Bag;
import com.revolver.survey.guest.entity.Survey;
import com.revolver.survey.guest.entity.User;
import com.revolver.survey.guest.model.Page;
import com.revolver.survey.utils.DataProcessUtils;
import com.revolver.survey.utils.GlobalNames;
import com.revolver.survey.utils.GlobalValues;
import com.revolver.survey.utils.ValidateUtils;

/**
 * 
 * @author REVOLVER
 * 
 */
@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware<Survey> {

	// *******************成员变量区********************
	private static final long serialVersionUID = 1L;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private BagService bagService;

	private File logo;
	private String logoFileName;
	private String logoContentType;
	private User user;
	private String pageNoStr;
	private List<Bag> bagList;

	// ******************Action()区********************

	// -----------------Survey CRUD---------------------

	public String myEngageSurvey() {
		Page<Survey> page = surveyService.getMyEngageSurvey(pageNoStr, GlobalValues.SURVEY_LIST_PAGE_SIZE, user);
		reqMap.put(GlobalNames.PAGE, page);

		return "toMyEngageSurveyPage";
	}

	public String top10() {
		List<Survey> newTenSurveys = surveyService.getTenNewSurveyList();
		List<Survey> hotTenSurveys = surveyService.getTenHotSurveyList();
		reqMap.put(GlobalNames.NEW_TEN_SURVEYS, newTenSurveys);
		reqMap.put(GlobalNames.HOT_TEN_SURVEYS, hotTenSurveys);
		return "toTop10Page";
	}

	public String toMoveOrCopyPage() throws Exception {

		Page<Survey> page = surveyService.getSurveyPage(user.getUserId(), pageNoStr, GlobalValues.SURVEY_LIST_PAGE_SIZE, false);

		reqMap.put(GlobalNames.PAGE, page);
		if (bagId != null) {
			Bag bag = bagService.getEntityById(bagId);
			sessionMap.put(GlobalNames.CURRENT_BAG, bag);
		}
		return "toMoveOrCopyPage";
	}

	public void prepareBagOrderUpdate() throws Exception {
		getT();
	}

	public String bagOrderUpdate() {

		if (!ValidateUtils.validateList(bagList)) {
			addActionError("您填写的序号不能重复!!!");
			return "toAdjustBagOrderPage";
		}

		bagService.batchUpdateBagOrder(bagList);

		return "toDesignPage";
	}

	public void prepareAdjustBagOrder() throws Exception {
		getT();
	}

	public String adjustBagOrder() {
		return "toAdjustBagOrderPage";
	}

	public String completeSurvey() {
		boolean iscompleted = surveyService.completed(surveyId);
		if (!iscompleted) {
			this.addActionError("完成调查失败,您的包裹还没有完善!");
		}
		return "toSurveyCmpletedPage";
	}

	/**
	 * 更新调查
	 * 
	 * @throws Exception
	 */
	public void prepareUpdate() throws Exception {
		getT();
		this.inputPath = "/guestPages/survey_edit.jsp";
	}

	private void getT() throws Exception {
		this.t = surveyService.getEntityById(surveyId);
	}

	public String update() throws Exception {
		String virtualPath = "/surveyLogos";

		String realPath = application.getRealPath(virtualPath);

		String logoPath = DataProcessUtils.resizeImages(logo, realPath, logoFileName);

		if (DataProcessUtils.validateString(logoPath)) {
			this.t.setLogoPath(logoPath);
		}

		surveyService.updateEntity(t);

		return "toMyUncompletedAction";
	}

	/**
	 * 删除调查
	 * 
	 * @return
	 * @throws Exception
	 */
	public String remove() {

		try {
			surveyService.deleteEntity(t);
		} catch (Exception e) {
			throw new CatchDelSurveyException();
		}

		return "toMyUncompletedAction";
	}

	/**
	 * 创建保存调查
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepareSave() throws Exception {
		this.inputPath = "/guestPages/survey_create.jsp";
	}

	public String save() throws Exception {
		this.t.setUser(user);

		String virtualPath = "/surveyLogos";

		String realPath = application.getRealPath(virtualPath);

		String logoPath = DataProcessUtils.resizeImages(logo, realPath, logoFileName);

		if (DataProcessUtils.validateString(logoPath)) {
			this.t.setLogoPath(logoPath);
		}
		surveyService.saveEntity(t);

		return "toMyUncompletedAction";
	}

	/**
	 * 显示未完成的调查列表页面
	 * 
	 * @return
	 */
	public String myUncompleted() {

		Page<Survey> page = surveyService.getUncompletedPage(pageNoStr, 5, user);
		reqMap.put(GlobalNames.PAGE, page);

		return "toUncompletedListPage";
	}

	/**
	 * 显示完成的调查列表页面
	 * 
	 * @return
	 */
	public String myCompletedSurvey() {

		Page<Survey> page = surveyService.getCompletedPage(pageNoStr, GlobalValues.SURVEY_LIST_PAGE_SIZE, user);
		reqMap.put(GlobalNames.PAGE, page);

		return "toCompletedListPage";
	}

	// -----------------跳转页面---------------------

	/**
	 * 去调查设计页面
	 * 
	 * @throws Exception
	 */
	public void prepareDesign() throws Exception {
		this.t = surveyService.getEntityById(surveyId);
	}

	public String design() {
		sessionMap.put(GlobalNames.CURRENT_SURVEY, t);
		return "toDesignPage";
	}

	/**
	 * 去编辑调查页面
	 * 
	 * @throws Exception
	 */
	public void prepareEditSurvey() throws Exception {
		this.t = surveyService.getEntityById(surveyId);
	}

	public String editSurvey() throws Exception {

		return "toEditSurveyPage";
	}

	// **************getXxx()/setXxx()区***************

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public String getLogoContentType() {
		return logoContentType;
	}

	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public String getPageNoStr() {
		return pageNoStr;
	}

	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}

	public List<Bag> getBagList() {
		return bagList;
	}

	public void setBagList(List<Bag> bagList) {
		this.bagList = bagList;
	}

}

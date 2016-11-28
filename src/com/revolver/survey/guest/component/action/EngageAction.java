package com.revolver.survey.guest.component.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.revolver.survey.base.i.UserAware;
import com.revolver.survey.base.m.BaseAction;
import com.revolver.survey.guest.component.service.i.AnswerService;
import com.revolver.survey.guest.component.service.i.BagService;
import com.revolver.survey.guest.component.service.i.SurveyService;
import com.revolver.survey.guest.entity.Bag;
import com.revolver.survey.guest.entity.Survey;
import com.revolver.survey.guest.entity.User;
import com.revolver.survey.guest.model.Page;
import com.revolver.survey.utils.DataProcessUtils;
import com.revolver.survey.utils.GlobalNames;
import com.revolver.survey.utils.GlobalValues;

/**
 * 
 * @author REVOLVER
 * 
 */
@Controller
@Scope("prototype")
public class EngageAction extends BaseAction<Survey> implements UserAware<Survey> {
	// ******************成员属性区********************
	private static final long serialVersionUID = 1L;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private BagService bagService;

	@Autowired
	private AnswerService answerService;

	private Bag currentBag;
	private String pageNoStr;

	private User user;

	// ******************Action()区********************

	public String findAllAvailableSurvey() {

		Page<Survey> page = surveyService.findAllAvailableSurvey(pageNoStr, GlobalValues.SURVEY_PAGE_SIZE);

		reqMap.put(GlobalNames.PAGE, page);

		return "toFindAllAvailableSurveyPage";
	}

	@SuppressWarnings("unchecked")
	public String doEngage() {
		Map<Integer, Map<String, String[]>> allBagMap = (Map<Integer, Map<String, String[]>>) sessionMap.get(GlobalNames.ALL_BAG_MAP);

		String submitName = DataProcessUtils.parserParametesMap(parameters);

		if ("submit_prev".equals(submitName)) {
			currentBag = bagService.getPrevBag(surveyId, bagId);
			allBagMap.put(bagId, parameters);

		}

		if ("submit_next".equals(submitName)) {
			currentBag = bagService.getNextBag(surveyId, bagId);
			allBagMap.put(bagId, parameters);
		}

		if ("submit_done".equals(submitName)) {

			allBagMap.put(bagId, parameters);
			
//			SurveyToken token = new SurveyToken();
//			
//			token.setSurveyId(surveyId);
//			token.bind(token);
			
			answerService.parseAndSave(surveyId, allBagMap);

			try {
				surveyService.saveEngage(user.getUserId(), surveyId);
			} catch (Exception e) {
			}

			return "toShowSummaryAction";
		}

		if ("submit_quit".equals(submitName)) {
			sessionMap.remove(GlobalNames.CURRENT_SURVEY);
			return "toSurveyAction_top10";
		}

		return "toEngageAnswerPage";
	}

	public void prepareEntry() throws Exception {
		this.t = surveyService.getEntityById(surveyId);
	}

	public String entry() {

		currentBag = bagService.getFirstBag(surveyId);

		sessionMap.put(GlobalNames.CURRENT_SURVEY, t);

		Map<Integer, Map<String, String[]>> allBagMap = new HashMap<Integer, Map<String, String[]>>();

		sessionMap.put(GlobalNames.ALL_BAG_MAP, allBagMap);

		return "toEngageAnswerPage";
	}

	// ******************GetXxx()/SetXxx()区********************

	public Bag getCurrentBag() {
		return currentBag;
	}

	public void setCurrentBag(Bag currentBag) {
		this.currentBag = currentBag;
	}

	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
}

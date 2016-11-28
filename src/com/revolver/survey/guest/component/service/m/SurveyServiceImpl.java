package com.revolver.survey.guest.component.service.m;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolver.survey.base.m.BaseServiceImpl;
import com.revolver.survey.guest.component.dao.i.SurveyDao;
import com.revolver.survey.guest.component.service.i.SurveyService;
import com.revolver.survey.guest.entity.Answer;
import com.revolver.survey.guest.entity.Bag;
import com.revolver.survey.guest.entity.Question;
import com.revolver.survey.guest.entity.Survey;
import com.revolver.survey.guest.entity.User;
import com.revolver.survey.guest.model.Page;
import com.revolver.survey.utils.DataProcessUtils;
import com.revolver.survey.utils.ValidateUtils;

/**
 * 
 * @author REVOLVER
 * 
 */
@Service
public class SurveyServiceImpl extends BaseServiceImpl<Survey> implements SurveyService {
	@Autowired
	private SurveyDao surveyDao;

	@Override
	public Page<Survey> getUncompletedPage(String pageNoStr, int pageSize, User user) {
		// 获取总记录数
		int totalRecordNo = surveyDao.getUncompletedCount(user);

		// 创建page对象
		Page<Survey> page = new Page<Survey>(pageNoStr, totalRecordNo, pageSize);

		Integer pageNo = page.getPageNo();

		List<Survey> list = surveyDao.getUncompletedList(pageNo, pageSize, user);

		page.setList(list);

		return page;
	}

	@Override
	public Page<Survey> getCompletedPage(String pageNoStr, int pageSize, User user) {
		// 获取总记录数
		int totalRecordNo = surveyDao.getCompletedCount(user);

		// 创建page对象
		Page<Survey> page = new Page<Survey>(pageNoStr, totalRecordNo, pageSize);

		Integer pageNo = page.getPageNo();

		List<Survey> list = surveyDao.getCompletedList(pageNo, pageSize, user);

		page.setList(list);

		return page;
	}

	@Override
	public boolean completed(Integer surveyId) {
		try {
			Survey survey = surveyDao.getEntityById(surveyId);
			Set<Bag> bagSet = survey.getBagSet();
			for (Bag bag : bagSet) {
				Set<Question> questionSet = bag.getQuestionSet();
				if (!ValidateUtils.validateCollection(questionSet)) {
					return false;
				}
				if (bag.getBagOrder() == 0) {
					bag.setBagOrder(bag.getBagId());
				}
			}
			if (!ValidateUtils.validateCollection(bagSet)) {
				return false;
			}
			survey.setCompleted(true);
			survey.setCompletedTime(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Page<Survey> getSurveyPage(Integer userId, String pageNoStr, int surveyListPageSize, boolean completed) {

		int totalRecordNo = surveyDao.getSurveyCount(userId, completed);

		// 创建page对象
		Page<Survey> page = new Page<Survey>(pageNoStr, totalRecordNo, surveyListPageSize);

		Integer pageNo = page.getPageNo();

		List<Survey> list = surveyDao.getSurveyList(pageNo, surveyListPageSize, userId, completed);

		page.setList(list);

		return page;
	}

	@Override
	public List<Survey> getTenNewSurveyList() {

		return surveyDao.getTenNewSurveyList();
	}

	@Override
	public List<Survey> getTenHotSurveyList() {

		return surveyDao.getTenHotSurveyList();
	}

	@Override
	public Page<Survey> findAllAvailableSurvey(String pageNoStr, int allSurveyPageSize) {

		int totalRecordNo = surveyDao.getSurveyCompletedCount();
		// 创建page对象
		Page<Survey> page = new Page<Survey>(pageNoStr, totalRecordNo, allSurveyPageSize);

		Integer pageNo = page.getPageNo();

		List<Survey> list = surveyDao.getSurveyList(pageNo, allSurveyPageSize);

		page.setList(list);

		return page;
	}

	@Override
	public void saveEngage(Integer userId, Integer surveyId) {
		surveyDao.saveEngage(userId, surveyId);
	}

	@Override
	public Page<Survey> getMyEngageSurvey(String pageNoStr, int surveyListPageSize, User user) {

		int totalRecordNo = surveyDao.getEngageCount(user.getUserId());
		// 创建page对象
		Page<Survey> page = new Page<Survey>(pageNoStr, totalRecordNo, surveyListPageSize);

		Integer pageNo = page.getPageNo();

		List<Survey> list = surveyDao.getEngageList(user.getUserId(), pageNo, surveyListPageSize);

		page.setList(list);

		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.revolver.survey.guest.component.service.i.SurveyService#getCompletedList(java.lang.Integer, int)
	 */
	@Override
	public Page<Survey> getCompletedList(String pageNoStr, int i) {

		int totalRecordNo = surveyDao.getCompletedCount();
		// 创建page对象
		Page<Survey> page = new Page<Survey>(pageNoStr, totalRecordNo, i);

		Integer pageNo = page.getPageNo();

		List<Survey> list = surveyDao.getCompletedList(pageNo, i);

		page.setList(list);

		return page;
	}

	@Override
	public HSSFWorkbook generateWorkBook(Integer surveyId) {
		HSSFWorkbook workBook = new HSSFWorkbook();

		Survey survey = surveyDao.getEntityById(surveyId);
		int engageCount = surveyDao.getEngageSurveyCount(surveyId);

		List<Question> questionList = surveyDao.getQuestionListBySurveyId(surveyId);
		

//		//************************************
//			SurveyToken token = new SurveyToken();
//
//			token.setSurveyId(surveyId);
//			token.bind(token);
//		//************************************
		List<Answer> answerList = surveyDao.getAnswerBySurveyId(surveyId);

		HSSFSheet sheet = workBook.createSheet(survey.getTitle() + "-" + engageCount + "人参与");

		if (engageCount == 0) {
			sheet.createRow(0).createCell(0).setCellValue("该调查还没有答案!!!");

			return workBook;
		}

		// 创建问题标题行
		HSSFRow questionTitleRow = sheet.createRow(0);

		for (int i = 0; i < questionList.size(); i++) {
			Question question = questionList.get(i);
			String questionName = question.getQuestionName();

			questionTitleRow.createCell(i).setCellValue(questionName);
		}

		// 创建答案行
		Map<String, Map<Integer, String>> bigMap = convertAnswer(answerList, questionList);

		Set<String> uuidSet = bigMap.keySet();

		ArrayList<String> uuidList = new ArrayList<String>(uuidSet);

		for (int i = 0; i < uuidList.size(); i++) {

			String uuid = uuidList.get(i);
			
			Map<Integer, String> smallMap = bigMap.get(uuid);
			
			HSSFRow answerRow = sheet.createRow(i + 1);

			for (int j = 0; j < questionList.size(); j++) {
				
				Question question = questionList.get(j);
				
				Integer questionId = question.getQuestionId();
				
				String content = smallMap.get(questionId);
				
				answerRow.createCell(j).setCellValue(content);
			}
		}

		return workBook;
	}

	/**
	 * @param answerList
	 * @param questionList
	 * @return
	 */
	private Map<String, Map<Integer, String>> convertAnswer(List<Answer> answerList, List<Question> questionList) {

		Map<String, Map<Integer, String>> bigMap = new HashMap<String, Map<Integer, String>>();

		Map<Integer, Question> questionMap = convertQuestion(questionList);

		for (Answer answer : answerList) {
			String uuid = answer.getUuid();
			Integer questionId = answer.getQuestionId();

			Question question = questionMap.get(questionId);

			int questionType = question.getQuestionType();

			String mainAnswers = answer.getMainAnswers();

			if (questionType == 0 || questionType == 1 || questionType == 2) {

				String[] mainAnswerArr = DataProcessUtils.toArrayStrOffComma(mainAnswers);

				if (ValidateUtils.validateArray(mainAnswerArr)) {
					StringBuilder mainAnswerStrBuilder = new StringBuilder();

					String[] optionsArray = question.getOptionsArray();

					for (int i = 0; i < mainAnswerArr.length; i++) {
						String optionStr = mainAnswerArr[i];
						
						if(!"other".equals(optionStr)){
							Integer optionId = DataProcessUtils.parserStr(optionStr);
							mainAnswerStrBuilder.append(optionsArray[optionId]).append(",");
						}else{
							optionStr = "[其它项:"+optionStr+"]";
							mainAnswerStrBuilder.append(optionStr);
						}

					}
					mainAnswers = mainAnswerStrBuilder.toString();
				}
			}
			if (questionType == 4 || questionType == 5 || questionType == 6) {

				StringBuilder matrixTotal = new StringBuilder();

				String[] matrixAswerArr = DataProcessUtils.toArrayStrOffComma(mainAnswers);

				for (int i = 0; i < matrixAswerArr.length; i++) {
					String matrixAswer = null;

					String[] newMatrixAswerArr = matrixAswerArr[i].split("_");

					String rowStr = newMatrixAswerArr[0];
					String colStr = newMatrixAswerArr[1];

					Integer rowIndex = DataProcessUtils.parserStr(rowStr);
					Integer colIndex = DataProcessUtils.parserStr(colStr);

					String[] matrixRowTitlesArray = question.getMatrixRowTitlesArray();
					String[] matrixColTitlesArray = question.getMatrixColTitlesArray();

					String rowTitle = matrixRowTitlesArray[rowIndex];
					String colTitle = matrixColTitlesArray[colIndex];

					matrixAswer = rowTitle + "_" + colTitle;

					if (questionType == 6) {
						String[] optionsArray = question.getMatrixOptionsArray();

						String optionStr = newMatrixAswerArr[2];

						Integer optionIndex = DataProcessUtils.parserStr(optionStr);

						String option = optionsArray[optionIndex];

						matrixAswer = matrixAswer + "_" + option;
					}

					matrixTotal.append(matrixAswer).append(",");
				}

				mainAnswers = matrixTotal.toString();
			}

			mainAnswers = DataProcessUtils.removeLastComma(mainAnswers);

			String otherAnswer = answer.getOtherAnswers();

			Map<Integer, String> smallMap = bigMap.get(uuid);
			if (smallMap == null) {
				smallMap = new HashMap<Integer, String>();
				bigMap.put(uuid, smallMap);
			}
			String content = smallMap.get(questionId);
			if (content == null) {

				if (mainAnswers != null) {
					content = mainAnswers;
					if (otherAnswer != null) {
						content = content + " [其他项：" + otherAnswer + "]";
					}
				} else {
					if (otherAnswer != null) {
						content = " [其他项：" + otherAnswer + "]";
					}
				}

			} else {

				if (mainAnswers != null) {
					content = mainAnswers + content ;
					if (otherAnswer != null) {
						content = content + " [其他项：" + otherAnswer + "]";
					}
				} else {
					if (otherAnswer != null) {
						content = content + " [其他项：" + otherAnswer + "]";
					}
				}
			}

			smallMap.put(questionId, content);
		}

		return bigMap;
	}

	/**
	 * @param questionList
	 * @return
	 */
	private Map<Integer, Question> convertQuestion(List<Question> questionList) {
		Map<Integer, Question> questionMap = new HashMap<Integer, Question>();

		for (Question question : questionList) {

			questionMap.put(question.getQuestionId(), question);
		}
		return questionMap;
	}
}

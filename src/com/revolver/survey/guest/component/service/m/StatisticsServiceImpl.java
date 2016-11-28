package com.revolver.survey.guest.component.service.m;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolver.survey.base.m.BaseServiceImpl;
import com.revolver.survey.guest.component.dao.i.AnswerDao;
import com.revolver.survey.guest.component.dao.i.QuestionDao;
import com.revolver.survey.guest.component.service.i.StatisticsService;
import com.revolver.survey.guest.entity.Question;
import com.revolver.survey.guest.entity.Survey;
import com.revolver.survey.guest.model.OptionStatisticModel;
import com.revolver.survey.guest.model.QuestionStatisticModel;
/**
 * 
 * @author REVOLVER
 *
 */
@Service
public class StatisticsServiceImpl extends BaseServiceImpl<Survey> implements StatisticsService {

	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private AnswerDao answerDao;

	@Override
	public QuestionStatisticModel getQsm(Integer questionId) {

		QuestionStatisticModel qsm = new QuestionStatisticModel();

		// 问题名称
		Question question = questionDao.getEntityById(questionId);

		qsm.setQuestionName(question.getQuestionName());

		// 总人数
		int totalCount = answerDao.getTotalEngageCount(questionId);

		qsm.setTotalCount(totalCount);

		String[] optionsArray = question.getOptionsArray();

		List<OptionStatisticModel> osmList = answerDao.getosmList(optionsArray, questionId);

		if (question.isHasOther() && question.getOtherType() == 0) {
			int otherCount = answerDao.getTotalOtherCount(questionId);
			OptionStatisticModel osm = new OptionStatisticModel();
			osm.setLable("其它");
			osm.setCount(otherCount);
			osmList.add(osm);
		}

		qsm.setOsmList(osmList);
		return qsm;
	}

	@Override
	public List<String> getOtherTextList(Integer questionId) {

		return answerDao.getOtherTextList(questionId);
	}

	@Override
	public List<String> getTextList(Integer questionId) {

		return answerDao.getTextList(questionId);
	}

	@Override
	public List<OptionStatisticModel> getOsmList(Integer questionId, Integer rowNo) {

		// 问题名称
		Question question = questionDao.getEntityById(questionId);

		// 获取行标题
		String[] matrixRowTitlesArray = question.getMatrixRowTitlesArray();
		String rowTitle = matrixRowTitlesArray[rowNo];

		// 获取列标题
		String[] matrixColTitlesArray = question.getMatrixColTitlesArray();
		List<OptionStatisticModel> osmList = new ArrayList<OptionStatisticModel>();

		for (int i = 0; i < matrixColTitlesArray.length; i++) {
			String colTitle = matrixColTitlesArray[i];

			String lable = rowTitle + "_" + colTitle;

			String currentValue = rowNo + "_" + i;

			int count = answerDao.getOptionEngageCount(questionId, currentValue);

			if (count == 0)
				continue;

			OptionStatisticModel osm = new OptionStatisticModel();

			osm.setLable(lable);
			osm.setCount(count);

			osmList.add(osm);
		}

		return osmList;
	}

	@Override
	public List<OptionStatisticModel> getOsmList(Integer questionId, Integer rowNo, Integer colNo) {

		// 问题名称
		Question question = questionDao.getEntityById(questionId);

		//// 获取option
		String[] matrixOptionsArray = question.getMatrixOptionsArray();

		List<OptionStatisticModel> osmList = new ArrayList<OptionStatisticModel>();

		for (int i = 0; i < matrixOptionsArray.length; i++) {
			String lable = matrixOptionsArray[i];

			String currentValue = rowNo + "_" + colNo + "_" + i;
			
			int count = answerDao.getOptionEngageCount(questionId, currentValue);
			
			if(count == 0) continue;
			OptionStatisticModel osm = new OptionStatisticModel();

			osm.setLable(lable);
			osm.setCount(count);

			osmList.add(osm);
		}

		return osmList;
	}

}

package com.revolver.survey.guest.component.service.m;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolver.survey.base.m.BaseServiceImpl;
import com.revolver.survey.guest.component.dao.i.AnswerDao;
import com.revolver.survey.guest.component.service.i.AnswerService;
import com.revolver.survey.guest.entity.Answer;
import com.revolver.survey.utils.DataProcessUtils;
/**
 * 
 * @author REVOLVER
 *
 */
@Service
public class AnswerServiceImpl extends BaseServiceImpl<Answer> implements AnswerService {
	@Autowired
	private AnswerDao answerDao;
	
	@Override
	public void parseAndSave(Integer surveyId, Map<Integer, Map<String, String[]>> allBagMap) {
		
		List<Answer> answerList = new ArrayList<Answer>();
		
		//准备基本信息
		Date answerTime = new Date();
		String uuid = UUID.randomUUID().toString();
		
		Collection<Map<String,String[]>> values = allBagMap.values();
		
		//声明一个缓存矩阵单选的答案数据的Map
		Map<Integer, String> matrixRadioMap = new HashMap<Integer, String>();

		
		for (Map<String, String[]> paramMap : values) {
			Set<Entry<String,String[]>> entrySet = paramMap.entrySet();
			
			for (Entry<String, String[]> param : entrySet) {
				
				Answer answer = new Answer(surveyId,null,answerTime,uuid,null,null);
				
				//得到每一个请求参数
				String paramName = param.getKey();
				String[] paramValArr = param.getValue();
				
				//要解析的答案请求参数的name值都是以question开头的，如果不是则不解析
				if(!paramName.startsWith("question")) continue ;
			
				//区分paramName的三种情况
				if(paramName.contains("other")) {
					//文本框形式的其他项
					//questionId
					//paramName:question27Other
					String questionIdStr = paramName.substring(8, paramName.lastIndexOf("other"));
					Integer questionId = DataProcessUtils.parserStr(questionIdStr);
					answer.setQuestionId(questionId);
					
					//otherAnswers
					String otherAnswers = paramValArr[0];
					answer.setOtherAnswers(otherAnswers);
					
					answerList.add(answer);
					
				} else if(paramName.contains("_")) {
					//矩阵单选
					//检测当前paramName中的questionId
					String questionIdStr = paramName.substring(8, paramName.lastIndexOf("_"));
					Integer questionId = DataProcessUtils.parserStr(questionIdStr);
					
					//根据questionId从matrixRadioMap中获取之前保存的旧的单选值
					String oldValue = matrixRadioMap.get(questionId);
					
					if(oldValue == null) {
						//如果旧值是空的，说明是第一次保存，保存当前值即可
						oldValue = paramValArr[0];
						
					}else{
						//如果旧值不为空，那么就将新的值追加到旧值后面，然后再保存
						oldValue = oldValue + "," + paramValArr[0];
						
					}
					
					//会将原来的值覆盖
					matrixRadioMap.put(questionId, oldValue);
					
					
				} else {
					//其他所有情况
					//解析得到questionId
					//paramName:question+questionId
					String questionIdStr = paramName.substring(8);
					Integer questionId = DataProcessUtils.parserStr(questionIdStr);
					answer.setQuestionId(questionId);
					
					//mainAnswers
					String mainAnswers = DataProcessUtils.toStrArrayOffComma(paramValArr);
					answer.setMainAnswers(mainAnswers);
					
					answerList.add(answer);
				}
			}
			
		}
		
		Set<Entry<Integer, String>> entrySet = matrixRadioMap.entrySet();
		for (Entry<Integer, String> entry : entrySet) {
			Integer questionId = entry.getKey();
			String mainAnswers = entry.getValue();
			Answer answer = new Answer(surveyId, questionId, answerTime, uuid, mainAnswers, null);
			answerList.add(answer);
		}
		
		answerDao.batchSaveAnswerList(answerList);
	}
}

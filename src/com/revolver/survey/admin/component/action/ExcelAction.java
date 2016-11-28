/**
 * 
 */
package com.revolver.survey.admin.component.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.revolver.survey.base.m.BaseAction;
import com.revolver.survey.guest.component.service.i.SurveyService;
import com.revolver.survey.guest.entity.Survey;
import com.revolver.survey.guest.model.Page;
import com.revolver.survey.utils.GlobalNames;

/**
 * @author REVOLVER2016年2月26日
 * 
 */
@Controller
@Scope("prototype")
public class ExcelAction extends BaseAction<Survey> {
	// *******************成员变量区********************
	private static final long serialVersionUID = 1L;
	@Autowired
	private SurveyService surveyService;

	private String pageNoStr;
	private ByteArrayInputStream inputStream;
	private int contentLength;

	// ******************Action()区********************
	/**
	 * 导出Excel表格
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String exportExcel() throws IOException {

		HSSFWorkbook workBook = surveyService.generateWorkBook(t.getSurveyId());

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		workBook.write(out);

		byte[] bytes = out.toByteArray();

		inputStream = new ByteArrayInputStream(bytes);
		contentLength = inputStream.available();

		return "exportExcelStream";
	}

	/**
	 * 获取所有要导出调查页面
	 * 
	 * @return
	 */
	public String showAllSurvey() {

		Page<Survey> page = surveyService.getCompletedList(pageNoStr, 40);

		reqMap.put(GlobalNames.PAGE, page);

		return "toExportExlPage";
	}

	// **************getXxx()/setXxx()区***************

	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}
	
	/**
	 * @return the inputStream
	 */
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
	
	/**
	 * @return the contentLength
	 */
	public int getContentLength() {
		return contentLength;
	}
	
	public String getFileName() throws Exception{
		Survey survey = surveyService.getEntityById(surveyId);
		String title = survey.getTitle();
		title = new String(title.getBytes("gbk"), "iso-8859-1");
		
		return System.nanoTime() +"_"+title+".xls";
	}
	
}

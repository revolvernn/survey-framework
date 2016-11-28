package com.revolver.survey.utils;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.revolver.survey.admin.entity.Resource;
import com.revolver.survey.guest.model.OptionStatisticModel;
import com.revolver.survey.guest.model.QuestionStatisticModel;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 工具类
 * 
 * @author REVOLVER
 * 
 */
public class DataProcessUtils {
	
	/**
	 * 根据偏移量生成日志表的表名
	 * @param offset
	 * 		0：当前月
	 * 		>0：以后的某个月
	 * 		<0：以前的某个月
	 * @return
	 */
	public static String generateTableName(int offset) {
		
		int yearOffset = (offset - (offset % 12))/12;
		
		offset = offset % 12;
		
		//logs_2016_6
		Calendar calendar = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR) + yearOffset;
		
		//0~11
		int month = calendar.get(Calendar.MONTH) + 1 + offset;
		
		if(month < 1) {
			month = month + 12;
			year--;
		}
		
		if(month > 12) {
			month = month - 12;
			year++;
		}
		
		return "logs_"+year+"_"+month;
	}
	
	
	public static String generateRandomString(int length) {
		StringBuilder randomString = new StringBuilder();
		
		for(int i = 0; i < length; i++) {
			int randomNumber = getRangedRandomNumber();
			char suitableChar = (char) randomNumber;
			randomString.append(suitableChar);
		}
		
		return randomString.toString();
	}
	
	public static int getRangedRandomNumber() {
		
		while(true) {
			int randomNumber = (int)(Math.random()*100);
			if(randomNumber < 48 || 
					(randomNumber>57 && randomNumber<65) || 
					(randomNumber>90 && randomNumber<97) || 
					randomNumber > 122 ) {
				continue;
			}else{
				return randomNumber;
			}
		}
		
	}

	/**
	 * Br replace Comma
	 * 
	 * @param source
	 * @return String
	 */
	public static String toStrBrReComma(String source) {
		if (!validateString(source))
			return null;

		while (source.contains(",,")) {
			source = source.replaceAll(",,", ",");
		}
		return source.trim().replaceAll("\r\n", ",");
	}

	/**
	 * Comma replace Br
	 * 
	 * @param source
	 * @return String
	 */
	public static String toStrCommaReBr(String source) {
		if (!validateString(source))
			return null;
		else
			return source.trim().replaceAll(",", "\r\n");
	}

	/**
	 * String Off Comma
	 * 
	 * @param source
	 * @return String[]
	 */
	public static String[] toArrayStrOffComma(String source) {
		if (!validateString(source))
			return null;
		else
			return source.trim().split(",");
	}

	/**
	 * String[] Off Comma
	 * 
	 * @param strArray
	 * @return String
	 */
	public static String toStrArrayOffComma(String[] strArray) {
		if (!arrayValidate(strArray)) {
			return null;
		}

		StringBuilder sb = new StringBuilder("");

		for (String value : strArray) {
			sb.append(value).append(",");
		}

		String result = sb.toString();

		return result.substring(0, result.length() - 1);
	}

	/**
	 * 验证字符串是否有效
	 * 
	 * @param source
	 * @return
	 */
	public static boolean validateString(String source) {
		return source != null && source.length() > 0;
	}

	/**
	 * 验证字符数组是否有效
	 * 
	 * @param strArray
	 * @return
	 */
	public static boolean arrayValidate(String[] strArray) {
		return strArray != null && strArray.length > 0;
	}

	/**
	 * 将图片压缩按本来的长宽比例压缩为100宽度的jpg图片
	 * 
	 * @param sourceFile
	 * @param realPath
	 *            /surveyLogos目录的真实路径，后面没有斜杠
	 * @return 将生成的文件路径返回 /surveyLogos/4198393905112.jpg
	 */
	public static String resizeImages(File sourceFile, String realPath, String fileName) {

		OutputStream out = null;

		try {
			// 1.构造原始图片对应的Image对象
			BufferedImage sourceImage = ImageIO.read(sourceFile);

			// 2.获取原始图片的宽高值
			int sourceWidth = sourceImage.getWidth();
			int sourceHeight = sourceImage.getHeight();

			// 3.计算目标图片的宽高值
			int targetWidth = 100;
			int targetHeight = sourceHeight / (sourceWidth / 100);

			// 4.创建压缩后的目标图片对应的Image对象
			BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

			// 5.绘制目标图片
			targetImage.getGraphics().drawImage(sourceImage, 0, 0, targetWidth, targetHeight, null);

			fileName = fileName.trim();

			if (fileName.contains(".")) {
				fileName = fileName.substring(0, fileName.indexOf("."));
			}

			// 6.构造目标图片文件名
			String targetFileName = System.nanoTime() + "_" + fileName + ".jpg";

			// 7.创建目标图片对应的输出流
			out = new FileOutputStream(realPath + "/" + targetFileName);

			// 8.获取JPEG图片编码器
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

			// 9.JPEG编码
			encoder.encode(targetImage);

			// 10.返回文件名
			return "/surveyLogos/" + targetFileName;

		} catch (Exception e) {

			return null;
		} finally {
			// 10.关闭流
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * MD5加密
	 * 
	 * @param source
	 * @return
	 */
	public static String md5(String source) {
		StringBuffer sb = new StringBuffer();

		try {
			byte[] bytes = source.getBytes();
			char[] code = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			MessageDigest digest = MessageDigest.getInstance("MD5");

			byte[] digests = digest.digest(bytes);

			for (int i = 0; i < digests.length; i++) {
				byte b = digests[i];

				// ①低四位转换
				int lowNumber = b & 0x0F;// 0x0F→00001111

				// ②高四位转换
				int highNumber = (b >> 4) & 0x0F;

				sb.append(code[lowNumber]).append(code[highNumber]);
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static Serializable copyObject(Serializable object) {

		// 字节数组输出流
		ByteArrayOutputStream baos = null;
		// 对象组输出流
		ObjectOutputStream oos = null;

		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		// 序列化接口类
		Serializable readObject = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);

			oos.writeObject(object);

			byte[] byteArray = baos.toByteArray();

			bais = new ByteArrayInputStream(byteArray);
			ois = new ObjectInputStream(bais);

			readObject = (Serializable) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return readObject;
	}

	public static String parserParametesMap(Map<String, String[]> parameters) {

		if (!ValidateUtils.validateMap(parameters))
			return null;
		Iterator<String> iterator = parameters.keySet().iterator();

		while (iterator.hasNext()) {
			String value = iterator.next();
			if (value.startsWith("submit_"))
				return value;
		}
		return null;
	}

	public static JFreeChart generateChartByOsm(QuestionStatisticModel qsm) {
		String chartTitle = qsm.getQuestionName() + "\r\n【共 " + qsm.getTotalCount() + " 人次参与】";

		DefaultPieDataset dataset = new DefaultPieDataset();
		List<OptionStatisticModel> osmList = qsm.getOsmList();

		for (OptionStatisticModel osm : osmList) {
			dataset.setValue(osm.getLable(), osm.getCount());
		}

		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, dataset);

		// 设置“标题”部分字体、风格、字号
		chart.getTitle().setFont(new Font("隶书", Font.BOLD, 50));

		// 设置“图例”部分信息字体、风格、字号
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 20));

		// 获取代表当前图表绘图区的PiePlot对象
		PiePlot plot = (PiePlot) chart.getPlot();

		// 设置标签字体、风格、字号
		plot.setLabelFont(new Font("微软雅黑", Font.ITALIC, 15));

		// 设置前景色半透明
		plot.setForegroundAlpha(0.6f);

		// 设置标签信息格式
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}/{3},{2}"));

		return chart;
	}

	public static JFreeChart generateChartByOsmList(List<OptionStatisticModel> osmList) {
		DefaultPieDataset dataset = new DefaultPieDataset();

		for (OptionStatisticModel osm : osmList) {
			dataset.setValue(osm.getLable(), osm.getCount());
		}

		JFreeChart chart = ChartFactory.createPieChart3D(null, dataset);

		// 设置“标题”部分字体、风格、字号
		// chart.getTitle().setFont(new Font("隶书", Font.BOLD, 50));

		// 设置“图例”部分信息字体、风格、字号
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 20));

		// 获取代表当前图表绘图区的PiePlot对象
		PiePlot plot = (PiePlot) chart.getPlot();

		// 设置标签字体、风格、字号
		plot.setLabelFont(new Font("微软雅黑", Font.ITALIC, 15));

		// 设置前景色半透明
		plot.setForegroundAlpha(0.6f);

		// 设置标签信息格式
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}/{3},{2}"));

		return chart;
	}

	/**
	 * @param optionStr
	 * @return
	 */
	public static Integer parserStr(String optionStr) {
		Integer i = null;
		try {
			i = Integer.parseInt(optionStr);
		} catch (NumberFormatException e) {
		}
		return i;
	}

	/**
	 * @param source
	 * @return
	 */
	public static String removeLastComma(String source) {
		if(!ValidateUtils.stringValidate(source))return null;
		
		if(!source.contains(",")) {
			return source;
		}
		
		int index = source.lastIndexOf(",");
		
		return source.substring(0, index);
	}

	/**
	 * @param actionName
	 * @return
	 */
	public static String translateName(String actionName) {
			
			if(!ValidateUtils.stringValidate(actionName)) return null;
			
			actionName = actionName.toLowerCase();
			
			actionName = actionName.replaceAll("export", "导出");
			actionName = actionName.replaceAll("user", "用户");
			actionName = actionName.replaceAll("survey", "调查");
			actionName = actionName.replaceAll("bag", "包裹");
			actionName = actionName.replaceAll("question", "问题");
			actionName = actionName.replaceAll("answer", "答案");
			actionName = actionName.replaceAll("statistics", "统计");
			actionName = actionName.replaceAll("show", "显示");
			actionName = actionName.replaceAll("list", "列表");
			actionName = actionName.replaceAll("action", "");
			actionName = actionName.replaceAll("page", "页面");
			actionName = actionName.replaceAll("remove", "删除");
			actionName = actionName.replaceAll("delete", "删除");
			actionName = actionName.replaceAll("move", "移动");
			actionName = actionName.replaceAll("copy", "复制");
			actionName = actionName.replaceAll("this", "这个");
			actionName = actionName.replaceAll("entry", "入口");
			actionName = actionName.replaceAll("edit", "编辑");
			actionName = actionName.replaceAll("update", "更新");
			actionName = actionName.replaceAll("save", "保存");
			actionName = actionName.replaceAll("add", "保存");
			actionName = actionName.replaceAll("create", "创建");
			actionName = actionName.replaceAll("get", "获取");
			actionName = actionName.replaceAll("find", "查找");
			actionName = actionName.replaceAll("all", "全部");
			actionName = actionName.replaceAll("uncompleted", "未完成的");
			actionName = actionName.replaceAll("completed", "已完成的");
			actionName = actionName.replaceAll("complete", "完成");
			actionName = actionName.replaceAll("available", "可用的");
			actionName = actionName.replaceAll("top", "前");
			actionName = actionName.replaceAll("text", "文本");
			actionName = actionName.replaceAll("other", "其他");
			actionName = actionName.replaceAll("login", "登录");
			actionName = actionName.replaceAll("logout", "退出登录");
			actionName = actionName.replaceAll("register", "注册");
			actionName = actionName.replaceAll("regist", "注册");
			actionName = actionName.replaceAll("result", "结果");
			actionName = actionName.replaceAll("matrix", "矩阵");
			actionName = actionName.replaceAll("normal", "常规");
			actionName = actionName.replaceAll("cell", "单元格");
			actionName = actionName.replaceAll("select", "下拉列表");
			actionName = actionName.replaceAll("engaged", "参与");
			actionName = actionName.replaceAll("engage", "参与");
			actionName = actionName.replaceAll("mycenter", "个人中心");
			actionName = actionName.replaceAll("pay", "充值");
			actionName = actionName.replaceAll("vip", "续费");
			actionName = actionName.replaceAll("my", "我的");
			actionName = actionName.replaceAll("design", "设计");
			actionName = actionName.replaceAll("type", "类型");
			actionName = actionName.replaceAll("chosen", "选择");
			actionName = actionName.replaceAll("order", "顺序");
			actionName = actionName.replaceAll("adjust", "调整");
			actionName = actionName.replaceAll("workbook", "工作表");
			actionName = actionName.replaceAll("authorities", "权限");
			actionName = actionName.replaceAll("authority", "权限");
			actionName = actionName.replaceAll("auth", "权限");
			actionName = actionName.replaceAll("summary", "摘要");
			actionName = actionName.replaceAll("generate", "生成");
			actionName = actionName.replaceAll("chart", "图表");
			actionName = actionName.replaceAll("image", "图片");
			actionName = actionName.replaceAll("admins", "管理员");
			actionName = actionName.replaceAll("admin", "管理员");
			actionName = actionName.replaceAll("resources", "资源");
			actionName = actionName.replaceAll("resource", "资源");
			actionName = actionName.replaceAll("res", "资源");
			actionName = actionName.replaceAll("batch", "批量");
			actionName = actionName.replaceAll("roles", "角色");
			actionName = actionName.replaceAll("role", "角色");
			actionName = actionName.replaceAll("manager", "管理");
			actionName = actionName.replaceAll("to", "前往");
			actionName = actionName.replaceAll("or", "或");
			actionName = actionName.replaceAll("do", "执行");
			actionName = actionName.replaceAll("10", "十");
			actionName = actionName.replaceAll("calculation", "计算");
			actionName = actionName.replaceAll("calculateauth", "计算权限码");
			actionName = actionName.replaceAll("logs", "日志");
			actionName = actionName.replaceAll("log", "日志");
			actionName = actionName.replaceAll("main", "主");
			actionName = actionName.replaceAll("export", "导出");
			actionName = actionName.replaceAll("option", "下拉列表形式的");
			actionName = actionName.replaceAll("choosen", "选择");
			actionName = actionName.replaceAll("choose", "选择");
			actionName = actionName.replaceAll("code", "码");
			
			return actionName;
	}

	/**
	 * @param codeArr
	 * @return
	 */
	public static String generateAuthStr(Long[] codeArr) {
		if(!ValidateUtils.validateArray(codeArr)) return null;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < codeArr.length; i++) {
			Long code = codeArr[i];
			
			if(code == null){
				code = 0L;
			}
			sb.append(code).append(",");
			
		}
		
		return sb.substring(0, sb.lastIndexOf(","));
	}

	/**
	 * @param resource
	 * @param authorityStr
	 * @return
	 */
	public static boolean checkAuthority(Resource resource, String authorityStr) {
		String[] codeStrArr = DataProcessUtils.toArrayStrOffComma(authorityStr);
		int resPos = resource.getResPos();
		String codeStr = codeStrArr[resPos];
		if(codeStr == null || "null".equals(codeStr)) {
			return false;
		}else{
			
			Long code = Long.parseLong(codeStr);
			long resCodeValue = resource.getResCode();
			long result = code & resCodeValue;
			return result != 0;
			
		}
	}


	/**
	 * SELECT COUNT(*) FROM (SELECT * FROM LOGS UNION SELECT * FROM logs_2016_3) log_unin;
	 * @param tableNames
	 * @return
	 */
	public static String generateSubSelect(List<String> tableNames) {
		
		StringBuilder subSelect = new StringBuilder();
		
		for (String tableName : tableNames) {
			subSelect.append("SELECT * FROM ").append(tableName).append(" UNION ");
		}
		return subSelect.substring(0,subSelect.lastIndexOf(" UNION "));
	}
}

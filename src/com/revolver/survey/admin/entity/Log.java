/**
 * 
 */
package com.revolver.survey.admin.entity;

/**
 * @author REVOLVER2016年3月1日 日志类
 */
public class Log {
	// OID
	private Integer logId;

	// 操作者
	private String operator;

	// 操作时间
	private String operateTime;

	// 方法名
	private String methodName;

	// 方法类型
	private String methodType;

	// 方法参数
	private String methodArgs;

	// 方法返回的结果值
	private String methodReturnValue;

	// 方法执行结果信息
	private String methodResultMsg;

	/**
	 * 
	 */
	public Log() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param operator
	 * @param operateTime
	 * @param methodName
	 * @param methodType
	 * @param methodArgs
	 * @param methodReturnValue
	 * @param methodResultMsg
	 */
	public Log(String operator, String operateTime, String methodName, String methodType, String methodArgs, String methodReturnValue, String methodResultMsg) {
		super();
		this.operator = operator;
		this.operateTime = operateTime;
		this.methodName = methodName;
		this.methodType = methodType;
		this.methodArgs = methodArgs;
		this.methodReturnValue = methodReturnValue;
		this.methodResultMsg = methodResultMsg;
	}

	/**
	 * @param logId
	 * @param operator
	 * @param operateTime
	 * @param methodName
	 * @param methodType
	 * @param methodArgs
	 * @param methodReturnValue
	 * @param methodResultMsg
	 */
	public Log(Integer logId, String operator, String operateTime, String methodName, String methodType, String methodArgs, String methodReturnValue, String methodResultMsg) {
		super();
		this.logId = logId;
		this.operator = operator;
		this.operateTime = operateTime;
		this.methodName = methodName;
		this.methodType = methodType;
		this.methodArgs = methodArgs;
		this.methodReturnValue = methodReturnValue;
		this.methodResultMsg = methodResultMsg;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getMethodArgs() {
		return methodArgs;
	}

	public void setMethodArgs(String methodArgs) {
		this.methodArgs = methodArgs;
	}

	public String getMethodReturnValue() {
		return methodReturnValue;
	}

	public void setMethodReturnValue(String methodReturnValue) {
		this.methodReturnValue = methodReturnValue;
	}

	public String getMethodResultMsg() {
		return methodResultMsg;
	}

	public void setMethodResultMsg(String methodResultMsg) {
		this.methodResultMsg = methodResultMsg;
	}

	@Override
	public String toString() {
		return "Log [logId=" + logId + ", operator=" + operator + ", operateTime=" + operateTime + ", methodName=" + methodName + ", methodType=" + methodType + ", methodArgs=" + methodArgs + ", methodReturnValue=" + methodReturnValue + ", methodResultMsg=" + methodResultMsg + "]";
	}
}

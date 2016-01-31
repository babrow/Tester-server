package controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import models.Account;
import models.Test;
import models.TestAttr;
import models.TestResult;
import models.TestResultAttr;
import play.Logger;
import play.db.jpa.GenericModel;
import play.db.jpa.GenericModel.JPAQuery;
import play.db.jpa.JPABase;
import play.mvc.Controller;

public class Api extends Controller {
	
	
	public static class Result<T extends GenericModel> {
		public enum ResultCode {
			ERROR, SUCCESS
		}
		
		private ResultCode resultCode;
		private String resultMessage;
		private T data;
		
		private Result() {
			
		}
		
		public Result(ResultCode resultCode, String message) {
			this(resultCode, message, null);
		}
		
		public Result(ResultCode resultCode, String message, T data) {
			this.setResultCode(ResultCode.SUCCESS);
			this.setResultMessage(message); 
			this.setData(data);
		}

		public ResultCode getResultCode() {
			return resultCode;
		}

		public void setResultCode(ResultCode resultCode) {
			this.resultCode = resultCode;
		}

		public String getResultMessage() {
			return resultMessage;
		}

		public void setResultMessage(String resultMessage) {
			this.resultMessage = resultMessage;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}
	}
	
	
	public static void saveResult(Long testId, Long accountId, Date resultDate, Map<String, BigDecimal> results) {
		Test test = Test.findById(testId);
		Account account = Account.findById(accountId);

		TestResult tr = new TestResult(account, test, resultDate).save();

		List<TestAttr> testAttrs = TestAttr.find("byTest", test).fetch();
		for (TestAttr testAttr : testAttrs) {
			BigDecimal value = results.get(testAttr.getName());
			if (value != null) {
				new TestResultAttr(testAttr, tr, value).save();
			}
		}
		renderJSON(new Result(Result.ResultCode.SUCCESS, StringUtils.EMPTY));
	}
	
	public static void login() {
		Account account = null;
		try {
			String str = params.get("body");
			Account acc = new Gson().fromJson(str, Account.class);
			account = Account.find("byEmail", acc.getEmail()).first();			
		} catch(Exception e) {
			Logger.error(e, "Error getting user");
		}
		renderJSON(account);
	}
}

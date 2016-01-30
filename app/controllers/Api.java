package controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import models.Account;
import models.Test;
import models.TestAttr;
import models.TestResult;
import models.TestResultAttr;
import play.db.jpa.GenericModel.JPAQuery;
import play.db.jpa.JPABase;
import play.mvc.Controller;

public class Api extends Controller {
	
	private enum ResultCode {
		ERROR, SUCCESS
	}
	
	public static class Result {
		private ResultCode resultCode;
		private String resultMessage;
		
		private Result() {
			
		}
		
		public static Result errorMsg(String message) {
			Result r = new Result();
			r.setResultCode(ResultCode.ERROR);
			r.setResultMessage(message); 
			return r; 
		}
		
		public static Result successMsg(String message) {
			Result r = new Result();
			r.setResultCode(ResultCode.SUCCESS);
			r.setResultMessage(message); 
			return r; 
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
		renderJSON(Result.successMsg(StringUtils.EMPTY));
	}
	
	public static void login(String userName, String password) {
		Account account = Account.find("byEmail", userName).first();
		if (account != null) {
			renderJSON(account);	
		} else {
			renderJSON(Result.errorMsg("Неверный логин или пароль"));
		}
	}
}

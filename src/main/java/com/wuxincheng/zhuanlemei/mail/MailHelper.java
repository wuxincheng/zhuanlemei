package com.wuxincheng.zhuanlemei.mail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.wuxincheng.zhuanlemei.util.MailUtil;

public class MailHelper {

	public static void main(String[] args) {
		
		try {
			MailUtil.sendMail("smtp.qq.com", 25, "2720554078@qq.com", "wuxinchenghappy@qq.com", "", "测试标题", "测试内容", "text/html");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
}

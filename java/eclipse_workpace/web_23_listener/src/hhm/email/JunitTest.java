package hhm.email;

import org.junit.Test;

public class JunitTest {
	public static void main(String[] args) {
		try {
			MailUtils.sendMail("jerry@hhm.com","这是标题", "没事，我就试一下");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void send() {
		try {
			MailUtils.sendMail("jerry@hhm.com","这是标题", "没事，我就试一下");
			System.out.println("finish");
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
}

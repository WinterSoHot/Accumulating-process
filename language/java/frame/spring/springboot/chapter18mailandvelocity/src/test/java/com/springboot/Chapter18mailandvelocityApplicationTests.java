package com.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter18mailandvelocityApplicationTests {

	@Autowired
	JavaMailSender sender;

	@Test
	public void sendSimpleMail() {
		SimpleMailMessage smsg = new SimpleMailMessage();
		smsg.setFrom("gudongxian@qq.com");
		smsg.setTo("gudongxian@qq.com");
		smsg.setSubject("主题:简单邮件");
		smsg.setText("测试邮件内容");

		sender.send(smsg);
	}

    @Test
    public void sendAttachmentsMail() throws Exception {

        MimeMessage mimeMessage = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("gudongxian@qq.com");
        helper.setTo("gudongxian@qq.com");
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");

        FileSystemResource file = new FileSystemResource(new File("background.jpg"));
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file);

        sender.send(mimeMessage);
    }

    @Test
    public void sendInlineMail() throws Exception {

        MimeMessage mimeMessage = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("gudongxian@qq.com");
        helper.setTo("gudongxian@qq.com");
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><img src=\"cid:background\" ></body></html>", true);

        FileSystemResource file = new FileSystemResource(new File("background.jpg"));
        helper.addInline("background", file);

        sender.send(mimeMessage);
    }

    @Test
    public void sendTemplateMail() throws Exception {


    }

}

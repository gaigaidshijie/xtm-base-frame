package com.xtm.mail.service;

import com.xtm.mail.model.Mail;
import com.xtm.mail.properties.MailProperties;
import com.xtm.mail.queue.MailQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/19 10:36
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Slf4j
public class MailService {

    private JavaMailSender mailSender;
    private MailProperties mailProperties;
    public MailService(JavaMailSender javaMailSender,MailProperties mailProperties){
        this.mailSender = javaMailSender;
        this.mailProperties = mailProperties;
    }

    /**
     * 简单发送
     * @param mail
     */
    public void send(Mail mail){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getUsername());
        message.setTo(mail.getEmail());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        mailSender.send(message);
        log.info("发送邮件：{}------->成功",mail);
    }

    /**
     * 附件发送
     * @param mail
     */
    public void sendHtml(Mail mail) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(mailProperties.getUsername(),mailProperties.getAlias());
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        helper.setText(
                "<html><body><img src=\"cid:springcloud\" ></body></html>",
                true);
        // 发送图片
        if(mail.getImgUrl()!=null){
            for(String imgUrl:mail.getImgUrl()){
                FileSystemResource fileResource = new FileSystemResource(new File(imgUrl));
                if (fileResource.exists()) {
                    helper.addInline(Objects.requireNonNull(fileResource.getFilename()), fileResource);
                }
            }
        }


        // 发送附件
        if(mail.getFileUrl()!=null){
            for(String fileUrl:mail.getFileUrl()){
                FileSystemResource fileResource = new FileSystemResource(new File(fileUrl));
                if (fileResource.exists()) {
                    helper.addAttachment(Objects.requireNonNull(fileResource.getFilename()), fileResource);
                }
            }
        }


        mailSender.send(message);
        log.info("邮件发送成功");
    }


    /**
     * 邮件队列发送
     * @param mail
     * @throws Exception
     */
    public void sendQueue(Mail mail) throws Exception {
        MailQueue.getMailQueue().produce(mail);
    }


}

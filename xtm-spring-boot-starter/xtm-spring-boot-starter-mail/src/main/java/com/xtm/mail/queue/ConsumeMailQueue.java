package com.xtm.mail.queue;

import com.xtm.common.concurrent.TaskExecutor;
import com.xtm.mail.model.Mail;
import com.xtm.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <p>Description:[邮件消费队列] </p>
 * Created on : 2020/8/19 14:50
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Component
@Slf4j
public class ConsumeMailQueue {

    @Autowired
    private MailService mailService;


    @PostConstruct
    public void startThread() {
        TaskExecutor.submit(()->{
            new PollMail(mailService);
        });
    }

    static class PollMail implements Runnable {
        MailService mailService;

        public PollMail(MailService mailService) {
            this.mailService = mailService;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Mail mail = MailQueue.getMailQueue().consume();
                    if (mail != null) {
                        log.info("剩余邮件总数:{}",MailQueue.getMailQueue().size());
                        if(mail.getType()==0){
                            mailService.sendHtml(mail);
                        }else{
                            mailService.send(mail);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @PreDestroy
    public void stopThread() {
        log.info("destroy");
    }
}

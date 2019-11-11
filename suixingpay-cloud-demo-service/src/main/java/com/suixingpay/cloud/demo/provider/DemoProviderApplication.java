/**  
 * All rights Reserved, Designed By Suixingpay.
 * @author: matieli[ma_tl@suixingpay.com] 
 * @date: 2018年1月31日 下午5:56:34   
 * @Copyright ©2018 Suixingpay. All rights reserved. 
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.suixingpay.cloud.demo.provider;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
public class DemoProviderApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {

        SpringApplication.run(DemoProviderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        printInfo("执行前");
    }

    private void printInfo(String pre){
        Long aMoney = jdbcTemplate.queryForObject("SELECT MONEY FROM USER WHERE NAME = 'A'", Long.class);
        Long bMoney = jdbcTemplate.queryForObject("SELECT MONEY FROM USER WHERE NAME = 'B'", Long.class);
        log.info(pre + "----- A Money {}, B Money {}",aMoney,bMoney);
        jdbcTemplate.execute("UPDATE USER SET MONEY = 100");
    }
}

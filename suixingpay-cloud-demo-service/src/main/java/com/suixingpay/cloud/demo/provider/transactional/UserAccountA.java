package com.suixingpay.cloud.demo.provider.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class UserAccountA {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserAccountB userAccountB;
    //情况一： A 有事务 A 回滚  B 不回滚
    //情况二： A 有事务 A 不回滚  B 回滚
    //情况三： A 无事务 A 不回滚 B 回滚

    @Transactional
    public void eventOne() throws RollBackException {
        jdbcTemplate.execute("UPDATE USER SET MONEY = MONEY - 100 WHERE NAME = 'A'");
        userAccountB.eventOne();
        throw new RollBackException();
    }

    @Transactional
    public void eventTwo() throws RollBackException {
        jdbcTemplate.execute("UPDATE USER SET MONEY = MONEY - 100 WHERE NAME = 'A'");
        userAccountB.eventTwo();
    }


    public void eventThree() throws RollBackException {
        jdbcTemplate.execute("UPDATE USER SET MONEY = MONEY - 100 WHERE NAME = 'A'");
        userAccountB.eventThree();
    }
}

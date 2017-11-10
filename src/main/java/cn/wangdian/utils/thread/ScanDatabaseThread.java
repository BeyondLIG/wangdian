package cn.wangdian.utils.thread;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * 这是用来每隔特定时间就去扫描数据库 wd_shopkeeper 表，判断该 shopkeeper 是否已经年费过期的线程
 */
public class ScanDatabaseThread implements Runnable{
    @Override
    public void run() {
        //加载配置文件，为了获取 DataSource
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        DruidDataSource dataSource = (DruidDataSource) context.getBean("dataSource"); //阿里提供的数据库
        try {
            //获取数据库连接
            Connection conn = dataSource.getConnection();
            //创建预编译 statement
            PreparedStatement searchAndUpdateShopKeeperStatusWhereDeadTimeLessThanNow = conn.prepareStatement(
                    "UPDATE wd_shopkeeper SET status=1 WHERE deathTime < ? AND status=0");
            PreparedStatement updateUserStatusWhereCorrespondingShopKeeperLocked = conn.prepareStatement(
                    "UPDATE wd_user u set u.status=1 WHERE u.telephone in (SELECT s.telephone from wd_shopkeeper s WHERE s.deathTime < ?)"
            );
            //死循环
            while (true) {
                //使用 try-catch 是为了避免在更新过程中发生什么意外跑出异常，使线程停止了工作。现在的设计即使线程除了异常，以会继续执行下去
                try {
                    long now = Calendar.getInstance().getTimeInMillis(); //计算当前时间
                    //查找所有需要被更新的店主
                    //更新用户表中的状态为锁定
                    updateUserStatusWhereCorrespondingShopKeeperLocked.setDate(1, new Date(now));
                    updateUserStatusWhereCorrespondingShopKeeperLocked.executeUpdate();
                    //设置店长的状态为锁定
                    searchAndUpdateShopKeeperStatusWhereDeadTimeLessThanNow.setDate(1, new Date(now));
                    searchAndUpdateShopKeeperStatusWhereDeadTimeLessThanNow.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    //线程睡眠一个小时
                    Thread.sleep(3600000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

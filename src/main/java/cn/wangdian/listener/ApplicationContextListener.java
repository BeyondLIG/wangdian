package cn.wangdian.listener;

import cn.wangdian.utils.thread.ScanDatabaseThread;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * 这是一个 APP 上下文监听器
 */
public class ApplicationContextListener extends ContextLoaderListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        //程序初始化的时候，自定义开启一个线程来进行数据库的扫描
//        new Thread(new ScanDatabaseThread()).start();
    }
}

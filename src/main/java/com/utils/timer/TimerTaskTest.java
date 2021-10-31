package com.utils.timer;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Java 定时任务调度工具 - Timer
 * @author YUDI
 * @date 2020/5/1 0:46
 */
public class TimerTaskTest extends TimerTask {

    private String name;

    public TimerTaskTest(String name) {
        this.name = name;
    }

    public Date getDate(){
        // 获取本地日期时间
        LocalDateTime now = LocalDateTime.now();
        // 获取系统时区
        ZoneId zoneId = ZoneId.systemDefault();
        // 本地时间与时区结合，获取带有时间规则的日期与时间
        ZonedDateTime zdt = now.atZone(zoneId);
        // 根据时间错获取 Date 对象
        return Date.from(zdt.toInstant());
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
        System.out.println(sdf.format(getDate()));
        System.out.println(name + "定时任务调度.");
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTaskTest t1 = new TimerTaskTest("task1");
        TimerTaskTest t2 = new TimerTaskTest("task2");
        TimerTaskTest t3 = new TimerTaskTest("task3");
        TimerTaskTest t4 = new TimerTaskTest("task4");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND,5);   // 当前时间 +5 秒

        // 通过 Timer 对象调用 TimerTask（即任务具体逻辑）
        timer.schedule(t1,3000);        // 延迟 3 秒后执行
        timer.schedule(t2,3000,5000);   // 延迟 3 秒后执行，随后每隔 5 秒执行一次
        System.out.println("获取该任务距离下次执行的时间："+ sdf.format(t2.scheduledExecutionTime()));
        t1.cancel();                    // 取消 t1 任务，即该任务不会执行
        System.out.println("移除被取消的任务的数量："+ timer.purge() +" 个.");
        timer.cancel();                 // 丢弃 Timer 中安排的所有任务，同时结束掉该后台线程

        timer = new Timer();
        timer.schedule(t3,c.getTime());     // 根据设定的时间执行
        c.add(Calendar.SECOND,7);           // 当前时间 +7 秒，由于上方已 +5 秒，实际则为 +12 秒
        timer.schedule(t4,c.getTime(),2000);    // 根据设定的时间执行，随后每隔 2 秒执行一次
        System.out.println(sdf.format(new Date()));

        /*
            以下两个函数与上方函数区别在于：
            1、若任务计划执行时间早于当前时间
                以下函数会补足这段时间内没执行的任务次数，
                并根据上次开始的时间计算下次要执行的时间，Schedule() 直接开始任务
            2、若任务执行时间超出时间间隔
                以下函数依旧根据上次任务开始时间计算下次任务开始时间，
                不会出现任务延后情况，但存在并发性，Schedule() 则会根据上次任务实际完成时间开始下次任务
        */
        // timer.scheduleAtFixedRate(t1,c.getTime(),1000);
        // timer.scheduleAtFixedRate(t2,3000,4000);
    }
}

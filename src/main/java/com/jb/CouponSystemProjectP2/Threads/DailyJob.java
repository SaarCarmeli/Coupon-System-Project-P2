package com.jb.CouponSystemProjectP2.Threads;

import com.jb.CouponSystemProjectP2.Repositories.CouponRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;
import java.time.LocalDate;

@EnableAsync
@EnableScheduling
public class DailyJob {
    CouponRepository couponRepository;
    Date date;

    @Async
    @Scheduled(cron = "0 30 0 * * ?", zone = "Asia/Jerusalem")
    //@Scheduled(cron = "* * * * * ?", zone = "Asia/Jerusalem")
    public void deleteExpiredCoupons() {
        date = Date.valueOf(LocalDate.now());
        couponRepository.deleteByEndDateBefore(date);
    }
}

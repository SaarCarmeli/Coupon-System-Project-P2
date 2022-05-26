package com.jb.CouponSystemProjectP2.Threads;

import com.jb.CouponSystemProjectP2.Repositories.CouponRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;
import java.time.LocalDate;

/**
 * A thread class to schedule deletion of expired coupons on a daily basis.
 */
@EnableAsync
@EnableScheduling
public class DailyJob {
    CouponRepository couponRepository;
    Date date;

    /**
     * An asynchronous (thread) method to delete expired coupons every day in  00:30:00
     */
    @Async
    @Scheduled(cron = "0 30 0 * * ?", zone = "Asia/Jerusalem") // todo change to 00:00:01
    public void deleteExpiredCoupons() {
        date = Date.valueOf(LocalDate.now());
        couponRepository.deleteByEndDateBefore(date);
    }
}

package com.jb.CouponSystemProjectP2.Repositories;

import com.jb.CouponSystemProjectP2.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Transactional
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    void deleteByEndDateBefore(Date endDate);

    @Query(value = "SELECT EXISTS (SELECT * FROM `coupon_project_p2`.`coupons` WHERE `title` = :title AND `company_id` = :companyId )", nativeQuery = true)
    byte existsByTitleAndCompanyId(@Param("title") String title, @Param("companyId") int company_id);

    @Query(value = "SELECT EXISTS (SELECT * FROM `coupon_project_p2`.`coupons` WHERE `coupon_id` = :couponId AND `company_id` = :companyId )", nativeQuery = true)
    byte existsByCouponIdAndCompanyId(@Param("couponId") int couponId, @Param("companyId") int companyId);

    @Query(value = "SELECT * FROM `coupon_project_p2`.`coupons` WHERE `company_id` = :companyId", nativeQuery = true)
    List<Coupon> findByCompanyId(@Param("companyId") int companyId);

    @Query(value = "SELECT * FROM `coupon_project_p2`.`coupons` WHERE `company_id` = :companyId AND `category` = :category", nativeQuery = true)
    List<Coupon> findByCompanyIdAndCategory(@Param("companyId") int companyId, @Param("category") String category);

    @Query(value = "SELECT * FROM `coupon_project_p2`.`coupons` WHERE `company_id` = :companyId AND `price` < :price", nativeQuery = true)
    List<Coupon> findByCompanyIdAndPriceLessThan(@Param("companyId") int companyId, @Param("price") double price);

    @Query(value = "SELECT c.coupon_id, c.amount, c.category, c.description, c.end_date, c.image, c.price, c.start_date, c.title, c.company_id" +
            "FROM `coupon_project_p2`.`coupons` AS c " +
            "JOIN `coupon_project_p2`.`customers_coupons` AS cc ON cc.coupons_coupon_id = c.coupon_id" +
            "WHERE cc.customer_customer_id = :customerId",
            nativeQuery = true)
    List<Coupon> findByCustomerId(@Param("customerId") int customerId);

    @Query(value = "SELECT c.coupon_id, c.amount, c.category, c.description, c.end_date, c.image, c.price, c.start_date, c.title, c.company_id" +
            "FROM `coupon_project_p2`.`coupons` AS c " +
            "JOIN `coupon_project_p2`.`customers_coupons` AS cc ON cc.coupons_coupon_id = c.coupon_id" +
            "WHERE cc.customer_customer_id = :customerId AND c.category = :category",
            nativeQuery = true)
    List<Coupon> findByCustomerIdAndCategory(@Param("customerId") int customerId, @Param("category") String category);

    @Query(value = "SELECT c.coupon_id, c.amount, c.category, c.description, c.end_date, c.image, c.price, c.start_date, c.title, c.company_id" +
            "FROM `coupon_project_p2`.`coupons` AS c " +
            "JOIN `coupon_project_p2`.`customers_coupons` AS cc ON cc.coupons_coupon_id = c.coupon_id" +
            "WHERE cc.customer_customer_id = :customerId AND c.price < :price",
            nativeQuery = true)
    List<Coupon> findByCustomerIdAndPriceLessThan(@Param("customerId") int customerId, @Param("price") double price);
}

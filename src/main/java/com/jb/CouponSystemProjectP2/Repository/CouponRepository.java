package com.jb.CouponSystemProjectP2.Repository;

import com.jb.CouponSystemProjectP2.Beans.Category;
import com.jb.CouponSystemProjectP2.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
//    @Query(value = "SELECT EXISTS (SELECT * FROM )",nativeQuery = true) todo build later
//    boolean existsByTitleAndCompanyId(String title, int company_id);
//    //SELECT EXISTS (SELECT * FROM coupon_project_p2.company WHERE company_id=3)
//    /*
//    "SELECT c.coupon_id, c.company_id, c.amount, c.price, c.category, c.title, c.description, c.image, c.start_date, c.end_date " +
//                    "FROM `coupon_project`.`coupons` AS c " +
//                    "JOIN `coupon_project`.`customer_to_coupon` AS ctc ON ctc.id_coupon = c.coupon_id " +
//                    "WHERE ctc.id_customer = ?"
//     */
    void deleteByEndDateAfter(Date endDate);

    @Query(value = "SELECT * FROM `coupon_project_p2`.`coupons` WHERE `company_id` = :companyId", nativeQuery = true)
    List<Coupon> findByCompanyId(@Param("companyId") int companyId);
    @Query(value = "SELECT * FROM `coupon_project_p2`.`coupons` WHERE `company_id` = :companyId AND `category` = :category", nativeQuery = true)
    List<Coupon> findByCompanyIdAndCategory(@Param("companyId") int companyId, @Param("category") Category category);
    @Query(value = "SELECT * FROM `coupon_project_p2`.`coupons` WHERE `company_id` = :companyId AND `price` < :price", nativeQuery = true)
    List<Coupon> findByCompanyIdAndPriceLessThan(@Param("companyId") int companyId, @Param("price") double price);


}

package com.zerobase.leisure.controller.coupon;

import com.zerobase.leisure.domain.form.AddLeisureCouponGroupForm;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.coupon.LeisureCouponGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leisure/couponGroup")
@RequiredArgsConstructor
public class LeisureCouponGroupController {

    private final LeisureCouponGroupService leisureCouponGroupService;

    @PostMapping
    public WebResponseData<String> addLeisureCouponGroup(@RequestBody AddLeisureCouponGroupForm form) {
        leisureCouponGroupService.addLeisureCouponGroup(form);
        return WebResponseData.ok("쿠폰을 등록했습니다.");
    }

    @PutMapping
    public WebResponseData<String> updateLeisureCouponGroup(@RequestBody AddLeisureCouponGroupForm form) {
        leisureCouponGroupService.updateLeisureCouponGroup(form);
        return WebResponseData.ok("쿠폰을 수정했습니다.");
    }

    @DeleteMapping
    public WebResponseData<String> deleteLeisureCouponGroup(@RequestParam Long leisureCouponGroupId) {
        leisureCouponGroupService.deleteLeisureCouponGroup(leisureCouponGroupId);

        return WebResponseData.ok("성공적으로 삭제되었습니다.");
    }
}

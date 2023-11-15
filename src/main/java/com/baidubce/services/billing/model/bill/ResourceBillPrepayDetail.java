/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.billing.model.bill;

import java.math.BigDecimal;

import lombok.Data;

/**
 * the detail info if the resource is a prepay resource
 */
@Data
public class ResourceBillPrepayDetail {

    /**
     * the type of order
     */
    private String orderType;

    /**
     * the chinese name of the order type
     */
    private String orderTypeDesc;

    /**
     * the origin price of the order
     */
    private BigDecimal orderOriginPrice;

    /**
     * the time length of the order resource
     */
    private BigDecimal time;

    /**
     * the time unit of the order resource
     */
    private String timeUnit;

    /**
     * the time of the order create
     */
    private String orderCreateTime;

    /**
     * 现金支付
     */
    private BigDecimal cash = BigDecimal.ZERO;

    /**
     * 返点支付
     */
    private BigDecimal rebate = BigDecimal.ZERO;

    /**
     * 帐期支付
     */
    private BigDecimal creditCost = BigDecimal.ZERO;

    /**
     * 账期退款
     */
    private BigDecimal creditRefund = BigDecimal.ZERO;

    /**
     * 欠费
     */
    private BigDecimal debt = BigDecimal.ZERO;

    /**
     * 代金劵
     */
    private BigDecimal couponPrice = BigDecimal.ZERO;

    /**
     * 折扣券
     */
    private BigDecimal discountCouponPrice = BigDecimal.ZERO;

    /**
     * 现金券
     */
    private BigDecimal cashEquivalentCouponPrice = BigDecimal.ZERO;

    /**
     * 折扣金额
     */
    private BigDecimal discountPrice = BigDecimal.ZERO;

    /**
     * 销账金额
     */
    private BigDecimal sysGold = BigDecimal.ZERO;

    private BigDecimal creditRefundDeduct = BigDecimal.ZERO;

    private BigDecimal deductPrice = BigDecimal.ZERO;

    private BigDecimal deductCash = BigDecimal.ZERO;

    private BigDecimal deductRebate = BigDecimal.ZERO;

    private BigDecimal deductCreditCost = BigDecimal.ZERO;

    private BigDecimal deductCouponPrice = BigDecimal.ZERO;

    private BigDecimal deductDiscountCouponPrice = BigDecimal.ZERO;

    private BigDecimal deductDiscountPrice = BigDecimal.ZERO;

    private BigDecimal deductCashEquivalentCouponPrice = BigDecimal.ZERO;

}

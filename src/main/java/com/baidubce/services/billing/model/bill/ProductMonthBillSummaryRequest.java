/*
 * Copyright (c) 2022 Baidu.com, Inc. All Rights Reserved
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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

/**
 * request model for query product month bill
 */
@Data
public class ProductMonthBillSummaryRequest extends AbstractBceRequest {

    /**
     * the month of bill
     * yyyy-MM
     */
    private String billMonth;

    /**
     * the type of charging
     * enum: prepay/postpay
     */
    private String productType;

    /**
     * the type of service
     */
    private String serviceType;

    /**
     * the account id you want to query
     * tips: only the master account of organization group can set the parameter to query the sub account's bill
     */
    private String queryAccountId;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

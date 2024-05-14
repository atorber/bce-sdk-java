/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.eipgroup.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Eip {

    /**
     * The name of eip.
     */
    private String name;

    /**
     * The ip of eip.
     */
    private String eip;

    /**
     * The status of eip.
     */
    private String status;

    /**
     * The eipInstanceType of eip.
     */
    private String eipInstanceType;

    /**
     * The instanceType of eip.
     */
    private String instanceType;

    /**
     * The instanceId of eip.
     */
    private String instanceId;

    /**
     * The shareGroupId of eip.
     */
    private String shareGroupId;

    /**
     * The paymentTiming of eip.
     */
    private String paymentTiming;

    /**
     * The bandwidthInMbps of eip.
     */
    private int bandwidthInMbps;

    /**
     * The billingMethod of eip.
     */
    private String billingMethod;

    /**
     * The createTime of eip.
     */
    private String createTime;

    /**
     * The expireTime of eip.
     */
    private String expireTime;
}

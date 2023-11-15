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
package com.baidubce.services.blb.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response for appBlb ipGroup backendPolicy.
 */
public class AppBackendPolicyResponse extends AbstractBceResponse {

    /**
     * the short id of the AppIpGroupBackendPolicy.
     */
    private String id;
    /**
     * the type of AppIpGroupBackendPolicy.
     */
    private String type;
    /**
     * the protocol of health check.
     */
    private String healthCheck;
    /**
     * the port of health check.
     */
    private Integer healthCheckPort;
    /**
     * the uri of health check.
     */
    private String healthCheckUrlPath;
    /**
     * the timeout (in second) of health check.
     */
    private Integer healthCheckTimeoutInSecond;
    /**
     * the interval (in second) of health check.
     */
    private Integer healthCheckIntervalInSecond;
    /**
     * down retry times of health check.
     */
    private Integer healthCheckDownRetry;
    /**
     * up retry times of health check.
     */
    private Integer healthCheckUpRetry;
    /**
     * the normal status of health check.
     */
    private String healthCheckNormalStatus;
    /**
     * the string of udp health check.
     */
    private String udpHealthCheckString;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHealthCheck() {
        return healthCheck;
    }

    public void setHealthCheck(String healthCheck) {
        this.healthCheck = healthCheck;
    }

    public Integer getHealthCheckPort() {
        return healthCheckPort;
    }

    public void setHealthCheckPort(Integer healthCheckPort) {
        this.healthCheckPort = healthCheckPort;
    }

    public String getHealthCheckUrlPath() {
        return healthCheckUrlPath;
    }

    public void setHealthCheckUrlPath(String healthCheckUrlPath) {
        this.healthCheckUrlPath = healthCheckUrlPath;
    }

    public Integer getHealthCheckTimeoutInSecond() {
        return healthCheckTimeoutInSecond;
    }

    public void setHealthCheckTimeoutInSecond(Integer healthCheckTimeoutInSecond) {
        this.healthCheckTimeoutInSecond = healthCheckTimeoutInSecond;
    }

    public Integer getHealthCheckIntervalInSecond() {
        return healthCheckIntervalInSecond;
    }

    public void setHealthCheckIntervalInSecond(Integer healthCheckIntervalInSecond) {
        this.healthCheckIntervalInSecond = healthCheckIntervalInSecond;
    }

    public Integer getHealthCheckDownRetry() {
        return healthCheckDownRetry;
    }

    public void setHealthCheckDownRetry(Integer healthCheckDownRetry) {
        this.healthCheckDownRetry = healthCheckDownRetry;
    }

    public Integer getHealthCheckUpRetry() {
        return healthCheckUpRetry;
    }

    public void setHealthCheckUpRetry(Integer healthCheckUpRetry) {
        this.healthCheckUpRetry = healthCheckUpRetry;
    }

    public String getHealthCheckNormalStatus() {
        return healthCheckNormalStatus;
    }

    public void setHealthCheckNormalStatus(String healthCheckNormalStatus) {
        this.healthCheckNormalStatus = healthCheckNormalStatus;
    }

    public String getUdpHealthCheckString() {
        return udpHealthCheckString;
    }

    public void setUdpHealthCheckString(String udpHealthCheckString) {
        this.udpHealthCheckString = udpHealthCheckString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}

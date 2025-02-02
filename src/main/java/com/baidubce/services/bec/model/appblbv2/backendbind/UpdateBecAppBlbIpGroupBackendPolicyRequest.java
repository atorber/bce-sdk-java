/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.appblbv2.backendbind;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-20 11:18
 * @Version v1.0
 * <p>
 * The request for updating app blb ip group policy.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBecAppBlbIpGroupBackendPolicyRequest extends AbstractBceRequest {

    /**
     * The app blb id.
     */
    private String blbId;

    /**
     * The unhealthy check threshold, making it unavailable after consecutive unsuccessful health checks.
     * Default is 3, it should be an integer between 2-5.
     */
    private Integer healthCheckDownRetry;

    /**
     * The health check host, like "localhost", default empty, valid under HTTP protocol.
     */
    private String healthCheckHost;

    /**
     * The health check interval time, the unit is second.
     * Default is 3, it should be an integer between 1-10.
     */
    private Integer healthCheckIntervalInSecond;

    /**
     * The health check http status code under healthy condition, like http_1xx|http_2xx; valid under HTTP protocol.
     */
    private String healthCheckNormalStatus;

    /**
     * The health check port, must assign under HTTP protocol.
     */
    private Integer healthCheckPort;

    /**
     * The health check timeout time, the unit is second.
     * Default is 3, it should be an integer between 1-60.
     */
    private Integer healthCheckTimeoutInSecond;

    /**
     * The healthy check threshold, making it available after consecutive successful health checks.
     * Default is 3, it should be an integer between 2-5.
     */
    private Integer healthCheckUpRetry;

    /**
     * The health check url path, default /, valid under HTTP protocol.
     */
    private String healthCheckUrlPath;

    /**
     * The ip group policy id.
     */
    private String id;

    /**
     * The ip group id.
     */
    private String ipGroupId;

    /**
     * The ip group protocol, TCP/HTTP/UDP.
     */
    private String type;

    /**
     * The UDP health check string, must assign under UDP protocol.
     */
    private String udpHealthCheckString;

    /**
     * An ASCII string whose length is less than 64.
     * <p>
     * The request will be idempotent if clientToken is provided.
     * If the clientToken is not specified by the user, a random String generated by default algorithm will be used.
     * See more detail at
     * <a href = "https://bce.baidu.com/doc/BEC/API.html">
     * BCE API doc</a>
     */
    @JsonIgnore
    private String clientToken;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return Request entity with credentials.
     */
    public UpdateBecAppBlbIpGroupBackendPolicyRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
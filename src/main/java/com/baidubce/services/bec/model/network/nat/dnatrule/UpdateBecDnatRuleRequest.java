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
package com.baidubce.services.bec.model.network.nat.dnatrule;

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
 * The request for updating D-NAT rule.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBecDnatRuleRequest extends AbstractBceRequest {

    /**
     * The nat id.
     */
    private String natId;

    /**
     * The rule id.
     */
    private String ruleId;

    /**
     * The intranet ip address.
     */
    private String privateIpAddress;

    /**
     * The intranet port.
     */
    private String privatePort;

    /**
     * Protocol, must be [TCP|UDP|all].
     */
    private String protocol;

    /**
     * The public ip address of the D-NAT.
     */
    private String publicIpAddress;

    /**
     * The public port of the D-NAT, 1-65535; must assign under TCP/UDP protocol.
     */
    private String publicPort;

    /**
     * The rule name.
     */
    private String ruleName;

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
    public UpdateBecDnatRuleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
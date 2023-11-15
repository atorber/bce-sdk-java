/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.eni.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * The request for creating new Elastic Network Interface Card.
 */
@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateEniRequest extends AbstractBceRequest {

    /**
     * An ASCII string whose length is less than 64.
     * The request will be idempotent if client token is provided.
     * If the clientToken is not specified by the user, a random String generated by default algorithm will be used.
     * See more detail at
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     * BCE API doc</a>
     */
    @JsonIgnore
    private String clientToken;

    /**
     * Name of the ENI which will be created
     */
    private String name;

    /**
     * id of the subnet to which the ENI belongs.
     */
    private String subnetId;

    /**
     * A set of security groups bound to the ENI
     */
    private List<String> securityGroupIds;

    /**
     * A set of enterprise security groups bound to the ENI
     */
    private List<String> enterpriseSecurityGroupIds;

    /**
     * Intranet IP information
     * Only one of the specified IPs can be the primary IP, and the others must be secondary IPs.
     */
    private List<PrivateIp> privateIpSet;

    /**
     * Description of ENI.
     */
    private String description;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return CreateEniRequest with credentials.
     */
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

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
package com.baidubce.services.bec.model.network.nat.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bec.model.network.nat.NatDeploymentInstance;
import com.baidubce.services.tag.model.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-21 16:58
 * @Version v1.0
 * <p>
 * The request for creating batch nats.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchCreateBecNatsRequest extends AbstractBceRequest {

    /**
     * The nat bandwidth, >1.
     */
    private Integer bandwidth;

    /**
     * The nat deployment instances.
     */
    private List<NatDeploymentInstance> deployInstances;

    /**
     * The description.
     */
    private String desc;

    /**
     * The nat name.
     */
    private String name;

    /**
     * The tags.
     */
    private List<Tag> tags;

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
    public BatchCreateBecNatsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
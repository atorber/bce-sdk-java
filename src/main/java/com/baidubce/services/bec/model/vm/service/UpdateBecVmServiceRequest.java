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
package com.baidubce.services.bec.model.vm.service;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bec.model.purchase.DeploymentInstance;
import com.baidubce.services.bec.model.vm.VolumeConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * The request for updating BEC virtual machine service.
 */
@Data
@Builder
public class UpdateBecVmServiceRequest extends AbstractBceRequest {

    /**
     * The Id of the vm service.
     */
    private String serviceId;

    /**
     * List of deployment instances.
     */
    private List<DeploymentInstance> deployInstances;

    /**
     * The name of the vm service.
     */
    private String serviceName;

    /**
     * The type of the vm service.
     */
    private String type;

    /**
     * The cpu of the vm service.
     */
    private int cpu;

    /**
     * The memory of the vm service.
     */
    private int memory;

    /**
     * Data disk configuration information.
     */
    private VolumeConfig dataStorage;

    /**
     * The password of the vm service.
     */
    private String adminPass;

    /**
     * The imageId of the service.
     */
    private String imageId;

    /**
     * The imageType of the service.
     */
    private String imageType;

    /**
     * The bandwidth of the service.
     */
    private float bandwidth;

    /**
     * The vm name of the service.
     */
    private String vmName;

    /**
     * The spec of the vm service.
     */
    private String spec;

    /**
     * The hostname of the vm service.
     */
    private String hostname;

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
     * @return UpdateBecVmServiceRequest with credentials.
     */
    public UpdateBecVmServiceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

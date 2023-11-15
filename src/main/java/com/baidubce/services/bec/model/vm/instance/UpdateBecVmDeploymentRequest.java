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
package com.baidubce.services.bec.model.vm.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bec.model.vm.VolumeConfig;
import com.baidubce.services.bec.model.vm.network.VmNetworkConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * The request for updating BEC virtual machine resources.
 */
@Data
public class UpdateBecVmDeploymentRequest extends AbstractBceRequest {

    /**
     * The id of the BEC virtual machine.
     */
    private String vmID;

    /**
     * The hostname of the BEC virtual machine.
     */
    private String hostname;

    /**
     * Update type.
     */
    private String type;

    /**
     * The number of CPUs of the virtual machine instance.
     */
    private int cpu;

    /**
     * The memory of the virtual machine instance.
     */
    private int memory;

    /**
     * Data disk size.
     */
    private VolumeConfig dataStorage;

    /**
     * Password.
     */
    private String adminPass;

    /**
     * Image id.
     */
    private String imageId;

    /**
     * The bandwidth of the BEC virtual machine.
     */
    private float bandwidth;

    /**
     * The name of the BEC virtual machine.
     */
    private String vmName;

    /**
     * Virtual machine network information configuration
     */
    private VmNetworkConfig networkConfig;

    /**
     * Whether IPV6 is required
     */
    private Boolean needIpv6PublicIp;

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
    public UpdateBecVmDeploymentRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

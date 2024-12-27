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
package com.baidubce.services.bec.model.vm.deployset;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-07 09:41
 * @Version v1.0
 * <p>
 * The request for deleting the deployment set's virtual machine.
 */
@Data
public class DeleteBecDeploySetInstancesRequest extends AbstractBceRequest {

    /**
     * The virtual machine instance ids of the deployment set.
     */
    List<String> instanceIdList;

    /**
     * The id of the deployment set.
     */
    String deploysetId;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return DeleteBecDeploySetInstancesRequest with credentials.
     */
    public DeleteBecDeploySetInstancesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
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

package com.baidubce.services.tablestorage.model;

import com.baidubce.services.tablestorage.TableStorageConstants;

/**
 * Represent the request for show instance operation
 */
public class ShowInstanceRequest extends AbstractTableStorageRequest {

    private String instanceName;

    /**
     * Construct a show instance request with target instance name
     *
     * @param instanceName The target instance name
     */
    public ShowInstanceRequest(String instanceName) {
        super();
        this.instanceName = instanceName;
    }

    /**
     * Get the target instance name
     *
     * @return The target instance name
     */
    public String getInstanceName() {
        return instanceName;
    }

    /**
     * Convert this request to json string
     *
     * @return An empty string.
     */
    @Override
    public String toJsonString() {
        return TableStorageConstants.EMPTY_JSON_STR;
    }
}

/* vim: set expandtab ts=4 sw=4 sts=4 tw=100: */
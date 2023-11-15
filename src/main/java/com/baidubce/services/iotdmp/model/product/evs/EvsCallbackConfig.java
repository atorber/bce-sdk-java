/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdmp.model.product.evs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EvsCallbackConfig {

    private Boolean enabled = Boolean.FALSE;
    private List<EvsCallbackType> types = null;
    private String endpoint = null;
    private Boolean authEnabled = Boolean.FALSE;
    private String key = null;

    public EvsCallbackConfig(Boolean enabled, List<EvsCallbackType> types, String endpoint) {
        this.enabled = enabled;
        this.types = types;
        this.endpoint = endpoint;
    }
}
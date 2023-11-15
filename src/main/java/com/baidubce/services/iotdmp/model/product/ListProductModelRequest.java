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
package com.baidubce.services.iotdmp.model.product;

import com.baidubce.model.GenericAccountRequest;
import com.baidubce.services.iotdmp.model.device.DeviceType;
import com.baidubce.services.iotdmp.model.product.repositories.ProductModelType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListProductModelRequest extends GenericAccountRequest {
    private String productName;
    @Builder.Default
    private final int pageNo = 1;
    @Builder.Default
    private final int pageSize = 10;
    private ProductModelType type;
    private DeviceType deviceType;
    private String keyword;
}

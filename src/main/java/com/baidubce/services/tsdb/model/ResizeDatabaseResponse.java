/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.tsdb.model;

import static com.baidubce.services.tsdb.TsdbConstants.DateFormat.DATETIME_FORMAT;

import java.math.BigDecimal;
import java.util.Date;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Resize database response model.
 */
@Data
public class ResizeDatabaseResponse extends AbstractBceResponse {

    private BigDecimal charge;

    private String orderId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT, timezone = "UTC")
    private Date expiredTime;
}

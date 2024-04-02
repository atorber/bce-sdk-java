/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.as.model.asgroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * The request for getting the asNode list.
 */
@Data
public class ListAsNodeRequest extends AbstractBceRequest {
    /**
     * The ID of auto scaling group.
     */
    private String groupId;

    /**
     * 过滤规则的关键词
     * */
    private String keyword;
    /**
     * 关键词的类型
     * */
    private String keywordType;
    /**
     * 规则的顺序，默认为"desc"
     * */
    private String order;
    /**
     * 排序规则的字段，默认为"createTime"
     * */
    private String orderBy;
    /**
     * 页码，默认为1
     * */
    private Integer pageNo;
    /**
     * 页面大小，默认为1000
     * */
    private Integer pageSize;

    @Override
    public ListAsNodeRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

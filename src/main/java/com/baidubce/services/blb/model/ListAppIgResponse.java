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
package com.baidubce.services.blb.model;

import com.baidubce.model.ListResponse;

import java.util.List;

/**
 * The response for list appBlb ipGroup
 */
public class ListAppIgResponse extends ListResponse {

    /**
     * the appIpGroup info list.
     */
    private List<AppIgResponse> appIpGroupList;

    public List<AppIgResponse> getAppIpGroupList() {
        return appIpGroupList;
    }

    public void setAppIpGroupList(List<AppIgResponse> appIpGroupList) {
        this.appIpGroupList = appIpGroupList;
    }
}

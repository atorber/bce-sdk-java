/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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

import lombok.ToString;

/**
 * The response for create blb.
 */
public class CreateBlbResponse extends ListResponse {

    /**
     * the id of the blb.
     */
    private String blbId;
    /**
     * the name of the blb.
     */
    private String name;
    /**
     * the description of the blb.
     */
    private String desc;
    /**
     * the address of the blb.
     */
    private String address;

    public String getBlbId() {
        return blbId;
    }

    public void setBlbId(String blbId) {
        this.blbId = blbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CreateBlbResponse{" +
                "blbId='" + blbId + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

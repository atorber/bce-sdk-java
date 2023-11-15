/*
 * Copyright 2016 Baidu, Inc.
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

package com.baidubce.services.cdn.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Date;

/**
 * @author yixing
 *
 */
public class GetPrefetchStatusRequest extends CdnRequest {
    private String marker;
    private String id;
    private String url;

    private Date endTime;
    private Date startTime;
    
    /**
     * @return marker
     */
    public String getMarker() {
        return marker;
    }

    /**
     * @param marker
     */
    public void setMarker(String marker) {
        this.marker = marker;
    }
    
    /**
     * @return id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return returns this object
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * @param t
     */
    public void setEndTime(Date t) {
        this.endTime = t;
    }
    
    /**
     * @return endTime
     */
    public Date getEndTime() {
        return endTime;
    }
    
    /**
     * @param t
     */
    public void setStartTime(Date t) {
        this.startTime = t;
    }
    
    /**
     * @return startTime
     */
    public Date getStartTime() {
        return startTime;
    }
    
    /**
     * @param id
     * @return returns this object
     */
    public GetPrefetchStatusRequest withId(String id) {
        this.setId(id);
        return this;
    }

    /**
     * @param url
     * @return returns this object
     */
    public GetPrefetchStatusRequest withUrl(String url) {
        this.setUrl(url);
        return this;
    }
    
    /**
     * @param startTime
     * @return returns this object
     */
    public GetPrefetchStatusRequest withStartTime(Date startTime) {
        this.setStartTime(startTime);
        return this;
    }
    
    /**
     * @param endTime
     * @return returns this object
     */
    public GetPrefetchStatusRequest withEndTime(Date endTime) {
        this.setEndTime(endTime);
        return this;
    }

    /**
     * @param marker
     * @return returns this object
     */
    public GetPrefetchStatusRequest withMarker(String marker) {
        this.setMarker(marker);
        return this;
    }

}

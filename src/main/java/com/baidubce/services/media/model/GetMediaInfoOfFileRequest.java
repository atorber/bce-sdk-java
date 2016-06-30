/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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
 
package com.baidubce.services.media.model;

import static com.baidubce.util.Validate.checkStringNotEmpty;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GetMediaInfoOfFileRequest extends AbstractBceRequest {

    private String key    = null;

    private String bucket = null;

    /**
     **/
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        checkStringNotEmpty(key, "The parameter key should NOT be null or empty string.");
        this.key = key;
    }

    public GetMediaInfoOfFileRequest withKey(String key) {
        checkStringNotEmpty(key, "The parameter key should NOT be null or empty string.");
        this.key = key;
        return this;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        checkStringNotEmpty(bucket, "The parameter bucket should NOT be null or empty string.");
        this.bucket = bucket;
    }

    public GetMediaInfoOfFileRequest withBucket(String bucket) {
        checkStringNotEmpty(bucket, "The parameter bucket should NOT be null or empty string.");
        this.bucket = bucket;
        return this;
    }

    public GetMediaInfoOfFileRequest withRequestCredentials(
            BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetMediaInfoOfFileRequest {\n");
        sb.append("    Bucket: ").append(bucket).append("\n");
        sb.append("    Key: ").append(key).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
/*
 * Copyright 2018 Baidu, Inc.
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
package com.baidubce.services.kms.model;

import com.baidubce.auth.BceCredentials;

/**
 * Provides options for enableKey master key.
 */
public class UpdateRotationRequest extends GenericKmsRequest {

    private String keyId;

    private int rotateCycle;

    public UpdateRotationRequest() {
    }

    public UpdateRotationRequest(String keyId, int rotateCycle) {
        this.setKeyId(keyId);
        this.setRotateCycle(rotateCycle);
    }

    /**
     * Overrides abstract method withRequestCredentials(BceCredentials) in AbstractBceRequest
     */ 
    public UpdateRotationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setRotateCycle(int rotateCycle){
        this.rotateCycle = rotateCycle;
    }

    public int getRotateCycle() {
        return this.rotateCycle;
    }
}
// vim: et tw=100 ts=4 sw=4 cc=120

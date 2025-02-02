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
package com.baidubce.services.peerconn.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.eip.model.Billing;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The request for creating a peer connection.
 */
public class CreatePeerConnRequest extends AbstractBceRequest {

    /**
     * An ASCII string whose length is less than 64.
     *
     * The request will be idempotent if clientToken is provided.
     * If the clientToken is not specified by the user, a random String generated by default algorithm will be used.
     * See more detail at
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     *     BCE API doc</a>
     */
    @JsonIgnore
    private String clientToken;

    /**
     * The bandwidthInMbps of peer conn.
     */
    private int bandwidthInMbps;

    /**
     * The description of peer conn.
     */
    private String description;

    /**
     * The localIfName of peer conn.
     */
    private String localIfName;

    /**
     * The localVpcId of peer conn.
     */
    private String localVpcId;

    /**
     * The peerAccountId of peer conn.
     */
    private String peerAccountId;

    /**
     * The peerVpcId of peer conn.
     */
    private String peerVpcId;

    /**
     * The peerRegion of peer conn.
     */
    private String peerRegion;

    /**
     * The peerIfName of peer conn.
     */
    private String peerIfName;

    /**
     * The billing of peer conn that will be created.
     */
    private Billing billing;

    /**
     * the list of tags which will be bound to peer conn instance
     */
    private List<TagModel> tags;

    /**
     * The ID of resourceGroup which will be bound to peer conn.
     */
    private String resourceGroupId;

    /**
     * The delete protect switch of peer conn.
     */
    private Boolean deleteProtect;

    /**
     * Configure billing for the request.
     *
     * @param billing The billing of CreatePeerConnRequest
     * @return CreatePeerConnRequest with specific billing
     */
    public CreatePeerConnRequest withBilling(Billing billing) {
        this.billing = billing;
        return this;
    }

    /**
     * Configure peerIfName for the request.
     *
     * @param peerIfName The peerIfName of CreatePeerConnRequest
     * @return CreatePeerConnRequest with specific peerIfName
     */
    public CreatePeerConnRequest withPeerIfName(String peerIfName) {
        this.peerIfName = peerIfName;
        return this;
    }

    /**
     * Configure peerRegion for the request.
     *
     * @param peerRegion The peerIfName of CreatePeerConnRequest
     * @return CreatePeerConnRequest with specific peerRegion
     */
    public CreatePeerConnRequest withPeerRegion(String peerRegion) {
        this.peerRegion = peerRegion;
        return this;
    }

    /**
     * Configure peerVpcId for the request.
     *
     * @param peerVpcId The peerVpcId of CreatePeerConnRequest
     * @return CreatePeerConnRequest with specific peerVpcId
     */
    public CreatePeerConnRequest withPeerVpcId(String peerVpcId) {
        this.peerVpcId = peerVpcId;
        return this;
    }

    /**
     * Configure peerAccountId for the request.
     *
     * @param peerAccountId The peerAccountId of CreatePeerConnRequest
     * @return CreatePeerConnRequest with specific peerAccountId
     */
    public CreatePeerConnRequest withPeerAccountId(String peerAccountId) {
        this.peerAccountId = peerAccountId;
        return  this;
    }

    /**
     * Configure localVpcId for the request.
     *
     * @param localVpcId The localVpcId of CreatePeerConnRequest.
     * @return CreatePeerConnRequest with specific localVpcId.
     */
    public CreatePeerConnRequest withLocalVpcId(String localVpcId) {
        this.localVpcId = localVpcId;
        return this;
    }

    /**
     * Configure localIfName for the request.
     *
     * @param localIfName The localIfName of CreatePeerConnRequest.
     * @return CreatePeerConnRequest with specific localIfName.
     */
    public CreatePeerConnRequest withLocalIfName(String localIfName) {
        this.localIfName = localIfName;
        return this;
    }

    /**
     * Configure description for the request.
     *
     * @param description The description of CreatePeerConnRequest.
     * @return CreatePeerConnRequest with specific description.
     */
    public CreatePeerConnRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Configure bandwidthInMbps for the request.
     *
     * @param bandwidthInMbps The bandwidthInMbps of CreatePeerConnRequest.
     * @return CreatePeerConnRequest with specific bandwidthInMbps.
     */
    public CreatePeerConnRequest withBandwidthInMbps(int bandwidthInMbps) {
        this.bandwidthInMbps = bandwidthInMbps;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public int getBandwidthInMbps() {
        return bandwidthInMbps;
    }

    public void setBandwidthInMbps(int bandwidthInMbps) {
        this.bandwidthInMbps = bandwidthInMbps;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalIfName() {
        return localIfName;
    }

    public void setLocalIfName(String localIfName) {
        this.localIfName = localIfName;
    }

    public String getLocalVpcId() {
        return localVpcId;
    }

    public void setLocalVpcId(String localVpcId) {
        this.localVpcId = localVpcId;
    }

    public String getPeerAccountId() {
        return peerAccountId;
    }

    public void setPeerAccountId(String peerAccountId) {
        this.peerAccountId = peerAccountId;
    }

    public String getPeerVpcId() {
        return peerVpcId;
    }

    public void setPeerVpcId(String peerVpcId) {
        this.peerVpcId = peerVpcId;
    }

    public String getPeerRegion() {
        return peerRegion;
    }

    public void setPeerRegion(String peerRegion) {
        this.peerRegion = peerRegion;
    }

    public String getPeerIfName() {
        return peerIfName;
    }

    public void setPeerIfName(String peerIfName) {
        this.peerIfName = peerIfName;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    public String getResourceGroupId() {
        return resourceGroupId;
    }

    public void setResourceGroupId(String resourceGroupId) {
        this.resourceGroupId = resourceGroupId;
    }

    public Boolean getDeleteProtect() {
        return deleteProtect;
    }

    public void setDeleteProtect(Boolean deleteProtect) {
        this.deleteProtect = deleteProtect;
    }
}

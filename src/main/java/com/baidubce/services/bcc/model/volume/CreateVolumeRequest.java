/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.volume;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcc.model.AutoSnapshotPolicyModel;
import com.baidubce.services.bcc.model.Billing;
import com.baidubce.services.bcc.model.TagModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.List;
/**
 * The request for creating a new volume.
 */
@Data
public class CreateVolumeRequest extends AbstractBceRequest {

    /**
     * An ASCII string whose length is less than 64.
     * Configure optional client token for the request. The request will be idempotent if client token is provided.
     * See more detail at
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     *     BCE API doc</a>
     */
    @JsonIgnore
    private String clientToken;

    /**
     * The optional parameter to specify how many volumes to buy, default value is 1.
     * The maximum to create for one time is 5.
     */
    private int purchaseCount = 1;

    /**
     * The size of volume to create in GB.
     * By specifying the snapshotId,
     * it will create volume from the specified snapshot and the parameter cdsSizeInGB will be ignored.
     */
    private int cdsSizeInGB;

    /**
     * The storage type of volume, see more detail in
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#StorageType">BCE API doc</a>
     */
    private String storageType;

    /**
     * The detail model to specify the billing.
     */
    @Deprecated
    private Billing billing;

    /**
     * The id of snapshot.
     * By specifying the snapshotId,
     * it will create volume from the specified snapshot and the parameter cdsSizeInGB will be ignored.
     */
    private String snapshotId;

    /**
     * the name of available zone, optional param
     * through listZones, we can get all available zone info at current region
     * e.g. "cn-gz-a"  "cn-gz-b"
     */
    private String zoneName;

    /**
     * The id of cluster.
     */
    private String clusterId;

    /**
     * The optional parameter to specify the payment for the volume.
     * The billing type and payment method, including Prepaid and Postpaid,
     * need to be specified only when the instanceId is not empty and the corresponding instance type is prepaid.
     * If instanceId is empty:
     * create a post payment type CDS;
     * If the instanceId is not empty:
     * If the instance is prepaid, a chargeType needs to be specified;
     * If the instance is post paid, create a post paid CDS
     */
    private String chargeType;

    private int cdsExtraIo;

    private String instanceId;

    private String encryptKey;

    private String name;

    private String description;

    private int renewTime;

    private String renewTimeUnit;

    private Boolean relationTag;

    private List<TagModel> tags;

    private List<String> resGroupIds;

    private AutoSnapshotPolicyModel autoSnapshotPolicy;

    public CreateVolumeRequest withCdsExtraIo(int cdsExtraIo) {
        this.cdsExtraIo = cdsExtraIo;
        return this;
    }

    public CreateVolumeRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public CreateVolumeRequest withName(String name) {
        this.name = name;
        return this;
    }

    public CreateVolumeRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public CreateVolumeRequest withRenewTime(int renewTime) {
        this.renewTime = renewTime;
        return this;
    }

    public CreateVolumeRequest withRenewTimeUnit(String renewTimeUnit) {
        this.renewTimeUnit = renewTimeUnit;
        return this;
    }

    public CreateVolumeRequest withRelationTag(Boolean relationTag) {
        this.relationTag = relationTag;
        return this;
    }

    public CreateVolumeRequest withTags(List<TagModel> tags) {
        this.tags = tags;
        return this;
    }

    public CreateVolumeRequest withResGroupIds(List<String> resGroupIds) {
        this.resGroupIds = resGroupIds;
        return this;
    }

    public CreateVolumeRequest withAutoSnapshotPolicy(AutoSnapshotPolicyModel autoSnapshotPolicy) {
        this.autoSnapshotPolicy = autoSnapshotPolicy;
        return this;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    /**
     * Configure optional client token for the request. The request will be idempotent if client token is provided.
     *
     * @param clientToken An ASCII string whose length is less than 64.
     *                    See more detail at
     *                    <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     *                        BCE API doc</a>
     * @return CreateVolumeRequest with specific clientToken
     */
    public CreateVolumeRequest withClientToken(String clientToken) {
        this.clientToken = clientToken;
        return this;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    /**
     * Configure purchaseCount for the request.
     *
     * @param purchaseCount The number of volume to buy, the default value is 1.
     * @return CreateVolumeRequest with specific purchaseCount
     */
    public CreateVolumeRequest withPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
        return this;
    }

    public int getCdsSizeInGB() {
        return cdsSizeInGB;
    }

    public void setCdsSizeInGB(int cdsSizeInGB) {
        this.cdsSizeInGB = cdsSizeInGB;
    }

    /**
     * Configure cdsSizeInGB for the request.
     *
     * @param cdsSizeInGB The size of volume to create in GB. By specifying the snapshotId,
     *                    it will create volume from the specified snapshot and the parameter cdsSizeInGB will be ignored.
     * @return CreateVolumeRequest with cdsSizeInGB.
     */
    public CreateVolumeRequest withCdsSizeInGB(int cdsSizeInGB) {
        this.cdsSizeInGB = cdsSizeInGB;
        return this;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    /**
     * Configure storageType for the request.
     *
     * @param storageType The storage type of volume, see more detail in
     *                    <a href = "https://bce.baidu.com/doc/BCC/API.html#StorageType">BCE API doc</a>
     * @return CreateVolumeRequest with storageType.
     */
    public CreateVolumeRequest withStorageType(String storageType) {
        this.storageType = storageType;
        return this;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    /**
     * Configure billing for the request.
     *
     * @param billing The detail model to specify the billing.
     * @return CreateVolumeRequest with specific billing
     */
    public CreateVolumeRequest withBilling(Billing billing) {
        this.billing = billing;
        return this;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    /**
     * Configure snapshotId for the request.
     *
     * @param snapshotId The id of snapshot.By specifying the snapshotId,
     *                   it will create volume from the specified snapshot and the parameter cdsSizeInGB will be ignored.
     * @return CreateVolumeRequest with snapshotId.
     */
    public CreateVolumeRequest withSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
        return this;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public CreateVolumeRequest withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public CreateVolumeRequest withClusterId(String clusterId) {
        this.clusterId = clusterId;
        return this;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public CreateVolumeRequest withChargeType(String chargeType) {
        this.chargeType = chargeType;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return CreateVolumeRequest with credentials.
     */
    @Override
    public CreateVolumeRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

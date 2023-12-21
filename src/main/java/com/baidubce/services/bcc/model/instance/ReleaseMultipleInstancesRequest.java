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
package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * The request for releasing the instance by post.
 */
public class ReleaseMultipleInstancesRequest extends AbstractBceRequest {

    /**
     * The id of instances.
     */
    private List<String> instanceIds;

    /**
     * Whether to release the EIP and the data disk attached to the instance at the current time
     * in the same time. If<code>true</code>, it means release, and the parameters deleteCdsSnapshotFlag works.
     * If<code>false</code>, it means not to release, and the parameters deleteCdsSnapshotFlag does not works.
     */
    private boolean relatedReleaseFlag;

    /**
     * Whether to delete the CDS Snapshot. If<code>true</code>, it means delete.
     */
    private boolean deleteCdsSnapshotFlag;

    /**
     * Whether to recycle the bcc. If<code>true</code>, it means recycle.
     */
    private boolean bccRecycleFlag;

    /**
     * Whether to delete the ENI. If<code>true</code>, it means delete.
     */
    private boolean deleteRelatedEnisFlag;

    public List<String> getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
    }

    public ReleaseMultipleInstancesRequest withInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
        return this;
    }

    public boolean isRelatedReleaseFlag() {
        return relatedReleaseFlag;
    }

    public void setRelatedReleaseFlag(boolean relatedReleaseFlag) {
        this.relatedReleaseFlag = relatedReleaseFlag;
    }

    public ReleaseMultipleInstancesRequest withRelatedReleaseFlag(boolean relatedReleaseFlag) {
        this.relatedReleaseFlag = relatedReleaseFlag;
        return this;
    }

    public boolean isDeleteCdsSnapshotFlag() {
        return deleteCdsSnapshotFlag;
    }

    public void setDeleteCdsSnapshotFlag(boolean deleteCdsSnapshotFlag) {
        this.deleteCdsSnapshotFlag = deleteCdsSnapshotFlag;
    }

    public ReleaseMultipleInstancesRequest withDeleteCdsSnapshotFlag(boolean deleteCdsSnapshotFlag) {
        this.deleteCdsSnapshotFlag = deleteCdsSnapshotFlag;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ReleaseInstanceByPostRequest with credentials.
     */
    public ReleaseMultipleInstancesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public boolean isBccRecycleFlag() {
        return bccRecycleFlag;
    }

    public void setBccRecycleFlag(boolean bccRecycleFlag) {
        this.bccRecycleFlag = bccRecycleFlag;
    }

    public ReleaseMultipleInstancesRequest withBccRecycleFlag(boolean bccRecycleFlag) {
        this.bccRecycleFlag = bccRecycleFlag;
        return this;
    }

    public boolean isDeleteRelatedEnisFlag() {
        return deleteRelatedEnisFlag;
    }

    public void setDeleteRelatedEnisFlag(boolean deleteRelatedEnisFlag) {
        this.deleteRelatedEnisFlag = deleteRelatedEnisFlag;
    }

    public ReleaseMultipleInstancesRequest withDeleteRelatedEnisFlag(boolean deleteRelatedEnisFlag) {
        this.deleteRelatedEnisFlag = deleteRelatedEnisFlag;
        return this;
    }
}

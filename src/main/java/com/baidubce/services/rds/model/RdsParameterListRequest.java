package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request of get the rds parameter list
 */
public class RdsParameterListRequest extends AbstractBceRequest {

    private String instanceId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}

package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

@Data
public class RestartClusterRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public RestartClusterRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public RestartClusterRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

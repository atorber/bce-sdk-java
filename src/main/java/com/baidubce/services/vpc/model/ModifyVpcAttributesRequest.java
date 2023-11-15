package com.baidubce.services.vpc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request for modify vpc
 */
public class ModifyVpcAttributesRequest extends AbstractBceRequest {

    /**
     * An ASCII string whose length is less than 64.
     * <p/>
     * The request will be idempotent if clientToken is provided.
     * If the clientToken is not specified by the user, a random String generated by default algorithm will be used.
     * See more detail at
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     * BCE API doc</a>
     */
    @JsonIgnore
    private String clientToken;

    /**
     * The id of vpc.
     */
    private String vpcId;

    /**
     * The new name of vpc.
     */
    private String name;

    /**
     * The new description for vpc.
     */
    private String description;

    /**
     * whether assign vpc Ipv6 cidr
     */
    private Boolean enableIpv6;

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public Boolean getEnableIpv6() {
        return enableIpv6;
    }

    public void setEnableIpv6(Boolean enableIpv6) {
        this.enableIpv6 = enableIpv6;
    }

    /**
     * Configure optional client token for the request. The request will be idempotent if client token is provided.
     * If the clientToken is not specified by the user, a random String generated by default algorithm will be used.
     *
     * @param clientToken An ASCII string whose length is less than 64.
     *                    See more detail at
     *                    <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     *                    BCE API doc</a>
     * @return ModifySubnetAttributesRequest with specific clientToken
     */
    public ModifyVpcAttributesRequest withClientToken(String clientToken) {
        this.setClientToken(clientToken);
        return this;
    }

    /**
     * Configure vpc id for the request.
     *
     * @param vpcId the id of vpc
     * @return ModifyVpcAttributesRequest with vpcId
     */
    public ModifyVpcAttributesRequest withVpcId(String vpcId) {
        this.vpcId = vpcId;
        return this;
    }

    /**
     * Configure name for the request.
     *
     * @param name The name of vpc
     * @return ModifyVpcAttributesRequest with specific name
     */
    public ModifyVpcAttributesRequest withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Configure description for the request.
     *
     * @param description the type of vpc
     * @return ModifyVpcAttributesRequest with description
     */
    public ModifyVpcAttributesRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ModifyVpcAttributesRequest with credentials.
     */
    @Override
    public ModifyVpcAttributesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

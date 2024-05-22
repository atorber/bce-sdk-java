package com.baidubce.services.bcc.model;

import com.baidubce.services.eni.model.Eni;
import lombok.Data;

import java.util.List;

@Data
public class NicInfo {
    private String eniId;
    private String eniUuid;
    private String name;
    private String type;
    private String subnetId;
    private String subnetType;
    private String az;
    private String description;
    private String deviceId;
    private String status;
    private List<IpInfo> ips;
    private List<IpInfo> ipv6s;
    private String macAddress;
    private String vpcId;
    private List<String> securityGroups;
    private String createdTime;
    private int eniNum;
    private int eriNum;
    private List<Eni> eriInfos;
    private List<Eni> eniInfos;

    @Data
    public static class IpInfo {
        private String privateIp;
        private String eip;
        private boolean primary;
        private String eipId;
        private String eipAllocationId;
        private Integer eipSize;
        private String eipStatus;
        private String eipGroupId;
    }
}

package com.baidubce.examples.ipcollection.ipgroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipcollection.IpCollectionClient;
import com.baidubce.services.ipcollection.IpCollectionClientConfiguration;
import com.baidubce.services.ipcollection.model.ipgroup.QueryIpGroupListRequest;
import com.baidubce.services.ipcollection.model.ipgroup.QueryIpGroupListResponse;

public class ExampleQueryIpGroupList {
    /**
     * ExampleQueryIpGroupList main method
     *
     * @param args
     */
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名
        IpCollectionClientConfiguration config = new IpCollectionClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        IpCollectionClient ipCollectionClient = new IpCollectionClient(config);

        QueryIpGroupListRequest request = new QueryIpGroupListRequest();
        request.setIpVersion("IPv4");
        request.setMaxKeys(10);
        request.setMarker("ipg-de165m88ipiz");
        try {
            QueryIpGroupListResponse response = ipCollectionClient.queryIpGroupList(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
package com.baidubce.examples.aihc.inference;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcInferenceClient;
import com.baidubce.services.aihc.model.inference.ListPodRequest;
import com.baidubce.services.aihc.model.inference.ListPodResponse;

public class ExampleListPod {
    public static void main(String[] args) {
        String ak = "Your AK";
        String sk = "Your SK";
        String endpoint = "aihc.baidubce.com";
        String region = "bj";

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        config.setProtocol(Protocol.HTTPS);
        AihcInferenceClient client = new AihcInferenceClient(config);

        ListPodRequest request = new ListPodRequest();
        request.setAppId("test");

        try {
            ListPodResponse response = client.listPod(request, region);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

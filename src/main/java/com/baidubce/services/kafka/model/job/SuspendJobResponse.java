package com.baidubce.services.kafka.model.job;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class SuspendJobResponse extends AbstractBceResponse {

    private String jobId;
}

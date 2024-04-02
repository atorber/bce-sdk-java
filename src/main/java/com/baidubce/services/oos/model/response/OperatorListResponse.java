package com.baidubce.services.oos.model.response;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperatorListResponse extends AbstractBceResponse {
    private boolean success;
    private String msg;
    private Integer code;
    private OperatorPageModel result;
}

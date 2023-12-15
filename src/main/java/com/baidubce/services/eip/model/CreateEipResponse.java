/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eip.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.ToString;

/**
 * Created by caidahai on 2016/6/23.
 */
@ToString
public class CreateEipResponse extends AbstractBceResponse {
    private String eip;

    public String getEip() {
        return eip;
    }

    public void setEip(String eip) {
        this.eip = eip;
    }
}

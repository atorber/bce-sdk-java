package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by hansongda on 20/12/01
 */
public class GetDomainQUICSwitchResponse extends CdnResponse {
    private boolean quic;

    public boolean isQuic() {
        return quic;
    }

    public void setQuic(boolean quic) {
        this.quic = quic;
    }
}

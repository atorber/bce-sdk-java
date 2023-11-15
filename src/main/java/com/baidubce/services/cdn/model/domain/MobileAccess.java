package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/27
 */
public class MobileAccess extends JsonObject {
    /**
     * true表示有针对性地对源端（移动端或PC端等）请求的资源内容分发，false表示无特殊针对
     * 必选
     */
    private boolean distinguishClient;

    /**
     * @return distinguishClient
     */
    public boolean isDistinguishClient() {
        return distinguishClient;
    }

    /**
     * @param distinguishClient Resource content distribution is requested on the source side
     */
    public void setDistinguishClient(boolean distinguishClient) {
        this.distinguishClient = distinguishClient;
    }

    /**
     * @param distinguishClient Resource content distribution is requested on the source side
     * @return this object
     */
    public MobileAccess withDistinguishClient(boolean distinguishClient) {
        this.distinguishClient = distinguishClient;
        return this;
    }
}

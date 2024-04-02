package com.baidubce.services.bcm.model.custom;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * 获取自定义空间事件列表响应对象
 *
 * @Author: wanglu51
 * @Date: 2023/12/6 19:58
 */
@Data
public class ListNamespaceEventsResponse extends AbstractBceResponse {
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;
    private List<NamespaceEvent> result;
}

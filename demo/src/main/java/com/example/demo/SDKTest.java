package com.example.demo;

import com.ctg.ag.sdk.biz.AepCommandModbusClient;
import com.ctg.ag.sdk.biz.AepPublicProductManagementClient;
import com.ctg.ag.sdk.biz.aep_public_product_management.QueryPublicByPublicProductIdRequest;

/**
 * @author yb
 * @description
 * @data 2020/8/17
 */
public class SDKTest {
    public static void main(String[] args) throws Exception {
        AepPublicProductManagementClient client = AepPublicProductManagementClient.newClient().build();
        QueryPublicByPublicProductIdRequest request = new QueryPublicByPublicProductIdRequest();
        client.QueryPublicByPublicProductId(request);
    }
}

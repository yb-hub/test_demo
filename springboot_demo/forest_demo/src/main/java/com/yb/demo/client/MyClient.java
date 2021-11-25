package com.yb.demo.client;

import com.dtflys.forest.annotation.Request;

public interface MyClient {
    @Request(url = "http://127.0.0.1:8001/forest/api/get")
    String getTest();
}

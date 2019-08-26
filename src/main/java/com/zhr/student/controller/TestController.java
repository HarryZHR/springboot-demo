package com.zhr.student.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class TestController {
    private RestTemplate restTemplate;

    public void test() {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://sys.china-kedu.com/ERPHRWeb/Handler/HandlerAPI.ashx?Command=FenJiDepartment", String.class);
        String secondBusinessStr = responseEntity.getBody();
        List<String> hospitalList = new ArrayList<>();
        if (secondBusinessStr != null) {
//            JSONArray secondBusinessList = JSONArray.fromObject(secondBusinessStr);
//            if (secondBusinessList.size() > 0) {
//
//            }
        }
    }
}

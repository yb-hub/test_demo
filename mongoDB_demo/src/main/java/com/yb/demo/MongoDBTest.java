package com.yb.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import com.yb.demo.entity.ImageEntity;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @author yb
 * @description
 * @data 2021/11/24
 */
@Controller
@Slf4j
public class MongoDBTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建collect
     */
    @PostMapping("/create/collect")
    public ResponseEntity createCollect(@RequestParam(value = "collectName") String collectName) {
        log.info("创建collect");
        MongoCollection<Document> result = mongoTemplate.createCollection(collectName);
        log.info("创建结果，result={}", result);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/insert")
    public ResponseEntity insert(@RequestBody ImageEntity imageEntity){
        log.info("新增图片，imageEntity={}",imageEntity);
        ImageEntity result = mongoTemplate.insert(imageEntity,"imageCollect");
        log.info("新增结果，result={}",result);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/queryList")
    public ResponseEntity queryList(@RequestParam(value = "tagName") String tagName){
        log.info("查询图片列表");
//        List<ImageEntity> imageCollect = mongoTemplate.findAll(ImageEntity.class, "imageCollect");
        Query query = new Query(Criteria.where("tagList").elemMatch(Criteria.where("tagName").is(tagName))).limit(1);
        List<ImageEntity> imageCollect = mongoTemplate.find(query,ImageEntity.class, "imageCollect");
        log.info("查询结果，result={}",imageCollect);
        return ResponseEntity.ok(imageCollect);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ImageEntity imageEntity){
        log.info("更新图片");
        Query query = new Query(Criteria.where("name").is(imageEntity.getName()));
        Update update = new Update();
        String str = JSON.toJSONString(imageEntity);
        JSONObject jsonObject = JSON.parseObject(str);
        jsonObject.forEach((key,value)->{
            if(!key.equals("name")){
                update.set(key,value);
            }
        });
        UpdateResult result = mongoTemplate.updateFirst(query, update, ImageEntity.class,"imageCollect");
        log.info("更新结果，result={}",result);
        return ResponseEntity.ok("success");
    }
}

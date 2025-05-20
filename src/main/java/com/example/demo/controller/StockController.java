package com.example.demo.controller;


import com.example.demo.entity.Stock;
import com.example.demo.service.StockService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {

    @Resource
    private StockService stockService;

    @GetMapping("/selectStock")
    public List<Stock> selectStock(){
        return stockService.selectAll();
    }
    @GetMapping("/RedisStock/{goodsId}")
    public String RedisStock(@PathVariable Integer goodsId){
        //初始化redis
        stockService.initStockToRedis(goodsId);
    return "初始化成功";
    }
    @GetMapping("/RedisDeduct/{goodsId}")
    public String RedisDeduct(@PathVariable Integer goodsId){
        //在redis中扣除
        boolean b = stockService.deductStock(goodsId);
        return b ?"扣减成功":"扣减失败";
    }
//    @GetMapping("/MysqlDeduct/{goodsId}")
//    public String MysqlDeduct(@PathVariable Integer goodsId){
//        //在redis中扣除
//        boolean b = stockService.syncStockToDb(goodsId);
//        return b ?"扣减数据库成功":"扣减数据库失败";
//    }

}

package com.example.demo.service;


import com.example.demo.entity.Stock;

import java.util.List;

public interface StockService {

     List<Stock> selectAll();

     //初始化Redis
     void initStockToRedis(Integer goodsId);

     //扣减Redis中的库存
     boolean deductStock(Integer goodsId);

     //扣减数据库
     boolean syncStockToDb(Integer goodsId);
}

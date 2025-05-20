package com.example.demo.dao;

import com.example.demo.entity.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface StockMapper {

     List<Stock> selectAll();
     Stock selectByPrimaryKey(Integer goodsId);

     int updateByPrimaryKey(@Param("id") Integer productId,
                                @Param("stock") Integer quantity);
}

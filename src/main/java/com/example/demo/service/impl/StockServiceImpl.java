package com.example.demo.service.impl;

import com.example.demo.dao.StockMapper;
import com.example.demo.entity.Stock;
import com.example.demo.service.StockService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class StockServiceImpl implements StockService {

    @Resource
    private StockMapper stockMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private DefaultRedisScript<Long> stockDeductScript;

    @Resource
    private DefaultRedisScript<Long> releaseLockScript;

    private static final String STOCK_KEY = "stock:";
    private static final String LOCK_KEY_PREFIX = "lock:stock:";
    @Override
    public List<Stock> selectAll() {
        return stockMapper.selectAll();
    }

    @Override
    public void initStockToRedis(Integer goodsId) {
        Stock stock = stockMapper.selectByPrimaryKey(goodsId);
        System.out.println(stock);
        if (stock == null){
          throw new RuntimeException("商品不存在");
        }
        String stockKey = getStockKey(stock.getId());
        System.out.println(stock.getStock());
        //确保只有一个线程可以操作
        redisTemplate.opsForValue().setIfAbsent(stockKey,stock.getStock());
        System.out.println(redisTemplate.opsForValue().get(stockKey));
    }

    @Override
    public boolean deductStock(Integer goodsId) {
        String stockKey = getStockKey(goodsId);
        String LockKey = getLockKey(goodsId);
        if(!redisTemplate.hasKey(stockKey)){
            synchronized (this){
                if(!redisTemplate.hasKey(stockKey)){
                    initStockToRedis(goodsId);
                }
            }
        }
        //获得分布式锁
        boolean locked = false;
        try {
            locked = acquireLock(LockKey);
            if (!locked){
                return false;// 获取锁失败，稍后重试
            }
            Long result = redisTemplate.execute(
                    stockDeductScript,
                    Collections.singletonList(stockKey),
                    1
            );
            System.out.println("redis:"+redisTemplate.opsForValue().get(stockKey));
            return result != null && result == 1;
        }finally {
            if (locked){
                releaseLock(LockKey);
            }
        }
    }

    @Override
    @Transactional
    public boolean syncStockToDb(Integer goodsId) {
        String stock =getStockKey(goodsId);
        Integer redisStock = (Integer) redisTemplate.opsForValue().get(stock);
        if(redisStock!=null){
            Stock stock1 = stockMapper.selectByPrimaryKey(goodsId);
            if(stock1==null){
                return false;
            }else {
                if(stock1.getStock()-redisStock>0){
                    int i = stockMapper.updateByPrimaryKey(goodsId, redisStock);
                    if(i!=1){
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
//获取锁分布式锁
    private boolean acquireLock(String lockKey){
        return redisTemplate.opsForValue().setIfAbsent(
                lockKey,
                Thread.currentThread().getId(),
                10,
                TimeUnit.SECONDS
        );
    }
//释放锁
    private void releaseLock(String lockKey){
       redisTemplate.execute(
                releaseLockScript,
                Collections.singletonList(lockKey),
                Thread.currentThread().getId()
        );
    }
    //获取redisKey
    private String getStockKey(Integer goodsId){
        return STOCK_KEY+goodsId;
    }
    //获取线程锁Id
    private String getLockKey(Integer productId) {
        return LOCK_KEY_PREFIX + productId;
    }
}

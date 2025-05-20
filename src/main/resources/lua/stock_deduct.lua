-- 库存扣减Lua脚本
local stock = tonumber(redis.call('get', KEYS[1]))
local quantity = tonumber(ARGV[1])
if stock >= quantity then
    redis.call('decrby', KEYS[1], quantity)
    return 1
else
    return 0
end
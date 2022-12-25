-- 详细命令参考 https://redis.io/commands/?group=hash
-- hash的值是一个键值对
-- 新增元素
hset user:1000 username antirez birthyear 1977 verified 1
hset user:1001 username hopeurl birthyear 2000 verified 1
-- 获取元素信息
hget user:1000 username
hget user:1000 birthyear
-- 获取user信息中id为1001的所有信息
hgetall user:1001
-- 获取user信息中id为1001的所有属性
hkeys user:1000
-- 获取user信息中id为1001的所有属性值
hvals user:1000
-- 获取user信息中id为1001的字段数量
hlen user:1000


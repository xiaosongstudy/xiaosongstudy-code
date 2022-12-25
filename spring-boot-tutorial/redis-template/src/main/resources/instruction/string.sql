set username 张三
-- 根据建获取值
get username
-- 将对应键的值递增1
    incr rank
get rank
-- 将对应键的值递减1
    decr rank
-- 指定每次新增时新增多少 数据类型为integer类型所以在新增的时候需要考虑其边界
    incrby rank 2147483645
-- 删除某个键
    del username
-- 设置key的存活时间，单位为秒
    expire rank 5
-- 给指定key设置value然后指定过期时间
set password admin123 ex 60
-- 查看存活时间
    ttl password
-- 如果不存在就设置（redis设置分布式锁原理）
    setnx password 123456
-- 可以使用mset或者mget命令进行批量操作
    mset a 10
    mget a username password
-- 判断某个key是否存在
    exists username
    exists birthday
-- 获取键的数据类型
    type username
-- 获取所有键
    keys *
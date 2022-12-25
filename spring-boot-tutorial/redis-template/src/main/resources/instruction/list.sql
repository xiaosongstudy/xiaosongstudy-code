-- redis list的实现是基于链表实现的，目的是为了实现大数据量下依然保持一个常量级的插入速率，但是相反其检索效率会薄弱一些，不会像数组那样可以通过下标检索
-- 头(head)插元素
lpush nameList zhangsan
lpush nameList lisi
lpush nameList wangwu

-- 尾(tail)插元素
rpush nameList liudaqiang
rpush nameList hopeurl

-- 遍历列表 0是第一个元素 -1是最后一个元素
lrange nameList 0 -1

-- 批量向列表中添加元素，不限制列表数据类型
type nameList
rpush dataList 1 2 3 "我是字符串" 55.4 A english
lrange dataList 0 -1

-- 获取列表的长度
llen dataList
-- 去除列表中的元素，它也有rpop以及lpop两种方式，取出后列表中则不会有该元素
rpop dataList
-- 经典应用案例
-- 1.记住用户发布到社交网络的最新更新
-- 2.进程之间的通信，使用消费者-生产者模式，其中生产者将项目推送到列表中，消费者(通常是工作人员)消费这些项目并执行操作。
-- Redis有特殊的列表命令来使用这个用例更加可靠和高效、
-- 丢弃旧的项
ltrim myList 0 2
lpush myList 1 2 3 4 5
lrange myList 0 -1
-- blpop myList 1

-- 当列表中没有元素的时候会自动删除列表


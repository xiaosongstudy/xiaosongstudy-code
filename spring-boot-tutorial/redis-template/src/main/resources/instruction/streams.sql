-- https://redis.io/docs/data-types/streams/
-- 使用streams追加温度信息
-- 1694332444900-0
XADD temperatures:us-ny:10007 * temp_f 87.2 pressure 29.69 humidity 46
-- 1694332470340-0
XADD temperatures:us-ny:10007 * temp_f 83.1 pressure 29.21 humidity 46.5
-- 1694332509299-0
XADD temperatures:us-ny:10007 * temp_f 81.9 pressure 28.37 humidity 43.7

-- 读取从 ID 1694332470340-0 开始的前两个流条目
xrange temperatures:us-ny:10007 1694332470340-0 + count 2

-- 从流末尾开始读取最多 100 个新流条目，如果没有写入条目，则最多阻塞 300 毫秒
-- 演示这个命令的时候需要借助redis命令行，这样会更加直观一些
xread count 200 block 300 streams temperatures:us-ny:10007 $

-- 获取 Stream 内的项目数
xlen temperatures:us-ny:10007

-- 要按范围查询流，我们只需要指定两个 ID：开始和结束。
-- 返回的范围将包括以开始或结束作为 ID 的元素，因此该范围是包含在内的。两个特殊的 ID - 和 + 分别表示可能的最小和最大 ID
xrange temperatures:us-ny:10007 - +

-- 省略id的序列部分
xrange temperatures:us-ny:10007 1694632959190 1694633190298

-- 追加计数参数
xrange temperatures:us-ny:10007 - + count 2

-- 数据量庞大时也支持类似mysql那种分页的效果，这里注意需要追加一个左括号
xrange temperatures:us-ny:10007 (1694633067989 + count 2

-- XREVRANGE 命令以相反的顺序获取开始和停止参数 ，这里暂时先放着

-- 使用 XREAD 监听新项目
-- 一个流可以有多个客户端（消费者）等待数据。默认情况下，每个新项目都将传递给正在等待给定流中数据的每个消费者。这种行为与阻塞列表不同，阻塞列表中每个消费者都会获得不同的元素。然而，扇出到多个消费者的能力类似于 Pub/Sub
-- 在 Pub/Sub 中，消息是即发即忘的，并且永远不会存储，而在使用阻止列表时，当客户端收到消息时，它会从列表中弹出（有效删除），而流以根本不同的方式工作。所有消息都无限期地追加到流中（除非用户明确要求删除条目）：不同的消费者将通过记住最后收到的消息的 ID 从其角度知道什么是新消息
-- 流消费者组提供了 Pub/Sub 或阻塞列表无法实现的控制级别，同一流具有不同的组、已处理项目的显式确认、检查待处理项目的能力、未处理消息的声明以及每个项目的一致历史记录可见性单个客户端，只能看到其私有的过去消息历史记录

-- 获取全部数据
xread streams temperatures:us-ny:10007 0
xread streams temperatures:us-ny:10007 1694633231719

-- 阻塞式读取最新的数据，如果没有最新的将会一直等待，因为指定的阻塞时间为0，0就是一直阻塞，这个就有点类似于tail -f命令的效果
XREAD BLOCK 0 STREAMS temperatures:us-ny:10007 $

-- 逐步衍生到消费者组功能

-- 然而，使用消息需要使用特定命令进行显式确认。 Redis 将确认解释为：此消息已正确处理，因此可以将其从消费者组中逐出
+----------------------------------------+
| consumer_group_name: mygroup           |
| consumer_group_stream: somekey         |
| last_delivered_id: 1292309234234-92    |
|                                        |
| consumers:                             |
|    "consumer-1" with pending messages  |
|       1292309234234-4                  |
|       1292309234232-8                  |
|    "consumer-42" with pending messages |
|       ... (and so forth)               |
+----------------------------------------+

-- 创建消费者组
xgroup create temperatures:us-ny:10007 temp-group $
-- XGROUP CREATE 还支持自动创建流（如果不存在），使用可选的 MKSTREAM 子命令作为最后一个参数
xgroup create mystream mygroup $ MKSTREAM
-- 初始化测试数据
xadd mystream * message apple
xadd mystream * message orange
xadd mystream * message banana
xadd mystream * message apricot

-- 读取信息
xreadgroup group mygroup Alice streams mystream >
-- 上面的命令行中有另一个非常重要的细节，在强制 STREAMS 选项之后，为键 mystream 请求的 ID 是特殊 ID > 。这个特殊的ID仅在消费者组的上下文中有效，它意味着：到目前为止，消息从未传递给其他消费者

-- 如果 ID 是任何其他有效的数字 ID，那么该命令将允许我们访问待处理消息的历史记录。也就是说，传递给此指定使用者（由提供的名称标识）且迄今为止从未通过 XACK 确认的消息集
-- 获取未确认的信息
xpending mystream mygroup
-- https://redis.io/commands/xack/
-- 消息确认
xack mystream mygroup 1694637242949-0 1694637242971-0
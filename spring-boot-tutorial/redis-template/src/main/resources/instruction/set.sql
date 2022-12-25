-- set是无序集合
-- 新增元素
sadd myset 1 2 3
-- 遍历集合
smembers myset
-- Redis 有测试成员资格的命令。例如，检查一个元素是否存在
sismember myset 30
-- 集合有利于表达对象之间的关系。例如，我们可以轻松地使用集合来实现标签
-- 一个例子是标记新闻文章。如果文章 ID 1000 被标记为标签 1、2、5 和 77，则集合可以将这些标签 ID 与新闻项相关联：
sadd news:1000:tags 1 2 5 77
sadd news:1001:tags 1 2 5
sadd news:1002:tags 1 5
-- 获取给定对象的所有标签很简单
smembers news:1000:tags
-- 还有其他重要的操作仍然可以使用正确的 Redis 命令轻松实现。
-- 例如，我们可能想要一个包含标签 1、2、10 和 27 的所有对象的列表。我们可以使用 SINTER 命令执行此操作，该命令执行不同集合之间的交集
sinter news:1001:tags news:1002:tags
sinter news:1000:tags news:1001:tags
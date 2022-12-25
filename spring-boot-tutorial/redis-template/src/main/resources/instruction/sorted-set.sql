-- 有序集合是一种数据类型，类似于 Set 和 Hash 的混合。
-- 与集合一样，有序集合由唯一的、不重复的字符串元素组成，因此在某种意义上，有序集合也是一个集合。
-- 然而，虽然集合中的元素没有排序，但有序集合中的每个元素都与一个浮点值相关联，称为分数（这就是为什么该类型也类似于散列的原因，因为每个元素都映射到一个值）
-- 此外，有序集合中的元素是按顺序获取的（因此它们不是按要求排序的，顺序是用于表示有序集合的数据结构的特性）。它们按照以下规则排序
-- 如果 B 和 A 是两个具有不同分数的元素，则 A > B 如果 A.score > B.score
-- 如果 B 和 A 的分数完全相同，则如果 A 字符串在字典序上大于 B 字符串，则 A > B。 B 和 A 字符串不能相等，因为排序集只有唯一元素
zadd hackers 1940 "Alan Kay"
zadd hackers 1957 "Sophie Wilson"
zadd hackers 1953 "Richard Stallman"
zadd hackers 1949 "Anita Borg"
zadd hackers 1965 "Yukihiro Matsumoto"
zadd hackers 1914 "Hedy Lamarr"
zadd hackers 1916 "Claude Shannon"
zadd hackers 1969 "Linus Torvalds"
zadd hackers 1912 "Alan Turing"

-- 遍历所有集合 升序排列
zrange hackers 0 -1
-- 遍历所有集合 降序排列
zrevrange hackers 0 -1
-- 带分数返回
zrange hackers 0 -1 withscores

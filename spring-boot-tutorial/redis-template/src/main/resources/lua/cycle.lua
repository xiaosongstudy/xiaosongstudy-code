---
---循环结构
--- Generated by Luanalysis
--- Created by songshiping.
--- DateTime: 2023/9/6 22:15
---
local a = 12
while (a > 0)
do
    print('---', a)
    a = a - 1
end

-- var 从 exp1 变化到 exp2，每次变化以 exp3 为步长递增 var，并执行一次 "执行体"。exp3 是可选的，如果不指定，默认为1。
print('-------------------------------------------')
for var = 1, 10, 2
do
    print('-----', var)
end

-- 泛型for循环
print('---------------泛型for循环---------------------')
weekdays = { 'one', 'two', 'three', 'four', 'five', 'six' }
for next, value in ipairs(weekdays)
do
    print('ntx=', next, 'value=', value)
end
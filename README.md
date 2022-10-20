# Application Collection
## Spring Boot Tutorial
> 本工程为Spring Boot系列案例代码，持续更新中...
### base-application
本项目是一个基础服务功能，综合了日常项目断言、全局异常处理、缓存Redis、同一请求响应处理、系统基础工具类（包含线程任务调度、时间、md5、异常等工具类。）
### canal-cache-sync
本项目基于canal实现mysql数据库增量数据同步。
### rpc-tutorial
本项目是有关rpc相关的案例代码，目前完成的部分为rpc基础简单示例`simple-rpc`。
### security-dynamic-role
本项目为动态权限配置案例代码，基于Spring Boot + Spring Security + MySQL实现动态权限配置，无需手动在接口上静态配置权限信息，
所有权限信息均配置于数据库中，这样可以更加灵活的控制系统各项权限，基础功能已实现。
### source-track
本项目为spring 系列源码追踪项目，以Spring Boot作为载体。
1. BeanFactory与ApplicationContext
> BeanFactory是ApplicationContext实现的一个顶级父类接口。BeanFactory定义的功能不是特别多，但是他的子实现类做了许多的事情。
```java
ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SourceTrackApplication.class, args);
```

SpringApplication.run()方法是有返回值的，其返回值==ConfigurableApplicationContext==是ApplicationContext的子例，这里我们主要关注右侧的四个接口	

![image-20221019232140450](F:\xiaosongstudy-code\images\image-20221019232140450.png)

### websocket-template
之前也零零散散的编写过其他的案例代码，但是这一次想要更加系统的编写以及管理案例代码，第一个案例是与WebSocket的相关的。
### xxj-job-template
xxj-job开源项目示例代码（待完善，初步接触过，但是具体代码原理尚未仔细研究）
## request-invocation
本demo提供了多种方式的请求响应示例代码，包含jdk原生、spring boot、netty等方式。
参考链接：
https://www.jianshu.com/p/29e38bcc8a1d
```txt
2022/10/11
今天初步完成了基于jdk原生api发送请求，目前支持get，post常用请求方式、支持单文件上传(待优化)，关于文件上传，此部分将联合
hopeurl-file-center项目一起进行联调，本项目作为客户端，`hopeurl-file-center`作为服务端。
```
## hopeurl-file-center
这个demo项目虽然也是基于spring boot编写的，但是为了更方便之后参考，所以单独拎出来作为了一个新的模块。
见名知意：本demo是文件中心案例。
### TODO
- 单文件上传
- 批量文件上传
- 断点续传
- 秒传
- 大文件分片上传
> 参考学习地址：
> 1. https://blog.csdn.net/dimudan2015/article/details/81910690
> 2. https://cloud.tencent.com/developer/article/1541199
> 3. https://cloud.tencent.com/developer/article/2123591?from=article.detail.1541199
> 4. https://gitee.com/KT1205529635/simple-uploader
> 5. https://blog.csdn.net/top_code/article/details/8896047?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-8896047-blog-81910690.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-8896047-blog-81910690.pc_relevant_default&utm_relevant_index=1

# coding-spring-test

## 功能描述  
> **SpringBoot持续化集成开发项目**  
> **GitHub: [https://github.com/csulwk/coding-spring-test.git](https://github.com/csulwk/coding-spring-test.git)**  

实现目标：  
- [x] 集成mybatis和mysql  
- [x] 集成swagger  
- [x] 集成Git配置中心  
- [x] 使用本地缓存配置
- [x] 对接日志云  
- [x] 对接健康检查  
- [ ] 对接注册中心  
- [x] 对接调用链和性能监控zipkin  
- [x] 对接metrics和jvm监控, 集成prometheus和grafana组件  
- [x] 使用jasypt增强安全性  
- [x] 使用jenkins持续化集成


## 开发环境
* `SpringBoot 2.2.1`  
* `SpringCloud Hoxton.SR1`  
* `Jdk 1.8.66`  
* `Maven 3.6.0`  
* `MySQL 5.6.46`  
* `Docker 19.03.1`  
* `Jenkins 2.204.1`  
* `Zipkin 2.12.2`  
* `Elasticsearch 6.4.1`  
* `Kibana 6.4.1`  
* `Logstash 6.4.1`  
* `Filebeat 6.8.2`  
* `Prometheus 2.16.0`  
* `Grafana 6.7.0`  

## 项目结构
```
coding-spring-test
├─ bin
│  ├─ coding-spring-test.sh     -- 应用启动脚本
│  └─ jenkins_publisher.sh      -- Jenkins自动化发布脚本
├─ cfg
│  ├─ elk                       -- ELK组件配置
│  ├─ npg                       -- Prometheus配置
│  └─ jenkins                   -- Jenkins配置
├─ init
│  └─ db_test.sql               -- 初始化建表语句
├─ src
│  ├─ assembly
│  │  └─ assembly.xml           -- 编译打包规范
│  ├─ main
│  │  ├─ java
│  │  │  ├─ com.lwk.coding
│  │  │  │  ├─ annotation       -- 自定义注解
│  │  │  │  ├─ aop              -- 切面类：异常处理，请求日志打印
│  │  │  │  ├─ cloud            -- 配置中心缓存本地配置项
│  │  │  │  ├─ config           -- 自定义配置类
│  │  │  │  ├─ constant         -- 自定义常量
│  │  │  │  ├─ controller       -- 请求处理类
│  │  │  │  ├─ dao              -- 数据处理类
│  │  │  │  ├─ entity           -- 实体类
│  │  │  │  │  ├─ exception     -- 自定义异常类
│  │  │  │  │  ├─ req           -- 请求类
│  │  │  │  │  └─ resp          -- 响应类
│  │  │  │  ├─ mapper           -- 映射文件配置类
│  │  │  │  ├─ metrics          -- 应用监控处理类
│  │  │  │  ├─ service          -- 业务处理类
│  │  │  │  ├─ util             -- 工具类
│  │  │  │  └─ validator        -- 自定义校验规则
│  │  │  └─ Application.java    -- 应用启动类
│  │  └─ resources
│  │     ├─ config              -- 多环境日志配置
│  │     ├─ mapper              -- 映射文件配置
│  │     ├─ META-INF            -- 启用自动配置，搭配cloud包中的类使用
│  │     └─ bootstrap.yml       -- 应用配置文件
│  └─ test                      -- 测试类
├─ .gitattributes
├─ .gitignore
├─ pom.xml
└─ README.md
```

## 版本变更
TBD  

# SciManagement科研管理系统

## 1简介

SciManagement科研管理系统是一个基于Spring Boot+Mybatis Plus+Redis后端框架的项目管理系统，主要数据库采用mysql+redis缓存实现

## 2功能介绍

1.独立的管理员用户登录、注册，后端不实现权限分级

2.科研项目与研究室模块：

- [x] 2.1研究室功能

- [x] 2.2科研项目功能
- [x] 2.3科研子项目功能
- [x] 2.4科研成果功能

3.人力资源模块：

- [x] 3.1科研人员功能
- [x] 3.2主任功能
- [x] 3.3秘书功能

4.用地资源和相关单位模块：

- [x] 5.1委托方功能
- [x] 5.2合作方功能
- [x] 5.3监测方功能
- [x] 5.4办公用地功能

6.联系电话专栏：

- [x] 6.*联系电话通查

所有的子功能主要都是实现简单的增删查改

- 注意尽管不同功能间标注了模块，但这并不是一个分布式架构

## 3环境配置

```mysql
-- java jdk: v1.8.0
// 更高的版本暂时无法适配

-- redis: v5.0.14.1

-- mysql: v8.1.0
// mysql版本大于v5.7即可
```

## 4代码结构

代码采用spring标准MVC架构，对于所有的数据对象我们都创建了相应的实体类，并且有相应的mapper类与之绑定，对于较为复杂或者承当数据传输的实体，依照开发标准定义为DTO实体类，service中为所有的服务进行注册，受控制器调用

```mysql
-- src --main.jva.com.example.scimanagement
 -- config --在spring中注册的配置中心，主要是mybatis和CORS跨域配置
 -- contorller --控制器，提供了所有方法的接口
 -- dto --数据传输实体类，主要作用是用户信息传输和跨表联查的临时信息载体
 -- entity --数据实体类，包括子实体（用于联查）
 -- mapper --实现实体类与数据库的数据映射
 -- service --服务注册中心，控制器可以调用内部的方法
 -- utils --工具类，包括格式审阅，跨表联查组件和日期格式化组件
       --main.resources
 -- db.sci.sql --mysql建表语句，包括对应所有实体的表和中间表
 -- application.yml --配置中心，包括redis和mysql的连接池配置
```

## 5打包

```mysql
使用maven辅助生命周期进行打包即可
```


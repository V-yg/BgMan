# BgMan后台权限管理系统

​	后台权限管理系统(Background Management)，觉得BgMan好听，就叫BgMan了。BgMan是一款简单高效的后台加权限管理系统，使用Spring Boot 2.x，Shiro和Layui构建 ,权限控制的方式为 RBAC，代码通熟易，JWT（无状态token）过期自动刷新，数据全程 ajax 获取，封装 ajax 工具类、菜单无线层级展示，加入树形组件、图标选择器。数据交互都是以 JSON 格式交互。后台接口RESTful 风格无论作为企业级后台管理系统还是私活框架亦或者拿来当做后台系统练手，都是一个绝佳的项目，并且会不断进行迭代更新，欢迎一起来维护此项目，邮箱 yiie315@163.com

#### 当前版本：![https://img.shields.io/badge/V1.1.0-green](https://img.shields.io/badge/V1.1.0-green)

#### 系统架构：

Spring Boot 2.x+Layui+Mybatis+MySQL+Shiro+Redis+JWT+Swagger2+thymeleaf+druid

#### 具体架构：

核心框架		 ![https://img.shields.io/badge/SpringBoot-2.1.6-yellow](https://img.shields.io/badge/SpringBoot-2.1.6-yellow)

持久层框架	 ![https://img.shields.io/badge/Mybatis-2.1.1-blue](https://img.shields.io/badge/Mybatis-2.1.1-blue)

数据库连接池	 ![https://img.shields.io/badge/Druid-1.1.10-ff69b4 ](https://img.shields.io/badge/Druid-1.1.10-ff69b4)

安全框架		 ![https://img.shields.io/badge/Apache%20Shiro-1.1.10-33d879 ](https://img.shields.io/badge/Apache%20Shiro-1.1.10-33d879)

无状态		 ![https://img.shields.io/badge/JWT-0.9.1-85ce2e ](https://img.shields.io/badge/JWT-0.9.1-85ce2e)

缓存数据库	 ![https://img.shields.io/badge/Redis-3.2.6-9d21c0 ](https://img.shields.io/badge/Redis-3.2.6-9d21c0 )

日志框架		 ![https://img.shields.io/badge/Logback-1.3.0-e9e855 ](https://img.shields.io/badge/Logback-1.3.0-e9e855)

接口框架		 ![https://img.shields.io/badge/Swagger2-2.9.2-d03773 ](https://img.shields.io/badge/Swagger2-2.9.2-d03773)

前端模板		 ![https://img.shields.io/badge/Layui-2.1.0-5ba5e1 ](https://img.shields.io/badge/Layui-2.1.0-5ba5e1)

#### 体验地址： http://www.yiiang.com:8088/pgman

**体验账号**

| 账号  | 密码  | 权限                                     |
| ----- | ----- | ---------------------------------------- |
| guest | guest | 有所有的查看权限，无删除、授权、编辑权限 |

#### 系统模块：

```
├─组织管理
│  ├─用户管理
│  ├─部门管理
│  ├─角色管理
│  └─菜单权限管理
├─系统管理
│  ├─接口管理
│  ├─操作日志
│  ├─登录日志
│  ├─SQL监控
│  ├─服务器性能监控
├─VPN管理
│  ├─VPN用户管理
│  ├─VPN数据
│  ├─流量管理
│  ├─节点管理
└─其他模块
   │  ├─表单组件
   │  ├─表单组合
   │  ├─树形组件
   │  ├─图标选择器
   │  └─导出excel/PDF
```

#### 系统截图：
![https://github.com/V-yg/BgMan/blob/master/images/1.png ](https://github.com/V-yg/BgMan/blob/master/images/1.png)
![https://github.com/V-yg/BgMan/blob/master/images/2.png ](https://github.com/V-yg/BgMan/blob/master/images/2.png)
![https://github.com/V-yg/BgMan/blob/master/images/3.png ](https://github.com/V-yg/BgMan/blob/master/images/3.png)
![https://github.com/V-yg/BgMan/blob/master/images/4.png ](https://github.com/V-yg/BgMan/blob/master/images/4.png)
![https://github.com/V-yg/BgMan/blob/master/images/5.png ](https://github.com/V-yg/BgMan/blob/master/images/5.png)
![https://github.com/V-yg/BgMan/blob/master/images/6.png ](https://github.com/V-yg/BgMan/blob/master/images/6.png)
![https://github.com/V-yg/BgMan/blob/master/images/7.png ](https://github.com/V-yg/BgMan/blob/master/images/7.png)
![https://github.com/V-yg/BgMan/blob/master/images/8.png ](https://github.com/V-yg/BgMan/blob/master/images/8.png)
![https://github.com/V-yg/BgMan/blob/master/images/9.png ](https://github.com/V-yg/BgMan/blob/master/images/9.png)
![https://github.com/V-yg/BgMan/blob/master/images/10.png ](https://github.com/V-yg/BgMan/blob/master/images/10.png)
![https://github.com/V-yg/BgMan/blob/master/images/11.png ](https://github.com/V-yg/BgMan/blob/master/images/11.png)
![https://github.com/V-yg/BgMan/blob/master/images/12.png ](https://github.com/V-yg/BgMan/blob/master/images/12.png) ![https://github.com/V-yg/BgMan/blob/master/images/13.png ](https://github.com/V-yg/BgMan/blob/master/images/13.png)



### 浏览器兼容：

|[<img src="https://raw.github.com/alrra/browser-logos/master/src/archive/internet-explorer_9-11/internet-explorer_9-11_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>IE| [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/edge/edge_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Edge | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/firefox/firefox_48x48.png" alt="Firefox" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Firefox | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/chrome/chrome_48x48.png" alt="Chrome" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Chrome | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/safari/safari_48x48.png" alt="Safari" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Safari |[<img src="https://raw.github.com/alrra/browser-logos/master/src/opera/opera_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Opera
| --------- | --------- | --------- | --------- | --------- |--------- |
|IE 10+| Edge| last 15 versions| last 15 versions| last 10 versions| last 15 versions






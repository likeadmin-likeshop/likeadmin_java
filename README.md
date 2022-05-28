 <h1 align="center">likeadmin通用管理后台（JAVA版本）</h1>
<h4 align="center">📈快速开发前后端解决方案</h4> 
<p align="center">
<a href="https://www.java.com/zh-CN/download/"><img src="https://img.shields.io/badge/JAVA-8.0-d74f11"> </a><a href="#"> <img src="https://img.shields.io/badge/Spring Boot-2.5-5e
a931"> </a><a href="https://www.tslang.cn/"><img src="https://img.shields.io/badge/TypeScript-3.1-294e80"> </a><a href="#"><img src="https://img.shields.io/badge/Vue.js-3.2-4eb883"> </a><a href="#"><img src="https://img.shields.io/badge/vite-2.0.0-ffc018"> </a><a href="#"><img src="https://img.shields.io/badge/Element Plus-1.2.0-409eff"> </a>
<div align="center">
  <img  width="80%"  src="https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=f4dc6a16c63dac26da3017979e818802" />
</div>
 <br>

# 👨‍💻‍ 简介
<a href="https://gitee.com/likeshop_gitee">likeshop开源团队</a>基于以往开源产品的经验，希望能够为开源社区做出更多的贡献，推出永久免费开源的likeadmin通用前后端分离管理后台系统。遵循Apache2开源许可协议，您可以免费使用，甚至允许把你基于likeadmin开发的软件应用开源、发布、销售。
<br>
### 🏀 联系我们
likeadmin官网：https://www.likeadmin.cn/
likeadmin交流群QQ：629475409
<br>
#  🧐 进一步了解
### 🧰 场景介绍
1.likeadmin已经搭建好前后端分离的底层，包含程序安装、登录、登出、工作台、菜单权限控制、角色、管理员、网站设置、图库管理等基础功能，无需重复造轮子。<br>
2.可视化系统程序安装界面，可自定义安装数据，开发者可快速扩展发行自己的软件产品。<br>
3.likeadmin定位为通用的软件系统管理后台，方便开发者快速开发软件系统，文档清晰、代码易懂、简单易用。<br>
4.未来将持续集成通用的微信/支付宝支付，阿里云/腾讯云短信，阿里云/腾讯云OSS等通用模块。
### 🐙 后端架构方面
1.服务端使用JAVA8开发，性能有突破性的提升。<br>
2.使用Spring Boot2.5框架，目前国内流行的JAVA框架，高性能、简单易用、文档齐全、支持Mave高级项目管理工具、支持Redis等。
### 🐹 前端架构方面
1.使用最流性的前后端分离方案typescript、vue3、vite开发，保持了代码的简洁、一致和规范。<br>
2.后台界面使用element-plus UI框架，简单精美的后台界面，丰富的组件库，方便快速开发，满足各种后台交互。
<br>


# ⚙️项目启动（开发模式）
## 环境要求
[JDK1.8](https://www.java.com/zh-CN/download/ "JDK1.8")、Mysql5.7、Redis6.x、node14.x<br>
## Mysql数据库和Redis缓存配置
1.复制“/server/like-admin/src/main/resources/application-example-pro.yml”文件为“/server/like-admin/src/main/resources/application-pro.yml”<br>
2.启动并登录Mysql，创建编码为utf8mb4的数据库，导入项目中/sql/install.sql文件，数据库创建完毕。打开“application-pro.yml”，找到datasource，修改url项的地址为数据库ip地址，地址“/”后面为数据库名称，username项为数据库账号，password项为数据密码，然后保存。
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=640156ad1b5b55defef7b0418e700a99)<br>
3.启动Redis，同样打开“application-pro.yml”，找到redis，修改host项为Redis的地址，port项为redis的端口，有密码填写密码，无密码则留空，然后保存。
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=463ab8cb922dfdd59a7624d816c8750a)<br>
## JAVA服务端部署
**服务端部署有两种方式，选择一种即可。**
### JAVA服务端部署方式①
下载并安装[Maven 3.8.x](https://maven.apache.org/download.cgi "Maven 3.8.x")，需要配置好JAVA_HOME变量，可在终端运行`java -version`和`mvn -v`验证。
![](https://md.likeshop.cn/Public/Uploads/2022-05-27/62909b7c55dff.png)<br>

#### 构建项目
打开终端，使用cd命令进入“\server”目录，运行 `mvn install`，构建项目。
![](https://md.likeshop.cn/Public/Uploads/2022-05-27/62909c6a1330e.png)
#### 运行项目
打开终端，使用cd命令进入“\server\like-admin”目录，运行`mvn spring-boot:run`。
![](https://md.likeshop.cn/Public/Uploads/2022-05-27/62909c6a1330e.png)<br>
打开浏览器，访问http://127.0.0.1:8082 ，即可看到接口返回信息。
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=88b7fac34ec63b01ca3f3fd88276e241)<br>
### JAVA服务端部署方式②（IDEA）
使用IDEA打开server目录，打开设置，将项目的SDK设置为1.8，等待依赖加载（右下角有提示），加载完以后。
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=314475d7aa4b529ff079fdfa5400c755)<br>
点击右上角运行图标启动项目。
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=49e647f1ec18b7f9a52aa8246eae9588)<br>
打开浏览器，访问http://127.0.0.1:8082 ，即可看到接口返回信息。
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=88b7fac34ec63b01ca3f3fd88276e241)<br>
## 前端部署
打开“/.env.development”文件，将文件内容“VITE_APP_BASE_URL”项的值修改为服务端的地址，有端口号需要加上端口号,保存。
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=6eb2a594e5c81d1e076db170e315e914)<br>
打开终端，使用cd命令进入“/admin”目录，运行`npm install`安装依赖。
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=8378d21ee57ac759ca649fd97a6dc955)<br>
打开终端，使用cd命令进入“/admin”目录，运行`npm run dev`,在开发者模式中运行项目,然后打开浏览器访问终端显示本地地址。
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=cf549fa67917f051bb66e8a43cfc97c0)<br>

访问后，进入登录页面。如果报系统错误，请检查前端JAVA服务端部署步骤，一般为Mysql和Redis参数配置错误。
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=6578d11fdf414b24520c0bc3769bed01)<br>
默认的账号为：admin，密码为123456，输入账号密码登入后台。
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=c28c218c67223b810759209a8dc72f05)<br>
## 📁 目录结构
```shell
│
├─📂admin  //管理后台前端源码
│
├─📂frontend  // //前端已编译代码目录（入口目录、上线运行）
│
├─📂server //java项目目录（编辑器、IDE打开此目录）
│ │ ├─📂like-admin //管理后台模块
│ │ ├─📂like-common //公共模块
│ │ ├─📂like-front //前台模块
│
├─📂sql //项目数据库结构
│
```



### 🪐接口文档
[点击这里进入更多更详细文档。](https://www.likeadmin.cn "点击这里进入更多更详细文档。")

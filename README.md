 <a href="https://gitee.likeshop.cn/giteemddai">![gitee头图 java.png](https://resource.likeshop.cn/gitee/mddai.png)</a><br>
 <h1 align="center">likeadmin通用管理后台（Java）</h1>
<h4 align="center">⚡️快速开发、🛠️ 一键生成代码、✅后台多端自适应、📱手机端、🖥️PC（电脑）端前台</h4> 
<p align="center">
<a href="https://www.java.com/zh-CN/download/"><img src="https://img.shields.io/badge/JAVA-8-d74f11"> </a><a href="#"> <img src="https://img.shields.io/badge/Spring Boot-2-5ea931"> </a><a href="https://www.tslang.cn/"><img src="https://img.shields.io/badge/TypeScript-3-294e80"></a> <a href="#"><img src="https://img.shields.io/badge/Vue.js-3-4eb883"> </a><a href="#"><img src="https://img.shields.io/badge/vite-2-ffc018"> </a><a href="#"><img src="https://img.shields.io/badge/Element Plus-2-409eff"> </a><a target="_blank" href="https://www.docker.com/"><img src="https://img.shields.io/badge/Docker--139cff"></a>
</p>
<p align="center">
<a href="https://mp.weixin.qq.com/"><img src="https://img.shields.io/badge/微信-公众号-05ce66"></a>
<a href="https://mp.weixin.qq.com/"><img src="https://img.shields.io/badge/微信-小程序-05ce66"></a>
<a href="https://open.weixin.qq.com/"><img src="https://img.shields.io/badge/微信-开放平台-05ce66"></a>
<a href="https://cloud.tencent.com/"><img src="https://img.shields.io/badge/腾讯云-COS-00a3ff"></a>
<a href="https://cloud.tencent.com/"><img src="https://img.shields.io/badge/腾讯云-短信-00a3ff"></a>
<a href="https://www.aliyun.com/"><img src="https://img.shields.io/badge/阿里云-OSS-ff6a00"></a>
<a href="https://www.aliyun.com/"><img src="https://img.shields.io/badge/阿里云-短信-ff6a00"></a>
<a href="https://www.qiniu.com/"><img src="https://img.shields.io/badge/七牛云-OSS-07beff"></a>
</p>
<div align="center">
  <img  width="80%"  src="https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=9cf02b831e49d6a411bafbc4d79f51d4" /><br>
</div>
 <br>

## 🚀🚀🚀docker本地一句命令快速部署体验
### 快速部署
1.安装启动 [docker](https://www.docker.com/) 之后，在终端运行以下命令，等待程序启动完毕即可体验。<br>
  ```shell
  docker run -it --name likeadmin_java -p 20222:20222 -p 20223:20223 likeshop/likeadmin_java:1.5.0
  ```
<br>

### 本地访问
命令运行完毕以后，请等待程序启动完毕再访问。由于谷歌浏览器对本地部署不友好原因，访问的链接下面尾部有/就带上/。PC端管理后台帐号为admin，密码为123456 。<br>
PC端管理后台：http://127.0.0.1:20222/
<br>PC端前台：http://127.0.0.1:20223/pc/
<br>手机端前台：http://127.0.0.1:20223/mobile/
### ⚠️注意
docker快速部署只适合本地部署体验，不熟悉docker请勿用于生产环境，可能造成数据丢失等问题。
<br>


## 👀体验
### 管理后台
地址：https://java-admin.likeadmin.cn <br>
账号：admin 密码：123456
### 手机端uniapp前台
<img  width="40%"  src="https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=260c0869d9ba7e692b2db1e216078241" /><br>
### 手机端网页前台
https://java-front.likeadmin.cn/mobile
### PC（电脑）端网页前台
https://java-front.likeadmin.cn/pc/
### 开发文档 
地址：[https://www.likeadmin.cn](https://www.likeadmin.cn "https://www.likeadmin.cn")


## 👨‍💻‍简介
我们希望能够为开源社区做出更多的贡献，推出永久免费开源的likeadmin通用前后端分离管理后台系统。遵循MIT开源许可协议，您可以免费使用，甚至允许把你基于likeadmin开发的软件应用开源、发布、销售。
<br>
##  🧐进一步了解
### 🧰场景介绍
1.likeadmin已经搭建好前后端分离的底层，包含程序安装、登录、登出、工作台、菜单权限控制、角色、管理员、部门管理、岗位管理、素材管理、网站设置、图库管理等基础功能，无需重复造轮子。更有开发者工具功能，一键生成代码，大大节省开发时间。<br>
2.可视化系统程序安装界面，可自定义安装数据，开发者可快速扩展发行自己的软件产品。<br>
3.likeadmin定位为通用的软件系统管理后台，方便开发者快速开发软件系统，文档清晰、代码易懂、简单易用。<br>
4.手机端uniapp前台，含导导航配置、微信登录、个人登录等等基础功能，方便根据业务开发含手机前台的项目。


### 🐙 后端架构方面
1.服务端使用Java8开发，性能有突破性的提升。<br>
2.使用Spring Boot2.7.5框架，目前国内流行的Java框架，高性能、简单易用、文档齐全、支持Mave高级项目管理工具、支持Redis等。
### 🐹 前端架构方面
#### 后台
1.使用最流性的前后端分离方案typescript、vue3、vite开发，保持了代码的简洁、一致和规范。<br>
2.后台界面使用element-plus UI框架，简单精美的后台界面，丰富的组件库，方便快速开发，满足各种后台交互。
<br>
### 前台
手机端uniapp前台，可以编译成手机H5网页、微信小程序、安卓App，苹果App等客户端。<br>
### 🛠️ 代码生成器
一键生成前后端业务代码，大大提示开发效率。<br>
## 界面预览
### 🖥️后台页面
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=9cf02b831e49d6a411bafbc4d79f51d4) <br>
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=eb83547d55b4f41f0d92fd6a3e01d87e) <br>
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=818d843fb9cba396226e32dad1a58f3c) <br>
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=194ab31919cd4dd619e6c453d7a44304) <br>
![](https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=f6770e2a8069d7b6ea3d83b91204b9d6) <br>
<br>
### 📱手机端前台界面
<div class="half">
<img  width="30%"  src="https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=2dbac190afadfb6650a04c8af44980e1" />  <img  width="30%"  src="https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=73adbdb91ff5c43ca3e694a99effae7a" />  <img  width="30%"  src="https://md.likeshop.cn/server/index.php?s=/api/attachment/visitFile&sign=55b51eaebd7d696f96ccbf60d4694368" />
</div><br>

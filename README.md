# 新能源汽车销售平台

基于 Spring Boot + Vue 的新能源汽车销售管理系统，包含用户前台、管理后台和后端接口服务。项目使用 MySQL 存储业务数据，Redis 集中管理 Session，前端页面已打包到后端资源目录中，启动后端后可直接通过浏览器访问前台和后台页面。

这是一个典型的前后端分离项目：Vue 前端负责页面交互，Spring Boot 后端提供 REST 接口，MyBatis-Plus 负责数据库访问，Apache Shiro 负责登录认证与权限控制。前台面向普通用户，后台面向管理员，业务数据统一存储在 MySQL 中。

## 项目功能

### 用户前台

- 用户注册、登录、个人中心
- 新能源汽车信息浏览与详情查看（按品牌类别筛选）
- 预约看车
- 汽车订单管理（下单、支付、查看）
- 配送订单管理
- 保养订单管理
- 维修订单管理
- 保险订单管理
- 新闻资讯浏览
- 收藏、评论、文件上传等通用功能
- 系统简介、关于我们

### 管理后台

- 用户管理（用户账号维护）
- 品牌类别管理
- 新能源汽车信息管理
- 预约看车管理
- 汽车订单管理（审核、发货）
- 配送信息管理
- 配送订单管理
- 保养订单管理
- 维修信息管理
- 维修订单管理
- 保险信息管理
- 新闻资讯管理
- 系统简介管理
- 关于我们管理
- 轮播图/配置管理
- 留言反馈管理

## 技术栈

### 后端

| 技术 | 版本/说明 |
|------|-----------|
| Java | 8 (JDK 1.8) |
| Spring Boot | 2.3.12.RELEASE |
| Spring Web | 内嵌 Tomcat |
| MyBatis-Plus | 数据访问层 |
| MySQL | mysql-connector-java 8.x |
| Redis | Spring Session 集中存储（Lettuce 连接池） |
| Apache Shiro | 认证与权限控制 |
| JSR303 | 参数校验（spring-boot-starter-validation） |
| Lombok | 简化实体类 |
| Fastjson | JSON 处理 |
| Hutool | 工具类库 |
| Apache POI | Excel 导出 |
| Maven | 项目构建（war 打包） |

### 前端

| 技术 | 版本/说明 |
|------|-----------|
| Vue | 2.6.x |
| Element UI | 2.15.x |
| Vue Router | 3.5.x |
| Vuex | 3.1.x |
| vue-resource | HTTP 请求 |
| vue-baidu-map | 百度地图组件 |
| vue-quill-editor | 富文本编辑器 |
| vue-aplayer | 音乐播放器 |
| vue2-countdown | 倒计时组件 |
| Sass | CSS 预处理器 |
| Vue CLI | 4.5.x 脚手架 |

## 项目结构

``text
hdspringbootproject02
├── pom.xml                          # Maven 配置
├── HELP.md
├── README.md
├── db_backup_20260910.sql          # 数据库备份
├── src
│   ├── springbootsnu6t.sql          # 数据库初始化脚本（表结构 + 示例数据）
│   ├── main
│   │   ├── java/com/qf
│   │   │   ├── Hdspringbootproject02Application.java  # 启动类
│   │   │   ├── config/               # Shiro、跨域等配置
│   │   │   ├── controller/           # 21 个 REST 控制器
│   │   │   ├── entity/               # 21 个数据库实体
│   │   │   ├── interceptor/          # 认证拦截器
│   │   │   ├── mapper/               # MyBatis Mapper 接口
│   │   │   ├── service/              # 业务逻辑层
│   │   │   └── utils/                # 工具类
│   │   └── resources
│   │       ├── application.yaml      # 应用配置（数据源、Redis、MyBatis-Plus）
│   │       ├── mapper/               # MyBatis XML 映射文件
│   │       ├── admin/                # 管理后台源码及已构建 dist
│   │       ├── front/                # 用户前台源码及已构建 dist
│   │       └── static/               # 静态资源（上传文件等）
│   └── test
└── upload/                            # 运行时文件上传目录
``

后端代码采用经典分层架构：

``text
Controller（HTTP 接口） → Service（业务逻辑） → Mapper（数据访问） → MySQL
``

## 环境要求

- **JDK** 1.8
- **Maven** 3.x
- **MySQL** 5.7 或 8.x
- **Redis** 5.x 或 7.x（用于 Session 共享，可选但推荐）
- **IntelliJ IDEA**（推荐，用于启动后端）
- **Node.js / npm**（仅在需要重新构建前端时使用）

## 数据库初始化

本项目可使用本地 MySQL，也可使用 Docker 容器运行 MySQL，以下两种方式任选其一。

### 方式一：Docker 容器（推荐，端口 3307，与本地 3306 服务隔离）

1. 启动 MySQL 容器：

``powershell
docker run -d --name hdspringboot-mysql -p 3307:3306 -e MYSQL_ROOT_PASSWORD=henu -e TZ=Asia/Shanghai -v hdspringboot-mysql-data:/var/lib/mysql mysql:5.7
``

2. 创建数据库：

``sql
CREATE DATABASE springbootsnu6t DEFAULT CHARACTER SET utf8mb4;
``

3. 导入 SQL 文件：

``powershell
mysql --user=root --password=henu --host=127.0.0.1 --port=3307 springbootsnu6t < src/springbootsnu6t.sql
``

4. （可选）启动 Redis 容器（Session 共享）：

``powershell
docker run -d --name hdspringboot-redis -p 6380:6379 -v hdspringboot-redis-data:/data redis:7-alpine redis-server --requirepass hdredis2026 --appendonly yes
``

> Redis 用于 spring-session-data-redis 的 Session 集中存储（多实例共享登录态），--appendonly yes 开启 AOF 持久化，数据挂载 /data，容器重建数据不丢失。pplication.yaml 中 Redis 连接为 127.0.0.1:6380、密码 hdredis2026。

### 方式二：本地 MySQL（默认 3306）

1. 启动本地 MySQL 服务。
2. 创建数据库：

``sql
CREATE DATABASE springbootsnu6t DEFAULT CHARACTER SET utf8mb4;
``

3. 导入 SQL 文件 src/springbootsnu6t.sql，可使用 Navicat、DataGrip、MySQL Workbench 或命令行导入。

> SQL 文件默认假定目标数据库名为 springbootsnu6t，导入前请先创建并选中该数据库。脚本包含表结构和示例数据，导入完成后即可使用默认账号登录。

## 修改配置

配置文件位置：src/main/resources/application.yaml

### 数据源配置

当前默认配置（Docker MySQL 3307）：

``yaml
spring:
  datasource:
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3307/springbootsnu6t?useUnicode=true&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8
    username: 
    password: 
``

- 如果使用本地 MySQL（3306），将 url 中的端口改为 3306。
- 用户名和密码支持环境变量 MYSQL_USERNAME、MYSQL_PASSWORD 覆盖，未设置时使用默认值 oot / henu。

### Redis 配置

``yaml
spring:
  redis:
    host: 127.0.0.1
    port: 6380
    password: 
  session:
    store-type: redis
    timeout: 1h
``

- 如果不使用 Redis，需将 spring.session.store-type 改为 
one，并确保 Shiro 的 Session 管理不依赖 Redis。
- 密码支持环境变量 REDIS_PASSWORD 覆盖。

### 前端 API 地址

用户前台配置文件：src/main/resources/front/front/src/config/config.js

``js
export default {
    baseUrl: 'http://localhost:8080/springbootsnu6t/',
    // ...
}
``

如果后端端口或上下文路径变更，需同步修改此配置并重新构建前端。

## 启动后端

### 方式一：IntelliJ IDEA（推荐）

1. 用 IDEA 打开项目根目录。
2. 等待 Maven 依赖下载完成。
3. 找到主类：src/main/java/com/qf/Hdspringbootproject02Application.java
4. 右键运行 Hdspringbootproject02Application。
5. 控制台无数据库连接错误，并显示 Tomcat 启动成功即可。

启动成功的标志是控制台出现 Tomcat started on port(s): 8080 和 Started Hdspringbootproject02Application。

### 方式二：命令行

``powershell
mvn spring-boot:run "-Dspring-boot.run.skip=false"
``

> 由于 pom.xml 中 spring-boot-maven-plugin 配置了 <skip>true</skip>，命令行启动时需要显式覆盖该配置；使用 IDEA 直接运行主类不受此影响。

## 访问地址

后端默认端口：8080
后端上下文路径：/springbootsnu6t

| 入口 | 地址 |
|------|------|
| 用户前台 | http://localhost:8080/springbootsnu6t/front/dist/index.html |
| 管理后台 | http://localhost:8080/springbootsnu6t/admin/dist/index.html |
| 后端接口根路径 | http://localhost:8080/springbootsnu6t/ |

## 默认账号

| 角色 | 账号 | 密码 |
|------|------|------|
| 管理员 | admin | admin |
| 普通用户 | 以数据库中 yonghu 表为准 | 123456 |

具体账号数据以 src/springbootsnu6t.sql 导入后的数据库内容为准。

## 前端开发说明

项目中的前端构建结果已经放在 src/main/resources 下，正常运行项目不需要单独启动 npm。

| 端 | 源码目录 |
|----|----------|
| 用户前台 | src/main/resources/front/front/ |
| 管理后台 | src/main/resources/admin/admin/ |

仅当修改 Vue 页面时，才需要进入对应目录安装依赖并重新打包：

``bash
cd src/main/resources/front/front
npm install
npm run build
``

重新构建后端时执行：

``powershell
mvn clean package
``

项目当前是 war 打包方式，日常开发直接运行 Hdspringbootproject02Application 即可。打包产物会生成在 	arget 目录。

## 认证机制说明

- 后端使用 **Apache Shiro** 进行认证与权限管理，配合 AuthorizationInterceptor 拦截器做登录态校验。
- Token 由后端写入 **HttpOnly Cookie**，前端不再手动携带 header、不再从 localStorage 读取。
- 前端 ue-resource 配置了 credentials: true，自动携带 Cookie。
- 响应拦截器检测到 code == 401 或 403 时自动跳转登录页。
- Session 通过 **Spring Session + Redis** 集中存储，支持多实例部署共享登录态。

## 数据库表一览

| 表名 | 说明 |
|------|------|
| yonghu | 用户账号 |
| users | 管理员账号 |
| xinnengyuanqiche | 新能源汽车信息 |
| pinpaileibie | 品牌类别 |
| yuyuekanche | 预约看车 |
| qichedingdan | 汽车订单 |
| peisongxinxi | 配送信息 |
| peisongdingdan | 配送订单 |
| baoyangdingdan | 保养订单 |
| weixiuxinxi | 维修信息 |
| weixiudingdan | 维修订单 |
| baoxianxinxi | 保险信息 |
| news | 新闻资讯 |
| systemintro | 系统简介 |
| aboutus | 关于我们 |
| config | 轮播图/配置 |
| messages | 留言反馈 |
| storeup | 收藏 |
| discussxinnengyuanqiche | 汽车评论 |
| token | 登录令牌 |
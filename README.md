# 新能源汽车销售平台

基于 Spring Boot 的新能源汽车销售平台，包含用户前台、管理后台和后端接口服务。项目使用 MySQL 存储业务数据，前端页面已打包到后端资源目录中，启动后端后可直接通过浏览器访问前台和后台页面。

这是一个典型的前后端分离项目：Vue 前端负责页面交互，Spring Boot 后端提供 REST 接口，MyBatis / MyBatis-Plus 负责数据库访问，Apache Shiro 负责登录认证与权限控制。前台面向普通用户，后台面向管理员，业务数据统一存储在 MySQL 中。

## 项目功能

- 用户注册、登录、个人中心
- 新能源汽车信息浏览与详情查看
- 预约看车、汽车订单管理
- 配送信息、配送订单管理
- 维修订单、保养订单管理
- 品牌类别、新闻资讯、系统简介、关于我们管理
- 收藏、评论、文件上传等通用功能
- 管理员后台数据维护
- 集成百度 AI / 千帆相关工具类

## 技术栈

后端：

- Java 8
- Spring Boot 2.3.12.RELEASE
- MyBatis / MyBatis-Plus
- MySQL
- Apache Shiro
- Maven

前端：

- Vue
- Element UI
- npm

其他：

- 百度 AI Java SDK
- 百度千帆 SDK
- Fastjson
- Hutool
- Apache POI

## 项目结构

```text
hdspringbootproject02
├── pom.xml
├── HELP.md
├── src
│   ├── springbootsnu6t.sql
│   ├── main
│   │   ├── java/com/qf
│   │   │   ├── controller
│   │   │   ├── entity
│   │   │   ├── mapper
│   │   │   ├── service
│   │   │   ├── utils
│   │   │   └── Hdspringbootproject02Application.java
│   │   └── resources
│   │       ├── application.yaml
│   │       ├── admin
│   │       ├── front
│   │       └── static
│   └── test
└── upload
```

后端代码采用分层结构：

```text
Controller -> Service -> Mapper -> MySQL
```

- `controller`：HTTP 接口控制器
- `service`：业务逻辑
- `mapper`：数据库访问接口及 MyBatis XML
- `entity`：数据库实体对象
- `config` / `interceptor`：系统配置和权限拦截
- `src/main/resources/front`：用户前台源码及已构建的 `dist`
- `src/main/resources/admin`：管理后台源码及已构建的 `dist`

## 环境要求

- JDK 1.8
- Maven 3.x
- MySQL 5.7 或 8.x
- IntelliJ IDEA，推荐
- Node.js / npm，仅在需要重新构建前端时使用

## 数据库初始化

1. 启动 MySQL。

2. 创建数据库：

```sql
CREATE DATABASE springbootsnu6t DEFAULT CHARACTER SET utf8mb4;
```

3. 导入 SQL 文件：

```text
src/springbootsnu6t.sql
```

可以使用 Navicat、DataGrip、MySQL Workbench 或命令行导入。

注意：SQL 文件默认假定目标数据库名为 `springbootsnu6t`，导入前请先创建并选中该数据库。脚本包含表结构和示例数据，导入完成后即可使用默认账号登录。

## 修改数据库配置

配置文件位置：

```text
src/main/resources/application.yaml
```

默认配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/springbootsnu6t?useUnicode=true&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8
    username: root
    password: henu
```

如果本机 MySQL 用户名或密码不同，需要改成自己的配置。

## 启动后端

推荐使用 IntelliJ IDEA 启动：

1. 用 IDEA 打开项目根目录。
2. 等待 Maven 依赖下载完成。
3. 找到主类：

```text
src/main/java/com/qf/Hdspringbootproject02Application.java
```

4. 右键运行 `Hdspringbootproject02Application`。
5. 控制台无数据库连接错误，并显示 Tomcat 启动成功即可。

启动成功的标志是控制台出现 `Tomcat started on port(s): 8080` 和 `Started Hdspringbootproject02Application`。

也可以在项目根目录使用命令行启动：

```powershell
mvn spring-boot:run "-Dspring-boot.run.skip=false"
```

由于 `pom.xml` 中 `spring-boot-maven-plugin` 配置了 `<skip>true</skip>`，命令行启动时需要显式覆盖该配置；使用 IDEA 直接运行主类不受此影响。

## 访问地址

后端默认端口：

```text
8080
```

后端上下文路径：

```text
/springbootsnu6t
```

用户前台：

```text
http://localhost:8080/springbootsnu6t/front/dist/index.html
```

管理后台：

```text
http://localhost:8080/springbootsnu6t/admin/dist/index.html
```

## 默认账号

管理端：

```text
账号：admin
密码：admin
```

用户端：

```text
账号：账号1
密码：123456
```

具体账号数据以 `src/springbootsnu6t.sql` 导入后的数据库内容为准。

## 前端说明

项目中的前端构建结果已经放在 `src/main/resources` 下，正常运行项目不需要单独启动 npm。

前台源码目录：

```text
src/main/resources/front/front
```

后台源码目录：

```text
src/main/resources/admin/admin
```

仅当修改 Vue 页面时，才需要进入对应目录安装依赖并重新打包：

```bash
npm install
npm run build
```

重新构建后端时可以执行：

```powershell
mvn clean package
```

项目当前是 `war` 打包方式，日常开发直接运行 `Hdspringbootproject02Application` 即可。打包产物会生成在 `target` 目录。

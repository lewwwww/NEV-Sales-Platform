运行文件

第 1 步：建库并导入数据
启动 MySQL。
执行（Navicat / 命令行均可）：
CREATE DATABASE springbootsnu6t DEFAULT CHARACTER SET utf8mb4;
USE springbootsnu6t;
导入文件：
d:\learned\idea\xiangmu\hdspringbootproject02\src\springbootsnu6t.sql
（Navicat：右键库 → 运行 SQL 文件）

第 2 步：改数据库密码（如本机不是 henu）
编辑：
d:\learned\idea\xiangmu\hdspringbootproject02\src\main\resources\application.yaml

把 spring.datasource.password 改成你本机 MySQL 的 root 密码。

第 3 步：用 IDEA 启动后端（最稳妥）
用 IDEA 打开项目根目录 hdspringbootproject02。
等待 Maven 依赖下载完成。
找到主类 Hdspringbootproject02Application → 右键 Run。
控制台出现 Tomcat 在 8080 启动、无数据库报错即成功。
不要用 mvn spring-boot:run 作为首选：pom.xml 里 spring-boot-maven-plugin 设置了 <skip>true</skip>，该插件基本被跳过，mvn package 也不会打出可执行的 Spring Boot 胖包。
命令行若必须跑，可用 IDE 同款方式：先 mvn compile，再用 IDEA 的 Run；或临时把 skip 改为 false 后再 mvn spring-boot:run（需改 pom，当前仓库未改）。

第 4 步：浏览器访问（无需再开 npm）
前后端已打进 src/main/resources，由 InterceptorConfig 映射 classpath:/front/、classpath:/admin/。
端	地址
用户前台
http://localhost:8080/springbootsnu6t/front/dist/index.html
管理后台
http://localhost:8080/springbootsnu6t/admin/dist/index.html

第 5 步：登录账号（SQL 自带）
端	账号	密码
管理端 users
admin
admin
用户端 yonghu
如 账号1
123456（库中为 MD5 e10adc...）

三、前端目录与 dist 情况
端	源码目录	已有 dist
用户前台
src\main\resources\front\front\
有：front\front\dist\
管理后台
src\main\resources\admin\admin\
有：admin\admin\dist\
（重复目录）
admin\admin\admin\
也有 dist，与上面内容类似；日常用 admin\admin\dist 即可
package.json 脚本（两端相同结构）：

npm run serve — 开发热更新
npm run build — 打包到 dist
npm run lint — 代码检查
仅在你改 Vue 页面时才需要：

cd src\main\resources\front\front
npm install
npm run build
cd src\main\resources\admin\admin
npm install
npm run build
管理端开发服务器在 vue.config.js 里为 8081，并代理 /springbootsnu6t 到后端；用户端 vue.config.js 未配 devServer，npm run serve 默认可能占 8080，与后端冲突，小白建议直接用第 4 步的 dist 地址。


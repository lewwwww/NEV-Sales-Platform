# -*- coding: utf-8 -*-
import sys

def load(path):
    raw = open(path, 'rb').read()
    nl = b'\r\n' if raw.count(b'\r\n') > raw.count(b'\n') / 2 else b'\n'
    return raw.decode('utf-8'), nl.decode('utf-8')

def save(path, text, nl):
    open(path, 'wb').write(text.encode('utf-8'))

def rep(text, old, new, tag):
    n = text.count(old)
    if n != 1:
        print(f'[FAIL] {tag}: matched {n}, abort'); sys.exit(1)
    print(f'[OK] {tag}')
    return text.replace(old, new)

p = r'D:\yy的成长记录\30-工作准备\岗位基础补强\专业知识\1-7-1实习项目-面试作战卡.md'
t, NL = load(p)

# ============ 1) 9 章末尾加 9.4 运维交付怎么讲 ============
sec94 = (
    '## 9.4 运维交付怎么讲（联调 · 自测 · 文档编撰）' + NL + NL +
    '简历里"运维交付"那句下半段"参与联调、自测与文档编撰"别讲空，落到项目真实动作：' + NL + NL +
    '**① 联调（前后端对接）**：项目是用户端 front + 管理端 admin 两个 Vue 调一个后端，联调 = 对齐接口路径 / 参数 / R 统一返回结构（code/msg/data）。真实踩过的坑（都是 1-7-2「八」场景题）：' + NL +
    '- 前端 8080 调后端跨域 → OPTIONS 预检被拦截器当业务请求拦了返回 401 → 拦截器放行 OPTIONS（见 11.3）' + NL +
    '- 页面能开但 JS/CSS 404 → WebMvcConfigurationSupport 覆盖了静态资源映射 → InterceptorConfig 显式注册（见 11.2）' + NL + NL +
    '**② 自测（接口自测）**：Postman / curl 对着接口打，覆盖正常 + 异常边界：' + NL +
    '- 正常：登录、分页、条件筛选、增删改查、统计' + NL +
    '- 异常：参数缺失（JSR303 校验）、Token 过期（401）、越权（A 查 B 订单被拦）、库存不足、重复下单' + NL +
    '- 1-7-2「八」场景题基本就是自测 / 排查中真实发现的问题' + NL + NL +
    '**③ 文档编撰**：' + NL +
    '- 部署文档：README——环境要求（JDK1.8 / Maven / MySQL）、Docker MySQL 容器（3307）、前端 dist 打进后端、war 部署外置 Tomcat（见 13.4）' + NL +
    '- 接口说明：RESTful 接口的路径 / 方法 / 参数 / 返回（参与整理）' + NL +
    '- 诚实边界：README 部署文档是实际产出，接口文档是"参与整理"，别把没做的说成全是你写的' + NL + NL +
    '**面试口述版（40 秒）：**' + NL + NL +
    '> 运维交付这块，我负责三类统计接口的开发（数量 / 时间 / 分组，SQL 数据库端聚合）给 ECharts 用。联调上，项目是用户端和管理端两个 Vue 调一个后端，我参与了对齐接口路径、参数和 R 统一返回结构，期间排查过跨域 OPTIONS 被拦截、静态资源 404 这类问题。自测上，我用 Postman 对着接口覆盖了正常流程和异常边界——Token 过期返回 401、用户越权查别人订单被拦截、库存不足、重复下单这些场景。文档上，我整理了 README 的部署说明（环境、Docker MySQL、war 部署步骤）和接口说明。'
)
old = '- 时间维度分组用 `DATE_FORMAT` 按天/月/年。' + NL + NL + '---' + NL + NL + '# 10 核心链路追踪（一次请求全流程怎么讲）'
new = '- 时间维度分组用 `DATE_FORMAT` 按天/月/年。' + NL + NL + sec94 + NL + NL + '---' + NL + NL + '# 10 核心链路追踪（一次请求全流程怎么讲）'
t = rep(t, old, new, '9.4 运维交付怎么讲')

# ============ 2) 13.4 加交叉提示 ============
old = '## 13.4 dist 是什么 / 前后端怎么部署成一体的' + NL + NL + '**dist 是什么：**'
new = ('## 13.4 dist 是什么 / 前后端怎么部署成一体的' + NL + NL +
       '> 📍 运维交付怎么讲（联调 · 自测 · 文档编撰）见「9.4」；本节约部署 / dist。' + NL + NL +
       '**dist 是什么：**')
t = rep(t, old, new, '13.4 提示')

save(p, t, NL)
print('[DONE] 9.4 主内容 + 13.4 提示已写入')

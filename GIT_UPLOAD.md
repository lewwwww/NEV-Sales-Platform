# 后续提交代码到 GitHub 的操作说明

当前项目已经绑定远程仓库：

```bash
https://github.com/lewwwww/new-energy-vehicle-sales-platform.git
```

以后每次修改代码后，按下面步骤提交即可。

## 1. 查看当前修改状态

```bash
git status
```

如果看到红色文件，表示这些文件被修改了但还没有加入提交。

## 2. 添加修改到暂存区

提交全部修改：

```bash
git add .
```

只提交某个文件：

```bash
git add 文件路径
```

例如：

```bash
git add src/main/java/com/qf/Hdspringbootproject02Application.java
```

## 3. 生成本地提交

```bash
git commit -m "提交说明"
```

例如：

```bash
git commit -m "修改登录功能"
```

提交说明建议写清楚这次改了什么。

## 4. 推送到 GitHub

```bash
git push
```

第一次已经执行过 `git push -u origin main`，所以后续一般只需要 `git push`。

## 常用完整流程

```bash
git status
git add .
git commit -m "本次修改说明"
git push
```

## 如果提交时提示没有内容可提交

如果看到类似：

```text
nothing to commit, working tree clean
```

说明当前没有新的修改，不需要提交。

## 如果 push 被拒绝

如果出现：

```text
push declined due to repository rule violations
```

通常说明提交里包含了 GitHub 不允许上传的敏感信息，例如：

- 密码
- Token
- API Key
- Secret Key

需要先把敏感内容从代码中移除，改成环境变量或占位符后再提交。

## 查看提交记录

```bash
git log --oneline
```

可以看到最近的提交记录。

## 查看远程仓库地址

```bash
git remote -v
```

可以确认当前项目推送到哪个 GitHub 仓库。

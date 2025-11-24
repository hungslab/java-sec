## 项目简介

a rep for coding my study, may be from 0 to 0.1

写的一点 Java 安全相关的代码，主要是记录并整理一下，没有多少技术含量，权当个人技术笔记。

包含各种常见反序列化利用链（aspectj、c3p0、CC、Rome、Fastjson、Jackson、Hessian2、Snakeyaml，二次序列化等）以及Spring、Dubbo、Shiro、Tomcat、Nexus等框架、中间件漏洞的分析，Jndi bypass、rasp等。


## 模块概览

仓库包含多个模块，每个模块包含若干demo，用于学习、复现或研究 Java 常见利用链。

- **`deserialization`**: 反序列化与类加载相关基础。
- **`serialization`**: 常见利用链。
- **`jndiattack`**: JNDI 相关，jdk高版本bypass。
- **`rasp`**: 一个简单的 rasp agent。
- **`common`**: 通用漏洞模块。

## 注意事项
- 本仓库仅用于合法合规用途，严禁用于违法违规用途。
- 本工具中所涉及的漏洞均为网上已公开。
- 本仓库只包含代码，不包含相关分析文章。

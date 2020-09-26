## Meaning
随着知识不断的汲取，写了很多的代码实现例子，也看到很多有趣的功能实现例子。  
因此想要好好整理一番，方便回顾与学习，所以这是一个总结向的代码仓库。  
本人会把个人学习的知识、实际项目遇到的难题、有趣的代码例子存于此，包括但不限于以下几种：
- 原生 API
- 工具操作类
- 设计模式
- 数据结构
- Java 版本相关特性
- ...  

该仓库不涉及 Java 框架相关部分，需要的朋友可以移步这里哦 => [Java 框架及工具汇总](https://github.com/yudi-corgi/java_framework_summary):smile: 

## Directory Struct
```lua
├── jar -- 代码所需的依赖包
└── src/com -- 源码目录
    ├── datastruct      -- 数据结构，如数组、集合、链表
    ├── designpatterns  -- 设计模式
    ├── encryption      -- 加解密算法
    ├── file            -- 文件操作
    ├── io              -- I/O 流操作
    ├── jdk8learn       -- Java 8 相关特性示例
    ├── thread          -- 多线程示例
    └── utils           -- 常用操作工具
```

## Data Structure
### 数组
* 二维数组顺（逆）时针螺旋遍历
### 集合
* ArrayList、LinkedList 基础使用
* HashSet 基础使用
* HashMap、Hashtable 基础使用
### 链表
* 链表翻转：递归方式、迭代方式
* 跳表：增删、查询
### 树
* 哈夫曼树构建实例

## Design Patterns
### 代理模式（结构）
* 静态代理：继承方式、聚合方式
* 动态代理：jdk、cglib
### 工厂模式（创建）
* 简单工厂模式
* 工厂方法模式
* 抽象工厂模式
### 适配器模式（结构）
* 类适配器
* 对象适配器
* 接口适配器
### 策略模式（行为）
* 简单使用（数值加减操作）
* if..else 过多情况处理（以不同状态码不同处理方式为例）
### 建造者模式（创建）
* 以抽象/具体建造者、指导者、产品四个角色为基础实现简单例子（制造机器人）
* 去除指导者，将具体建造者作为静态内部类放在产品类中，也可灵活控制建造细节（造房子）
### 外观模式（结构）
* 模拟 KFC 套餐购买，系统包含多个食品购买，由外观类包装系统的多个接口来构成一个简洁的高层接口
### 模板模式（行为）
* 模拟游戏运行，构建相同的运行抽象模板（方法），由具体模板根据需要重写部分功能（方法）
### 观察者模式（行为）
* 根据被观察者的状态（整型数值）转变，触发观察者输出不同进制下的数值
### 责任链模式（行为）
* 根据日志信息级别调用对应的日志处理对象
### 装饰器模式（结构）
* 简单装饰图形接口，并增加涂色功能的例子（该模式可直接参考 Java IO 相关类）
### 访问者模式（行为）
* 根据不同访问者（Boss、CFO、CPA）调用不同的账单（Consume、Income）逻辑
- 通过抽象（AbstractVisitor）继承、接口（Visitor）实现模拟不同层次的访问者，体现该模式跨层次的访问或针对特定层次的逻辑进行定义的优点

## Encryption/Decryption Algorithm
* 实现方式基于 JDK、BouncyCastle(第三方扩展) 、CommosCodec(该扩展只是对 JDK 的封装)
* Base64 API 使用
* 消息摘要（MessageDigest）：MD5、SHA-1/SHA-2、Hmac(MAC)
* 对称加密（SymmetricEncryption）：DES/3DES、AES、PBE
* 非对称加密（AsymmetricEncryption）：待补充...
* **PS1**：示例中加解密都是在同一个方法，实际应用中应当是分开的，由发送方与接受方约定密钥生成方式、加解密算法，密钥也可通过网络传输(安全性低，不推荐)
* **PS2**：各类加解密算法的实现方式有多种，示例只使用其中之一，其它方式基本只需要替换算法名称，使用逻辑基本相同，如 Hmac 算法下的 HmacMD5、HmacSHA256 等

## I/O
* 常用字节流与字符流API的基础使用方式，同时并有简单的拷贝文件例子实现
* **BIO** 配合多线程的简单实现方式
* **NIO** 的实现，以多人网络通信聊天为例子实现

## Java8 Lambda / Stream API
* 常用函数式接口
* 方法引用
* Stream API：filter(筛选)、map(映射)、reduce(归约)
* 自定义收集器 Collector、分割迭代器 Spliterator

## Thread
* 线程创建：实现 Runnable 接口、实现 Callable<T> 接口
* 线程池简单使用，CountDownLatch 计数器简单使用

## Utils
* Timer 定时任务调度


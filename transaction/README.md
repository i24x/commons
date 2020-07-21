# 说明

使用jpa简化CRUD，每次重启就会清除数据，需要创建数据库：test
设置mysql储存引擎为~~MyISAM~~ InnoDB
https://www.jianshu.com/p/d4c3634447d0

普通事务失效原因：

1.如使用mysql且引擎是MyISAM，则事务会不起作用，原因是MyISAM不支持事务，可以改成InnoDB

2. 如果使用了spring+mvc，则context:component-scan重复扫描问题可能会引起事务失败。 （即父子容器）

3. @Transactional 注解开启配置，必须放到listener里加载，如果放到DispatcherServlet的配置里，事务也是不起作用的。 

4. @Transactional 注解只能应用到 public 可见度的方法上。 如果你在 protected、private 或者 package-visible 的方法上使用 @Transactional 注解，它也不会报错，事务也会失效。 

5. Spring团队建议在具体的类（或类的方法）上使用 @Transactional 注解，而不要使用在类所要实现的任何接口上。在接口上使用 @Transactional 注解，只能当你设置了基于接口的代理时它才生效。因为注解是 不能继承 的，这就意味着如果正在使用基于类的代理时，那么事务的设置将不能被基于类的代理所识别，而且对象也将不会被事务代理所包装。

spring的事务管理器入口org.springframework.transaction.interceptor.TransactionInterceptor#invoke打上断点，然后运行到该事务方法。

方法入口的断点处用evaluate expression执行AopUtils.isAopProxy这个方法来看一下注入的serviceImpl是否被代理了，结果是true。


# Spring-Transaction事务传播特性

Spring提供了7中事务传播行为，用来控制多个方法相互调用的事务边界

本文的调用发是methodA，被调用方是methodB

doBegin:397, JpaTransactionManager (org.springframework.orm.jpa)
getTransaction:378, AbstractPlatformTransactionManager (org.springframework.transaction.support)
createTransactionIfNecessary:475, TransactionAspectSupport (org.springframework.transaction.interceptor)
invokeWithinTransaction:289, TransactionAspectSupport (org.springframework.transaction.interceptor)
invoke:98, **_TransactionInterceptor_** (org.springframework.transaction.interceptor)
proceed:186, ReflectiveMethodInvocation (org.springframework.aop.framework)
intercept:688, CglibAopProxy$DynamicAdvisedInterceptor (org.springframework.aop.framework)
method3:-1, ClassA$$EnhancerBySpringCGLIB$$3b684d54 (com.transaction.demo.service.required)
method23:60, _01_RequiredController (com.transaction.demo.controller)
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:497, Method (java.lang.reflect)
doInvoke:190, InvocableHandlerMethod (org.springframework.web.method.support)
invokeForRequest:138, InvocableHandlerMethod (org.springframework.web.method.support)
invokeAndHandle:104, ServletInvocableHandlerMethod (org.springframework.web.servlet.mvc.method.annotation)
invokeHandlerMethod:892, RequestMappingHandlerAdapter (org.springframework.web.servlet.mvc.method.annotation)
handleInternal:797, RequestMappingHandlerAdapter (org.springframework.web.servlet.mvc.method.annotation)
handle:87, AbstractHandlerMethodAdapter (org.springframework.web.servlet.mvc.method)
doDispatch:1039, DispatcherServlet (org.springframework.web.servlet)
doService:942, DispatcherServlet (org.springframework.web.servlet)
processRequest:1005, FrameworkServlet (org.springframework.web.servlet)
doGet:897, FrameworkServlet (org.springframework.web.servlet)
service:634, HttpServlet (javax.servlet.http)
service:882, FrameworkServlet (org.springframework.web.servlet)
service:741, HttpServlet (javax.servlet.http)




### 1.REQUIRED 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
默认的传播行为


方法B的事务传播特性是REQUIRED，方法A有事务，方法A调用方法B，
方法B不开启事务

方法B抛异常，方法A、B全部回滚

方法B抛异常，方法A捕获，会抛异常，全部回滚

### 2.SUPPORTS 支持当前事务，如果当前没有事务，就以非事务方式执行。

方法B的事务传播特性是SUPPORTS

方法A没有事务，方法A、B运行异常都不会回滚

方法A有事务，方法A异常，方法A、B都不会回滚

方法A有事务，方法B异常，方法A、B全部回滚


### 3.MANDATORY 使用当前的事务，如果当前没有事务，就抛出异常。 具有事物依赖+REQUIRED

该方法必须运行在一个事务中，否则就报错

方法B为MANDATORY

方法A没有事务，调用方法B执行会报错 A不回滚 B回滚不提交

方法A有事务，执行异常，方法A、B都回滚

方法A有事务，方法B抛异常，都回滚

方法A有事务，方法B抛异常，方法A捕获异常，报错

### 4.REQUIRES_NEW 新建事务，如果当前存在事务，把当前事务挂起。

AB事物独立

B方法运行在一个新的事务中

方法A有事务，方法A异常，方法A回滚，方法B不回滚

方法A有事务，方法B抛异常，都会回滚

方法A有事务，方法B抛异常，方法A捕获，方法B回滚

### 5.NOT_SUPPORTED 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。与Never区别

方法B是NOT_SUPPORTED  B用于记录日志

方法A有事务，方法B抛异常，方法A回滚，其中B提交

方法A有事务，方法B抛异常，方法A捕获，都不回滚

### 6.NEVER 以非事务方式执行，如果当前存在事务，则抛出异常。 用得少

该方法不能运行在另一个事务环境中
方法B是NEVER
方法A有事务，调用方法B会报错

### 7.NESTED  如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。
解决内层方法事物失效（REQUIRED）
方法B是NESTED  Hibernate不支持，如果外层方法catch异常也可以执行

方法A没有事务，方法B抛异常，方法B回滚，方法A不回滚

方法A没有事务，方法A抛异常，都不回滚

_方法A有事务，方法A抛异常，都回滚_

_方法A有事务，方法B抛异常，都回滚_

_**方法A有事务，方法B抛异常，方法A捕获异常，方法B回滚，方法A不回滚**_

![image](https://raw.githubusercontent.com/liueleven/study/master/%E5%9B%BE%E5%BA%93/17-spring/03-spring-transaction%E4%BC%A0%E6%92%AD%E8%A1%8C%E4%B8%BA.png)
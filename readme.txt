demo的作用
1.ssh框架测试demo  可以用于测试框架的方便测试框架的各种语法
2.spring测试，
3.struts2异常处理



总结：
spring：
spring创建对象有两种方式：
第一种：一种注解的方式：
在配置文件加入<context:component-scan base-package="cn.itcast"/>
在对应的类中加入 @Controller @Service @Repository   就可以了
第二种方式：在配置文件配置：
<bean name="transactionManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager" >
	<property name="sessionFactory" ref="sessionFactory" ></property>
</bean>注意注入的属性一定在在改类中有，没有就会去查询子类

以下注解不需要扫描
1.@Resource  
2.@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
	<!-- 开启注解事务 -->
上面的注解只需要配置文件中开启就可以<tx:annotation-driven transaction-manager="transactionManager" />

@Autowired和@Resource 的区别：
1、@Autowired是放在属性中（不需要getset方法就可以装配 根据属性的类型来装配）
@Resourced是放在set方法上（更新参数的类型来装配）
2.在一种情况下只能用@Resource不能使用@Authcode
@Resource//根据类型注入spring工厂中的会话工厂对象sessionFactory
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	测试类中也经常用@Resource而不用@Authwried
3.@Autowired必须要扫描(属于spring的注解)  但是@Resource中不需要扫描（属于jdk的注解）
4.使用@Autowried的时候 一定是注入给接口不能是具体的实现类


struts2：
1.所有的action都需要继承actionsupport类
三种驱动方式：如果是属性驱动或是对象驱动都需要设置setget方法  如果是模型驱动需要实现某一接口



hibernate：
1.无论是hql 还是离线查询 或是原生的SQL  都需要先获得session  。通过session获得响应的执行的api
2.如何使把sessionFactory交给管理  获得要么使用注解的方式，要么如果在配置文件中依赖注入
  要么如果注解的方式：@Resource//根据类型注入spring工厂中的会话工厂对象sessionFactory
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	测试类
3.如果使用spring来管理dao  不仅帮你管理连接工厂 还帮你对应的api（这个api你可以不用）   


dao层框架的作用：
1.帮我们生成连接 
2.有对应的api帮我们操作SQL语句
3.帮我们封装好了对象的结果集



web层框架的作用：
1.帮我们接受参数，处理封装接受的参数
2.帮我们在做预处理（拦截器，过滤器）
3.帮我们封装响应

spring的作用：就是帮我们创建对象同时注入属性值（要么通过注解的方式，要么通过配置文件的方式）
1.ioc 对象反转  对象统统由spring帮我们创建
2.di依赖注入  
3.切面编程  事务 过滤器 拦截器






	
  
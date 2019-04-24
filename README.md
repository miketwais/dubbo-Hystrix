# dubbo-Hystrix
Based on springboot and dubbo implement Hystrix.<br/>
Hystrix超时配置的为1s,当实现类睡眠超过1s，服务调用者将执行服务降级函数.<br/>

访问方式：<br/>
1.修改dubbo-config.xml中的注册中心地址的配置<br/>
2.启动本地zookeeper<br/>
3.启动provider和consumer<br/>
4.访问：http://127.0.0.1:8002/sayHello?name=%22zhangsan%22 ，多次访问，随机产生时间并sleep,大于1秒时，会返回“service request fail”<br/>
小于1秒时，返回“HELLO "zhangsan" from Dubbo Spring Boot sleep 47ms”

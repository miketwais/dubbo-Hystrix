package org.asiainfo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;


@Component
@Service("defaultApiService")
public class DefaultServiceImpl implements DefaultApiService {

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") })
	public String defaultMethod(String str) {
		Date start = new Date();
		SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		String startTime = format0.format(start.getTime());//这个就是把时间戳经过处理得到期望格式的时间
		System.out.println("provider--defaultMethod开始处理--start--：" + startTime);
		/*
		 * Hystrix超时配置的为1s,当实现类睡眠超过1s，服务调用者将执行服务降级函数
		 */
		int nextInt = new Random().nextInt(2000);
		System.out.println("sleep " + nextInt + "ms");
		try {
			Thread.sleep(nextInt);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Date end =new Date();
		String endTime = format0.format(end.getTime());
		System.out.println("provider--defaultMethod结束处理--end--：" + endTime);
		return "HELLO  " + str + " from Dubbo Spring Boot "+"sleep " + nextInt + "ms";
	}

}
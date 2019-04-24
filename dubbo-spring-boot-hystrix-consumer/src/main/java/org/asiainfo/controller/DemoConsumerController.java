package org.asiainfo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.asiainfo.service.DefaultApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class DemoConsumerController {

	/**
	 * 引入服务提供者
	 */
	// com.alibaba.dubbo.config.annotation.Reference
	//@Reference
	@Autowired(required=true)
	private DefaultApiService demoService;

	@RequestMapping("/sayHello")
	@HystrixCommand(fallbackMethod = "failBackMethod")
	public String sayHello(@RequestParam String name) {
		Date start = new Date();
		SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		String startTime = format0.format(start.getTime());//这个就是把时间戳经过处理得到期望格式的时间
		System.out.println("sayHello控制器开始处理--start--：" + startTime);

		String ss =demoService.defaultMethod(name);
		System.out.println(ss);
		Date end =new Date();
		String endTime = format0.format(end.getTime());
		System.out.println("sayHello控制器结束处理--end--：" + endTime);
		return ss;
	}

	/**
	 * 服务降级
	 * @param name
	 * @return
	 */
	public String failBackMethod(String name) {
		Date start = new Date();
		SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		String startTime = format0.format(start.getTime());//这个就是把时间戳经过处理得到期望格式的时间
		System.out.println("sayHello控制器开始处理--end--failBackMethod：" + startTime);
		return "service request fail";
	}

}
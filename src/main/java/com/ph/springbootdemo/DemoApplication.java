package com.ph.springbootdemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@Controller
public class DemoApplication {
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);
		
	}
	
	@RequestMapping(value="/hello")
	@ResponseBody
	public String demo(){
		return "hello";
	}
	@RequestMapping(value="/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/upload")
	public String upload(){
		return "upload";
	}

	@PostMapping("/testWebhook")
	@ResponseBody
	public Object tstWebhook(HttpServletRequest request){
		System.out.println("webhook is work");
		Map m= new HashMap(16);
		m.put("code",200);
		m.put("msg","webhook is work");
		m.put("data",null);
//		return "webhook is work";
		return m;
	}
	
	
	@PostMapping("/callback/test")
	@ResponseBody
	public String callbacktest(HttpServletRequest request){
		
		Map<String, String> para = getParamMap(request);
		System.out.println(para);
		
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("code", "SUCCESS");
		msg.put("desc", "处理成功");
		msg.put("data", "1234567890");
		
		return JSONObject.toJSONString(msg);
	}
	
	
	public static Map<String, String> getParamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<String, String>();
		Map<String, String[]> requestMap = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = requestMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			if (entry.getValue().length == 1) {
				paramMap.put(entry.getKey(), entry.getValue()[0]);
			} else {
				String[] values = entry.getValue();
				String value = "";
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
				paramMap.put(entry.getKey(), value);
			}
		}
		return paramMap;
	}
	
	
}

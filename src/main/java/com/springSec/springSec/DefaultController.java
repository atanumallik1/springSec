package com.springSec.springSec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@ResponseBody
public class DefaultController {
    
    @GetMapping("")
    public String getDefault() {
	return "you are at landing page";
    }

    @GetMapping("hello")
    public String fetchUserExample() {
	return "hello";
    }
}
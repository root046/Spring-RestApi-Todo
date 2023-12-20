package com.bader88.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return "test..";
    }

    @RequestMapping("html-from-controller")
    @ResponseBody
    public String html(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html>");
            stringBuffer.append("<head>");
                stringBuffer.append("<title> TEST </title>");
            stringBuffer.append("</head>");

            stringBuffer.append("<body>");
                stringBuffer.append("<h1>HTML FROM JAVA CONTROLLER<h1>");
            stringBuffer.append("</body>");
        stringBuffer.append("</html>");
        return stringBuffer.toString();
    }

    @RequestMapping("html-from-jsp")
    public String sayHelloJsp(){
        return "sayHello";
    }
}

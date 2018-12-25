package com.imooc.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class AppErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    private ErrorAttributes errorAttributes;
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @Autowired
    public AppErrorController(ErrorAttributes errorAttributes){
        this.errorAttributes=errorAttributes;
    }

    /*web页面处理*/
    @RequestMapping(value = ERROR_PATH,produces = "text/html")
    public String errorPageHandler(HttpServletRequest httpServletRequest,
       HttpServletResponse httpServletResponse){
        int status = httpServletResponse.getStatus();
        switch (status){
            case 403:
                return "403";
            case 404:
                return "404";
            case 500:
                return "500";
        }
        return "index";
    }
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public ApiResponse errorApiHandler(HttpServletRequest request){
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String,Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes,false);
        int status = getStatus(request);
        return ApiResponse.ofMessage(status,String.valueOf(attr.getOrDefault("message","error")));
    }
    private int getStatus(HttpServletRequest request){
        Integer attribute =(Integer) request.getAttribute("javax.servlet.error.status_code");
        if (attribute !=null){
            return attribute;
        }
        return 500;
    }
}

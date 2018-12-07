package com.cloud.common.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice   //用来增强其他的controller中的被RequestMapping注解的方法
public class GlobalExceptionHandler {
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody   //返回json数据   如果其他controller声明为restcontroller则不用加这个注解就可以返回json格式数据
    public ResponseData jsonErrorHandler(HttpServletRequest req, GlobalException e) throws Exception{
        ResponseData r = new ResponseData();
        r.setCode(e.getCode());
        r.setMessage(e.getMessage());
        r.setData(null);   //此时返回的data为空   ，其他值定义为别的错误时的值
        r.setStatus(false);
        return r;
    }

    /*
    应用到所有被requestMapping注解了的方法上，在其执行前初始化数据绑定器
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){}

    /*
    把值绑定到Model中，使全局RequestMapping可以获取到该值
     */
    @ModelAttribute
    public void  addAttribute(Model model){
        model.addAttribute("author","lijun");
    }
    /*
    用法
    @RequestMapping("/home")
    public String home(ModelMap modelMap) {
        System.out.println(modelMap.get("author"));
    }
    或者
    @RequestMapping("/home")
    public String home(@ModelAttribute("author") String author) {
        System.out.println(author);
    }
     */

}

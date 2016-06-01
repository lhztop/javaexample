/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.test.web.controller.flashscope;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * flash scope是spring 3.1引入的新功能。
 * 其中RedirectAttributes用于注解（annotation），两个方法如下：
 * 1. addAttribute， 将附加到request的query parameter
 * 2. addFlashAttribute， 临时加入，不能通过query parameter使用， 可以RequestContextUtils.getInputFlashMap(request);取得map。
 * @author lihongzhong
 */
@Controller
public class FlashScopeController {
    
    @RequestMapping("/test/flash")
    public String flash(ModelMap mm, RedirectAttributes ra, HttpServletRequest request) {
        ra.addFlashAttribute("test1", "val1");
        ra.addAttribute("test2", "val2");
        mm.addAttribute("testm1", "valm1");
        
        return "redirect:/test/flashr";
    }
    @RequestMapping("/test/flashr")
    public String flashr(String test2, ModelMap mm, HttpServletRequest request) {
        Map<String,?> map = RequestContextUtils.getInputFlashMap(request);
        System.out.println(request.getAttribute("test1"));
        System.out.println(map);
        mm.addAttribute("test2", test2);
        return "/test/flashr";
    }
}

package main.java.controller;

import main.java.entity.Address;
import main.java.entity.Blog;
import main.java.exception.HelloException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * SessionAttributes，只能放在类上面
 * 1.除了可以通过属性名指定需要放在会话中的属性外（实际上使用的value值）
 * 2.还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中（实际上使用的是types）
 */
@SessionAttributes(value = {"names"})
@RequestMapping("/test")
@Controller
public class TestController {

    /**
     * 1.使用RequestMapping注解来映射请求的url，用来修饰类的话则在方法请求上先加上类上的路径
     * 2.返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver
     * 3.了解：可用params和headers来更加精确的映射请求，params和headers支持简单的表达式
     * 4.了解：支持ant风格的url，用?、*、**来匹配路径
     * 会通过prefix + returnVal + 后缀这样的方式得到实际的物理视图，然后会做转发操作
     * @return /WEB-INF/views/success.jsp
     */
    @RequestMapping("/helloWorld")
    public String success() {
        System.out.println("helloWorld");
        return "success";
    }

    /**
     * PathVariable:映射url绑定的占位符
     * @return /WEB-INF/views/success.jsp
     */
    @RequestMapping("/pathVariable/{id}")
    public String pathVariable(@PathVariable("id") Integer sid) {
        System.out.println("pathVariable:id=" + sid);
        return "success";
    }

    /**
     * RequestParam: 方法入参绑定;同理@RequestHeader、CookieValue也可以绑定
     * @return /WEB-INF/views/success.jsp
     */
    @RequestMapping("/requestParam")
    public String requestParam(@RequestParam("id") Integer sid) {
        System.out.println("requestParam:id=" + sid);
        return "success";
    }

    @RequestMapping("/pojo")
    public String pojo(Blog blog) {
        // name被InitBinder设置了不设置到参数中
        System.out.println("blog=" + blog);
        return "success";
    }

    /**
     * 可用原生api作为参数传入
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     * java.security.Principal
     * Locale
     * InputStream
     * OutputStream
     * Reader
     * Writer
     */
    @RequestMapping("/servletApi")
    public String servletApi(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("path" + request.getContextPath());
        System.out.println("type" + response.getContentType());
        return "success";
    }

    /**
     * 返回值时ModelAndView类型
     * Spring MVC 会把ModelAndView的model中的数据放入到request域对象中
     * @return 包含视图和模型信息
     */
    @RequestMapping("/returnModelAndView")
    public ModelAndView returnModelAndView() {
        ModelAndView modelAndView = new ModelAndView("success");
        // 添加模型数据到modelAndView中
        modelAndView.addObject("date", new Date());
        return modelAndView;
    }

    /**
     * 目标方法可以添加Map类型（也可以是Model和ModelMap）的参数
     * @return 包含视图和模型信息
     */
    @RequestMapping("/mapModel")
    public String mapModel(Map map) {
        // 添加模型数据到map中
        map.put("names", Arrays.asList("Tom", "Jerry", "Lily"));
        return "success";
    }

    /**
     * 自定义视图解析器 main.java.view.TestView 首字母小写
     * 通过该方式可以去整合excel解析等
     */
    @RequestMapping("/testView")
    public String testView() {
        return "testView";
    }

    /**
     * 一般情况下，视图都是采取转发的方式跳转视图
     * 如果返回的字符串中带forward:或redirect:前缀，Spring MVC会对他们进行特殊处理：
     * 将forward:和redirect:当成指示符，其后的字符串当成URL来处理
     * 本例会完成一个到index.jsp的重定向操作
     */
    @RequestMapping("/testRedirect")
    public String testRedirect() {
        System.out.println("testRedirect");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/testConvert")
    public String testConvert(@RequestParam("name") Address address) {
        System.out.println("testConvert address:" + address);
        return "redirect:/index.jsp";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("name");
    }

    /**
     * 需加入jackson以来
     * 处理JSON，底层使用的HttpMessageConverter,默认有6个实现类，加入jackson后有七个
     * RequestBody:入参为json格式
     * ResponseBody:响应为json格式
     */
    @ResponseBody
    @RequestMapping("/json")
    public List<Blog> json(@RequestBody Blog blog) {
        return Collections.singletonList(new Blog());
    }

    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file")MultipartFile file) {

        return "redirect:/index.jsp";
    }

    @RequestMapping("/testException")
    public String testException() {
        System.out.println(1 / 0);
        return "redirect:/index.jsp";
    }

    @RequestMapping("/testExceptionResponseStatus")
    public String testExceptionResponseStatus() {
        throw new HelloException();
    }

    @RequestMapping("/testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver() {
        throw new ArrayIndexOutOfBoundsException();
    }

}

package cn.meeler.test.controller.interrupt;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

public class MeelerInterrupt extends HandlerInterceptorAdapter {
    public MeelerInterrupt() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (true) {
//            return super.preHandle(request, response, handler);
//        }
        String name = request.getParameter("name");
        System.out.println(name);
        if (request.getRequestURI().endsWith(".html")||request.getRequestURI().endsWith(".js")){
            return super.preHandle(request, response, handler);
        }
        Date date = new Date();
        String remoteAddr = request.getRemoteAddr();
        String requestURI = request.getRequestURI();
        String remoteHost = request.getRemoteHost();
        System.out.println(requestURI);
        System.out.println(remoteAddr);
        System.out.println(remoteHost);
        Class<?> aClass = handler.getClass();
        System.out.println(aClass.getName());
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object bean = handlerMethod.getBean();
        Class<?> aClass1 = bean.getClass();
        System.out.println(aClass1);
        Method method = handlerMethod.getMethod();
        boolean present = method.isAnnotationPresent(ToLog.class);
        if (present){
            System.out.println("to print log");
        }else {
            System.out.println("hello !!!");
        }
//        method.isAnnotationPresent();
//        System.out.println(method);
//        System.out.println(method.getName());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String characterEncoding = response.getCharacterEncoding();
        System.out.println(characterEncoding);
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
}

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}

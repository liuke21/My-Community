package com.lk.InterCeptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lk.Mapper.userMapper;
import com.lk.model.User;

@Service
public class SessionInterCeptor implements HandlerInterceptor{
	@Autowired
	private userMapper usermapper;
	  /**
     * 预处理，controller方法执行前
     * return true 放行，执行下一个拦截器，如果没有，执行controller中的方法
     * return false不放行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        //System.out.println("MyInterceptor1执行了...前1111");
        // request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
    	//通过cookies判断是否登录，获取用户信息
		Cookie[] cooks = request.getCookies();
		//System.out.println(cooks);
		String token=null;
		if(cooks!=null&&cooks.length>0){
			for(Cookie cook:cooks){
				if(("token").equals(cook.getName())){
					token = cook.getValue();
					User user = usermapper.selectUser(token);
					if(user!=null){
						request.getSession().setAttribute("user", user);
					}
					break;
				}
			}
		}
        return true;
    }

    /**
     * 后处理方法，controller方法执行后，success.jsp执行之前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("MyInterceptor1执行了...后1111");
        // request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
    }

    /**
     * success.jsp页面执行后，该方法会执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //System.out.println("MyInterceptor1执行了...最后1111");
    }
}

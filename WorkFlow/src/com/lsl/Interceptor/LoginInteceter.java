package com.lsl.Interceptor;

import com.lsl.util.sessionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * 自定义拦截器
 * 实现拦截器的接口intercept
 * 在struts配置文件中配置拦截器或者拦截器栈
 * 引用完自定义的拦截器要引用默认的拦截器defaultStack
 * （否则参数不会继续往下传递）
 */
public class LoginInteceter implements Interceptor {

	private static final long serialVersionUID = -7723927676880985670L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("执行拦截器..");
		/**
		 * 在拦截的请求当中，登录这一请求不应该被拦截
		 */
		System.out.println("请求的action名为："+arg0.getInvocationContext().getName());
		if(checkIsLogin(arg0.getInvocationContext().getName())){
			return arg0.invoke();
		}
		//不是便回登录页面
		return "login";
	}
	/**
	 * 检查请求是否是登录请求  true：放行
	 *检测当前请求是否登录 true：放行 
	 */
	private boolean checkIsLogin(String name) {
			if(name.equals("e_login_main")){
				return true;
			}else {
			if(sessionContext.getEmp()!=null)return true;	
			}
			return false;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}

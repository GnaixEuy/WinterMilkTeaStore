//package cn.chengyi.the_back_end.interceptor;
//
//import org.springframework.boot.configurationprocessor.json.JSONObject;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
// *
// * @author GnaixEuy
// * @date 2021/12/23
// * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
// */
//@Component
//public class TokenInterceptor implements HandlerInterceptor {
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//		//跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
//		if(request.getMethod().equals("OPTIONS")){
//			response.setStatus(HttpServletResponse.SC_OK);
//			return true;
//		}
//		response.setCharacterEncoding("utf-8");
//		String token = request.getHeader("token");
//		if (token!=null){
//			boolean result= TokenUtils.verify(token);
//			if (result){
//				System.out.println("通过拦截器");
//				return true;
//			}
//		}
//		response.setContentType("application/json; charset=utf-8");
//		try {
//			JSONObject json=new JSONObject();
//			json.put("msg","token verify fail");
//			json.put("code","500");
//			response.getWriter().append(json.toString());
//			System.out.println("认证失败，未通过拦截器");
//		} catch (Exception e) {
//			return false;
//		}
//		/**
//		 * 还可以在此处检验用户存不存在等操作
//		 */
//		return false;
//	}
//}
//
//

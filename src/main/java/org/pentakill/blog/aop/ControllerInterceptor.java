package org.pentakill.blog.aop;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.pentakill.blog.dao.redis.RedisIncreDao;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerInterceptor {

	@Resource(name = "redisIncreDao")
	private RedisIncreDao redisIncreDao;

	/**
	 * 定义拦截规则：有@RequestMapping注解的方法。
	 */
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void controllerMethodPointcut() {
	}

	/**
	 * 拦截器具体实现
	 * 
	 * @param pjp
	 * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
	 */
	@Around("controllerMethodPointcut()")
	// 指定拦截器规则；也可以直接把“execution(* com.xjj.........)”写进这里
	public Object Interceptor(ProceedingJoinPoint pjp) {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod(); // 获取被拦截的方法
		String methodName = method.getName(); // 获取被拦截的方法名

		System.out.println("请求开始，方法：" + methodName + "调用时间" + new Date());
		Object result = null;
		try {
			// 一切正常的情况下，继续执行被拦截的方法
			result = pjp.proceed();
		} catch (Throwable e) {
			throw new RuntimeErrorException(null);
		}
		redisIncreDao.incre(methodName);
		return result;
	}

}
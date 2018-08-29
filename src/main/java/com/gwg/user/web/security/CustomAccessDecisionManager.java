package com.gwg.user.web.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义访问授权策略：没有明说需要权限的（即没有对应的权限的资源），可以访问，用户具有其中一个或多个以上的权限的可以访问。
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {
	
	private static Logger logger = LoggerFactory.getLogger(CustomAccessDecisionManager.class);

	// 检查用户是否够权限访问资源
	// 参数authentication是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
	// 参数object是url
	// 参数configAttributes所需的权限
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		logger.info("***************判断用户有没有权限访问：{}",object);
	    
		// 没有明说需要的权限的（即没有对应的权限的资源），可以访问
		if (configAttributes == null) {
			return;
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = null;
			if(ca instanceof SecurityConfig){
				needRole= ca.getAttribute();
			}
			
			logger.info("****************访问资源需要的权限：{}", needRole);
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				logger.info("**************该用户具有的角色：{}", ga.getAuthority());
				if (needRole.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		// 注意：执行这里，后台是会抛异常的，但是界面会跳转到所配的access-denied-page页面
		throw new AccessDeniedException("no right");
	}

	/**
	 * 指示该AccessDecisionManager是否能够处理通过ConfigAttribute传递的授权请求
	 */
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	/**
	 * Indicates whether the <code>AccessDecisionManager</code> implementation is able to
	 * provide access control decisions for the indicated secured object type.
	 * 指示该AccessDecisionManager的实现是否能够为指定的安全对象类型提供访问控制决策
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}

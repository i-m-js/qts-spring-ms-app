package com.example.qtsapp.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.qtsapp.commons.AppContext;
import com.example.qtsapp.commons.utils.AppConstants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class GenericInfoInterceptor implements HandlerInterceptor {
	private final Logger log = LoggerFactory.getLogger(GenericInfoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String userId = request.getHeader(AppConstants.X_USER_ID);
		log.info("Trying to load user " + userId);
		if (userId != null) {
			AppContext.putParam(AppConstants.X_USER_ID, Long.valueOf(userId));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		AppContext.clear();
	}

}

package com.example.qtsapp.commons;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.qtsapp.commons.utils.AppConstants;

public final class AppContext {
	private static final Logger log = LoggerFactory.getLogger(AppContext.class);
	private static final ThreadLocal<Map<String, Object>> PARAMS = new ThreadLocal<>();
	private static final ThreadLocal<Map<String, Object>> ATTR_MAP = new ThreadLocal<>();

	private static Map<String, Object> getParamsMap() {
		if (PARAMS.get() == null) {
			log.debug("Initializing PARAMS thread-local map");
			Map<String, Object> map = new HashMap<>();
			PARAMS.set(map);
			return map;
		} else {
			return PARAMS.get();
		}
	}

	public static void putParam(String key, Object value) {
		getParamsMap().put(key, value);
	}

	public static Object getParam(String key) {
		return getParamsMap().get(key);
	}

	public static <T> Optional<T> getParam(String key, Class<T> type) {
		Object val = getParamsMap().get(key);
		log.debug("Value of " + key + " is " + String.valueOf(val));
		if (!Objects.isNull(val) && type.isInstance(val)) {
			return Optional.<T>of(type.cast(val));
		} else {
			return Optional.empty();
		}
	}

	public static void clear() {
		log.debug("Clearning AppContext");
		PARAMS.set(null);
		ATTR_MAP.set(null);
	}

	public static Long getUserId() {
		return AppContext.getParam(AppConstants.X_USER_ID, Long.class).orElse(null);
	}
}

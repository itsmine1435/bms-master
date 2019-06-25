package com.bms.configuration;

public class SecurityConstants {

	public static final String SECRET = "SecretKeyToGenJWTs";

	public static final long EXPIRATION_TIME = 600000; // 10 min in milli secs

	public static final String TOKEN_PREFIX = "Bearer ";

	public static final String HEADER_STRING = "Authorization";

	public static final String SIGN_UP_URL = "/bms/login";
}

package com.qf.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Token Cookie 工具（生产环境化改造：Token 从 localStorage 迁移到 HttpOnly Cookie）
 *
 * 说明：
 * 1. 登录成功后后端 Set-Cookie（HttpOnly，JS 不可读，防 XSS 窃取）；
 * 2. 拦截器从 Cookie 读取 token 校验；
 * 3. 退出登录时清除 Cookie。
 * SameSite=Lax：同源部署（dist 由后端伺服）或同站跨端口开发均可正常携带；
 * 跨站请求不携带，降低 CSRF 面。
 */
public class CookieUtil {

    /** Token Cookie 名称 */
    public static final String TOKEN_COOKIE_NAME = "token";

    /** Token 有效期（秒），与 TokenServiceImpl 中 token 过期时间(1小时)保持一致 */
    public static final int TOKEN_COOKIE_MAX_AGE = 3600;

    private CookieUtil() {
    }

    /**
     * 从请求中读取指定名称的 Cookie 值
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 登录成功：将 token 写入 HttpOnly Cookie
     */
    public static void addTokenCookie(HttpServletResponse response, String token) {
        addTokenCookie(response, token, TOKEN_COOKIE_MAX_AGE);
    }

    /**
     * 将 token 写入 HttpOnly Cookie（可指定有效期）
     */
    public static void addTokenCookie(HttpServletResponse response, String token, int maxAgeSeconds) {
        ResponseCookie cookie = ResponseCookie.from(TOKEN_COOKIE_NAME, token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(maxAgeSeconds)
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    /**
     * 退出登录：清除 token Cookie
     */
    public static void clearTokenCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(TOKEN_COOKIE_NAME, "")
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}

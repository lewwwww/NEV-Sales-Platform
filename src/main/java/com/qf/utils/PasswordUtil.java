package com.qf.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加盐哈希工具（BCrypt，内置随机盐，无需额外盐字段）
 *
 * 生产环境化改造统一密码存储方案：
 * 1. 新注册/重置/修改的密码一律使用 BCrypt 加盐哈希存储；
 * 2. 兼容旧数据渐进迁移：老账号（MD5 / 明文）登录时先按新算法(BCrypt)校验，
 *    失败再按旧算法(MD5/明文)兜底；兜底通过后由登录接口将数据库立即重写为新算法存储，
 *    保证改造后旧账号仍能正常登录，且首次登录即自动升级。
 */
public class PasswordUtil {

    /** BCrypt 编码器（线程安全），默认强度 10，内部自动生成随机盐 */
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /** BCrypt 哈希前缀 */
    private static final String BCRYPT_PREFIX = "$2";

    private PasswordUtil() {
    }

    /**
     * 使用 BCrypt 加盐哈希加密明文密码
     *
     * @param rawPassword 明文密码
     * @return BCrypt 哈希串
     */
    public static String encode(String rawPassword) {
        if (rawPassword == null) {
            return null;
        }
        return ENCODER.encode(rawPassword);
    }

    /**
     * 校验密码：先按新算法(BCrypt)校验，失败再按旧算法(MD5/明文)兜底
     *
     * @param rawPassword 用户输入的明文密码
     * @param stored      数据库中存储的密码（BCrypt / MD5 / 明文 三种格式兼容）
     * @return 是否匹配
     */
    public static boolean matches(String rawPassword, String stored) {
        if (rawPassword == null || stored == null) {
            return false;
        }
        if (isBcrypt(stored)) {
            return ENCODER.matches(rawPassword, stored);
        }
        if (isMd5Hex(stored)) {
            // 旧格式 MD5 兜底：兼容单次 MD5 与历史双重 MD5（resetPass 曾写入 md5(md5("123456"))）
            return MD5Util.md5(rawPassword).equalsIgnoreCase(stored)
                    || MD5Util.md5(MD5Util.md5(rawPassword)).equalsIgnoreCase(stored);
        }
        // 历史明文兜底（管理端 users 表改造前为明文比对）
        return stored.equals(rawPassword);
    }

    /**
     * 是否已是 BCrypt 哈希（无需再加密）
     */
    public static boolean isBcrypt(String password) {
        return password != null && password.startsWith(BCRYPT_PREFIX);
    }

    /**
     * 是否已是哈希格式（BCrypt 或 32 位 MD5 hex）
     * 用于 update 等接口避免对已哈希值重复加密
     */
    public static boolean isAlreadyHashed(String password) {
        return isBcrypt(password) || isMd5Hex(password);
    }

    /**
     * 是否为旧格式存储（MD5 或明文），需要渐进升级为 BCrypt
     */
    public static boolean needsUpgrade(String stored) {
        return stored != null && !isBcrypt(stored);
    }

    /**
     * 判断是否为 32 位 MD5 hex 字符串
     */
    private static boolean isMd5Hex(String s) {
        if (s == null || s.length() != 32) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                return false;
            }
        }
        return true;
    }
}

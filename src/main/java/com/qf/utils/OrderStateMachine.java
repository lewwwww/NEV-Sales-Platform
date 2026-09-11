package com.qf.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 订单状态机（简化版）：校验支付状态(ispay) × 发货状态(dingdanzhuangtai) 的合法流转。
 * <p>
 * 组合状态（本项目实际只出现三种）：
 * <pre>
 *   待支付未发货  ->(支付)->  已支付未发货  ->(发货/接单配送)->  已支付已发货(终态)
 * </pre>
 * 校验规则：状态字段（ispay / dingdanzhuangtai）发生变化时，目标组合状态必须在
 * 当前组合状态的合法流转集合内；否则拒绝更新，防止"已支付回退未支付、已发货回退未发货、跳步发货"等非法变更。
 */
public class OrderStateMachine {

    public static final String UNPAID = "未支付";
    public static final String PAID = "已支付";
    public static final String UNSHIPPED = "未发货";
    public static final String SHIPPED = "已发货";

    /** 组合状态 key：ispay + "|" + dingdanzhuangtai（空串表示字段为空，兼容历史数据） */
    private static final Map<String, Set<String>> TRANSITIONS = new HashMap<>();

    static {
        // 待支付未发货：可支付（ispay 空 -> 已支付），也可保持原状（改其他字段）
        add("|未发货", "已支付|未发货", "|未发货");
        add("未支付|未发货", "已支付|未发货", "未支付|未发货");
        // 已支付未发货：可发货（管理端接单配送）
        add("已支付|未发货", "已支付|已发货", "已支付|未发货");
        // 已支付已发货：终态，只允许保持原状
        add("已支付|已发货", "已支付|已发货");
    }

    private static void add(String from, String... to) {
        TRANSITIONS.computeIfAbsent(from, k -> new HashSet<>()).addAll(Arrays.asList(to));
    }

    /**
     * 校验状态流转是否合法。
     *
     * @param oldIspay  库里旧 ispay（可能为 null/空）
     * @param oldStatus 库里旧 dingdanzhuangtai
     * @param newIspay  新提交的 ispay
     * @param newStatus 新提交的 dingdanzhuangtai
     * @return 合法返回 null；非法返回错误提示文案
     */
    public static String checkTransition(String oldIspay, String oldStatus, String newIspay, String newStatus) {
        // 空状态按未发货处理：兼容历史订单状态字段为 NULL 的数据，避免老数据无法流转
        String fromKey = normalize(oldIspay) + "|" + normalizeStatus(oldStatus);
        String toKey = normalize(newIspay) + "|" + normalizeStatus(newStatus);
        Set<String> allowed = TRANSITIONS.get(fromKey);
        if (allowed == null || !allowed.contains(toKey)) {
            return "非法订单状态流转：" + display(oldIspay, oldStatus) + " → " + display(newIspay, newStatus);
        }
        return null;
    }

    private static String normalize(String s) {
        return s == null || s.trim().isEmpty() ? "" : s.trim();
    }

    /** 发货状态归一：null/空视为未发货（历史数据兼容） */
    private static String normalizeStatus(String s) {
        String v = normalize(s);
        return v.isEmpty() ? UNSHIPPED : v;
    }

    public static String display(String ispay, String status) {
        return (ispay == null || ispay.isEmpty() ? "待支付" : ispay) + "/" + (status == null || status.isEmpty() ? UNSHIPPED : status);
    }
}

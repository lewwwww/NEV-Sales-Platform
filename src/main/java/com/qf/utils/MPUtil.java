package com.qf.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Mybatis-Plus工具类
 */
public class MPUtil {
	public static final char UNDERLINE = '_';

	
	//mybatis plus allEQ 表达式转换
		public static Map allEQMapPre(Object bean,String pre) {
		   Map<String, Object> map =BeanUtil.beanToMap(bean);
		  return camelToUnderlineMap(map,pre);
	   }

		//mybatis plus allEQ 表达式转换
		public static Map allEQMap(Object bean) {
		   Map<String, Object> map =BeanUtil.beanToMap(bean);
		   return camelToUnderlineMap(map,"");
	   }

/**
 * 根据前缀生成所有包含该前缀的模糊查询条件
 * @param wrapper 查询包装器对象，用于构建查询条件
 * @param bean 包含查询条件的实体对象
 * @param pre 字段前缀，用于筛选需要生成模糊查询条件的字段
 * @return 返回构建好的查询包装器对象，包含所有匹配前缀的模糊查询条件
 */
		public static Wrapper allLikePre(Wrapper wrapper,Object bean,String pre) {
    // 将实体对象转换为Map形式
			   Map<String, Object> map =BeanUtil.beanToMap(bean);
    // 将Map中的键转换为下划线格式，并添加指定前缀
			   Map result = camelToUnderlineMap(map,pre);
			 
    // 根据转换后的结果生成模糊查询条件
			return genLike(wrapper,result);
		}
	
		public static Wrapper allLike(Wrapper wrapper,Object bean) {
			  Map result = BeanUtil.beanToMap(bean, true, true);			 
			return genLike(wrapper,result);
		}
	
	
		public static Wrapper genLike( Wrapper wrapper,Map param) {
			Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
			int i=0;
			while (it.hasNext()) {
				if(i>0) wrapper.and();
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				String value = (String) entry.getValue();
				wrapper.like(key, value);
				i++;
			}
			return wrapper;
		}
		
/**
 * 根据bean对象生成包含like或eq条件的包装器
 * @param wrapper 原始包装器对象
 * @param bean 包含查询条件的bean对象
 * @return 返回生成的新包装器对象，包含like或eq条件
 */
		public static Wrapper likeOrEq(Wrapper wrapper,Object bean) {
    // 将bean对象转换为map，忽略空值和下划线线转驼峰
			  Map result = BeanUtil.beanToMap(bean, true, true);
    // 调用genLikeOrEq方法生成最终的包装器
			return genLikeOrEq(wrapper,result);
		}
		
/**
 * 根据参数生成包含LIKE或等于条件的包装器
 * @param wrapper 初始包装器对象
 * @param param 包含参数键值对的Map
 * @return 返回构建好的包装器对象
 */
		public static Wrapper genLikeOrEq( Wrapper wrapper,Map param) {
    // 获取参数Map的迭代器
			Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
			int i=0;  // 用于标记是否是第一个参数
    // 遍历参数Map中的所有键值对
			while (it.hasNext()) {
        // 如果不是第一个参数，添加AND条件连接
				if(i>0) wrapper.and();
        // 获取当前键值对
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();
        // 判断参数值是否包含百分号(%)，决定使用LIKE还是等于条件
				if(entry.getValue().toString().contains("%")) {
            // 如果包含百分号，使用LIKE条件，并移除百分号
					wrapper.like(key, entry.getValue().toString().replace("%", ""));
				} else {
            // 如果不包含百分号，使用等于条件
					wrapper.eq(key, entry.getValue());
				}
				i++;  // 增加参数计数
			}
			return wrapper;  // 返回构建好的包装器
		}
		
		public static Wrapper allEq(Wrapper wrapper,Object bean) {
			  Map result = BeanUtil.beanToMap(bean, true, true);			 
			return genEq(wrapper,result);
		}
	
	
		public static Wrapper genEq( Wrapper wrapper,Map param) {
			Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
			int i=0;
			while (it.hasNext()) {
				if(i>0) wrapper.and();
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				wrapper.eq(key, entry.getValue());
				i++;
			}
			return wrapper;
		}
	
	
		public static Wrapper between(Wrapper wrapper,Map<String, Object> params) {
			for(String key : params.keySet()) {
				String columnName = "";
				if(key.endsWith("_start")) {
					columnName = key.substring(0, key.indexOf("_start"));
					if(StringUtils.isNotBlank(params.get(key).toString())) {
						wrapper.ge(columnName, params.get(key));
					}
				}
				if(key.endsWith("_end")) {
					columnName = key.substring(0, key.indexOf("_end"));
					if(StringUtils.isNotBlank(params.get(key).toString())) {
						wrapper.le(columnName, params.get(key));
					}
				}
			}
			return wrapper;
		}
	
		public static Wrapper sort(Wrapper wrapper,Map<String, Object> params) {
			String order = "";
			if(params.get("order") != null && StringUtils.isNotBlank(params.get("order").toString())) {
				order = params.get("order").toString();
			}
			if(params.get("sort") != null && StringUtils.isNotBlank(params.get("sort").toString())) {
				if(order.equalsIgnoreCase("desc")) {
					wrapper.orderDesc(Arrays.asList(params.get("sort")));
				} else {
					wrapper.orderAsc(Arrays.asList(params.get("sort")));
				}
			}
			return wrapper;
		}
	
	
	/**
	 * 驼峰格式字符串转换为下划线格式字符串
	 * 
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static void main(String[] ages) {
		System.out.println(camelToUnderline("ABCddfANM"));
	}
	
	public static Map camelToUnderlineMap(Map param, String pre) {

		Map<String, Object> newMap = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = camelToUnderline(key);
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {

				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		return newMap;
	}
}

/**
 * 
 */
package com.basic.framework.platform.basic.common.josn;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author gmc
 * @see json数据操作类,提供对象转json和json转对象方法
 */
public class JsonTools {

	/**
	 * json字符串转换成实体对象
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Object json2Obj(String jsonStr, Class<?> clazz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		return mapper.readValue(jsonStr, clazz);
	}
	
	/**
	 * 对象转json字符串
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String obj2Json(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		return mapper.writeValueAsString(obj);
	}
}

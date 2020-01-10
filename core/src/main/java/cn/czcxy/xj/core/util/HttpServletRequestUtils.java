package cn.czcxy.xj.core.util;

import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/9 0009.
 * httpServletReques工具
 */
public class HttpServletRequestUtils {
    /**
     * 将request中的数据转换为map
     *
     * @param request
     * @return
     */
    public static Map getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 把request中的参数转换到DataSourceRequest中
     * @param dataSourceRequest
     * @param request
     */
    public static void transRequestToDataSourceRequest(DataSourceRequest dataSourceRequest,HttpServletRequest request){
        dataSourceRequest.setData((HashMap<String, Object>)getParameterMap(request));
    }
}

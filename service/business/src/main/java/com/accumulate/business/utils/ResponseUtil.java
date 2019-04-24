package com.accumulate.business.utils;//package com.accumulate.services.utils;
//
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.ServletResponse;
//import java.io.PrintWriter;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Exrickx
// */
//@Slf4j
//public class ResponseUtil {
//
//    /**
//     * 使用response输出JSON
//     *
//     * @param response
//     * @param resultMap
//     */
//    public static void out(final ServletResponse response, final Map<String, Object> resultMap) {
//
//        PrintWriter out = null;
//        try {
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json");
//            out = response.getWriter();
//            out.println(new Gson().toJson(resultMap));
//        } catch (final Exception e) {
//            log.error(e + "输出JSON出错");
//        } finally {
//            if (out != null) {
//                out.flush();
//                out.close();
//            }
//        }
//    }
//
//    public static Map<String, Object> resultMap(final boolean flag, final Integer code, final String msg) {
//
//        final Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("success", flag);
//        resultMap.put("message", msg);
//        resultMap.put("code", code);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        resultMap.put("timestamp", sdf.format(new Date()));
//        return resultMap;
//    }
//
//    public static Map<String, Object> resultMap(final boolean flag, final Integer code, final String msg,
//            final Object data) {
//
//        final Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("success", flag);
//        resultMap.put("message", msg);
//        resultMap.put("code", code);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        resultMap.put("timestamp", sdf.format(new Date()));
//        resultMap.put("result", data);
//        return resultMap;
//    }
//}

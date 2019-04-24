//package com.accumulate.business.config;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.Objects;
//
///**
// * @System: 车贷金融
// * @Auther: hukaijia
// * @Description: 系统日志
// * @Modified By:
// */
//@Aspect
//@Order(1)
//@Component
//public class SystemLogAop {
//
//    private final Logger log = LoggerFactory.getLogger(SystemLogAop.class);
//
//    private final static String AFTER = "after";
//    private final static String EXCEPTION = "exception";
//
//    ThreadLocal<Long> startTime = new ThreadLocal<>();
//
//    // 切入点*Controller
//    @Pointcut("execution(public * com.accumulate.report.*.web.*Controller.*(..))")
//    public void systemLogAop() {
//    }
//
//    @Before("systemLogAop()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        startTime.set(System.currentTimeMillis());
//    }
//
//    @After("systemLogAop()")
//    public void doAfterReturning(JoinPoint joinPoint) throws Throwable {
//        saveSystemLogsBlock(joinPoint, AFTER, null, startTime);
//    }
//
//    /**
//     * 异常通知 用于拦截记录异常日志
//     *
//     * @param joinPoint
//     * @param e
//     */
//    @AfterThrowing(pointcut = "systemLogAop()", throwing = "e")
//    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
//        saveSystemLogsBlock(joinPoint, EXCEPTION, e, startTime);
//    }
//
//    private void saveSystemLogsBlock(JoinPoint joinPoint, String type, Throwable e, ThreadLocal<Long> startTime) {
//        try {
//            Map<String, Object> map = SystemLogUtil.saveSystemLog(joinPoint, type, null, startTime);
//            if (map.isEmpty()) {
//                return;
//            }
//            Object token = map.get("token");
//            if (Objects.isNull(token)) {
//                return;
//            }
////            User body = userService.getUserByToken(token.toString());
////            String userName = "";
////            if (Objects.isNull(body)) {
////                userName = "获取用户失败";
////            } else {
////                userName = body.getUserName();
////            }
////            SystemLog systemLogs = new SystemLog();
////            if (Objects.nonNull(body.getCompanyCode())) {
////                systemLogs.setCompanyCode(body.getCompanyCode());
////            }
////            systemLogs.setClientIp((String) map.get("reqIp"));
////            systemLogs.setOperator(body.getUserName());
////            systemLogs.setOperateTime((Date) map.get("creatTime"));
////            systemLogs.setRemark((String) map.get("remark"));
////            systemLogs.setExeTime(String.valueOf(map.get("exeTime")));
////            systemLogs.setExeMethod((String) map.get("methods"));
//////            systemLogs.setExeParams((String) map.get("params"));
////            systemLogs.setExeType((String) map.get("type"));
////            systemLogRepository.save(systemLogs);
//        } catch (Exception e1) {
//            e1.printStackTrace();
//            log.debug(e1.getMessage());
//        }
//    }
//}

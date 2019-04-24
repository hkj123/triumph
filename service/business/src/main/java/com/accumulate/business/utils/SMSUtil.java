package com.accumulate.business.utils;//package com.accumulate.services.utils;
//
//import cn.hutool.json.JSONObject;
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.profile.DefaultProfile;
//import com.aliyuncs.profile.IClientProfile;
//import com.accumulate.services.enums.SMSSignName;
//import com.accumulate.services.enums.SMSTemplate;
//import com.accumulate.services.vo.SMSPacket;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class SMSUtil {
//
//    //产品名称:云通信短信API产品,开发者无需替换
//    static final String product = "Dysmsapi";
//    //产品域名,开发者无需替换
//    static final String domain = "dysmsapi.aliyuncs.com";
//
//    static final String accessKeyId = "LTAIm3RcPjIzYFEW";
//    static final String accessKeySecret = "bZH8MWLDTF7a8aipEUN1RuQB8HDxOl";
//
//    public static Boolean sendSms(SMSPacket smsPacket) throws Exception {
//        //可自助调整超时时间
//        IAcsClient acsClient = initSmeService();
//        //组装请求对象-具体描述见控制台-文档部分内容
//        SendSmsRequest request = new SendSmsRequest();
//
//        request.setPhoneNumbers(smsPacket.getDestMobile());
//        request.setSignName(smsPacket.getSign().getSignName());
//        request.setTemplateCode(smsPacket.getTemplate().getTemplateName());
//        request.setTemplateParam(smsPacket.getParams().toJSONString(0));
//        SendSmsResponse sendSmsResponse = null;
//        try {
//            sendSmsResponse = acsClient.getAcsResponse(request);
//        } catch (ClientException e){
//            throw new Exception(sendSmsResponse.getMessage());
//        }
//        if (sendSmsResponse.getCode().toUpperCase().equals("OK")){
//            return true;
//        } else {
//            throw new Exception(sendSmsResponse.getMessage());
//        }
//    }
//
//    private static IAcsClient initSmeService() throws ClientException {
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//
//        //初始化acsClient,暂不支持region化
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//        return new DefaultAcsClient(profile);
//    }
//
//
//    public static QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException {
//
//        //可自助调整超时时间
//        IAcsClient acsClient = initSmeService();
//
//        //组装请求对象
//        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
//        //必填-号码
//        request.setPhoneNumber("18602987796");
//        //可选-流水号
//        request.setBizId(bizId);
//        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
//        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
//        request.setSendDate(ft.format(new Date()));
//        //必填-页大小
//        request.setPageSize(10L);
//        //必填-当前页码从1开始计数
//        request.setCurrentPage(1L);
//
//        //hint 此处可能会抛出异常，注意catch
//        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
//
//        return querySendDetailsResponse;
//    }
//
//    public static void main(String[] args) throws ClientException, InterruptedException {
//
//        JSONObject params = new JSONObject();
//        params.put("code","666666");
//        SMSPacket smsPacket = new SMSPacket();
//        smsPacket.setAccessKeyId(accessKeyId);
//        smsPacket.setAccessKeySecret(accessKeySecret);
//        smsPacket.setDestMobile("13319288870");
//        smsPacket.setSign(SMSSignName.DRORE_CLOUD_CN);
//        smsPacket.setTemplate(SMSTemplate.CHANGE_PASSWORD);
//        smsPacket.setParams(params);
//        try {
//            if (sendSms(smsPacket)) {
//                System.out.println("成功了");
//            }
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//}

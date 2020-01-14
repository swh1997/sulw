package cn.dgut.jsf.controller;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

        // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
        public static String APP_ID = "2016101500688332";

        // 商户私钥，您的PKCS8格式RSA2私钥
        public static String APP_PRIVATE_KEY ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCVwPRZUJbNEehbuwheyfh2EUohGJTOyVPVi8xcyv56Ep1uIriuHbMcozCCWwRL/eq4J5zfBFFsW6zXm2FKn+wBLsXjRBKCYvePcagHClyT8HzcOt3GojZxVEp88HExuBfGDCh1zKQLareNetMHkoWlxNnMZRQwUNJ9Qsm/BFEYA5Wcq+BhJqg9qy1vutIwMqSF2QURzvXuQu2RWtc0EiFexL49eGhvb9g2PP+7ixhN4u0TXt1TMoFF7x8zs7Q0r56OBEh5VHrcyOgt/CgF/iGWTxiFiuhJTBtj7k0SH7TLQGJnrGTnAUkg4Vf7Or/YQt81qs5gM9iLTjeK/5LMj2uXAgMBAAECggEBAJWEHjLidH3w6DZV6ciCDLENGB8tJL3FYw9t0a+/+Q22cjpZLlyhSIhKIo+0AflBXk1ZN5WvAQCYE1z9RMk2HHMd2PPMrlJCL3undaT54m7OiEt19qFwqbzC9NADXHDQpePVlbvvgsXnWbZHMFV6JDfa773UGbEXsODhgv5VeAXpU4NGQ8DitGY/AyYh8PSVAgZBtdVu44LE5r1wni2bSW0F8eiwmTDLsHEfdsOIeokTJTFgGXAo2pKB/EaN51JJfkKVCmA0pwKn1JSgD/FwbyGQdd3gUxr95GJwG+mPG/5ee+6NvWtSJBMiR1t8djgwGWvRxcLyGcV6qleflkx8yxECgYEA+hUFWvbcz5LUUzn+s7ALQP3cHvEVlK3VbyK7qb7BSqgTwpgeNUCilFNAxnMW9Rr46pLoKvzSy/fhPf9k1s7WqXlmQUyPeMrKc1X+lnOfFaig0Pb30YEKnrmN0luJomdKAhCQwi1lkM/v5RbLL6Y+eqRTUc6xf/Fz+2s2m57+PPkCgYEAmUwmzgBXwvspwivrFE6HdZP7/awNtz1d2aD5FALaUf77ssZl/QugO4fPonTFufUEbNy8h57tm6pgd6ZMjJck+h6Ru0T7uN47ykjpW99Dm1FfzIy/KYh71MN+F0bpHkBBiEcjie6SeDLe/voIbv8CjTfHQfU1mQEvX47Aruxr4Q8CgYAisqn0jg3jCwdEqOZK/iEBmndhH6gT4e6cJeCN07az6hAbzslaMCael9KD9lH4Hg7tVQa7eCqzPChXVskh+L7V8s8aPkolw1VFnWfebhpjgesWMz39Jn9XuH0R87tr+hLNqIi9OFlAN2mcioYUjWdb0tLwpdIA43ppJt4Dx5KU2QKBgD6/pxqnlUTgnkU2yPD0/Amq23kIlkytN98/PvVr/YWWw9x3zYN44Mu9WcZ72fu2SWtuJ1W0Pjp70STo02iw7ZfcL3biU89mWD8C/VJ33yqSco4+BnmvvlBm/RP2GLIjRy/LPsbU+utsl07zdcRJaff4Hv3mq1vH2mZY/pIbgEUNAoGBAJFpx1PueDaMvwLcWUM8Z8MczDNMJJ69le1TXzfHLPjDUcGV71Ix3+YdH++eg84jO3EMHfT6dPuaollUftQz+0VpVxlXn6UKGq5M4S/g4uUI88p27T7+/IQrP54CdEf64QlHLtOv7Hhd07iNP0Pvv9tah+zo238p8wZtUsXiAF2E";

        // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
        public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0Pw0BIKrP6aZPEKzzZiuXZ/0vdUzctgvBu4LyE282PfDUGEPCISMZdNn7ixY4Ck9x2/+Zoz1ch72fJEvJdukRu5cHtf8u42cZiX6Vf+0equ7/lHE87FA8h8+u1GcoC4q/HN87or97MBTcL2BbFEPXxJ8/I9XD7+wSvC8R6oOEBfQLkn9ca5Mlamp/8OXdZnmwIO4qPO8Ss8d/5/AJ0c0aK0AEVnpuXz+Aj6D/cM5TZ9EPNe1ms7ZqHI1hr4PTmOrWUJuEQJ2CmuIBNQqfeA/pASydu9Y67TT7sHMxpwEXyy5wC9yrzR4hfLJ5em7ch3Egh5fKSZR7ZN0/Iw5SbrNQwIDAQAB";

        // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        public static String notify_url = "http://61.142.10.253:8080/setMeal/toSetMealBuyWeb";

        // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
        public static String return_url = "http://localhost:8080/setMeal/return";

        // 签名方式
        public static String sign_type = "RSA2";

        // 字符编码格式
        public static String CHARSET = "utf-8";

        // 支付宝网关，这是沙箱的网关
        public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

        // 支付宝网关
        public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

        /**
         * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
         * @param sWord 要写入日志里的文本内容
         */
        public static void logResult(String sWord) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
                writer.write(sWord);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

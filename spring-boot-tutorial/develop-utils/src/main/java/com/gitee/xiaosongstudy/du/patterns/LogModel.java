package com.gitee.xiaosongstudy.du.patterns;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日志模型
 *
 * @author shiping.song
 * @date 2023/4/11 21:30
 * @since 1.0.0
 */
@Data
@Builder
public class LogModel implements Serializable {
    private static final long serialVersionUID = -6305462411765429830L;

    /**
     * 用户ip
     */
    private String userIp;

    /**
     * 请求路径
     */
    private String requestPath;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * postData
     */
    private String postData;

    /**
     * 原始json
     */
    private String jsonRow;

    /**
     * 响应数据
     */
    private String responseData;

    /**
     * 代理
     */
    private String userAgent;

    private static Pattern logPattern = Pattern.compile("\\[([a-z-]+)\\](\\{(.*)\\})");


    public static void main(String[] args) {
        // (\[([a-zA-Z-]+)\](\{.*\})\[?)|
        String logStr = "2023-04-06_01:[response-data]{\"code\":\"-1\",\"msg\":\"未知错误\"}[user_agent][[]]";
//        String logStr = "2023-04-06_00:[时间][2023-04-06 00:00:00][用户ip][220.181.3.95][request-path]/api[request-header]{\"connection\":[\"keep-alive\"],\"host\":[\"pkigw.dcvp.dfmc.com.cn\"],\"x-real-ip\":[\"220.181.3.95\"],\"x-forwarded-for\":[\"220.181.3.95\"],\"x-forwarded-proto\":[\"https\"],\"content-length\":[\"1061\"],\"api\":[\"pki.certificate.apply\"],\"appid\":[\"dcvp8b7445dee8da92ce022cf75cd3590cb8\"],\"noncestr\":[\"ade2356f0da54fd0b36f81f45934b6e6\"],\"timestamp\":[\"1680710416877\"],\"sign\":[\"fc7041b02f6c7bdc3026d16fcef0d2ac\"],\"content-type\":[\"application\\/json; charset=utf-8\"],\"user-agent\":[\"okhttp\\/3.10.0\"]}[post-data][][json-raw]{\"userInfo\":{\"userCode\":\"dfvp\",\"IVISN\":\"P008003160001087\",\"userID\":\"1\"},\"transId\":\"IVI00000000006\",\"userP10\":\"MIIClDCCAXwCAQAwTzEjMCEGA1UEAwwaVGVzdF9QMDA4MDAzMTYwMDAxMDg3X251bGwxCzAJBgNVBAYTAkNOMQ0wCwYDVQQKDARERlBWMQwwCgYDVQQLDANJVkkwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCgCZtUusyk36o2TeKMIkhFqjYb2bB9KpWi4eRNFuRk79sDlYQhz87oSO92ZckBAmSQ8KY3zMkiyyiXGpHLT1f2kTAcGUeMdb3LFG0+vGA482CO1JB1Md\\/x7Khz3CYo4g9MkHTiHvOm8J4B92FqyE4qk9zT4Y4vTtJQ\\/xR5D846f5goydyu9KMDKCgDBKehjrRSC2Y+XBz7HfTx\\/sq1fPsTMW5ePJr32eCnzC0T5zaK\\/OKFPRp8cZ+tOc56xqiIzRAH1a+kSY7RFuiXRV3OGnc8ofVrIzKaCZD2E9A5iKKrbh0QCKaOK11gGZ\\/ZPbOfm1VB7ouPQ0bZy+fsEsPqTnx5AgMBAAGgADANBgkqhkiG9w0BAQUFAAOCAQEABW5aMvODYtV\\/DPMR2p4qIMLot2HmdLLZnPS8\\/y1oZQY7xldBZCPL3i9hb9snthf42ny7JSk2Ond9YlIef6Ego41Rv4a8ateGgg4aqeN51A\\/clU\\/uYHWNYu0H0gSquMLQHEqQd61gMOlrao3PtmltwX9fFWGzGzdRJ280WIKM4BUvVtUgS\\/6xtMtp5m+PJVmPow4PYz\\/\\/OxALAu8MsOE1p6QaS+TnMqQcOKqUChRBR77sNPzSGlT4u8Lr09Tek5pdRjsUd1SI1mlWixr5d6WsAqi187MjBV\\/et9I3uorsUmbzVMnu9xG4jqJsixuYU14WR4nQ4D\\/0zUP3ftb6XDwDaw==\",\"certType\":2,\"keyPlace\":\"1\",\"certFormat\":\"p7b\"}[response-data]{\"code\":\"0901\",\"msg\":\"系统中没有该vin\"}[user_agent][[\"okhttp\\/3.10.0\"]]";
//        Pattern logPattern = Pattern.compile("(\\[([\u4e00-\u9fa5a-zA-Z]+)\\]\\[([0-9A-Za-z- :\\.]*)\\])|(\\[([a-zA-Z-]+)\\]([A-Za-z/]+))|(\\[([a-zA-Z-]+)\\](\\{.*\\}))|(\\[([a-zA-Z_]+)\\]\\[\\[(.*)\\]\\])");
        matchJsonDataStructure(logStr);
    }

    public static void matchJsonDataStructure(String localStr) {
        if (Objects.isNull(localStr) || localStr.trim().length() == 0) {
            return;
        }
        // 移除干扰参数
        localStr = localStr.replaceAll("\\[[a-z-]+\\]\\[[\\w\\./\\\\]*\\]", "");
        Matcher matcher = logPattern.matcher(localStr);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            matchJsonDataStructure(matcher.group(2));
        }
    }
}

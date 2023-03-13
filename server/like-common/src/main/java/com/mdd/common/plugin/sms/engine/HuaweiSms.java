package com.mdd.common.plugin.sms.engine;

import com.mdd.common.util.ToolUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 华为云短信
 */
public class HuaweiSms {
    private static final String WSSE_HEADER_FORMAT = "UsernameToken Username=\"%s\",PasswordDigest=\"%s\",Nonce=\"%s\",Created=\"%s\"";
    private static final String AUTH_HEADER_VALUE = "WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"Appkey\"";

    private Integer sendResult;     // 发送结果
    private String mobile;          // 手机号码
    private String templateId;      // 短信模板
    private String templateParams;  // 短信参数
    private final Map<String, String> config; // 短信配置

    /**
     * 构造方法
     *
     * @author fzr
     * @param config 短信配置
     */
    public HuaweiSms(Map<String, String> config) {
        this.config = config;
    }

    /**
     * 设置手机号
     *
     * @author fzr
     * @param mobile 手机号码
     * @return AliSms
     */
    public HuaweiSms setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    /**
     * 设置模板id
     *
     * @author fzr
     * @param templateId 模板id
     * @return AliSms
     */
    public HuaweiSms setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * 设置模板参数
     *
     * @author fzr
     * @param templateParams 模板参数
     * @return AliSms
     */
    public HuaweiSms setTemplateParams(String templateParams) {
        this.templateParams = templateParams;
        return this;
    }

    /**
     * 获取发送结果
     *
     * @author fzr
     * @return Integer [1=成功, 2=失败]
     */
    public Integer getSendResult() {
        return this.sendResult;
    }

    /**
     * 发送短信
     *
     * @author fzr
     * @return String
     */
    public String send() throws Exception {
        String url = "";                         // APP接入地址(在控制台"应用管理"页面获取)+接口访问URI
        String appKey = "xx";                    // APP_Key
        String appSecret = "xx";                 // APP_Secret
        String sender = "";                      // 国内短信签名通道号或国际/港澳台短信通道号
        String templateId = "";                  // 模板ID
        String signature = "";                   // 签名名称
        String templateParas = "[\""+"ff"+"\"]"; // 模板变量
        String receiver = "+86" + "18927154977"; // 短信接收人号码
        String statusCallBack = "";              // 接收报告地址,为空不报告

        // 请求Body
        String body = buildRequestBody(sender, receiver, templateId, templateParas, statusCallBack, signature);
        if (null == body || body.isEmpty()) {
            System.out.println("body is null.");
        }

        // 请求Headers
        String wsseHeader = buildWsseHeader(appKey, appSecret);
        if (null == wsseHeader || wsseHeader.isEmpty()) {
            System.out.println("wsse header is null.");
        }

        HostnameVerifier hv = (hostname, session) -> true;
        trustAllHttpsCertificates();

        Writer out = null;
        InputStream is = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        HttpsURLConnection connection;
        try {
            URL realUrl = new URL(url);
            connection = (HttpsURLConnection) realUrl.openConnection();
            connection.setHostnameVerifier(hv);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", AUTH_HEADER_VALUE);
            connection.setRequestProperty("X-WSSE", wsseHeader);
            connection.connect();

            out = new OutputStreamWriter(connection.getOutputStream());
            out.write(body);
            out.flush();
            out.close();

            int status = connection.getResponseCode();
            if (200 == status) { //200
                is = connection.getInputStream();
            } else { //400/401
                is = connection.getErrorStream();
            }

            String line;
            in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
                if (null != is) {
                    is.close();
                }
                if (null != in) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return  "";
    }

    /**
     * 构造请求体Body
     *
     * @author fzr
     * @param sender         (国内短信签名通道号或国际/港澳台短信通道号)
     * @param receiver       (短信接收人号码)
     * @param templateId     (模板编号)
     * @param templateParas  (模板参数)
     * @param statusCallBack (短信状态报告接收地址,推荐使用域名,为空表示不接收状态报告)
     * @param signature      (签名名称,使用国内短信通用模板时填写)
     * @return String
     */
    static String buildRequestBody(String sender, String receiver, String templateId, String templateParas,
                                   String statusCallBack, String signature) {
        if (null == sender || null == receiver || null == templateId || sender.isEmpty() || receiver.isEmpty() || templateId.isEmpty()) {
            System.out.println("buildRequestBody(): sender, receiver or templateId is null.");
            return null;
        }

        Map<String, String> map = new HashMap<>();
        map.put("from", sender);
        map.put("to", receiver);
        map.put("templateId", templateId);

        if (null != templateParas && !templateParas.isEmpty()) {
            map.put("templateParas", templateParas);
        }

        if (null != statusCallBack && !statusCallBack.isEmpty()) {
            map.put("statusCallback", statusCallBack);
        }

        if (null != signature && !signature.isEmpty()) {
            map.put("signature", signature);
        }

        String temp = "";
        StringBuilder sb = new StringBuilder();
        for (String s : map.keySet()) {
            try {
                temp = URLEncoder.encode(map.get(s), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            sb.append(s).append("=").append(temp).append("&");
        }

        return sb.deleteCharAt(sb.length()-1).toString();
    }

    /**
     * 构造X-WSSE参数值
     *
     * @author fzr
     * @param appKey    (令牌)
     * @param appSecret (密码)
     * @return String
     */
    static String buildWsseHeader(String appKey, String appSecret) {
        if (null == appKey || null == appSecret || appKey.isEmpty() || appSecret.isEmpty()) {
            System.out.println("buildWsseHeader(): appKey or appSecret is null.");
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = sdf.format(new Date()); //Created
        String nonce = ToolUtils.makeUUID().replace("-", "");

        MessageDigest md;
        byte[] passwordDigest = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update((nonce + time + appSecret).getBytes());
            passwordDigest = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String passwordDigestBase64Str = Base64.getEncoder().encodeToString(passwordDigest);
        return String.format(WSSE_HEADER_FORMAT, appKey, passwordDigestBase64Str, nonce, time);
    }

    /**
     * 信任所有证书
     */
    static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] chain, String authType) { }
                    public void checkServerTrusted(X509Certificate[] chain, String authType) { }
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

}

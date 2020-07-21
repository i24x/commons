package com.lsl.common.lang.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;

public class HttpUtils {

    public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse) {
        // post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // HttpClient client = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /** 请求发送成功，并得到响应 **/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /** 读取服务器返回过来的json字符串数据 **/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /** 把json字符串转换成json对象 **/
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
        }
        return jsonResult;
    }

    private static void synWDLoanState(String applicationId, String loanState, BigDecimal appAmt) {
        String url = "http://10.200.61.105:8580/router/trans/loan";
        JSONObject param = new JSONObject();
        JSONObject payload = new JSONObject();
        param.put("loanId", applicationId);
        param.put("duebillNo", "123345667890");
        param.put("loanStatus", loanState);
        param.put("appAmt", appAmt);

        param.put("payload", payload);
        param.put("_TransCode", "icsCallback");
        param.put("channelJnlNo", "339066546901090304");

        JSONObject p =
            JSONObject.parseObject("{\n" + "    \"payload\": {\n" + "         \"loanId\": \"338067351889117184\",\n"
                + "         \"duebillNo\": \"20200323113458000000041923\",\n" + "         \"loanStatus\": \"1\",\n"
                + "         \"appAmt\":\"1000\"\n" + "    },\n" + "    \"_TransCode\": \"icsCallback\",\n"
                + "    \"channelJnlNo\": \"339066546901090304\"\n" + "}");

        JSONObject response = HttpUtils.httpPost(url, p, false);

        String retCode = response.getString("classcode");
        if ("000000".equals(retCode)) {// 成功
            System.out.println(response.toJSONString());
            JSONObject respPayload = response.getJSONObject("payload");
            if (respPayload != null && respPayload.getBoolean("success")) {
                System.out.println(
                    String.format("ICS同步网贷互金贷款状态【%1$s】成功，申请号【%2$s】,贷款余额【%3$s】", loanState, applicationId, appAmt));
            } else {
                System.out.println(String.format("ICS同步网贷互金贷款状态【%1$s】失败，申请号【%2$s】，原因【%3$s】,贷款余额【%4$s】", loanState,
                    applicationId, response.getString("retMsg"), appAmt));
            }
        } else {
            System.out.println(String.format("ICS同步网贷互金贷款状态【%1$s】失败，申请号【%2$s】，原因【%3$s】,贷款余额【%4$s】", loanState,
                applicationId, response.getString("retMsg"), appAmt));
        }
    }

    public static void main(String[] args) {
        synWDLoanState("122345", "提前结清", new BigDecimal(5000));

    }
}

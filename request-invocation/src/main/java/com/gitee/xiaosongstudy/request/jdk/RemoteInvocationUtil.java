package com.gitee.xiaosongstudy.request.jdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gitee.xiaosongstudy.request.bean.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 远程调用工具类
 *
 * @author hopeurl
 */
public class RemoteInvocationUtil {

    /**
     * 连接超时时间，单位毫秒，默认值
     */
    public static final int TIMEOUT = 5000;

    private RemoteInvocationUtil() {
    }

    public static void main(String[] args) {
//        System.out.println(Objects.requireNonNull(RemoteInvocationUtil.class.getClassLoader().getResource("")).getPath());
        System.out.println(RemoteInvocationUtil.postAttachment("http://localhost:9022/request/upload", new File("E:\\认证图片\\平台授权说明书_看图王.jpg")));
//        test01();
    }

    private static void test01() {
        // 返回正常
//        RemoteInvocationUtil.getForEntity("http://localhost:9022/request/sayHello", null);
        // 返回正常
//        User forEntity = RemoteInvocationUtil.getForEntity("http://localhost:9022/request/getJsonStr", User.class);
//        System.out.println(forEntity);
        Map<String, Object> pathVariables = new HashMap<>();
        pathVariables.put("id", 1);
        pathVariables.put("username", "张三");
        // 在进行时间转化时后台需要额外做配置
//        pathVariables.put("birthday", LocalDate.now());
        System.out.println(RemoteInvocationUtil.getForEntity("http://localhost:9022/request/param", pathVariables, User.class));
        System.out.println(RemoteInvocationUtil.getForEntity("http://localhost:9022/request/paramWithEntity", pathVariables, User.class));
//        System.out.println(RemoteInvocationUtil.getForEntity("http://localhost:9022/request/param?id=1&username=%E5%BC%A0%E4%B8%89", null, User.class));
        Map<String, Object> postPathVariables = new HashMap<>();
        postPathVariables.put("param", "我是测试数据");
        RemoteInvocationUtil.postForLocation("http://localhost:9022/request/postStr", postPathVariables);
        User user = User.builder().id(20221010).username("里斯").build();
        System.out.println(RemoteInvocationUtil.postForEntity("http://localhost:9022/request/postWithEntity", JSON.toJSONString(user), String.class));
    }

    /**
     * get请求-有返回值
     *
     * @param url           请求地址
     * @param headersMap    请求头集合
     * @param pathVariables 参数
     * @param timeout       超时时间
     * @param entityClz     目标实体类
     * @param <T>
     * @return
     */
    public static <T> T getForEntity(String url, Map<String, String> headersMap, Map<String, Object> pathVariables, int timeout, Class<T> entityClz) {
        String result = doRequest(url, HttpMethod.GET, headersMap, null, pathVariables, timeout, null);
        return JSONObject.parseObject(result, entityClz);
    }

    public static <T> T getForEntity(String url, Map<String, String> headersMap, Map<String, Object> pathVariables, Class<T> entityClz) {
        return getForEntity(url, headersMap, pathVariables, TIMEOUT, entityClz);
    }

    public static <T> T getForEntity(String url, Map<String, Object> pathVariables, Class<T> entityClz) {
        return getForEntity(url, null, pathVariables, entityClz);
    }

    public static <T> T getForEntity(String url, Class<T> entityClz) {
        return getForEntity(url, null, entityClz);
    }

    public static void getForLocation(String url, Map<String, String> headersMap, Map<String, Object> pathVariables, int timeout) {
        doRequest(url, HttpMethod.GET, headersMap, null, pathVariables, timeout, null);
    }

    public static void getForLocation(String url, Map<String, String> headersMap, Map<String, Object> pathVariables) {
        getForLocation(url, headersMap, pathVariables, TIMEOUT);
    }

    public static void getForLocation(String url, Map<String, String> headersMap) {
        getForLocation(url, headersMap, null);
    }

    public static void getForLocation(String url) {
        getForLocation(url, null);
    }

    /**
     * post请求-有返回值
     *
     * @param url           目标地址
     * @param headersMap    请求头
     * @param body          请求体
     * @param pathVariables 参数
     * @param timeout       超时时间
     * @param entityClz     响应实体类字节码对象
     * @param <T>
     * @return
     */
    public static <T> T postForEntity(String url, Map<String, String> headersMap, String body, Map<String, Object> pathVariables, int timeout, Class<T> entityClz) {
        if (!isNotEmpty(headersMap)) {
            headersMap = new HashMap<>();
        }
        headersMap.put(HttpHeaders.CONTENT_TYPE, MimeType.APPLICATION_JSON_VALUE);
        // 执行结果
        String result = doRequest(url, HttpMethod.POST, headersMap, body, pathVariables, timeout, null);
        if (entityClz == String.class) {
            return (T) result;
        }
        return JSONObject.parseObject(result, entityClz);
    }

    public static <T> T postForEntity(String url, Map<String, String> headersMap, String body, Map<String, Object> pathVariables, Class<T> entityClz) {
        return postForEntity(url, headersMap, body, pathVariables, TIMEOUT, entityClz);
    }

    public static <T> T postForEntity(String url, Map<String, String> headersMap, String body, Class<T> entityClz) {
        return postForEntity(url, headersMap, body, null, TIMEOUT, entityClz);
    }

    public static <T> T postForEntity(String url, String body, Class<T> entityClz) {
        return postForEntity(url, null, body, entityClz);
    }

    public static <T> T postForEntity(String url, Class<T> entityClz) {
        return postForEntity(url, null);
    }

    public static void postForLocation(String url, Map<String, String> headersMap, String body, Map<String, Object> pathVariables, int timeout) {
        doRequest(url, HttpMethod.POST, headersMap, body, pathVariables, timeout, null);
    }

    public static void postForLocation(String url, Map<String, String> headersMap, String body, Map<String, Object> pathVariables) {
        postForLocation(url, headersMap, body, pathVariables, TIMEOUT);
    }

    public static void postForLocation(String url, Map<String, String> headersMap, String body) {
        postForLocation(url, headersMap, body, null);
    }

    public static void postForLocation(String url, Map<String, Object> pathVariables) {
        postForLocation(url, null, null, pathVariables);
    }

    public static void postForLocation(String url, String body) {
        postForLocation(url, null, body);
    }

    public static String postAttachment(String url, Map<String, String> headersMap, Map<String, Object> pathVariables, int timeout, File file) {
        return doRequest(url, HttpMethod.POST, headersMap, null, pathVariables, timeout, file);
    }

    public static String postAttachment(String url, File file) {
        return postAttachment(url, null, null, TIMEOUT, file);
    }

    /**
     * 执行发送请求操作
     *
     * @param url           目标地址
     * @param httpMethod    请求方法
     * @param headersMap    方法头
     * @param body          请求体
     * @param pathVariables 路径参数
     * @param timeout       超时时间
     * @return 可能存在的执行结果
     */
    private static String doRequest(String url, HttpMethod httpMethod, Map<String, String> headersMap, String body, Map<String, Object> pathVariables, int timeout, File file) {
        assertHttpRequest(url);
        HttpURLConnection conn = null;
        try {
            // 如果路径参数存在则需要修改请求地址
            url = setPathVariables(url, pathVariables);
            conn = getBaseConnection(url, httpMethod, timeout);
            // 设置请求头
            setHeaders(conn, headersMap);
            // 如果当前请求有请求体
            writeBody(conn, body);
            // 如果请求携带附件信息
            writeAttachment(conn, file);
            return resolveResponse(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
        }
        return null;
    }

    /**
     * 获取基础的连接
     *
     * @param url
     * @param httpMethod
     * @param timeout
     * @return
     * @throws IOException
     */

    private static HttpURLConnection getBaseConnection(String url, HttpMethod httpMethod, int timeout) throws IOException {
        URL httpUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
        conn.setRequestMethod(httpMethod.value);
        conn.setConnectTimeout(timeout);
        conn.setReadTimeout(timeout);
        if (HttpMethod.POST == httpMethod) {
            // post方式不能使用缓存
            conn.setUseCaches(false);
        }
        return conn;
    }

    /**
     * 发送附件
     *
     * @param conn
     * @param file
     * @throws IOException
     */
    private static void writeAttachment(HttpURLConnection conn, File file) throws IOException {
        if (null != file) {
            if (file.exists()) {
                OutputStream outputStream = null;
                DataInputStream in = null;
                conn.setDoOutput(true);
                String boundary = "----------" + System.currentTimeMillis();
                conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, HeaderValue.MULTIPART_FORM_DATA_VALUE + boundary);
                // 完善附件请求内容
                String sb = "--" +
                        boundary + "\r\n" +
                        "Content-Disposition: form-data;name=\"file\";filename=\"" + URLEncoder.encode(file.getName(), "utf-8") + "\r\n" +
                        "Content-Type:application/octet-stream\r\n\r\n";
                // 转化表头
                byte[] head = sb.getBytes(StandardCharsets.UTF_8);
                // 获取到输出流
                try {
                    outputStream = new DataOutputStream(conn.getOutputStream());
                    // 输出表头
                    outputStream.write(head);
                    // 文件正文内容 文件以流的形式传输
                    in = new DataInputStream(Files.newInputStream(file.toPath()));
                    int bytes;
                    byte[] bufferOut = new byte[1024 * 8];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        outputStream.write(bufferOut, 0, bytes);
                    }
                    // 结尾部分 定义最后数据分隔线
                    byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8);
                    outputStream.write(foot);
                    outputStream.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (in != null) {
                        in.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } else {
                throw new FileNotFoundException(String.format("文件【%s】不存在", file.getAbsolutePath()));
            }
        }
    }


    /**
     * 设置参数
     *
     * @param url
     * @param pathVariables
     * @return
     */
    private static String setPathVariables(String url, Map<String, Object> pathVariables) {
        if (isNotEmpty(pathVariables)) {
            final StringBuilder sb = new StringBuilder(url);
            sb.append("?");
            pathVariables.forEach((key, value) -> {
                try {
                    // 这里如果有中文的话会乱码，所以应该进行一次转码
                    sb.append(key).append("=").append(URLEncoder.encode(value.toString(), "utf-8")).append("&");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            });
            url = sb.toString();
            // 去掉最后一个&符号
            sb.replace(url.length() - 1, url.length(), "");
            url = sb.toString();
        }
        return url;
    }

    /**
     * 设置头信息
     *
     * @param conn
     * @param headersMap
     */
    private static void setHeaders(HttpURLConnection conn, Map<String, String> headersMap) {
        if (isNotEmpty(headersMap)) {
            Set<Map.Entry<String, String>> entries = headersMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * write body
     *
     * @param conn
     * @param body
     */
    private static void writeBody(HttpURLConnection conn, String body) throws IOException {
        if (null != body && body.trim().length() > 0) {
            conn.setDoOutput(true);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(body.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }


    /**
     * 解析响应数据
     *
     * @param conn
     * @return
     */
    private static String resolveResponse(HttpURLConnection conn) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        try {
            conn.connect();
            inputStream = conn.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] bytes = new byte[1024 * 4];
            int len;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != conn) {
                conn.disconnect();
            }
        }
        return sb.toString();
    }

    /**
     * 判断集合内容不为空
     *
     * @param map map集合
     * @return 校验结果
     */
    private static boolean isNotEmpty(Map<?, ?> map) {
        return null != map && !map.isEmpty();
    }

    /**
     * 断言是http请求
     *
     * @param url 待校验请求地址
     */
    private static void assertHttpRequest(String url) {
        String regUrl = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$";
        Pattern pattern = Pattern.compile(regUrl);
        Matcher matcher = pattern.matcher(url);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("非法Url地址【%s】，请检查！", url));
        }
    }

    /**
     * http方法类
     */
    public enum HttpMethod {
        /**
         * get请求
         */
        GET("GET"),
        /**
         * post请求
         */
        POST("POST"),
        /**
         * head请求
         */
        HEAD("HEAD"),
        /**
         * options请求
         */
        OPTIONS("OPTIONS"),
        /**
         * trace请求
         */
        TRACE("TRACE"),
        /**
         * patch请求
         */
        PATCH("PATCH"),
        /**
         * delete请求
         */
        DELETE("DELETE"),
        /**
         * put请求
         */
        PUT("PUT");

        HttpMethod(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * http headers类
     */
    public static class HttpHeaders {
        /**
         * The HTTP {@code Accept} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-5.3.2">Section 5.3.2 of RFC 7231</a>
         */
        public static final String ACCEPT = "Accept";
        /**
         * The HTTP {@code Accept-Charset} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-5.3.3">Section 5.3.3 of RFC 7231</a>
         */
        public static final String ACCEPT_CHARSET = "Accept-Charset";
        /**
         * The HTTP {@code Accept-Encoding} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-5.3.4">Section 5.3.4 of RFC 7231</a>
         */
        public static final String ACCEPT_ENCODING = "Accept-Encoding";
        /**
         * The HTTP {@code Accept-Language} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-5.3.5">Section 5.3.5 of RFC 7231</a>
         */
        public static final String ACCEPT_LANGUAGE = "Accept-Language";
        /**
         * The HTTP {@code Accept-Patch} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc5789#section-3.1">Section 3.1 of RFC 5789</a>
         * @since 5.3.6
         */
        public static final String ACCEPT_PATCH = "Accept-Patch";
        /**
         * The HTTP {@code Accept-Ranges} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7233#section-2.3">Section 5.3.5 of RFC 7233</a>
         */
        public static final String ACCEPT_RANGES = "Accept-Ranges";
        /**
         * The CORS {@code Access-Control-Allow-Credentials} response header field name.
         *
         * @see <a href="https://www.w3.org/TR/cors/">CORS W3C recommendation</a>
         */
        public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
        /**
         * The CORS {@code Access-Control-Allow-Headers} response header field name.
         *
         * @see <a href="https://www.w3.org/TR/cors/">CORS W3C recommendation</a>
         */
        public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
        /**
         * The CORS {@code Access-Control-Allow-Methods} response header field name.
         *
         * @see <a href="https://www.w3.org/TR/cors/">CORS W3C recommendation</a>
         */
        public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
        /**
         * The CORS {@code Access-Control-Allow-Origin} response header field name.
         *
         * @see <a href="https://www.w3.org/TR/cors/">CORS W3C recommendation</a>
         */
        public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
        /**
         * The CORS {@code Access-Control-Expose-Headers} response header field name.
         *
         * @see <a href="https://www.w3.org/TR/cors/">CORS W3C recommendation</a>
         */
        public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
        /**
         * The CORS {@code Access-Control-Max-Age} response header field name.
         *
         * @see <a href="https://www.w3.org/TR/cors/">CORS W3C recommendation</a>
         */
        public static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
        /**
         * The CORS {@code Access-Control-Request-Headers} request header field name.
         *
         * @see <a href="https://www.w3.org/TR/cors/">CORS W3C recommendation</a>
         */
        public static final String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";
        /**
         * The CORS {@code Access-Control-Request-Method} request header field name.
         *
         * @see <a href="https://www.w3.org/TR/cors/">CORS W3C recommendation</a>
         */
        public static final String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";
        /**
         * The HTTP {@code Age} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7234#section-5.1">Section 5.1 of RFC 7234</a>
         */
        public static final String AGE = "Age";
        /**
         * The HTTP {@code Allow} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-7.4.1">Section 7.4.1 of RFC 7231</a>
         */
        public static final String ALLOW = "Allow";
        /**
         * The HTTP {@code Authorization} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7235#section-4.2">Section 4.2 of RFC 7235</a>
         */
        public static final String AUTHORIZATION = "Authorization";
        /**
         * The HTTP {@code Cache-Control} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7234#section-5.2">Section 5.2 of RFC 7234</a>
         */
        public static final String CACHE_CONTROL = "Cache-Control";
        /**
         * The HTTP {@code Connection} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7230#section-6.1">Section 6.1 of RFC 7230</a>
         */
        public static final String CONNECTION = "Connection";
        /**
         * The HTTP {@code Content-Encoding} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-3.1.2.2">Section 3.1.2.2 of RFC 7231</a>
         */
        public static final String CONTENT_ENCODING = "Content-Encoding";
        /**
         * The HTTP {@code Content-Disposition} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc6266">RFC 6266</a>
         */
        public static final String CONTENT_DISPOSITION = "Content-Disposition";
        /**
         * The HTTP {@code Content-Language} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-3.1.3.2">Section 3.1.3.2 of RFC 7231</a>
         */
        public static final String CONTENT_LANGUAGE = "Content-Language";
        /**
         * The HTTP {@code Content-Length} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7230#section-3.3.2">Section 3.3.2 of RFC 7230</a>
         */
        public static final String CONTENT_LENGTH = "Content-Length";
        /**
         * The HTTP {@code Content-Location} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-3.1.4.2">Section 3.1.4.2 of RFC 7231</a>
         */
        public static final String CONTENT_LOCATION = "Content-Location";
        /**
         * The HTTP {@code Content-Range} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7233#section-4.2">Section 4.2 of RFC 7233</a>
         */
        public static final String CONTENT_RANGE = "Content-Range";
        /**
         * The HTTP {@code Content-Type} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-3.1.1.5">Section 3.1.1.5 of RFC 7231</a>
         */
        public static final String CONTENT_TYPE = "Content-Type";
        /**
         * The HTTP {@code Cookie} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc2109#section-4.3.4">Section 4.3.4 of RFC 2109</a>
         */
        public static final String COOKIE = "Cookie";
        /**
         * The HTTP {@code Date} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-7.1.1.2">Section 7.1.1.2 of RFC 7231</a>
         */
        public static final String DATE = "Date";
        /**
         * The HTTP {@code ETag} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7232#section-2.3">Section 2.3 of RFC 7232</a>
         */
        public static final String ETAG = "ETag";
        /**
         * The HTTP {@code Expect} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-5.1.1">Section 5.1.1 of RFC 7231</a>
         */
        public static final String EXPECT = "Expect";
        /**
         * The HTTP {@code Expires} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7234#section-5.3">Section 5.3 of RFC 7234</a>
         */
        public static final String EXPIRES = "Expires";
        /**
         * The HTTP {@code From} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-5.5.1">Section 5.5.1 of RFC 7231</a>
         */
        public static final String FROM = "From";
        /**
         * The HTTP {@code Host} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7230#section-5.4">Section 5.4 of RFC 7230</a>
         */
        public static final String HOST = "Host";
        /**
         * The HTTP {@code If-Match} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7232#section-3.1">Section 3.1 of RFC 7232</a>
         */
        public static final String IF_MATCH = "If-Match";
        /**
         * The HTTP {@code If-Modified-Since} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7232#section-3.3">Section 3.3 of RFC 7232</a>
         */
        public static final String IF_MODIFIED_SINCE = "If-Modified-Since";
        /**
         * The HTTP {@code If-None-Match} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7232#section-3.2">Section 3.2 of RFC 7232</a>
         */
        public static final String IF_NONE_MATCH = "If-None-Match";
        /**
         * The HTTP {@code If-Range} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7233#section-3.2">Section 3.2 of RFC 7233</a>
         */
        public static final String IF_RANGE = "If-Range";
        /**
         * The HTTP {@code If-Unmodified-Since} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7232#section-3.4">Section 3.4 of RFC 7232</a>
         */
        public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
        /**
         * The HTTP {@code Last-Modified} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7232#section-2.2">Section 2.2 of RFC 7232</a>
         */
        public static final String LAST_MODIFIED = "Last-Modified";
        /**
         * The HTTP {@code Link} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc5988">RFC 5988</a>
         */
        public static final String LINK = "Link";
        /**
         * The HTTP {@code Location} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-7.1.2">Section 7.1.2 of RFC 7231</a>
         */
        public static final String LOCATION = "Location";
        /**
         * The HTTP {@code Max-Forwards} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-5.1.2">Section 5.1.2 of RFC 7231</a>
         */
        public static final String MAX_FORWARDS = "Max-Forwards";
        /**
         * The HTTP {@code Origin} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc6454">RFC 6454</a>
         */
        public static final String ORIGIN = "Origin";
        /**
         * The HTTP {@code Pragma} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7234#section-5.4">Section 5.4 of RFC 7234</a>
         */
        public static final String PRAGMA = "Pragma";
        /**
         * The HTTP {@code Proxy-Authenticate} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7235#section-4.3">Section 4.3 of RFC 7235</a>
         */
        public static final String PROXY_AUTHENTICATE = "Proxy-Authenticate";
        /**
         * The HTTP {@code Proxy-Authorization} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7235#section-4.4">Section 4.4 of RFC 7235</a>
         */
        public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
        /**
         * The HTTP {@code Range} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7233#section-3.1">Section 3.1 of RFC 7233</a>
         */
        public static final String RANGE = "Range";
        /**
         * The HTTP {@code Referer} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-5.5.2">Section 5.5.2 of RFC 7231</a>
         */
        public static final String REFERER = "Referer";
        /**
         * The HTTP {@code Retry-After} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-7.1.3">Section 7.1.3 of RFC 7231</a>
         */
        public static final String RETRY_AFTER = "Retry-After";
        /**
         * The HTTP {@code Server} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-7.4.2">Section 7.4.2 of RFC 7231</a>
         */
        public static final String SERVER = "Server";
        /**
         * The HTTP {@code Set-Cookie} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc2109#section-4.2.2">Section 4.2.2 of RFC 2109</a>
         */
        public static final String SET_COOKIE = "Set-Cookie";
        /**
         * The HTTP {@code Set-Cookie2} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc2965">RFC 2965</a>
         */
        public static final String SET_COOKIE2 = "Set-Cookie2";
        /**
         * The HTTP {@code TE} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7230#section-4.3">Section 4.3 of RFC 7230</a>
         */
        public static final String TE = "TE";
        /**
         * The HTTP {@code Trailer} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7230#section-4.4">Section 4.4 of RFC 7230</a>
         */
        public static final String TRAILER = "Trailer";
        /**
         * The HTTP {@code Transfer-Encoding} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7230#section-3.3.1">Section 3.3.1 of RFC 7230</a>
         */
        public static final String TRANSFER_ENCODING = "Transfer-Encoding";
        /**
         * The HTTP {@code Upgrade} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7230#section-6.7">Section 6.7 of RFC 7230</a>
         */
        public static final String UPGRADE = "Upgrade";
        /**
         * The HTTP {@code User-Agent} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-5.5.3">Section 5.5.3 of RFC 7231</a>
         */
        public static final String USER_AGENT = "User-Agent";
        /**
         * The HTTP {@code Vary} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7231#section-7.1.4">Section 7.1.4 of RFC 7231</a>
         */
        public static final String VARY = "Vary";
        /**
         * The HTTP {@code Via} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7230#section-5.7.1">Section 5.7.1 of RFC 7230</a>
         */
        public static final String VIA = "Via";
        /**
         * The HTTP {@code Warning} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7234#section-5.5">Section 5.5 of RFC 7234</a>
         */
        public static final String WARNING = "Warning";
        /**
         * The HTTP {@code WWW-Authenticate} header field name.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7235#section-4.1">Section 4.1 of RFC 7235</a>
         */
        public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
        /**
         * http bearer头
         */
        public static final String BEARER = "Bearer ";
    }

    /**
     * 媒体类型类
     */
    public static class MimeType {
        public static final String APPLICATION_JSON_VALUE = "application/json";
        public static final String APPLICATION_GRAPHQL_VALUE = "application/graphql+json";
        public static final String APPLICATION_OCTET_STREAM_VALUE = "application/octet-stream";
        public static final String APPLICATION_XML_VALUE = "application/xml";
        public static final String IMAGE_GIF_VALUE = "image/gif";
        public static final String IMAGE_JPEG_VALUE = "image/jpeg";
        public static final String IMAGE_PNG_VALUE = "image/png";
        public static final String TEXT_HTML_VALUE = "text/html";
        public static final String TEXT_PLAIN_VALUE = "text/plain";
        public static final String TEXT_XML_VALUE = "text/xml";
    }

    /**
     * 请求值
     */
    public static class HeaderValue {
        /**
         * 服务于文件上传时 Content-Type
         */
        public static final String MULTIPART_FORM_DATA_VALUE = "multipart/form-data;boundary=";
    }
}

package life.hopeurl.ac.constant;

/**
 * @author shiping.song
 * @date 2023/6/10 15:21
 */
public class PatternConstant {

    /**
     * 请求后缀
     */
    public static final String REQUEST_SUFFIX = "(\\.[a-z]+)?";

    /**
     * 请求参数
     */
    public static final String REQUEST_PARAMS = "(\\?[a-zA-Z]+=[\\da-zA-Z._%\\-,、\\u4E00-\\u9FA5]+(&[a-zA-Z]+=[\\da-zA-Z._%\\-,、\\u4E00-\\u9FA5]+)*)?";

    /**
     * 请求地址
     */
    public static final String REQUEST_URL = "(http|https)://(([\\da-zA-Z]+(\\.[\\da-zA-Z]+)+)|(([0-9]{1,3}\\.){3}[0-9]{1,3}))(:[0-9]{1,5})?(/[\\da-zA-Z\\-]+)*/?" + REQUEST_PARAMS + REQUEST_SUFFIX;
}

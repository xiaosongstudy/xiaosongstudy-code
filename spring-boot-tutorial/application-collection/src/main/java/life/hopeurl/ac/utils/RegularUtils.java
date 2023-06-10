package life.hopeurl.ac.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shiping.song
 * @date 2023/6/10 15:18
 */
public class RegularUtils {

    /**
     * 按照规则全匹配内容
     *
     * @param pattern 正则规则
     * @param content 待校验内容
     * @return 匹配结果
     * @date 2023/6/10 15:46
     */
    public static boolean fullMatch(String pattern, String content) {
        Matcher matcher = compile(pattern, content);
        return matcher.matches();
    }

    /**
     * 检索指定内容中符合规则的字符串
     *
     * @param pattern 正则表达式
     * @param content 待检索内容
     * @return 符合条件的字符串数组
     * @date 2023/6/10 18:29
     */
    public static List<String> list(String pattern, String content) {
        Matcher matcher = compile(pattern, content);
        List<String> resultList = new ArrayList<>();
        while (matcher.find()) {
            resultList.add(matcher.group());
        }
        return resultList;
    }


    /**
     * 编译正则表达式，获取matcher对象
     *
     * @param pattern 正则规则
     * @param content 待检索内容
     * @return matcher
     * @date 2023/6/10 15:44
     */
    public static Matcher compile(String pattern, String content) {
        Pattern currentPattern = Pattern.compile(pattern);
        return currentPattern.matcher(content);
    }
}

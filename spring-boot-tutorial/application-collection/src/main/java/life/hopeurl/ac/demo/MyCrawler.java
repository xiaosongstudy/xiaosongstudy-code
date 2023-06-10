package life.hopeurl.ac.demo;

import life.hopeurl.ac.constant.PatternConstant;
import life.hopeurl.ac.utils.RegularUtils;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class MyCrawler {
    public static void main(String[] args) {
        String content = "这里有个链接可以看看https://www.baidu.com，这是我的个人博客https://www.cnblogs.com/zhangyinhua/p/8037599.html";
        List<String> urls = RegularUtils.list(PatternConstant.REQUEST_URL, content);
        try {
            if (CollectionUtils.isNotEmpty(urls)) {
                for (String url : urls) {
                    Document doc = Jsoup.connect(url).get();
                    Elements titleElements = doc.getElementsByTag("title");
                    Element titleElement = titleElements.get(0);
                    content = content.replace(url, url + "[title:" + titleElement.text() + "]");
                }
            }
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

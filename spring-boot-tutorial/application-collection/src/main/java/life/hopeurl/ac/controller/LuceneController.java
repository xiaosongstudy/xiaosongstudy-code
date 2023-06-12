package life.hopeurl.ac.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * lucene控制器
 *
 * @author shiping.song
 * @date 2023/6/11 02:17
 */
@RequestMapping("/lucene")
@RestController
@CrossOrigin("*")
public class LuceneController {

    @GetMapping("/listAllDocuments")
    public String listAllDocuments() {
        JSONObject data = new JSONObject();
        data.put("user", "admin");
        return JSON.toJSONString(data);
    }
}

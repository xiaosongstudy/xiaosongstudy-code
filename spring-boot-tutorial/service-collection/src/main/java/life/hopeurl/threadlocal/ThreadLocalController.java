package life.hopeurl.threadlocal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * threadlocal测试controller
 *
 * @author shiping.song
 * @date 2023/7/28 21:14
 * @email 2453332538@qq.com
 */
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    private final ThreadLocal<String> nameStore = new ThreadLocal<>();

    @GetMapping("/register")
    public String register() {
        String name = Thread.currentThread().getName();
        nameStore.set("9999999");
        System.out.println("register" + name);
        return "register";
    }

    @GetMapping("/getName")
    public String getName() {
        System.out.println("getName"+ Thread.currentThread().getName()+"==>"+nameStore.get());
        return "getName";
    }


    @GetMapping("/exception")
    public String exception() {
        String name = Thread.currentThread().getName();
        nameStore.set(name);
        System.out.println("exception" + name);
        String a = null;
        System.out.println(a.toUpperCase());
        return "exception";
    }
}

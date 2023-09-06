package life.hopeurl.ac.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author shiping.song
 * @date 2023/8/28 23:44
 * @email 2453332538@qq.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demo implements Serializable {
    private static final long serialVersionUID = 5385289373863206429L;

    private Integer id;

    private String username;

    private LocalDate birthday;

    private List<Demo> childDemo;
}

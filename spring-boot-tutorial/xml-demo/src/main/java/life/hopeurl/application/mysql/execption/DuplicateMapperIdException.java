package life.hopeurl.application.mysql.execption;

/**
 * 重复的mapper id异常
 *
 * @author shiping.song
 * @date 2023/5/2 21:49
 */
public class DuplicateMapperIdException extends RuntimeException{
    private static final long serialVersionUID = -4951154321081518087L;

    public DuplicateMapperIdException(String message) {
        super(message);
    }
}

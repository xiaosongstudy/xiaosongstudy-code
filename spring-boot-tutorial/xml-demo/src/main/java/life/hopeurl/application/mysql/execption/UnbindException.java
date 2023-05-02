package life.hopeurl.application.mysql.execption;

/**
 * 未绑定异常
 * @author shiping.song
 * @date 2023/5/2 22:03
 */
public class UnbindException extends RuntimeException{
    private static final long serialVersionUID = -8824758352095466817L;

    public UnbindException(String message) {
        super(message);
    }
}

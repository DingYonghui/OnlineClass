package schoolstudy.service;

/**
 * 登录与注册的类
 */
public class ServiceException extends Exception {
    public ServiceException(String error){
        super(error);
    }
}

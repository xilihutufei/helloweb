package pre.fei.service;

public interface IUserService {
    /**
     * 登录
     */
    boolean login(String userName, String password);


    /**
     * 注册
     */
    boolean regist(String userName, String password);

    /**
     * 获取用户图片
     * @param name
     * @return
     */
    String getPicByUsername(String name);
}

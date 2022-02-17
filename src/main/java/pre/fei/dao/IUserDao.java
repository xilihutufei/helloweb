package pre.fei.dao;

import pre.fei.vo.Result;

public interface IUserDao {

    Result addUser(String userName, String password);

    Result del(String userName, String password);

    String get(String userName);

    Result modifyUser(String userName, String password);

}

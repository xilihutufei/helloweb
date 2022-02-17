package pre.fei.dao.impl;

import org.springframework.stereotype.Repository;
import pre.fei.dao.IUserDao;
import pre.fei.enums.ResultEnum;
import pre.fei.util.RedisUtil;
import pre.fei.vo.Result;



@Repository
public class UserDaoImpl implements IUserDao {

    @Override
    public Result addUser(String userName, String password) {

        String string = RedisUtil.getString(userName);
        if (string != null && string.length() > 0){
            return Result.ofFail(ResultEnum.USER_EXIST);
        }
        if (RedisUtil.setString(userName, password)) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultEnum.SYSTEM_ERROR);

    }

    @Override
    public Result del(String userName, String password) {
        return null;
    }

    @Override
    public String get(String userName) {
        return RedisUtil.getString(userName);
    }

    @Override
    public Result modifyUser(String userName, String password) {
        return null;
    }
}

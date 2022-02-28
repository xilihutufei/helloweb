package pre.fei.dao.impl;

import com.google.gson.internal.$Gson$Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pre.fei.dao.IUserDao;
import pre.fei.enums.ResultEnum;
import pre.fei.util.RedisUtil;
import pre.fei.vo.Result;



@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Result addUser(String userName, String password) {

        String string = redisUtil.getString(userName);
        if (string != null && string.length() > 0){
            return Result.ofFail(ResultEnum.USER_EXIST);
        }
        if (redisUtil.setString(userName, password)) {
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
        return redisUtil.getString(userName);
    }

    @Override
    public Result modifyUser(String userName, String password) {
        return null;
    }
}

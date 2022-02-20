package pre.fei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.fei.dao.IUserDao;
import pre.fei.service.IUserService;
import pre.fei.vo.IConst;
import pre.fei.vo.Result;

import java.util.Objects;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public boolean login(String userName, String password) {
        String passwd = userDao.get(userName);
        if (passwd == null || passwd.length() == 0){
            return false;
        }
        return Objects.equals(passwd, password);

    }

    @Override
    public boolean regist(String userName, String password) {
        return false;
    }

    @Override
    public String getPicByUsername(String name) {
        return userDao.get(IConst.PIC + name);
    }
}

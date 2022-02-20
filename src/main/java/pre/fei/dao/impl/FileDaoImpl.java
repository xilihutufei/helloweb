package pre.fei.dao.impl;

import org.springframework.stereotype.Repository;
import pre.fei.dao.IFileDao;
import pre.fei.util.ParamUtil;
import pre.fei.util.RedisUtil;
import pre.fei.vo.IConst;
import pre.fei.vo.Result;


@Repository
public class FileDaoImpl implements IFileDao {

    @Override
    public Result setPic(String userName, String pic) {
        System.out.println("文件上传=========== userName = " + userName + ", pic = " + pic);
        if (pic == null || pic.length() == 0){
            return Result.ofFail("图片为空，保存失败");
        }
        String oldPic = getPic(userName);
        if (oldPic == null || oldPic.length() == 0){
            RedisUtil.setString(getPicKey(userName), pic);
        }else {
            RedisUtil.setString(getPicKey(userName), pic + ";" + oldPic);
        }
        return Result.ofSuccess();
    }

    @Override
    public String getPic(String userName) {
        return RedisUtil.getString(getPicKey(userName));
    }


    private String getPicKey(String userName){
        return IConst.PIC + userName;
    }
}

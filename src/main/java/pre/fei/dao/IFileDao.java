package pre.fei.dao;

import pre.fei.vo.Result;

public interface IFileDao {

    Result setPic(String userName, String pic);

    String getPic(String userName);

}

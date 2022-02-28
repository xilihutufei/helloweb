package pre.fei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pre.fei.config.EvmConfig;
import pre.fei.dao.IFileDao;
import pre.fei.service.IFileService;
import pre.fei.vo.IConst;
import pre.fei.vo.Result;

import java.io.File;
import java.util.UUID;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private IFileDao fileDao;

    @Autowired
    private EvmConfig config;

    @Override
    public Result uploadFile(String userName, MultipartFile file) {

        //不可以上传空文件
        if(file.isEmpty()) {
            return Result.ofFail("文件为空，重新选择噢~~~");
        }

        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        String uuidName = UUID.randomUUID() + suffix;
        filename = config.getImgPath() + uuidName;
        File dest = new File(filename);

        //若文件已存在则不执行保存操作
        try {
            if(!dest.exists()) {
                file.transferTo(dest);
            }
        } catch (Exception e) {
            return Result.ofFail("上传失败");
        }
        // 将上传的文件保存到redis中
        fileDao.setPic(userName, config.getShowImgPath()+uuidName);
        return Result.ofSuccess();
    }
}

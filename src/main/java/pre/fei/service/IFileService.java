package pre.fei.service;

import org.springframework.web.multipart.MultipartFile;
import pre.fei.vo.Result;

public interface IFileService {

    Result uploadFile(String userName, MultipartFile file);

}

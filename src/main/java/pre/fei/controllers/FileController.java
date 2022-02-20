package pre.fei.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pre.fei.service.IFileService;
import pre.fei.util.ParamUtil;
import pre.fei.vo.IConst;
import pre.fei.vo.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    private IFileService fileService;

    @ResponseBody
    @RequestMapping(value = "/upload", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        String user = ParamUtil.getUser(request);
        if (user == null || user.length() == 0){
            return "请先登录~~~";
        }

        Result result = fileService.uploadFile(user, file);
        if (result.getCode() == 0){
            return "上传成功";
        }else {
            return result == null ? "上传失败" : result.getMsg();
        }
    }

}

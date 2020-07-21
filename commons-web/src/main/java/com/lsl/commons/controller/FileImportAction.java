package com.lsl.commons.controller;

import java.io.*;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.lsl.commons.dao.UserDao;

@RequestMapping("/import")
@Controller
public class FileImportAction {
    public static String fileRoot = "D:\\";
    private static Logger logger = LoggerFactory.getLogger(FileImportAction.class);

    @RequestMapping(value = "/selectPtp", method = {RequestMethod.GET})
    public @ResponseBody Map selectPtp() {
        return Maps.newHashMap();
    }

    @RequestMapping(value = "/upload", method = {RequestMethod.POST, RequestMethod.GET})
    public String upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileParam = request.getParameter("username");
        StandardMultipartHttpServletRequest multipartRequest = (StandardMultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (MultipartFile file : fileMap.values()) {
            String fileName = file.getOriginalFilename();
            FileOutputStream fos = new FileOutputStream(new File(fileRoot + fileName));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
            byte[] bytes = new byte[1024];
            while (bis.read(bytes) != -1) {
                bos.write(bytes);
            }
            bos.close();
            bis.close();
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("上传" + fileMap.size() + "个文件成功!");
        return null;
    }

    @RequestMapping(value = "/uploadmulti", method = {RequestMethod.POST})
    public @ResponseBody String uploadmulti(@RequestParam("username") String username,
        @RequestParam("password") String password, HttpServletRequest request, @RequestParam("file") MultipartFile file)
        throws IOException {
        String originalFilename = file.getOriginalFilename();
        return username + "@" + password + ":" + originalFilename;
    }
}

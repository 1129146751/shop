package com.fx.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import com.fx.shop.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
@Slf4j
@Service
@Transactional
public class FileServiceImpl implements FileService {
    @Value("${file.path}")
    private String path;
    /**
     * 文件上传
     * @param file
     */
    @Override
    public String upload(MultipartFile file) throws IOException {
            String end = file.getOriginalFilename();
            int i = end.lastIndexOf(".");
            String fileName = DateUtil.format(new Date(), "yyyyMMddHHmmss") + end.substring(i);
            String newUrl = path + File.separator + fileName;
            log.info("--------------文件生成路径"+newUrl);
            file.transferTo(new File(newUrl));
            return newUrl;
    }
}

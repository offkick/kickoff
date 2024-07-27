package com.kickoff.admin.service.upload;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class LocalPlayerImageUploadServiceImpl implements PlayerImageUploadService{
    @Value("${upload.image.player}")
    private String playerImagePath;

    @Override
    public String upload(MultipartFile multipartFile) throws Exception{
        String fileName = multipartFile.getOriginalFilename();

        if(fileName == null || fileName.isBlank())
        {
            throw new RuntimeException();
        }

        int index = fileName.lastIndexOf(".");
        String fileExtension = fileName.substring(index);
        String imgType = fileName.substring(index+1).toLowerCase();

        if(!Sets.newHashSet("jpg", "jpeg", "png").contains(imgType))
        {
            throw new RuntimeException();
        }

        String savePath = UUID.randomUUID() +fileExtension;

        Path path = Paths.get(playerImagePath + savePath).toAbsolutePath();
        multipartFile.transferTo(path.toFile());
        return savePath;
    }
}

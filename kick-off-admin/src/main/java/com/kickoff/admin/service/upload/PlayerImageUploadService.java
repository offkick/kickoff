package com.kickoff.admin.service.upload;

import org.springframework.web.multipart.MultipartFile;


public interface PlayerImageUploadService {
    String upload(MultipartFile multipartFile) throws Exception;
}

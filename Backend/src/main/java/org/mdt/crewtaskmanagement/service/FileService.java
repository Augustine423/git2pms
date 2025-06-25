package org.mdt.crewtaskmanagement.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {

    public String upload(MultipartFile file);
}

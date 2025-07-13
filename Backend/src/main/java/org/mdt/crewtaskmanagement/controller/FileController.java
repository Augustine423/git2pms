package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.service.IFileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final IFileService fileService;

    @PostMapping("/upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        fileService.upload(file);
        return "success";
    }
}

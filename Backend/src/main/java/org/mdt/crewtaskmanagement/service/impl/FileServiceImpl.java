package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mdt.crewtaskmanagement.model.system.Component;
import org.mdt.crewtaskmanagement.model.system.MachGroup;
import org.mdt.crewtaskmanagement.model.system.Machinery;
import org.mdt.crewtaskmanagement.repository.entity.system.ComponentRepository;
import org.mdt.crewtaskmanagement.repository.entity.system.MachGroupRepository;
import org.mdt.crewtaskmanagement.repository.entity.system.MachineryRepository;
import org.mdt.crewtaskmanagement.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
@Service
@RequiredArgsConstructor
@Transactional
public class FileServiceImpl implements FileService {
    private final MachGroupRepository machGroupRepository;
    private final MachineryRepository machineryRepository;
    private final ComponentRepository componentRepository;

    @Override
    public String upload(MultipartFile file) {
            try (InputStream is = file.getInputStream()) {
                Workbook workbook;
                if (file.getOriginalFilename().endsWith(".xlsx")) {
                    workbook = new XSSFWorkbook(is);
                } else if (file.getOriginalFilename().endsWith(".xls")) {
                    workbook = new HSSFWorkbook(is);
                } else {
                    throw new IllegalArgumentException("Unsupported file type");
                }

                Sheet sheet = workbook.getSheetAt(0);

                for (Row row : sheet) {
                    if (row.getRowNum() == 0) continue; // skip header

                    String machGroupName = row.getCell(0).getStringCellValue().trim();
                    String machineryName = row.getCell(1).getStringCellValue().trim();
                    String componentName = row.getCell(2).getStringCellValue().trim();

                    MachGroup machGroup = machGroupRepository
                            .findByMachGroupName(machGroupName)
                            .orElseGet(() -> new MachGroup(machGroupName));


                    Machinery machinery = machineryRepository
                            .findByMachineryName(machineryName)
                            .orElseGet(() -> new Machinery(machineryName));

                    Component component = componentRepository
                            .findByComponentName(componentName)
                            .orElseGet(() -> new Component(componentName));

                    machinery.addComponent(component);
                    machGroup.addMachinery(machinery);

                    machGroupRepository.saveAndFlush(machGroup); // cascade saves all children
                }

                return "ok";
            } catch (IOException e) {
                e.printStackTrace(); // good for debugging
                return "error";
            }


    }
}

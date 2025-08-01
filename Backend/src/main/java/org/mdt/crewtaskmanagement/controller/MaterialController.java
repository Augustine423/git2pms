package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.dto.material.MaterialForRequestDto;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.service.impl.MaterialServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mdt/material")
public class MaterialController {
    private final MaterialServiceImpl materialService;

    @PostMapping("/register")
    public ResponseEntity<MaterialDto> registerMaterial(@RequestBody MaterialDto materialDto) {
        System.out.println(materialDto + "material registering`");
        return ResponseEntity.ok(materialService.registerMaterial(materialDto));
    }

    @PostMapping("/update")
    public ResponseEntity<MaterialDto> updateMaterial(@RequestBody MaterialDto materialDto) {
        return ResponseEntity.ok(materialService.updateMaterial(materialDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDto> getMaterialById(@PathVariable("id") long id) {
        return ResponseEntity.ok(materialService.getMaterialById(id));
    }

    @GetMapping("/all")
    public PageResult<MaterialDto> getAllMaterials(@RequestParam(defaultValue = "0",required = false) int page,
                                                   @RequestParam(defaultValue = "10",required = false) int size) {
        return materialService.getAllMaterials(page,size);
    }

    @GetMapping("/for-request")
    public PageResult<MaterialForRequestDto>  getMaterialsForRequest(@RequestParam(defaultValue = "0",required = false) int page,
                                                                               @RequestParam(defaultValue = "10",required = false) int size
    ){
        return materialService.findMaterialForRequest(page,size);
    }

    @GetMapping("/from-request/{reportRequestId}")
    public PageResult<MaterialForRequestDto> getMaterialsFromRequest(@PathVariable long reportRequestId,
                                                                               @RequestParam(defaultValue = "0",required = false) int page,
                                                                               @RequestParam(defaultValue = "10",required = false) int size){
        return materialService.getAllMaterialsFormReportRequest(reportRequestId,page,size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMaterialById(@PathVariable("id") long id) {
        materialService.deleteMaterialById(id);
        return ResponseEntity.ok("Deleted material with id " + id);
    }

}

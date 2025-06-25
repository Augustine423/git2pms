package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.dto.material.MaterialForRequestDto;
import org.mdt.crewtaskmanagement.service.MaterialService;
import org.mdt.crewtaskmanagement.service.impl.MaterialServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mdt/material")
public class MaterialController {
    private final MaterialServiceImpl materialService;
    @GetMapping
    public String hello(){
        return "Hello Worlddfksadfkj;a ";
    }

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

    @GetMapping("/")
    public ResponseEntity<List<MaterialDto>> getAllMaterials() {
        return ResponseEntity.ok(materialService.getAllMaterials());
    }
    @GetMapping("/for-request")
    public ResponseEntity<List<MaterialForRequestDto>>  getMaterialsForRequest(){
        return ResponseEntity.ok(materialService.findMaterialForRequest());
    }
    @GetMapping("/from-request/{reportRequestId}")
    public ResponseEntity<List<MaterialForRequestDto>> getMaterialsFromRequest(@PathVariable long reportRequestId){
        return ResponseEntity.ok(materialService.getAllMaterialsFromReportRequest(reportRequestId));
    }


//    @PutMapping("/add-quantity/{materialId}/{quantity}")
//    public ResponseEntity<String> addMaterial(@PathVariable long materialId, @PathVariable int quantity) {
//        return ResponseEntity.ok(materialService.addMaterial(materialId, quantity));
//    }
//    @PutMapping("/reduce-quantity/{materialId}/{quantity}")
//    public ResponseEntity<String> reduceMaterial(@PathVariable long materialId, @PathVariable int quantity) {
//        return ResponseEntity.ok(materialService.reduceQuantity(materialId, quantity));
//    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMaterialById(@PathVariable("id") long id) {
        materialService.deleteMaterialById(id);
        return ResponseEntity.ok("Deleted material with id " + id);
    }
}

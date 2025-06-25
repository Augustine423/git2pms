package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.dto.material.MaterialForRequestDto;
import org.mdt.crewtaskmanagement.mapper.MaterialMapper;
import org.mdt.crewtaskmanagement.model.Material;
import org.mdt.crewtaskmanagement.repository.entity.MaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MaterialServiceImpl  {
    private final MaterialRepository materialRepository;

    public MaterialDto registerMaterial(MaterialDto dto){
        Material material = MaterialMapper.fromDto(dto);
        materialRepository.save(material);
        return MaterialMapper.toDto(material);
    }

    public MaterialDto updateMaterial(MaterialDto dto){
        Material material = MaterialMapper.fromDto(dto);
        material.setId(dto.getId());
        materialRepository.save(material);
        return MaterialMapper.toDto(material);
    }

    public List<MaterialForRequestDto> getAllMaterialsFromReportRequest(long reportRequestId){
       return materialRepository.findAllMaterialsFromRequest(reportRequestId);
    }

    public List<MaterialForRequestDto> findMaterialForRequest(){
        List<MaterialForRequestDto>  materials = materialRepository.findAllMaterialsForRequest();
        return materials;
    }

    public MaterialDto getMaterialById(long id){
        return MaterialMapper.toDto( materialRepository.findById(id).orElseThrow());
    }
    public List<MaterialDto> getAllMaterials(){
        return materialRepository.findAll().stream().map(MaterialMapper::toDto).collect(Collectors.toList());
    }

//    public String addMaterial(long materialId, int quantity){
//        Material material = materialRepository.findById(materialId).orElseThrow();
//        int newQuantity = quantity + material.getQuantity();
//        material.setQuantity(newQuantity);
//        materialRepository.save(material);
//         var r =quantity + material.getName()  + "are added  .. so final quantity is " + newQuantity;
//        System.out.println(r);
//        return r;
//    }
//    public String reduceQuantity(long materialId, int quantity){
//        Material material = materialRepository.findById(materialId).orElseThrow();
//        int newQuantity = material.getQuantity() - quantity;
//        BigDecimal d = new BigDecimal(newQuantity);
//        material.setQuantity(newQuantity);
//        materialRepository.save(material);
//        var r =  quantity + material.getName()  + "are reduced .. so final quantity is " + newQuantity;
//        System.out.println(r);
//        return r;
//    }

    public void deleteMaterialById(long id){
        materialRepository.deleteById(id);
    }


}

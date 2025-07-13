package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.dto.material.MaterialForRequestDto;
import org.mdt.crewtaskmanagement.mapper.MaterialMapper;
import org.mdt.crewtaskmanagement.model.Material;
import org.mdt.crewtaskmanagement.model.MaterialReportRequest;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.repository.entity.MaterialReportRequestRepository;
import org.mdt.crewtaskmanagement.repository.entity.MaterialRepository;
import org.mdt.crewtaskmanagement.service.IMaterialService;
import org.mdt.crewtaskmanagement.service.IShipService;
import org.mdt.crewtaskmanagement.utils.GetEntitesWithPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MaterialServiceImpl implements IMaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialReportRequestRepository materialReportRequestRepository;
    private final IShipService shipService;

    public MaterialDto registerMaterial(MaterialDto dto) {
        Material material = MaterialMapper.fromDto(dto);
        material.setShip(shipService.getById(dto.getShipId()));
        materialRepository.save(material);
        return MaterialMapper.toDto(material);
    }

    public MaterialDto updateMaterial(MaterialDto dto) {
        Material material = MaterialMapper.fromDto(dto);
        material.setId(dto.getId());
        materialRepository.save(material);
        return MaterialMapper.toDto(material);
    }

    public MaterialDto getMaterialById(long id) {
        return MaterialMapper.toDto(materialRepository.findById(id).orElseThrow());
    }

    public PageResult<MaterialDto> getAllMaterials(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Material> materials = materialRepository.findAll(pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable, materials, MaterialMapper::toDto);
    }

    public PageResult<MaterialForRequestDto> findMaterialForRequest(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MaterialForRequestDto> materials = materialRepository.findAllMaterialsForRequest(pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable, materials, null);
    }

    public List<Material> findByMaterialName(String name, int quantity) {
        Pageable limit = PageRequest.of(0, quantity);
        return materialRepository.findAvailableByName(name, limit);
    }

    public void deleteMaterialById(long id) {
        materialRepository.deleteById(id);
    }

    @Override
    public void saveMaterialReportRequest(MaterialReportRequest materialReportRequest) {
        materialReportRequestRepository.save(materialReportRequest);
    }

    @Override
    public PageResult<MaterialForRequestDto> getAllMaterialsFormReportRequest(long reportRequestId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MaterialForRequestDto> materials = materialRepository.findAllMaterialsFromRequest(reportRequestId, pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable, materials, null);
    }


}

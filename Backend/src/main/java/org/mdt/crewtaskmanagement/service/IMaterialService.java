package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.dto.material.MaterialForRequestDto;
import org.mdt.crewtaskmanagement.model.Material;
import org.mdt.crewtaskmanagement.model.MaterialReportRequest;
import org.mdt.crewtaskmanagement.output.PageResult;

import java.util.List;

public interface IMaterialService {
    MaterialDto registerMaterial(MaterialDto materialDto);
    MaterialDto updateMaterial(MaterialDto materialDto);
    MaterialDto getMaterialById(long id);
    PageResult<MaterialDto> getAllMaterials(int page, int size);
    void deleteMaterialById(long id);
    List<Material> findByMaterialName(String materialName,int quantity);
    void saveMaterialReportRequest(MaterialReportRequest materialReportRequest);
    PageResult<MaterialForRequestDto> getAllMaterialsFormReportRequest(long reportRequestId,int page, int size);
}

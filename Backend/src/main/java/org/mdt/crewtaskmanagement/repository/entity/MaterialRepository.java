package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.dto.material.MaterialForRequestDto;
import org.mdt.crewtaskmanagement.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long> {
//
//    @Query("SELECT m FROM material" +
//            "ORDER BY some_column\n" +
//            "LIMIT 2;\n")
    @Query("select m from Material  m where m.name = :materialName order by m.id limit :quantity")
    Optional<List<Material>> findByMaterialName(@Param("materialName")String materialName, @Param("quantity") int quantity);

    @Query("""
    SELECT new org.mdt.crewtaskmanagement.dto.material.MaterialForRequestDto(
        MIN(m.id),          
        m.name,
        MIN(m.type),
        MIN(m.price),
        MIN(m.condition),
        MIN(m.receivedDate),
        MIN(m.lifeTime),
        COUNT(m)
    )
    FROM Material m
    WHERE m.useStatus = false
    GROUP BY m.name
""")
    List<MaterialForRequestDto> findAllMaterialsForRequest();

    @Query("""
    SELECT new org.mdt.crewtaskmanagement.dto.material.MaterialForRequestDto(
        MIN(mrr.material.id),          
        mrr.material.name,
        MIN(mrr.material.type),
        MIN(mrr.material.price),
        MIN(mrr.material.condition),
        MIN(mrr.material.receivedDate),
        MIN(mrr.material.lifeTime),
        COUNT(mrr.material)
    )
    FROM MaterialReportRequest mrr
    
    WHERE mrr.reportRequest.id = :reportRequestId
    GROUP BY mrr.material.name
""")
    List<MaterialForRequestDto> findAllMaterialsFromRequest(@Param("reportRequestId")Long reportRequestId);

    List<Material> findAllByUseStatusFalseAndName(String name);







}

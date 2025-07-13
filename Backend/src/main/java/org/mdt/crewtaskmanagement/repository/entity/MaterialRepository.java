package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.dto.material.MaterialForRequestDto;
import org.mdt.crewtaskmanagement.model.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    /**
     * Fetch a limited number of AVAILABLE materials with the given name.
     */
    @Query("""
        SELECT m FROM Material m 
        WHERE m.name = :materialName AND m.status = org.mdt.crewtaskmanagement.model.Material.MaterialStatus.AVAILABLE
        ORDER BY m.id
    """)
    List<Material> findAvailableByName(@Param("materialName") String materialName, Pageable pageable);

    /**
     * Group available materials for request page (summary view).
     */
    @Query("""
        SELECT new org.mdt.crewtaskmanagement.dto.material.MaterialForRequestDto(
            MIN(m.id),
            m.name,
            MIN(m.type),
            MIN(m.price),
            MIN(m.condition),
            MIN(m.receivedDate),
            COUNT(m)
        )
        FROM Material m
        WHERE m.status = org.mdt.crewtaskmanagement.model.Material.MaterialStatus.AVAILABLE
        GROUP BY m.name
    """)
    Page<MaterialForRequestDto> findAllMaterialsForRequest(Pageable pageable);

    /**
     * Get grouped materials from a report request.
     */
    @Query("""
        SELECT new org.mdt.crewtaskmanagement.dto.material.MaterialForRequestDto(
            MIN(mrr.material.id),
            mrr.material.name,
            MIN(mrr.material.type),
            MIN(mrr.material.price),
            MIN(mrr.material.condition),
            MIN(mrr.material.receivedDate),
            COUNT(mrr.material)
        )
        FROM MaterialReportRequest mrr
        WHERE mrr.reportRequest.id = :reportRequestId
        GROUP BY mrr.material.name
    """)
    Page<MaterialForRequestDto> findAllMaterialsFromRequest(@Param("reportRequestId") Long reportRequestId, Pageable pageable);

    /**
     * Find all materials by status and name.
     */
    List<Material> findAllByStatusAndName(Material.MaterialStatus status, String name);

    /**
     * Default pagination support for materials.
     */
    Page<Material> findAll(Pageable pageable);

    @Query("""
    SELECT m FROM Material m 
    WHERE m.name = :materialName AND m.status = org.mdt.crewtaskmanagement.model.Material.MaterialStatus.AVAILABLE
    ORDER BY m.id
""")
    List<Material> findByMaterialName(@Param("materialName") String materialName, Pageable pageable);

}

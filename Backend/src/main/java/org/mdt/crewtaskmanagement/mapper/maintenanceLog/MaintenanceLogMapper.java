package org.mdt.crewtaskmanagement.mapper.maintenanceLog;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.maintenancelog.MaintenanceLogDto;
import org.mdt.crewtaskmanagement.mapper.taskCompletion.TaskCompletionImageMapper;
import org.mdt.crewtaskmanagement.mapper.taskCompletion.TaskMaterialUsedMapper;
import org.mdt.crewtaskmanagement.model.*;
import org.mdt.crewtaskmanagement.repository.entity.TaskRepository;
import org.mdt.crewtaskmanagement.service.ICrewService;
import org.mdt.crewtaskmanagement.service.ITaskService;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MaintenanceLogMapper {




    public static  MaintenanceLog fromDto(MaintenanceLogDto dto, TaskAssignment taskAssignment, Crew verifyBy) {

        var maintenanceLog =  MaintenanceLog.builder()

                .taskAssignment(taskAssignment)
                .verifiedBy(verifyBy)
                .nextDue(LocalDate.parse(dto.getNextDue().substring(0, 10)))
                .remarks(dto.getRemarks())
                .lastWork(LocalDate.parse(dto.getLastWork()))
                .loggedAt(LocalDate.now())
                .build();

                if(dto.getTaskCompletionImages() != null) {
                    maintenanceLog.addImages(
                            dto.getTaskCompletionImages().stream()
                                    .map(img -> TaskCompletionImageMapper.fromDto(img))
                                    .collect(Collectors.toUnmodifiableList())
                    );
                }
                if(dto.getTaskMaterialUsed() != null) {
                    maintenanceLog.addMaterialsUsed(
                            dto.getTaskMaterialUsed().stream()
                                    .map(material -> TaskMaterialUsedMapper.fromDto(material))
                                    .collect(Collectors.toUnmodifiableList())
                    );
                }
                return maintenanceLog;
    }

    public MaintenanceLogDto toDto(MaintenanceLog log) {


        return null;
    }
}

package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.crew.CrewDto;
import org.mdt.crewtaskmanagement.dto.crewAssignment.CrewAssignmentDto;
import org.mdt.crewtaskmanagement.exception.ServiceBaseException;
import org.mdt.crewtaskmanagement.param.CrewParam;
import org.mdt.crewtaskmanagement.service.CrewService;
import org.mdt.crewtaskmanagement.service.impl.CrewAssignmentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mdt/crew")
public class CrewController {
    private final CrewService crewService;
    private final CrewAssignmentServiceImpl crewAssignmentService;

    @PostMapping("/register")
    public ResponseEntity<CrewDto> registerCrew(@RequestBody CrewDto crewDto) throws ServiceBaseException {
        return ResponseEntity.ok(crewService.registerCrew(crewDto));
    }

    @PostMapping("/update")
    public ResponseEntity<CrewDto> updateCrew(@RequestBody CrewDto crewDto) {
        return ResponseEntity.ok(crewService.updateCrew(crewDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrewDto> getCrewById(@PathVariable("id") long id) {

        return ResponseEntity.ok(crewService.getCrewById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<CrewDto>> getAllCrews() {
        return ResponseEntity.ok(crewService.getAllCrews());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCrewById(@PathVariable("id") long id) {
        crewService.deleteCrew(id);
        return ResponseEntity.ok("Deleted crew with id " + id);
    }
    @PostMapping("/assign-crew")
    public ResponseEntity<CrewAssignmentDto> createCrewAssignment(@RequestBody CrewAssignmentDto dto) {
        System.out.println(dto.getCrewId() + " fdfdf" + dto.getShipId());
        return ResponseEntity.ok(crewAssignmentService.addCrewAssignment(dto));
    }
    @GetMapping("/get-crews-for-assignment")
    public ResponseEntity<List<CrewDto>> getAllCrewForAssignments() {
        return ResponseEntity.ok(crewService.getCrewsForAssignments());
    }
    @GetMapping("/ship/{shipId}")
    public ResponseEntity<List<CrewDto>> getCrewsByShipId(@PathVariable long shipId) {
        return ResponseEntity.ok(crewService.getAllCrewsByShipId(shipId));
    }
    @GetMapping("/get-all-crew-count")
    public ResponseEntity<Integer> getAllCrewCount(){
        return ResponseEntity.ok(crewService.getAllCrewsCount());
    }

    @GetMapping("/get-all-active-crew-count")
    public ResponseEntity<Integer> getAllActiveCrewCount(){
        return ResponseEntity.ok(crewService.getAllActiveCrewCount());
    }


    @GetMapping("/search")
    public List<CrewDto> search(@RequestParam(required = false) String firstname,
                                @RequestParam(required = false) String lastName,
                                @RequestParam(required = false) String crewRank) {
        CrewParam param = new CrewParam(firstname, lastName, crewRank);
        return crewService.search(param);
    }


//    @GetMapping("/company/{companyId}")
//    public ResponseEntity<List<CrewDto>> getCrewsByCompanyId(@PathVariable("companyId") long companyId) {
//        return ResponseEntity.ok(crewService.getCrewsByCompanyId(companyId));
//    }

}

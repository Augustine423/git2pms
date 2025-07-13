package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.mdt.crewtaskmanagement.dto.crew.CrewDto;
import org.mdt.crewtaskmanagement.dto.crewAssignment.CrewAssignmentDto;
import org.mdt.crewtaskmanagement.exception.ServiceBaseException;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.param.CrewParam;
import org.mdt.crewtaskmanagement.service.ICrewService;
import org.mdt.crewtaskmanagement.service.impl.CrewAssignmentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mdt/crew")
public class CrewController {
    private final ICrewService crewService;
    private final CrewAssignmentServiceImpl crewAssignmentService;

    @PostMapping("/register")
    public ResponseEntity<CrewDto> registerCrew(@RequestBody CrewDto crewDto) throws ServiceBaseException {
        return ResponseEntity.ok(crewService.registerCrew(crewDto));
    }

    @PostMapping("/update")
    public ResponseEntity<CrewDto> updateCrew(@RequestBody CrewDto crewDto) {
        return ResponseEntity.ok(crewService.updateCrew(crewDto));
    }

    @PostMapping("/assign-crew-to-ship")
    public ResponseEntity<CrewAssignmentDto> assignCrewToShip(@RequestBody CrewAssignmentDto crewDto) {
        return ResponseEntity.ok(crewService.addCrewAssignment(crewDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrewDto> getCrewById(@PathVariable("id") long id) {
        return ResponseEntity.ok(crewService.getCrewById(id));
    }

    @GetMapping("/all")
    public PageResult<CrewDto> getAllCrews(@RequestParam(defaultValue = "0",required = false) int page,
                                      @RequestParam(defaultValue = "10",required = false) int size
    ) {
        return crewService.getAllCrews(page,size);
    }
    @GetMapping("/rank/{rank}")
    public PageResult<CrewDto> getAllCrewsWithPosition(@RequestParam(defaultValue = "0",required = false) int page,
                                                                @RequestParam(defaultValue = "10",required = false) int size,
                                                              @PathVariable("rank") String rank
    ) {
        return crewService.getCrewsWithPosition(rank,page,size);
    }

    @GetMapping("/rank/{rank}/{shipId}")
    public PageResult<CrewDto> getAllCrewsWithPositionByShipId(@RequestParam(defaultValue = "0",required = false) int page,
                                                                     @RequestParam(defaultValue = "10",required = false) int size,
                                                                      @PathVariable long shipId,@PathVariable("rank") String rank
    ) {
        return crewService.getAllCrewsWithPositionByShipId(shipId,rank,page,size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCrewById(@PathVariable("id") long id) {
        crewService.deleteCrew(id);
        return ResponseEntity.ok("Deleted crew with id " + id);
    }
    @PostMapping("/assign-crew")
    public ResponseEntity<CrewAssignmentDto> createCrewAssignment(@RequestBody CrewAssignmentDto dto) {
        return ResponseEntity.ok(crewAssignmentService.createCrewAssignment(dto));
    }
    @GetMapping("/get-crews-for-assignment")
    public PageResult<CrewDto> getAllAvailableCrews( @RequestParam(defaultValue = "0",required = false) int page,
                                                     @RequestParam(defaultValue = "10",required = false) int size) {
        return crewService.getCrewsForAssignments(page,size);
    }

    @GetMapping("/ship/{shipId}")
    public PageResult<CrewDto> getCrewsByShipId(@PathVariable long shipId,
                                                @RequestParam(defaultValue = "0",required = false) int page,
                                                @RequestParam(defaultValue = "10",required = false) int size) {
        return crewService.getAllCrewsByShipId(shipId,page,size );
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

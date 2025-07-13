package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.ship.ShipDto;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.service.impl.ShipServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mdt/ship")
public class ShipController {
    private final ShipServiceImpl shipService;

    @PostMapping("/register")
    public ResponseEntity<ShipDto> registerShip(@RequestBody ShipDto shipDto) {
        return ResponseEntity.ok(shipService.registerShip(shipDto));
    }

    @PostMapping("/update")
    public ResponseEntity<ShipDto> updateShip(@RequestBody ShipDto shipDto) {
        return ResponseEntity.ok(shipService.updateShip(shipDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipDto> getShipById(@PathVariable("id") long id) {
        return ResponseEntity.ok(shipService.getShipById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageResult<ShipDto>> getAllShips(
            @RequestParam(defaultValue = "0",required = false) int page,
            @RequestParam(defaultValue = "10",required = false) int size) {

        return ResponseEntity.ok(shipService.getAllShips(page,size));
    }

    @GetMapping("/company/{companyId}")
    public PageResult<ShipDto> getShipsByCompanyId(@PathVariable("companyId") long companyId,
                                            @RequestParam(defaultValue = "0",required = false) int page,
                                            @RequestParam(defaultValue = "10",required = false) int size
    ) {
        return shipService.getShipsByCompanyId(companyId,page,size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipById(@PathVariable("id") long id) {
        shipService.deleteShipById(id);
        return ResponseEntity.ok("Deleted ship with id " + id);
    }
}

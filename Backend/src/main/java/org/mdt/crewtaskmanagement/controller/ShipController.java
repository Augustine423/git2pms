package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.ship.ShipDto;
import org.mdt.crewtaskmanagement.service.ShipService;
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

    @GetMapping("/")
    public ResponseEntity<List<ShipDto>> getAllShips() {
        return ResponseEntity.ok(shipService.getAllShips());
    }

//    @GetMapping("/company/{companyId}")
//    public ResponseEntity<List<ShipDto>> getShipsByCompanyId(@PathVariable("companyId") long companyId) {
//        return ResponseEntity.ok(shipService.getShipsByCompanyId(companyId));
//    }
//
//    @GetMapping("/imo/{imoNumber}")
//    public ResponseEntity<ShipDto> getShipByImoNumber(@PathVariable("imoNumber") String imoNumber) {
//        return ResponseEntity.ok(shipService.getShipByImoNumber(imoNumber));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipById(@PathVariable("id") long id) {
        shipService.deleteShipById(id);
        return ResponseEntity.ok("Deleted ship with id " + id);
    }
}

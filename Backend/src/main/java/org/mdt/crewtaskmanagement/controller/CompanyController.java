package org.mdt.crewtaskmanagement.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.company.CompanyDto;
import org.mdt.crewtaskmanagement.dto.ship.ShipDto;
import org.mdt.crewtaskmanagement.service.CompanyService;
import org.mdt.crewtaskmanagement.service.impl.CompanyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mdt/company")
public class CompanyController {
    private final CompanyServiceImpl companyService;

    @PostMapping("/register")
    public ResponseEntity<CompanyDto> registerCompany(@Validated @RequestBody CompanyDto companyDto, BindingResult result) {
      return ResponseEntity.ok(companyService.registerCompany(companyDto));
    }
    @PostMapping("/update")
    public ResponseEntity<CompanyDto> updateCompany(@Validated @RequestBody CompanyDto companyDto,BindingResult result) {
        return ResponseEntity.ok(companyService.updateCompany(companyDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable("id") long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }
    @GetMapping("/")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable("id") long id) {
        companyService.deleteCompanyById(id);
        return ResponseEntity.ok("Deleted company with id " + id);
    }
    @GetMapping("/add-ship-to-company/{companyId}/{shipId}")
    public ResponseEntity<String> addShipsToCompany(@PathVariable long companyId, @PathVariable long shipId) {
        companyService.addShipsToCompany(companyId, shipId);
        return ResponseEntity.ok("Added ship with id " + shipId);
    }

}

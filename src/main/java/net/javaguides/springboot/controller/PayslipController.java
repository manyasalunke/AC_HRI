package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.payloads.ApiResponse;
import net.javaguides.springboot.payloads.PayslipDTO;
import net.javaguides.springboot.services.PayslipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payslips")
public class PayslipController {

    @Autowired
    private PayslipService payslipService;

    // create Payslip
    @PostMapping("/")
    public ResponseEntity<PayslipDTO> createPayslip(@Valid @RequestBody PayslipDTO payslipDTO) {
        PayslipDTO createPayslip = this.payslipService.createPayslip(payslipDTO);
        return new ResponseEntity<>(createPayslip, HttpStatus.CREATED);
    }

    // update Payslip
    @PutMapping("/{payslipId}")
    public ResponseEntity<PayslipDTO> updatePayslip(@Valid @RequestBody PayslipDTO payslipDTO, @PathVariable int payslipId) {
        PayslipDTO updatedPayslip = this.payslipService.updatePayslip(payslipDTO, payslipId);
        return new ResponseEntity<>(updatedPayslip, HttpStatus.CREATED);
    }

    // delete Payslip
    @DeleteMapping("/{payslipId}")
    public ResponseEntity<ApiResponse> deletePayslip(@PathVariable int payslipId) {
        this.payslipService.deletePayslip(payslipId);
        return new ResponseEntity<>(new ApiResponse("Payslip is deleted successfully !!", true), HttpStatus.OK);
    }

    // get Payslip
    @GetMapping("/{payslipId}")
    public ResponseEntity<PayslipDTO> getPayslip(@PathVariable int payslipId) {
        PayslipDTO payslipDto = this.payslipService.getPayslip(payslipId);
        return new ResponseEntity<>(payslipDto, HttpStatus.OK);
    }

    // get all Payslips
    @GetMapping("/")
    public ResponseEntity<List<PayslipDTO>> getPayslips() {
        List<PayslipDTO> payslips = this.payslipService.getPayslips();
        return ResponseEntity.ok(payslips);
    }
}

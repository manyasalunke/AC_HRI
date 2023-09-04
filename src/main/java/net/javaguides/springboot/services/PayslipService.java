package net.javaguides.springboot.services;

import net.javaguides.springboot.payloads.PayslipDTO;
import java.util.List;

public interface PayslipService {

    // create
    PayslipDTO createPayslip(PayslipDTO payslipDTO);

    // update
    PayslipDTO updatePayslip(PayslipDTO payslipDTO, Integer payslipId);

    // delete
    void deletePayslip(Integer payslipId);

    // get
    PayslipDTO getPayslip(Integer payslipId);

    // get All
    List<PayslipDTO> getPayslips();
}

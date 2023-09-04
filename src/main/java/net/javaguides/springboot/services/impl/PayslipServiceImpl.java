package net.javaguides.springboot.services.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Payslip;
import net.javaguides.springboot.payloads.PayslipDTO;
import net.javaguides.springboot.repository.PayslipRepository;
import net.javaguides.springboot.services.PayslipService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayslipServiceImpl implements PayslipService {

    @Autowired
    private PayslipRepository payslipRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PayslipServiceImpl(PayslipRepository payslipRepository) {
        this.payslipRepository = payslipRepository;
    }

    @Override
    public PayslipDTO createPayslip(PayslipDTO payslipDTO) {
        Payslip payslip = this.modelMapper.map(payslipDTO, Payslip.class);
        Payslip addedPayslip = this.payslipRepository.save(payslip);
        return this.modelMapper.map(addedPayslip, PayslipDTO.class);
    }

    @Override
    public PayslipDTO updatePayslip(PayslipDTO payslipDTO, Integer payslipId) {
        Payslip payslip = this.payslipRepository.findById(payslipId)
                .orElseThrow(() -> new ResourceNotFoundException("Payslip ", "Payslip Id", payslipId));

        payslip.setMonth(payslipDTO.getMonth());
        payslip.setYear(payslipDTO.getYear());
        payslip.setBasicSalary(payslipDTO.getBasicSalary());
        payslip.setAllowances(payslipDTO.getAllowances());
        payslip.setDeduction(payslipDTO.getDeduction());
        payslip.setNetSalary(payslipDTO.getNetSalary());
        payslip.setGrossSalary(payslipDTO.getGrossSalary());
        payslip.setPaySlipNumber(payslipDTO.getPaySlipNumber());
        payslip.setIssueDate(payslipDTO.getIssueDate());

        Payslip updatedPayslip = this.payslipRepository.save(payslip);
        return this.modelMapper.map(updatedPayslip, PayslipDTO.class);
    }

    @Override
    public void deletePayslip(Integer payslipId) {
        Payslip payslip = this.payslipRepository.findById(payslipId)
                .orElseThrow(() -> new ResourceNotFoundException("Payslip ", "Payslip id", payslipId));
        this.payslipRepository.delete(payslip);
    }

    @Override
    public PayslipDTO getPayslip(Integer payslipId) {
        Payslip payslip = this.payslipRepository.findById(payslipId)
                .orElseThrow(() -> new ResourceNotFoundException("Payslip", "Payslip id", payslipId));
        return this.modelMapper.map(payslip, PayslipDTO.class);
    }

    @Override
    public List<PayslipDTO> getPayslips() {
        List<Payslip> payslips = this.payslipRepository.findAll();
        List<PayslipDTO> payslipDto = payslips.stream().map((payslip) -> this.modelMapper.map(payslip, PayslipDTO.class))
                .collect(Collectors.toList());
        return payslipDto;
    }
}

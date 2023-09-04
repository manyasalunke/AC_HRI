    package net.javaguides.springboot.services;

    import net.javaguides.springboot.payloads.EmployeeDTO;
    import net.javaguides.springboot.payloads.EmployeeResponse;

    import java.util.List;

    public interface EmployeeService {
        EmployeeDTO createEmployee(EmployeeDTO employee);
        EmployeeDTO updateEmployee(EmployeeDTO employee, Integer employeeId);
        EmployeeDTO getEmployeeById(Integer employeeId);
        EmployeeResponse getAllEmployees(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
        void deleteEmployee(Integer employeeId);
        EmployeeDTO assignDepartment(int departmentId, int employeeId);

        //search posts
        List<EmployeeDTO> searchEmployee(String keyword);
    }
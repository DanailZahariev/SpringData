package com.example.springmappingobjects;

import com.example.springmappingobjects.dto.EmployeeDTO;
import com.example.springmappingobjects.entities.Address;
import com.example.springmappingobjects.entities.Employee;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

public class Mapper {
    public static void main(String[] args) {

        ModelMapper mapper = new ModelMapper();
        Address address = new Address("vitoshka", 15, "sofia", "bulgaria");
        Employee employee = new Employee("danail", "zahariev", BigDecimal.TEN, address);

        EmployeeDTO dto = mapper.map(employee, EmployeeDTO.class);

        System.out.println(dto);
    }
}

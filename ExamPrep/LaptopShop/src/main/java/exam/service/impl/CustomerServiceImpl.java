package exam.service.impl;

import com.google.gson.Gson;
import exam.model.entity.Customer;
import exam.model.entity.jsonDto.CustomerRootSeedDto;
import exam.repository.CustomerRepository;
import exam.service.CustomerService;
import exam.service.TownService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String CUSTOMER_FILE_PATH = "src/main/resources/files/json/customers.json";
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final TownService townService;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, TownService townService) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.townService = townService;
    }


    @Override
    public boolean areImported() {
        return customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(CUSTOMER_FILE_PATH));
    }

    @Override
    public String importCustomers() throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(gson.fromJson(readCustomersFileContent(), CustomerRootSeedDto[].class))
                .filter(customerRootSeedDto -> {

                    boolean isValid = validationUtil.isValid(customerRootSeedDto);

                    stringBuilder.append(isValid ? String.format("Successfully imported Customer %s %s - %s",
                            customerRootSeedDto.getFirstName(), customerRootSeedDto.getLastName(), customerRootSeedDto.getEmail())
                            : "Invalid Customer").append(System.lineSeparator());

                    return isValid;
                }).map(customerRootSeedDto -> {

                    Customer customer = modelMapper.map(customerRootSeedDto, Customer.class);
                    customer.setTown(townService.findTownByName(customerRootSeedDto.getTown().getName()));

                    return customer;
                }).forEach(customerRepository::save);


        return stringBuilder.toString();
    }
}

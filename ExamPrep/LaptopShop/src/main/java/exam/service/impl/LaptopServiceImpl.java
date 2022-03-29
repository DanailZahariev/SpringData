package exam.service.impl;

import com.google.gson.Gson;
import exam.model.entity.Laptop;
import exam.model.entity.jsonDto.LaptopRootSeedDto;
import exam.repository.LaptopRepository;
import exam.service.LaptopService;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {

    private static final String LAPTOP_FILE_PATH = "src/main/resources/files/json/laptops.json";
    private final LaptopRepository laptopRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ShopService shopService;

    public LaptopServiceImpl(LaptopRepository laptopRepository, ModelMapper modelMapper,
                             Gson gson, ValidationUtil validationUtil, ShopService shopService) {
        this.laptopRepository = laptopRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.shopService = shopService;
    }


    @Override
    public boolean areImported() {
        return laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of(LAPTOP_FILE_PATH));
    }

    @Override
    public String importLaptops() throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(gson.fromJson(readLaptopsFileContent(), LaptopRootSeedDto[].class))
                .filter(laptopRootSeedDto -> {

                    boolean isValid = validationUtil.isValid(laptopRootSeedDto);

                    stringBuilder.append(isValid ? String.format("Successfully imported Laptop %s - %.2f - %d - %d",
                            laptopRootSeedDto.getMacAddress(), laptopRootSeedDto.getCpuSpeed(),
                            laptopRootSeedDto.getRam(), laptopRootSeedDto.getStorage())
                            : "Invalid Laptop").append(System.lineSeparator());

                    return isValid;
                }).map(laptopRootSeedDto -> {

                    Laptop laptop = modelMapper.map(laptopRootSeedDto, Laptop.class);
                    laptop.setShop(shopService.findShopByName(laptopRootSeedDto.getShop().getName()));

                    return laptop;
                }).forEach(laptopRepository::save);


        return stringBuilder.toString();
    }

    @Override
    public String exportBestLaptops() {

        StringBuilder stringBuilder = new StringBuilder();

        List<Laptop> laptops = laptopRepository.findAllLaptops();

        laptops.forEach(laptop ->

                stringBuilder.append(String.format("Laptop - %s\n" +
                                        "*Cpu speed - %.2f\n" +
                                        "**Ram - %d\n" +
                                        "***Storage - %d\n" +
                                        "****Price - %.2f\n" +
                                        "#Shop name - %s\n" +
                                        "##Town - %s\n",
                                laptop.getMacAddress(), laptop.getCpuSpeed(), laptop.getRam(), laptop.getStorage(),
                                laptop.getPrice(), laptop.getShop().getName(), laptop.getShop().getTown().getName()))
                        .append(System.lineSeparator())

        );

        return stringBuilder.toString();
    }
}

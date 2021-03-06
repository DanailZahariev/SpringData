package exam.service.impl;

import exam.model.entity.Town;
import exam.model.xmlDto.TownRootSeedDto;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWN_FILE_PATH = "src/main/resources/files/xml/towns.xml";
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public TownServiceImpl(ModelMapper modelMapper, TownRepository townRepository,
                           ValidationUtil validationUtil, XmlParser xmlParser) {
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }


    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWN_FILE_PATH));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {

        StringBuilder stringBuilder = new StringBuilder();

        xmlParser.fromFile(TOWN_FILE_PATH, TownRootSeedDto.class).getTowns().stream()
                .filter(townSeedDto -> {

                    boolean isValid = validationUtil.isValid(townSeedDto);

                    stringBuilder.append(isValid ? String.format("Successfully imported Town %s",
                            townSeedDto.getName()) : "Invalid town").append(System.lineSeparator());


                    return isValid;
                }).map(townSeedDto -> modelMapper.map(townSeedDto, Town.class))
                .forEach(townRepository::save);


        return stringBuilder.toString();
    }

    @Override
    public Town findTownByName(String name) {
        return townRepository.findTownByName(name);
    }
}

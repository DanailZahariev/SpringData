package instagraphlite.service.impl;

import com.google.gson.Gson;
import instagraphlite.models.dto.PictureSeedDto;
import instagraphlite.models.entity.Picture;
import instagraphlite.repository.PictureRepository;
import instagraphlite.service.PictureService;
import instagraphlite.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PictureServiceImpl implements PictureService {

    private static final String PICTURE_FILE_PATH = "src/main/resources/files/pictures.json";
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public PictureServiceImpl(PictureRepository pictureRepository,
                              ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PICTURE_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(gson.fromJson(readFromFileContent(), PictureSeedDto[].class)).filter(pictureSeedDto -> {

                    boolean isValid = validationUtil.isValid(pictureSeedDto) && !isEntityExist(pictureSeedDto.getPath());
                    stringBuilder.append(isValid ? String.format("Successfully imported Picture, with size %.2f",
                            pictureSeedDto.getSize()) : "Invalid Picture").append(System.lineSeparator());


                    return isValid;
                }).map(pictureSeedDto -> modelMapper.map(pictureSeedDto, Picture.class))
                .forEach(pictureRepository::save);


        return stringBuilder.toString();
    }

    @Override
    public boolean isEntityExist(String path) {
        return pictureRepository.existsByPath(path);
    }

    @Override
    public String exportPictures() {

        StringBuilder stringBuilder = new StringBuilder();

        pictureRepository.findAllBySizeOrderById().forEach(picture ->
                stringBuilder.append(String.format("%.2f - %s%n",picture.getSize(),picture.getPath())));


        return stringBuilder.toString();
    }

    @Override
    public Picture findByPath(String path) {
        return pictureRepository.findByPath(path).orElse(null);
    }
}

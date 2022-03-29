package instagraphlite.service.impl;

import com.google.gson.Gson;
import instagraphlite.models.dto.UserSeedDto;
import instagraphlite.models.entity.User;
import instagraphlite.repository.UserRepository;
import instagraphlite.service.PictureService;
import instagraphlite.service.UserService;
import instagraphlite.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_FILE_PATH = "src/main/resources/files/users.json";
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final PictureService pictureService;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil,
                           PictureService pictureService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.pictureService = pictureService;
    }


    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(USER_FILE_PATH));
    }

    @Override
    public String importUsers() throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(gson.fromJson(readFromFileContent(), UserSeedDto[].class)).filter(userSeedDto -> {

            boolean isValid = validationUtil.isValid(userSeedDto)
                    && !isEntityExist(userSeedDto.getUsername())
                    && pictureService.isEntityExist(userSeedDto.getProfilePicture());

            stringBuilder.append(isValid ? "Successfully imported User: " + userSeedDto.getUsername()
                    : "Invalid User").append(System.lineSeparator());


            return isValid;
        }).map(userSeedDto -> {

            User user = modelMapper.map(userSeedDto, User.class);
            user.setProfilePicture(pictureService.findByPath(userSeedDto.getProfilePicture()));

            return user;
        }).forEach(userRepository::save);

        return stringBuilder.toString();
    }

    @Override
    public boolean isEntityExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public String exportUsersWithTheirPosts() {

        StringBuilder stringBuilder = new StringBuilder();
        userRepository.findAllByPostCountDescThenByUserId().forEach(user -> {
            stringBuilder.append(String.format("""
                    User: %s
                    Post count %d
                    """, user.getUsername(), user.getPosts().size()));

            user.getPosts().stream().sorted(Comparator.comparingDouble(a -> a.getPicture().getSize()))
                    .forEach(post -> {
                        stringBuilder.append(String.format("""
                                ==Post Details:
                                ----Caption: %s
                                ----Picture Size: %.2f
                                """, post.getCaption(), post.getPicture().getSize()));
                    });

        });

        return stringBuilder.toString();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

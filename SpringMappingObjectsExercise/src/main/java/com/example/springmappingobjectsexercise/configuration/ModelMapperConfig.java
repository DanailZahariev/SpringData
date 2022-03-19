package com.example.springmappingobjectsexercise.configuration;

import com.example.springmappingobjectsexercise.entities.Game;
import com.example.springmappingobjectsexercise.entities.dto.GameAddDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(GameAddDto.class, Game.class)
                .addMappings(mapper -> mapper.map(GameAddDto::getThumbnailUrl, Game::setImageThumbnail));

        Converter<String, LocalDate> localDateConverter = mappingContext -> LocalDate.parse(mappingContext.getSource(),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        modelMapper.addConverter(localDateConverter);

        return modelMapper;
    }
}

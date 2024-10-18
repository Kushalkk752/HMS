package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.payload.AppUserDto;
import com.hms.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private AppUserRepository appUserRepository;
    private ModelMapper modelMapper;

    public UserService(AppUserRepository appUserRepository, ModelMapper modelMapper) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
    }

    public AppUserDto create(AppUserDto appUserDto) {
        AppUser user = mapToEntity(appUserDto);
        AppUser save = appUserRepository.save(user);
        AppUserDto dto = mapToDto(save);
        return dto;
    }

    private AppUserDto mapToDto(AppUser save) {
        AppUserDto dto = modelMapper.map(save,AppUserDto.class);
        return dto;
    }

    private AppUser mapToEntity(AppUserDto appUserDto) {
        AppUser appUser = modelMapper.map(appUserDto,AppUser.class);
        return appUser;
    }
}

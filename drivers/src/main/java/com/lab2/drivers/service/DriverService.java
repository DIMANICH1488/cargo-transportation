package com.lab2.drivers.service;

import com.lab2.drivers.repo.DriverRepo;
import com.lab2.drivers.repo.model.Driver;
import com.lab2.drivers.repo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class DriverService {

    private final DriverRepo driverRepo;

    public List<Driver> list() {
        final List<Driver> items = driverRepo.findAll();
        return items;
    }

    public Driver read(long id) throws IllegalArgumentException {
        final Optional<Driver> item = driverRepo.findById(id);
        if (item.isEmpty()) {
            throw new IllegalArgumentException("Driver unknown");
        }
        return item.get();
    }

    public Driver create(Long userId, String info, Integer status) throws IllegalArgumentException{
        RestTemplate rt = new RestTemplate();
        String url = "http://localhost:8081/user/{id}";
        Map<String, Long> map = new HashMap<>();
        map.put("id", userId);
        ResponseEntity<User> user = rt.getForEntity(url, User.class, map);
        if (user.getStatusCode() != HttpStatus.OK || user.getBody().getStatus() != 1) {
            throw new IllegalArgumentException("User unknown");
        }

        final Driver driver = new Driver(userId, info, status);
        final Driver item = driverRepo.save(driver);
        return item;
    }

    public Driver update(long id, Long userId, String info, Integer status) throws IllegalArgumentException {
        final Optional<Driver> item = driverRepo.findById(id);
        if (item.isEmpty()) {
            throw new IllegalArgumentException("Driver unknown");
        }
        Driver driver = item.get();
        if (userId != null && userId > 0) {
            RestTemplate rt = new RestTemplate();
            String url = "http://localhost:8081/user/{id}";
            Map<String, Long> map = new HashMap<>();
            map.put("id", userId);
            ResponseEntity<User> user = rt.getForEntity(url, User.class, map);
            if (user.getStatusCode() != HttpStatus.OK || user.getBody().getStatus() != 1) {
                throw new IllegalArgumentException("User unknown");
            }
            driver.setUserId(userId);
        }
        if (info != null && !info.isBlank()) {
            driver.setInfo(info);
        }
        if (status != null) {
            driver.setStatus(status);
        }
        driverRepo.save(driver);
        return driver;
    }

    public void remove(long id) {
        driverRepo.deleteById(id);
    }
}

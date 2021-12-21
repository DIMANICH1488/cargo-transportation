package com.lab2.drivers.service;

import com.lab2.drivers.repo.DriverRepo;
import com.lab2.drivers.repo.model.Driver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Driver create(String info, Integer status) {
        final Driver driver = new Driver(info, status);
        final Driver item = driverRepo.save(driver);
        return item;
    }

    public Driver update(long id, String info, Integer status) throws IllegalArgumentException {
        final Optional<Driver> item = driverRepo.findById(id);
        if (item.isEmpty()) {
            throw new IllegalArgumentException("Driver unknown");
        }
        Driver driver = item.get();
        if (info != null && !info.isBlank()) {
            driver.setInfo(info);
        }
        if (status != null) {
            driver.setStatus(status );
        }
        driverRepo.save(driver);
        return driver;
    }

    public void remove(long id) {
        driverRepo.deleteById(id);
    }
}

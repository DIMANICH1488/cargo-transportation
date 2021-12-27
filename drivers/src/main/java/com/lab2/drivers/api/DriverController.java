package com.lab2.drivers.api;

import com.lab2.drivers.api.dto.DriverItem;
import com.lab2.drivers.repo.model.Driver;
import com.lab2.drivers.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/driver")
public final class DriverController {

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<Driver>> list() {
        List<Driver> items = driverService.list();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> read(@PathVariable long id) {
        try {
            Driver item = driverService.read(id);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Driver> create(@RequestBody DriverItem driver) {
        try {
            Long userId = driver.getUserId();
            String info = driver.getInfo();
            Integer status = driver.getStatus();
            Driver item = driverService.create(userId, info, status);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Driver> update(@PathVariable long id, @RequestBody DriverItem driver) {
        try {
            Long userId = driver.getUserId();
            String info = driver.getInfo();
            Integer status = driver.getStatus();
            Driver item = driverService.update(id, userId, info, status);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DriverItem> remove(@PathVariable long id) {
        driverService.remove(id);
        return ResponseEntity.noContent().build();
    }
}

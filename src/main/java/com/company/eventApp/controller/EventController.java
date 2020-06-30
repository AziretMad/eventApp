package com.company.eventApp.controller;

import com.company.eventApp.dto.EventDTO;
import com.company.eventApp.model.Cancel;
import com.company.eventApp.model.MovedDate;
import com.company.eventApp.service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventServiceImpl eventService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity(eventService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity getById(@RequestParam("id") Long id) {
        try {
            return new ResponseEntity(eventService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    public ResponseEntity create(@RequestBody EventDTO eventDTO) {
        try {
            return new ResponseEntity(eventService.create(eventDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@RequestParam("id") Long id) {
        try {
            eventService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping("/soon")
    public ResponseEntity getSoonEvents(){
        try {
            return new ResponseEntity(eventService.getSoonEvents(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/image/{id}")
    public ResponseEntity addImage(@PathVariable("id") Long id, MultipartFile picture){
        try {
            return new ResponseEntity(eventService.addImage(id, picture), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/move")
    public ResponseEntity moveDate(@RequestBody MovedDate movedDate){
        try {
            return new ResponseEntity(eventService.moveDate(movedDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/cancel")
    public ResponseEntity cancel(@RequestBody Cancel cancel){
        try {
            return new ResponseEntity(eventService.cancel(cancel), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

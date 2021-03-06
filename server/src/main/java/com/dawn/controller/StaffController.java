package com.dawn.controller;

import com.dawn.common.DawnCodingResult;
import com.dawn.dto.StaffDTO;
import com.dawn.service.StaffService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/staff")
    public ResponseEntity<DawnCodingResult<StaffDTO.GetStaff>> createNewStaff(@RequestPart("staff") String newStaffString,
                                                           @RequestPart("staffImage") MultipartFile staffImage) throws IOException, ParseException {
        StaffDTO.CreateStaff newStaff = new ObjectMapper().readValue(newStaffString, StaffDTO.CreateStaff.class);
        return new ResponseEntity<>(
                new DawnCodingResult<>(null,
                        staffService.createStaff(newStaff, staffImage)),HttpStatus.OK);
    }

    @GetMapping("/staffs/store/{storeId}")
    public ResponseEntity<DawnCodingResult<List<StaffDTO.GetStaff>>> getStaffOfStoreByStoreId(@PathVariable("storeId") int storeId) {
        return new ResponseEntity<>(
                new DawnCodingResult<>(null,
                        staffService.getAllStaffOfStore(storeId)),HttpStatus.OK);
    }

}

package com.techsoft.controller;

import com.techsoft.common.BookingRequest;
import com.techsoft.common.BookingResponse;
import com.techsoft.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping("/bookOrder")
    public BookingResponse bookOrder(@RequestBody BookingRequest bookingRequest){
        return bookingService.bookOder(bookingRequest);
    }
}

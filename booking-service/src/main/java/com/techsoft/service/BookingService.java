package com.techsoft.service;

import com.techsoft.common.BookingRequest;
import com.techsoft.common.BookingResponse;
import com.techsoft.common.Payment;
import com.techsoft.entity.BookOrder;
import com.techsoft.repository.BookingRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RefreshScope
public class BookingService {
    @Autowired
    private BookingRespository bookingRespository;
@Autowired
@Lazy
private RestTemplate restTemplate;
@Value("${microservice.payment-service.endpoints.endpoint.uri}")
private String baseUrl;


    public BookingResponse bookOder(BookingRequest bookingRequest){
        Payment payment = new Payment();
        BookOrder bookOrder = bookingRequest.getBookOrder();
        payment.setAmount(bookOrder.getPrice());
        payment.setOrderId(bookOrder.getId());
        Payment paymentResponse = restTemplate.postForObject(baseUrl, payment, Payment.class);
        String response = paymentResponse.getPaymentStatus().equals("Success")?"Payment processed Successful":"Payment Failure";
        bookingRespository.save(bookOrder);
        return new BookingResponse(bookOrder,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }
}

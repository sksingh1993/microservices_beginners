package com.techsoft.service;

import com.techsoft.common.BookingRequest;
import com.techsoft.common.BookingResponse;
import com.techsoft.common.Payment;
import com.techsoft.entity.BookOrder;
import com.techsoft.repository.BookingRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingService {
    @Autowired
    private BookingRespository bookingRespository;
@Autowired
private RestTemplate restTemplate;

    public BookingResponse bookOder(BookingRequest bookingRequest){
        Payment payment = new Payment();
        BookOrder bookOrder = bookingRequest.getBookOrder();
        payment.setAmount(bookOrder.getPrice());
        payment.setOrderId(bookOrder.getId());
        Payment paymentResponse = restTemplate.postForObject("http://localhost:8082/payment/doPayment", payment, Payment.class);
        String response = paymentResponse.getPaymentStatus().equals("success")?"Payment processed Successful":"Payment Failure";
        bookingRespository.save(bookOrder);
        return new BookingResponse(bookOrder,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }
}

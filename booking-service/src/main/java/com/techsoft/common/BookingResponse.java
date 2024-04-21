package com.techsoft.common;

import com.techsoft.entity.BookOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private BookOrder bookOrder;
    private Double amount;
    private String transactionId;
    private String response;
}

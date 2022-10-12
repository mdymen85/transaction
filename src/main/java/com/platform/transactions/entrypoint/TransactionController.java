package com.platform.transactions.entrypoint;

import com.platform.transactions.entrypoint.dto.TransactionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final IEntrypoint entrypoint;

    @RequestMapping(path = "/v1/transaction", method = RequestMethod.POST)
    public ResponseEntity<TransactionRequest> createTransaction(@RequestBody TransactionRequest request) {
        var transaction = entrypoint.createTransaction(request);
        return new ResponseEntity<TransactionRequest>(transaction, HttpStatus.CREATED);
    }

}

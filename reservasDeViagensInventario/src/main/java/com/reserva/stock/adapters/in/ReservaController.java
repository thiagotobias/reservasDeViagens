package com.reserva.stock.adapters.in;


import com.reserva.stock.adapters.dtos.ProductDto;
import com.reserva.stock.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReserveService reserveService;


    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> getAll() throws InterruptedException {
        List<ProductDto>producs=this.reserveService.getAllProducts();
        return ResponseEntity.ok(producs);
    }
}

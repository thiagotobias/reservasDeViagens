package com.reserva.stock.adapters.in;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.reserva.stock.adapters.dtos.ProductDto;
import com.reserva.stock.adapters.dtos.ReserveDto;
import com.reserva.stock.adapters.dtos.RollbackPayments;
import com.reserva.stock.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;


    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> getAll() throws InterruptedException {
        List<ProductDto> producs = this.reserveService.getAllProducts();
        return ResponseEntity.ok(producs);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> reserve(@RequestBody ReserveDto reservaDto) throws JsonProcessingException {
        var createdReserve = reserveService.reserve(reservaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdReserve.getReserveId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @PostMapping(value = "/rollback")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity reserve(@RequestBody RollbackPayments rollbackPayments) throws JsonProcessingException {
        reserveService.rollback(rollbackPayments);
        return ResponseEntity.ok().build();
    }

    //post enviar pagamentos
    //financeiro/reserva


}

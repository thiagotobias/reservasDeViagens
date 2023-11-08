package br.com.reservasDeViagensFinanceiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reservasDeViagensFinanceiro.model.dto.ReservaViagemDTO;
import br.com.reservasDeViagensFinanceiro.model.dto.TransacaoFinanceiraDTO;
import br.com.reservasDeViagensFinanceiro.service.FinanceiroService;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController {

    @Autowired
    private FinanceiroService financeiroService;

    @PostMapping("/reserva")
    public ResponseEntity<ReservaViagemDTO> criarTransacao(@RequestBody ReservaViagemDTO reserva) {
    	ReservaViagemDTO reservaViagem = financeiroService.processarReserva(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaViagem);
    }

    @GetMapping("/transacao/{id}")
    public ResponseEntity<TransacaoFinanceiraDTO> consultarTransacao(@PathVariable Long id) {
        TransacaoFinanceiraDTO transacao = financeiroService.consultarTransacao(id);
        if (transacao != null) {
            return ResponseEntity.ok(transacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/transacao")
    public ResponseEntity<List<TransacaoFinanceiraDTO>> consultarTransacao() {
        List<TransacaoFinanceiraDTO> transacao = financeiroService.consultarTransacao();
        if (transacao != null) {
            return ResponseEntity.ok(transacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/processarPagamento")
    public ResponseEntity<String> processarPagamento(@RequestBody ReservaViagemDTO reserva) {
        financeiroService.processarPagamento(reserva);
        return ResponseEntity.ok("Pagamento processado com sucesso");
    }
    
    @PostMapping("/processarEstorno")
    public ResponseEntity<String> processarEstorno(@RequestBody ReservaViagemDTO reserva) {
        financeiroService.processarEstorno(reserva);
        return ResponseEntity.ok("Pagamento processado com sucesso");
    }
    
//    @GetMapping("/ola")
//    public ResponseEntity<String> ola(){
//    	financeiroService.sendToPayments();
//    	return ResponseEntity.ok("Pagamento processado com sucesso");
//    }
    
}

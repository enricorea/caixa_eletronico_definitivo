package br.com.ifsp.caixa_eletronico.service;

import br.com.ifsp.caixa_eletronico.entity.Cliente;
import br.com.ifsp.caixa_eletronico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

//pacote service -> onde são adicionadas as regras de negócio
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente login(Cliente cliente) {
        return clienteRepository.findByNumeroContaAndPin(cliente.getNumeroConta(), cliente.getPin());
    }

    public BigDecimal consultarSaldo(Cliente cliente){
        cliente = clienteRepository.findClienteByNumeroConta(cliente.getNumeroConta());
        return cliente.getSaldo();
    }

    public BigDecimal sacar(Cliente cliente, BigDecimal saque) {
        cliente = clienteRepository.findClienteByNumeroConta(cliente.getNumeroConta());
        if(saque.floatValue()<0){
            throw new UnsupportedOperationException("Saque inferior a zero");
        }
        BigDecimal novoSaldo = cliente.getSaldo().subtract(saque);
        if(novoSaldo.floatValue() >= 0){
            cliente.setSaldo(novoSaldo.setScale(2, RoundingMode.HALF_EVEN));
            clienteRepository.save(cliente);
            return consultarSaldo(cliente);
        } else {
            throw new UnsupportedOperationException("Saque superior ao saldo atual");
        }
    }

    public BigDecimal depositar(Cliente cliente, BigDecimal deposito){

        cliente = clienteRepository.findClienteByNumeroConta(cliente.getNumeroConta());
        if(deposito.floatValue()<0){
            throw new UnsupportedOperationException("Depósito inferior a zero");
        }
        BigDecimal novoSaldo = cliente.getSaldo().add(deposito);
        cliente.setSaldo(novoSaldo.setScale(2, RoundingMode.HALF_EVEN));
        clienteRepository.save(cliente);
        return consultarSaldo(cliente);
    }
}

package br.com.ifsp.caixa_eletronico.http.controller;

import br.com.ifsp.caixa_eletronico.entity.Cliente;
import br.com.ifsp.caixa_eletronico.repository.ClienteRepository;
import br.com.ifsp.caixa_eletronico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

//controller -> onde são adicionados os endpoints (as rotas de acesso aos serviços de saldo)
@Controller
public class SaldoController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/saldo")
    public String consultar(HttpSession session, RedirectAttributes ra){
        Cliente cliente = getRecuperaSessao(session);
        if ( cliente != null ) {
            BigDecimal saldo = clienteService.consultarSaldo(cliente);
            //guardar na sessao e redirecionar p home
            ra.addFlashAttribute("saldo_valor", String.valueOf(saldo));
            return "redirect:/home";
        }
        else{
            return "redirect:/";
        }
    }

    @PostMapping("/saldo/sacar")
    public String sacar(HttpSession session, @RequestParam("saque") BigDecimal saque, RedirectAttributes ra){
        Cliente cliente = getRecuperaSessao(session);
        if ( cliente != null ) {
            try{
                BigDecimal saldo = clienteService.sacar(cliente, saque);
                ra.addFlashAttribute("saldo_valor", String.valueOf(saldo));
            } catch(UnsupportedOperationException e) {
                ra.addFlashAttribute("erro_saque",e.getMessage());
            }
            return "redirect:/home";
        }
        else{
            return "redirect:/";
        }
    }

    @PostMapping("/saldo/depositar")
    public String depositar(HttpSession session,
                            @RequestParam("deposito") BigDecimal deposito,
                            RedirectAttributes ra){
        Cliente cliente = getRecuperaSessao(session);
        if ( cliente != null ) {
            try{
                BigDecimal saldo = clienteService.depositar(cliente, deposito);
                ra.addFlashAttribute("saldo_valor", String.valueOf(saldo));
            } catch(UnsupportedOperationException e) {
                ra.addFlashAttribute("erro_deposito",e.getMessage());
            }
            return "redirect:/home";
        }
        else{
            return "redirect:/";
        }
    }
    private Cliente getRecuperaSessao(HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("clienteLogado");
        return cliente;
    }

}

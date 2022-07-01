package br.com.ifsp.caixa_eletronico.http.controller;

import br.com.ifsp.caixa_eletronico.entity.Cliente;
import br.com.ifsp.caixa_eletronico.repository.ClienteRepository;
import br.com.ifsp.caixa_eletronico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

//controller -> onde são adicionados os endpoints (as rotas de acesso aos serviços de login)

@Controller
public class LoginController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public String exibirIndex(Cliente cliente){
        return "index";
    }

    @GetMapping("/home")
    public String exibirHome(HttpSession session, Cliente cliente){
        if(session.getAttribute("clienteLogado") != null)
            return "home";
        return "index";
    }

    @PostMapping("/efetuarLogin")
    public String efetuarLogin(Cliente cliente,
                               RedirectAttributes ra,
                               HttpSession session){

            cliente = this.clienteService.login(cliente);
            if ( cliente != null ) {
                //guardar na sessao o objeto cliente e redirecionar p home
                session.setAttribute("clienteLogado", cliente);
                return "redirect:/home";
            }
            else{
                ra.addFlashAttribute("login_invalido","* Login/Senha inválidos");
                return "redirect:/";
            }
    }

}

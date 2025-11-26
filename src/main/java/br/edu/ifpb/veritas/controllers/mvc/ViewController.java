package br.edu.ifpb.veritas.controllers.mvc;

import br.edu.ifpb.veritas.service.ProcessoService;
import br.edu.ifpb.veritas.service.VotoService;
import br.edu.ifpb.veritas.service.CollegiateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final CollegiateService collegiateService;
    private final ProcessoService processoService;
    private final VotoService votoService;

    public ViewController(CollegiateService collegiateService,
                          ProcessoService processoService,
                          VotoService votoService) {
        this.collegiateService = collegiateService;
        this.processoService = processoService;
        this.votoService = votoService;
    }

    

  
    @GetMapping("/home")
    public String homePublica() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    // Página do usuário logado (dashboard)@GetMapping("/dashboard")
    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
    ModelAndView mv = new ModelAndView("dashboard");

        try {
            mv.addObject("collegiateCount", collegiateService.findAll().size());
    }   catch (Exception e) {
            System.out.println("Erro CollegiateService: " + e.getMessage());
            mv.addObject("collegiateCount", 0);
    }

        try {
            mv.addObject("processoCount", processoService.findAll().size());
    }   catch (Exception e) {
            System.out.println("Erro ProcessoService: " + e.getMessage());
            mv.addObject("processoCount", 0);
    }

        try {
            mv.addObject("votoCount", votoService.findAll().size());
    }   catch (Exception e) {
            System.out.println("Erro VotoService: " + e.getMessage());
            mv.addObject("votoCount", 0);
    }

    return mv;
}

}


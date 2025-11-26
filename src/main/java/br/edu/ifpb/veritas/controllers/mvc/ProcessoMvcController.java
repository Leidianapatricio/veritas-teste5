package br.edu.ifpb.veritas.controllers.mvc;

import br.edu.ifpb.veritas.models.Processo;
import br.edu.ifpb.veritas.service.CollegiateService;
import br.edu.ifpb.veritas.service.UserService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import br.edu.ifpb.veritas.service.ProcessoService;


@Controller
@RequestMapping("/processos")
public class ProcessoMvcController {

    private final ProcessoService processoService;
    private final CollegiateService collegiateService;
    private final UserService userService;

    public ProcessoMvcController(ProcessoService processoService, CollegiateService collegiateService, UserService userService) {
        this.processoService = processoService;
        this.collegiateService = collegiateService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("processos-list");
        mv.addObject("processos", processoService.findAll());
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView createForm() {
        ModelAndView mv = new ModelAndView("processos-form");
        mv.addObject("processo", new Processo());
        mv.addObject("collegiates", collegiateService.findAll());
        mv.addObject("users", userService.findAll());

    // Lista de assuntos predefinidos
        List<String> assuntos = List.of(
            "Solicitação de matrícula",
            "Requerimento de transferência",
            "Pedido de trancamento",
            "Revisão de nota",
            "Outro"
        );
        mv.addObject("assuntos", assuntos);

        return mv;
}

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("processos-form");
        processoService.findById(id).ifPresent(a -> mv.addObject("processo", a));
        mv.addObject("collegiates", collegiateService.findAll());
        mv.addObject("users", userService.findAll());

    // Lista de assuntos predefinidos
        List<String> assuntos = List.of(
            "Solicitação de matrícula",
            "Requerimento de transferência",
            "Pedido de trancamento",
            "Revisão de nota",
            "Outro"
    );
        mv.addObject("assuntos", assuntos);

        return mv;
}


    
    @PostMapping("/save")
    public ModelAndView save(
            @ModelAttribute Processo processo,
            @RequestParam Long autorId,
            @RequestParam Long colegiadoId
    ) {

        userService.findById(autorId).ifPresent(processo::setAutor);
        collegiateService.findById(colegiadoId).ifPresent(processo::setColegiado);

        processoService.save(processo);

        return new ModelAndView("redirect:/processos/list");
    }

    @GetMapping("/{id}")
    public ModelAndView view(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("processos-view");
        processoService.findById(id).ifPresent(a -> mv.addObject("processo", a));
        return mv;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        processoService.delete(id);
        return new ModelAndView("redirect:/processos/list");
    }
}

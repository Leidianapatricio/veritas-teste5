package br.edu.ifpb.veritas.controllers.mvc;

import br.edu.ifpb.veritas.models.Voto;
import br.edu.ifpb.veritas.service.ProcessoService;
import br.edu.ifpb.veritas.service.UserService;
import br.edu.ifpb.veritas.service.VotoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/votos")
public class VotoMvcController {

    private final VotoService votoService;
    private final ProcessoService assuntoService;
    private final UserService userService;

    public VotoMvcController(VotoService votoService, ProcessoService assuntoService, UserService userService) {
        this.votoService = votoService;
        this.assuntoService = assuntoService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("votos-list");
        mv.addObject("votos", votoService.findAll());
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView createForm(@RequestParam(required = false) Long assuntoId) {
        ModelAndView mv = new ModelAndView("votos-form");
        Voto voto = new Voto();

        if (assuntoId != null) {
            assuntoService.findById(assuntoId).ifPresent(voto::setAssunto);
        }

        mv.addObject("voto", voto);
        mv.addObject("assuntos", assuntoService.findAll());
        mv.addObject("users", userService.findAll());
        mv.addObject("opcoes", new String[]{"SIM","NAO","ABSTENCAO"});
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(
            @ModelAttribute Voto voto,
            @RequestParam Long userId,
            @RequestParam Long assuntoId,
            @RequestParam(required = false) String escolha
    ) {

        userService.findById(userId).ifPresent(voto::setUser);
        assuntoService.findById(assuntoId).ifPresent(voto::setAssunto);
        if (escolha != null) voto.setEscolha(escolha);

        votoService.save(voto);

        return new ModelAndView("redirect:/votos/list");
    }

    @GetMapping("/{id}")
    public ModelAndView view(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("votos-view");
        votoService.findById(id).ifPresent(v -> mv.addObject("voto", v));
        return mv;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        votoService.delete(id);
        return new ModelAndView("redirect:/votos/list");
    }
}

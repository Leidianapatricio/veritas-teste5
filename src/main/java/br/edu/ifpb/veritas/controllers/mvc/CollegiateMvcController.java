package br.edu.ifpb.veritas.controllers.mvc;

import br.edu.ifpb.veritas.models.Collegiate;
import br.edu.ifpb.veritas.models.User;
import br.edu.ifpb.veritas.service.CollegiateService;
import br.edu.ifpb.veritas.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/collegiate")
public class CollegiateMvcController {

    private final CollegiateService collegiateService;
    private final UserService userService;

    public CollegiateMvcController(CollegiateService collegiateService, UserService userService) {
        this.collegiateService = collegiateService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("collegiate-list");
        mv.addObject("collegiates", collegiateService.findAll());
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView createForm() {
        ModelAndView mv = new ModelAndView("collegiate-form");
        mv.addObject("collegiate", new Collegiate());
        mv.addObject("users", userService.findAll());
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("collegiate-form");
        collegiateService.findById(id).ifPresent(c -> mv.addObject("collegiate", c));
        mv.addObject("users", userService.findAll());
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(
            @ModelAttribute Collegiate collegiate,
            @RequestParam(required = false) Long coordenadorId,
            @RequestParam(required = false) java.util.List<Long> membersIds
    ) {

        if (coordenadorId != null) {
            userService.findById(coordenadorId).ifPresent(collegiate::setCoordenador);
        }

        if (membersIds != null) {
            java.util.List<User> members = userService.findAllById(membersIds);
            collegiate.setMembers(members);
        }

        collegiateService.save(collegiate);

        return new ModelAndView("redirect:/collegiate/list");
    }

    @GetMapping("/{id}")
    public ModelAndView view(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("collegiate-view");
        collegiateService.findById(id).ifPresent(c -> mv.addObject("collegiate", c));
        return mv;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        collegiateService.delete(id);
        return new ModelAndView("redirect:/collegiate/list");
    }
}

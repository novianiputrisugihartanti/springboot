package astratech.prg7_m5_p2_050.page;

import astratech.prg7_m5_p2_050.model.Helm;
import astratech.prg7_m5_p2_050.repository.HelmRepository;
import astratech.prg7_m5_p2_050.service.HelmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
public class HelmPageController {

    @Autowired
    HelmService helmService;

    @Autowired
    HelmRepository helmRepository;

    @RequestMapping("/listhelmth")
    public String getHelms(Model model){
        List<Helm> helm=helmService.getHelms();
        model.addAttribute("listHelm",helm);
        return "listhelmth";
    }

    @RequestMapping("/viewaddhelmth")
    public String viewaddHelm(Model model){
        //ini buat objek baru tapi bukan di set di javanya tapi di set di view nya (html)
        model.addAttribute("helmObject",new Helm());
        return "addhelmth";
    }

    @PostMapping("/saveHelmth")
    public String addHelm(Helm helm){
        helmService.save(helm);
        return "redirect:/listhelmth";
    }

    @GetMapping("/edit/{idHelm}")
    public String showupdateform(@PathVariable("idHelm") int idHelm, Model model){
        Helm helm = helmRepository.findById(idHelm).orElseThrow(() -> new IllegalArgumentException("Invalid Helm Id:" + idHelm));
        model.addAttribute("helm", helm);
        return "updateHelm";
    }

    @PostMapping("/update/{idHelm}")
    public String updateHelm(@PathVariable("idHelm") int idHelm, Helm helm, BindingResult bindingResult, Model model) {
        helm.setIdHelm(idHelm);
        if (bindingResult.hasErrors()){
            return "updateHelm";
        }
        helmRepository.save(helm);
        return "redirect:/listhelmth";

    }

    @GetMapping("/delete/{idHelm}")
    public String deleteHelm(@PathVariable("idHelm") int idHelm, Model model){
        Helm helm = helmRepository.findById(idHelm).orElseThrow(() -> new IllegalArgumentException("Invalid ID Helm:" + idHelm));
        helmRepository.delete(helm);
        return "redirect:/listhelmth";
    }
}

package com.techacademy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("country")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("countrylist", service.getCountry());
        return "country/list";
    }

    @GetMapping("/detail")
    public String postCountry(@RequestParam("code") String code,@RequestParam("name") String name,
            @RequestParam("population") int pupulation,Model model){

        service.updateCountry(code, name, pupulation);
        return "redirect:/country/list";

    }

    @GetMapping("/delete")
    public String deleteCountryForm(Model model) {
        return "country/delete";
    }

    @PostMapping("/delete")
    public String deleteCountry(@RequestParam("code") String code,Model model) {

        service.deleteCountry(code);

        return "redirect:/country/list";

    }
}
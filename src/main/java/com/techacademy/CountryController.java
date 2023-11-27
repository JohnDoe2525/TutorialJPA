package com.techacademy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value= {"/detail","detail/{code}/"})
    public String getCountry(@PathVariable(name="code",required=false)String code,Model model) {
        //codeが指定されていたら検索結果、無ければ空のクラスを設定
        Country country = code != null ? service.getCountry(code) : new Country();
        //Modelに登録
        model.addAttribute("country", country);
        return "country/detail";

    }

    @PostMapping("/detail")
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
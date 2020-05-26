package com.bohniman.travelpermit.controller;

import com.bohniman.travelpermit.services.DistrictService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @GetMapping(value = { "/home" })
    public ModelAndView noRole(ModelAndView mv) {
        mv = new ModelAndView("district/index");
        return mv;
    }

}
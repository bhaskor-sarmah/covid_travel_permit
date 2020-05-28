package com.bohniman.travelpermit.controller;

import com.bohniman.travelpermit.services.DistrictService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping(value = { "/reportingPending" })
    public ModelAndView reportingPending(ModelAndView mv) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv = new ModelAndView("district/pending");
        mv.addObject("passengerList", districtService.getPendingPassengerList(user));
        return mv;
    }

    @GetMapping(value = { "/reportedPassengers" })
    public ModelAndView reportedPassengers(ModelAndView mv) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv = new ModelAndView("district/reported");
        mv.addObject("passengerList", districtService.getReportedPassengerList(user));
        return mv;
    }

}
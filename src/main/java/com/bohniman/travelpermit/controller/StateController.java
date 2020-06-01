package com.bohniman.travelpermit.controller;

import com.bohniman.travelpermit.services.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/state")
public class StateController {

    @Autowired
    StateService stateService;

    @GetMapping(value = { "/home" })
    public ModelAndView noRole(ModelAndView mv) {
        mv = new ModelAndView("state/index");
        return mv;
    }

    @GetMapping(value = { "/reportingPending" })
    public ModelAndView reportingPending(ModelAndView mv) {
        // UserDetails user = (UserDetails)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv = new ModelAndView("state/pending");
        mv.addObject("passengerList", stateService.getPendingPassengerList());
        return mv;
    }

    @GetMapping(value = { "/reportedPassengers" })
    public ModelAndView reportedPassengers(ModelAndView mv) {
        // UserDetails user = (UserDetails)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv = new ModelAndView("state/reported");
        mv.addObject("passengerList", stateService.getReportedPassengerList());
        return mv;
    }

}
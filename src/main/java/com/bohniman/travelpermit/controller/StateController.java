package com.bohniman.travelpermit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bohniman.travelpermit.services.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/state")
public class StateController {

    @Autowired
    StateService stateService;

    @GetMapping(value = { "/home" })
    public ModelAndView noRole(ModelAndView mv) {
        mv = new ModelAndView("state/index");
        mv.addObject("dashboardList", stateService.getDistrictDashbord());
        return mv;
    }

    @GetMapping(value = { "/reportingPending" })
    public ModelAndView reportingPending(ModelAndView mv) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mv = new ModelAndView("state/pending");
        mv.addObject("passengerList", stateService.getPendingPassengerList(sdf.format(dt)));
        return mv;
    }

    @PostMapping(value = { "/reportingPending" })
    public ModelAndView postReportingPending(ModelAndView mv, @RequestParam("date") String date) {
        mv = new ModelAndView("state/pending");
        mv.addObject("passengerList", stateService.getPendingPassengerList(date));
        return mv;
    }

    @GetMapping(value = { "/reportedPassengers" })
    public ModelAndView reportedPassengers(ModelAndView mv) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mv = new ModelAndView("state/reported");
        mv.addObject("passengerList", stateService.getReportedPassengerList(sdf.format(dt)));
        return mv;
    }

    @PostMapping(value = { "/reportedPassengers" })
    public ModelAndView postReportedPassengers(ModelAndView mv, @RequestParam("date") String date) {
        mv = new ModelAndView("state/reported");
        mv.addObject("passengerList", stateService.getReportedPassengerList(date));
        return mv;
    }

    @GetMapping(value = { "/allPassengers" })
    public ModelAndView allPassengers(ModelAndView mv) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mv = new ModelAndView("state/allPassengers");
        mv.addObject("passengerList", stateService.getAllPassengerList(sdf.format(dt)));
        return mv;
    }

    @PostMapping(value = { "/allPassengers" })
    public ModelAndView postAllPassengers(ModelAndView mv, @RequestParam("date") String date) {
        mv = new ModelAndView("state/allPassengers");
        mv.addObject("passengerList", stateService.getAllPassengerList(date));
        return mv;
    }

}
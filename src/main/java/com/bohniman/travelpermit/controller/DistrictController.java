package com.bohniman.travelpermit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bohniman.travelpermit.services.DistrictService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mv = new ModelAndView("district/pending");
        mv.addObject("passengerList", districtService.getPendingPassengerList(sdf.format(dt), user));
        return mv;
    }

    @PostMapping(value = { "/reportingPending" })
    public ModelAndView postReportingPending(ModelAndView mv, @RequestParam("date") String date) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv = new ModelAndView("district/pending");
        mv.addObject("passengerList", districtService.getPendingPassengerList(date, user));
        return mv;
    }

    @GetMapping(value = { "/reportedPassengers" })
    public ModelAndView reportedPassengers(ModelAndView mv) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mv = new ModelAndView("district/reported");
        mv.addObject("passengerList", districtService.getReportedPassengerList(sdf.format(dt), user));
        return mv;
    }

    @PostMapping(value = { "/reportedPassengers" })
    public ModelAndView postReportedPassengers(ModelAndView mv, @RequestParam("date") String date) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv = new ModelAndView("district/reported");
        mv.addObject("passengerList", districtService.getReportedPassengerList(date, user));
        return mv;
    }

    @GetMapping(value = { "/allPassengers" })
    public ModelAndView allPassengers(ModelAndView mv) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mv = new ModelAndView("district/allPassengers");
        mv.addObject("passengerList", districtService.getAllPassengerList(sdf.format(dt), user));
        return mv;
    }

    @PostMapping(value = { "/allPassengers" })
    public ModelAndView postAllPassengers(ModelAndView mv, @RequestParam("date") String date) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv = new ModelAndView("district/allPassengers");
        mv.addObject("passengerList", districtService.getAllPassengerList(date, user));
        return mv;
    }

}
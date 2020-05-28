package com.bohniman.travelpermit.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import com.bohniman.travelpermit.model.QrCodeData;
import com.bohniman.travelpermit.model.QrCodeMemberDetail;
import com.bohniman.travelpermit.model.QrcodeScanDetail;
import com.bohniman.travelpermit.services.DeoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/deo")
public class DeoController {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    @Autowired
    DeoService deoService;

    @GetMapping(value = { "/home" })
    public ModelAndView noRole(ModelAndView mv) {
        // UserDetails user = (UserDetails)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv = new ModelAndView("deo/index");
        return mv;
    }

    @PostMapping(value = { "/getMemberDetails" })
    public ModelAndView getMemberDetails(ModelAndView mv, @RequestParam("token") String token) {
        // UserDetails user = (UserDetails)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv = new ModelAndView("deo/entry");
        QrCodeData qrCodeData = deoService.getQrCodeData(token);
        if (qrCodeData == null) {
            mv = new ModelAndView("deo/index");
            mv.addObject("msgError", "No Member Found for Token Number - " + token);
            return mv;
        } else {
            List<QrCodeMemberDetail> memList = qrCodeData.getMemberDetails();
            if (memList.size() > 0) {
                mv.addObject("member", memList.get(0));
            } else {
                mv = new ModelAndView("deo/index");
                mv.addObject("msgError", "No Member Found for Token Number - " + token);
                return mv;
            }
        }
        mv.addObject("token", token);
        for (QrcodeScanDetail scanDetail : qrCodeData.getScanDetails()) {
            if (scanDetail.getScanLocation().isScreeningCenter() == false) {
                mv.addObject("reportingTime", sdf.format(scanDetail.getScanDateTime()));
                break;
            }
        }
        mv.addObject("distList", deoService.getAllDistrict());
        mv.addObject("screeningList", deoService.getAllScreeningCentre());
        return mv;
    }

    @PostMapping(value = { "/saveMemberDetails" })
    public ModelAndView saveMemberDetails(ModelAndView mv, @ModelAttribute("member") QrCodeMemberDetail member,
            @RequestParam("token") String token) {
        if (member != null) {
            if (deoService.saveMemberDetails(member) != null) {
                mv = new ModelAndView("deo/index");
                mv.addObject("msgSuccess", "Passanger Details Saved Successfully !");
            } else {
                mv = new ModelAndView("deo/index");
                mv.addObject("msgError", "Passanger Details Saving Failed!");
            }
        } else {
            mv = new ModelAndView("deo/entry");
            QrCodeData qrCodeData = deoService.getQrCodeData(token);
            if (qrCodeData == null) {
                mv = new ModelAndView("deo/index");
                mv.addObject("msgError", "No Member Found for Token Number - " + token);
                return mv;
            } else {
                mv.addObject("member", member);
            }
            mv.addObject("token", token);
            for (QrcodeScanDetail scanDetail : qrCodeData.getScanDetails()) {
                if (scanDetail.getScanLocation().isScreeningCenter() == false) {
                    mv.addObject("reportingTime", sdf.format(scanDetail.getScanDateTime()));
                    break;
                }
            }
            mv.addObject("distList", deoService.getAllDistrict());
            mv.addObject("screeningList", deoService.getAllScreeningCentre());
        }
        return mv;
    }
}
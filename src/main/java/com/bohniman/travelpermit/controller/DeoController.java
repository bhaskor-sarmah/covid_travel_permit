package com.bohniman.travelpermit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.bohniman.travelpermit.model.QrCodeData;
import com.bohniman.travelpermit.model.QrCodeMemberDetail;
import com.bohniman.travelpermit.model.QrcodeScanDetail;
import com.bohniman.travelpermit.payload.QrCodePayload;
import com.bohniman.travelpermit.services.DeoService;
import com.bohniman.travelpermit.utils.LoggedInUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping(value = { "/new-entry" })
    public ModelAndView newEntry(ModelAndView mv, @ModelAttribute("flashMessage") String flashMessage) {

        String username = LoggedInUser.getLoggedInUsername();
        QrCodePayload qrCodePayload = deoService.getQrCodePayload(username);

        mv = new ModelAndView("deo/new-entry");
        mv.addObject("qrCodePayload", qrCodePayload);
        mv.addObject("distList", deoService.getAllDistrict());
        mv.addObject("screeningList", deoService.getAllScreeningCentre());
        mv.addObject("flashMessage", flashMessage);
        System.out.println(qrCodePayload);
        return mv;
    }

    @PostMapping(value = { "/save-entry" })
    public RedirectView saveEntry(RedirectAttributes attributes, @Valid @ModelAttribute QrCodePayload qrCodePayload) {

        if (deoService.saveEntry(qrCodePayload)) {
            attributes.addFlashAttribute("flashMessage", "Previous entry saved successfully");
        } else {
            attributes.addFlashAttribute("flashMessage", "Previous entry couldn't be saved.");
        }
        return new RedirectView("/deo/new-entry");
    }

    // ========================================================================
    // # PHOTO LOADING / Map Only GET requests
    // ========================================================================
    @GetMapping(path = "/get-image/{tokenId}")
    @ResponseBody
    public byte[] getSpdSuspectPhoto(@PathVariable(name = "tokenId") String tokenId) throws Exception {

        QrCodeData qrCodeData = deoService.getQrCodeData(tokenId);
        if (!Objects.equals(qrCodeData, null)) {
            File initialFile = new File(qrCodeData.getImagePath());
            InputStream targetStream = new FileInputStream(initialFile);
            byte[] data = StreamUtils.copyToByteArray(targetStream);
            return data;
        } else {
            throw new Exception("Image not found");
        }

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
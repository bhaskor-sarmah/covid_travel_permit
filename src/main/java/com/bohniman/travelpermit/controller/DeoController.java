package com.bohniman.travelpermit.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.bohniman.travelpermit.model.ClickedData;
import com.bohniman.travelpermit.model.Document;
import com.bohniman.travelpermit.model.QrCodeData;
import com.bohniman.travelpermit.model.QrCodeMemberDetail;
import com.bohniman.travelpermit.model.QrcodeScanDetail;
import com.bohniman.travelpermit.payload.QrCodePayload;
import com.bohniman.travelpermit.services.DeoService;
import com.bohniman.travelpermit.utils.AppStaticData;
import com.bohniman.travelpermit.utils.LoggedInUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView newEntry(ModelAndView mv, @ModelAttribute("flashMessage") String flashMessage,
            @ModelAttribute("qrCodePayload") QrCodePayload qrCodePayload,
            @ModelAttribute("entryStatus") String entryStatus) {

        mv = new ModelAndView("deo/new-entry");
        String username = LoggedInUser.getLoggedInUsername();

        ClickedData clickedData = deoService.getClickedData(username);

        if (!Objects.equals(clickedData, null)) {
            if (Objects.equals(qrCodePayload.getClickedDataId(), null)) {
                qrCodePayload = new QrCodePayload();
                qrCodePayload.setClickedDataId(clickedData.getId());
                qrCodePayload.setTokenId(clickedData.getTokenId());
                qrCodePayload.setAttemptNumber(AppStaticData.ATTEMPT_ONE);
            }
            mv.addObject("documents", clickedData.getDocuments());
            mv.addObject("flashMessage", flashMessage);
        } else {
            if (Objects.equals(entryStatus, AppStaticData.ENTRY_STATUS_COMPLETED)) {
                mv.addObject("flashMessage", "Data saved successfully. But no more token available.");
            } else {
                mv.addObject("flashMessage", "No token id found.");
            }
        }

        mv.addObject("qrCodePayload", qrCodePayload);
        mv.addObject("distList", deoService.getAllDistrict());
        mv.addObject("screeningList", deoService.getAllScreeningCentre());
        System.out.println(clickedData);
        System.out.println(qrCodePayload);
        return mv;
    }

    @PostMapping(value = { "/save-entry" })
    public RedirectView saveEntry(RedirectAttributes attributes, @Valid @ModelAttribute QrCodePayload qrCodePayload,
            BindingResult bindingResult) {
        String username = LoggedInUser.getLoggedInUsername();
        if (Objects.equals(qrCodePayload.getAttemptNumber(), AppStaticData.ATTEMPT_ONE)) {
            Map<String, String> result = deoService.saveEntry(qrCodePayload, username);
            if (result.containsKey(AppStaticData.ENTRY_STATUS_COMPLETED)) {
                attributes.addFlashAttribute("flashMessage", result.get(AppStaticData.ENTRY_STATUS_COMPLETED));
                attributes.addFlashAttribute("entryStatus", AppStaticData.ENTRY_STATUS_COMPLETED);
            } else if (result.containsKey(AppStaticData.ENTRY_STATUS_DUPLICATE)) {
                qrCodePayload.setAttemptNumber(AppStaticData.ATTEMPT_TWO);
                attributes.addFlashAttribute("flashMessage", result.get(AppStaticData.ENTRY_STATUS_DUPLICATE));
                attributes.addFlashAttribute("entryStatus", AppStaticData.ENTRY_STATUS_DUPLICATE);
                attributes.addFlashAttribute("qrCodePayload", qrCodePayload);
            } else if (result.containsKey(AppStaticData.ENTRY_STATUS_ERROR)) {
                qrCodePayload.setAttemptNumber(AppStaticData.ATTEMPT_ONE);
                attributes.addFlashAttribute("flashMessage", result.get(AppStaticData.ENTRY_STATUS_ERROR));
                attributes.addFlashAttribute("entryStatus", AppStaticData.ENTRY_STATUS_ERROR);
                attributes.addFlashAttribute("qrCodePayload", qrCodePayload);
            }

        } else {
            Map<String, String> result = deoService.duplicateEntry(qrCodePayload, username);
            if (result.containsKey(AppStaticData.ENTRY_STATUS_COMPLETED)) {
                attributes.addFlashAttribute("flashMessage", result.get(AppStaticData.ENTRY_STATUS_COMPLETED));
                attributes.addFlashAttribute("entryStatus", AppStaticData.ENTRY_STATUS_COMPLETED);
            } else if (result.containsKey(AppStaticData.ENTRY_STATUS_ERROR)) {
                qrCodePayload.setAttemptNumber(AppStaticData.ATTEMPT_ONE);
                attributes.addFlashAttribute("flashMessage", result.get(AppStaticData.ENTRY_STATUS_ERROR));
                attributes.addFlashAttribute("entryStatus", AppStaticData.ENTRY_STATUS_ERROR);
                attributes.addFlashAttribute("qrCodePayload", qrCodePayload);
            }
        }

        return new RedirectView("/deo/new-entry");
    }

    // ========================================================================
    // # PHOTO LOADING / Map Only GET requests
    // ========================================================================
    // @GetMapping(path = "/get-image/{tokenId}")
    // @ResponseBody
    // public byte[] getSpdSuspectPhoto(@PathVariable(name = "tokenId") String
    // tokenId) throws Exception {

    // QrCodeData qrCodeData = deoService.getQrCodeData(tokenId);
    // if (!Objects.equals(qrCodeData, null)) {
    // File initialFile = new File(qrCodeData.getImagePath());
    // InputStream targetStream = new FileInputStream(initialFile);
    // byte[] data = StreamUtils.copyToByteArray(targetStream);
    // return data;
    // } else {
    // throw new Exception("Image not found");
    // }

    // }

    @GetMapping(value = "/get-image/{documentId}")
    public void getImage(@PathVariable(name = "documentId") Long documentId, HttpServletResponse response,
            HttpServletRequest request) throws ServletException, IOException {

        Optional<Document> document = deoService.getDocumentId(documentId);
        if (document.isPresent()) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(document.get().getByteFile());
            response.getOutputStream().close();
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
package com.bohniman.travelpermit.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bohniman.travelpermit.model.QrCodeData;
import com.bohniman.travelpermit.model.QrCodeMemberDetail;
import com.bohniman.travelpermit.model.QrcodeScanDetail;
import com.bohniman.travelpermit.payload.PassengerDetails;
import com.bohniman.travelpermit.repository.QrCodeDataRepo;
import com.bohniman.travelpermit.repository.UserRepository;
import com.bohniman.travelpermit.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class DistrictService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    QrCodeDataRepo qrCodeRepo;

    public List<PassengerDetails> getPendingPassengerList(UserDetails user) {
        String district = userRepo.findByUsername(user.getUsername()).get().getUserScope().getDistrict()
                .getDistrictName();
        List<PassengerDetails> passengerList = new ArrayList<>();
        List<QrCodeData> qrList = qrCodeRepo.findAllByReachedScreeningCenterAndDestinationDistrictAndStatus(false,
                district, "ACTIVE");

        Date dt = new Date();
        for (QrCodeData qr : qrList) {
            PassengerDetails passengerDetails = new PassengerDetails();
            List<QrcodeScanDetail> scanDetails = qr.getScanDetails();
            List<QrCodeMemberDetail> memList = qr.getMemberDetails();
            for (QrCodeMemberDetail mem : memList) {
                passengerDetails.setTokenNo(qr.getTokenId());
                passengerDetails.setName(mem.getName());
                passengerDetails.setPhone(mem.getMobileNumber());
                passengerDetails.setDistrict(mem.getDistrict());
                passengerDetails.setThana(mem.getThana());
                passengerDetails.setPincode(mem.getPin());
                passengerDetails.setAddress(mem.getAddress());
                passengerDetails.setScreening_center(mem.getAssignedScreeningCenter());
                for (QrcodeScanDetail scanDetail : scanDetails) {
                    if (scanDetail.getScanLocation().isScreeningCenter() == false) {
                        passengerDetails.setArrival_time(scanDetail.getScanDateTime().toString());
                    }
                    if (scanDetail.getScanLocation().isScreeningCenter() == true) {
                        passengerDetails.setReporting_time(scanDetail.getScanDateTime().toString());
                    }
                    passengerDetails.setTimeDiff(
                            DateUtil.friendlyTimeDiff(dt.getTime() - scanDetail.getScanDateTime().getTime()));
                }
                passengerList.add(passengerDetails);
            }
        }
        return passengerList;
    }

    public List<PassengerDetails> getReportedPassengerList(UserDetails user) {
        String district = userRepo.findByUsername(user.getUsername()).get().getUserScope().getDistrict()
                .getDistrictName();
        List<PassengerDetails> passengerList = new ArrayList<>();
        List<QrCodeData> qrList = qrCodeRepo.findAllByReachedScreeningCenterAndDestinationDistrictAndStatus(true,
                district, "ACTIVE");

        Date dt = new Date();
        for (QrCodeData qr : qrList) {
            PassengerDetails passengerDetails = new PassengerDetails();
            List<QrcodeScanDetail> scanDetails = qr.getScanDetails();
            List<QrCodeMemberDetail> memList = qr.getMemberDetails();
            for (QrCodeMemberDetail mem : memList) {
                passengerDetails.setTokenNo(qr.getTokenId());
                passengerDetails.setName(mem.getName());
                passengerDetails.setPhone(mem.getMobileNumber());
                passengerDetails.setDistrict(mem.getDistrict());
                passengerDetails.setThana(mem.getThana());
                passengerDetails.setPincode(mem.getPin());
                passengerDetails.setAddress(mem.getAddress());
                passengerDetails.setScreening_center(mem.getAssignedScreeningCenter());
                for (QrcodeScanDetail scanDetail : scanDetails) {
                    if (scanDetail.getScanLocation().isScreeningCenter() == false) {
                        passengerDetails.setArrival_time(scanDetail.getScanDateTime().toString());
                    }
                    if (scanDetail.getScanLocation().isScreeningCenter() == true) {
                        passengerDetails.setReporting_time(scanDetail.getScanDateTime().toString());
                    }
                    passengerDetails.setTimeDiff(
                            DateUtil.friendlyTimeDiff(dt.getTime() - scanDetail.getScanDateTime().getTime()));
                }
                passengerList.add(passengerDetails);
            }
        }
        return passengerList;
    }

}
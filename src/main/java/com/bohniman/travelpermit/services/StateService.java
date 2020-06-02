package com.bohniman.travelpermit.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bohniman.travelpermit.model.District;
import com.bohniman.travelpermit.model.QrCodeData;
import com.bohniman.travelpermit.model.QrCodeMemberDetail;
import com.bohniman.travelpermit.model.QrcodeScanDetail;
import com.bohniman.travelpermit.payload.DistrictDashboard;
import com.bohniman.travelpermit.payload.PassengerDetails;
import com.bohniman.travelpermit.repository.DistrictRepo;
import com.bohniman.travelpermit.repository.QrCodeDataRepo;
import com.bohniman.travelpermit.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    @Autowired
    DistrictRepo distRepo;

    @Autowired
    QrCodeDataRepo qrCodeRepo;

    public List<PassengerDetails> getAllPassengerList() {
        // String district =
        // userRepo.findByUsername(user.getUsername()).get().getUserScope().getDistrict()
        // .getDistrictName();
        List<PassengerDetails> passengerList = new ArrayList<>();
        List<QrCodeData> qrList = qrCodeRepo.findAllByStatus("ACTIVE");

        Date dt = new Date();
        Date dtA = new Date();
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
                        passengerDetails.setArrival_time(sdf.format(scanDetail.getScanDateTime()));
                        dtA = scanDetail.getScanDateTime();
                    }
                    if (scanDetail.getScanLocation().isScreeningCenter() == true) {
                        passengerDetails.setReporting_time(sdf.format(scanDetail.getScanDateTime()));
                        dt = scanDetail.getScanDateTime();
                    }
                }
                passengerDetails.setTimeDiff(DateUtil.friendlyTimeDiff(dt.getTime() - dtA.getTime()));
                passengerList.add(passengerDetails);
            }
        }
        return passengerList;
    }

    public List<PassengerDetails> getPendingPassengerList() {
        // String district =
        // userRepo.findByUsername(user.getUsername()).get().getUserScope().getDistrict()
        // .getDistrictName();
        List<PassengerDetails> passengerList = new ArrayList<>();
        List<QrCodeData> qrList = qrCodeRepo.findAllByReachedScreeningCenterAndStatus(false, "ACTIVE");

        // Date dt = new Date();
        // Date dtA = new Date();
        for (QrCodeData qr : qrList) {
            PassengerDetails passengerDetails = new PassengerDetails();
            List<QrcodeScanDetail> scanDetails = qr.getScanDetails();
            List<QrCodeMemberDetail> memList = qr.getMemberDetails();
            for (QrCodeMemberDetail mem : memList) {
                Date dt = new Date();
                Date dtA = new Date();
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
                        passengerDetails.setArrival_time(sdf.format(scanDetail.getScanDateTime()));
                        dtA = scanDetail.getScanDateTime();
                    }
                    if (scanDetail.getScanLocation().isScreeningCenter() == true) {
                        passengerDetails.setReporting_time(sdf.format(scanDetail.getScanDateTime()));
                        dt = scanDetail.getScanDateTime();
                    }
                }
                passengerDetails.setTimeDiff(DateUtil.friendlyTimeDiff(dt.getTime() - dtA.getTime()));
                passengerList.add(passengerDetails);
            }
        }
        return passengerList;
    }

    public List<PassengerDetails> getReportedPassengerList() {
        // String district =
        // userRepo.findByUsername(user.getUsername()).get().getUserScope().getDistrict()
        // .getDistrictName();
        List<PassengerDetails> passengerList = new ArrayList<>();
        List<QrCodeData> qrList = qrCodeRepo.findAllByReachedScreeningCenterAndStatus(true, "ACTIVE");

        // Date dt = new Date();
        // Date dtA = new Date();
        for (QrCodeData qr : qrList) {
            PassengerDetails passengerDetails = new PassengerDetails();
            List<QrcodeScanDetail> scanDetails = qr.getScanDetails();
            List<QrCodeMemberDetail> memList = qr.getMemberDetails();
            for (QrCodeMemberDetail mem : memList) {
                Date dt = new Date();
                Date dtA = new Date();
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
                        passengerDetails.setArrival_time(sdf.format(scanDetail.getScanDateTime()));
                        dtA = scanDetail.getScanDateTime();
                    }
                    if (scanDetail.getScanLocation().isScreeningCenter() == true) {
                        passengerDetails.setReporting_time(sdf.format(scanDetail.getScanDateTime()));
                        dt = scanDetail.getScanDateTime();
                    }
                }
                passengerDetails.setTimeDiff(DateUtil.friendlyTimeDiff(dt.getTime() - dtA.getTime()));
                passengerList.add(passengerDetails);
            }
        }
        return passengerList;
    }

    public List<DistrictDashboard> getDistrictDashbord() {
        List<DistrictDashboard> dashboardList = new ArrayList<>();
        List<District> distList = distRepo.findAllByOrderByDistrict();
        Long allPass = 0L;
        Long allRep = 0L;
        Long allNotRep = 0L;
        for (District d : distList) {
            DistrictDashboard db = new DistrictDashboard();
            db.setDistrict(d.getDistrict());
            db.setTtl_no(qrCodeRepo.getDistrictWiseTotalCount(d.getDistrict()));
            db.setRpt_no(qrCodeRepo.getDistrictWiseScreenedTotalCount(d.getDistrict()));
            db.setNot_rpt_no(qrCodeRepo.getDistrictWiseNotScreenedTotalCount(d.getDistrict()));
            dashboardList.add(db);
            allPass += db.getTtl_no();
            allRep += db.getRpt_no();
            allNotRep += db.getNot_rpt_no();
        }
        DistrictDashboard db = new DistrictDashboard();
        db.setDistrict("TOTAL");
        db.setTtl_no(allPass);
        db.setRpt_no(allRep);
        db.setNot_rpt_no(allNotRep);
        dashboardList.add(db);
        for (DistrictDashboard d : dashboardList) {
            System.out.println(d.getDistrict());
        }
        return dashboardList;
    }

}
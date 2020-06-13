package com.bohniman.travelpermit.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bohniman.travelpermit.model.QrCodeData;
import com.bohniman.travelpermit.model.QrCodeMemberDetail;
import com.bohniman.travelpermit.model.QrcodeScanDetail;
import com.bohniman.travelpermit.payload.MasterTable;
import com.bohniman.travelpermit.payload.PassengerDetails;
import com.bohniman.travelpermit.repository.QrCodeDataRepo;
import com.bohniman.travelpermit.repository.QrcodeScanDetailRepo;
import com.bohniman.travelpermit.repository.UserRepository;
import com.bohniman.travelpermit.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class DistrictService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    @Autowired
    UserRepository userRepo;

    @Autowired
    QrCodeDataRepo qrCodeRepo;

    @Autowired
    QrcodeScanDetailRepo qrcodeScanDetailRepo;

    public List<MasterTable> getAllPassengerList(String date, UserDetails user) {
        String district = userRepo.findByUsername(user.getUsername()).get().getUserScope().getDistrict()
                .getDistrictName();
        List<Object[]> objList = qrCodeRepo.findAllByDateAndDistrict("COMPLETED", date, district);
        List<MasterTable> mList = new ArrayList<>();
        for (Object[] obj : objList) {
            int i = 0;
            MasterTable master = new MasterTable();
            master.setQrcodeId(String.valueOf(obj[i++]));
            master.setReachedScreeningCenter(((Boolean) obj[i++]) ? "1" : "0");
            master.setTokenId((String) obj[i++]);
            master.setDestinationDistrict((String) obj[i++]);
            master.setName((String) obj[i++]);
            master.setMobileNumber((String) obj[i++]);
            master.setPin((String) obj[i++]);
            master.setThana((String) obj[i++]);
            master.setAddress((String) obj[i++]);
            master.setAssignedScreeningCenter((String) obj[i++]);
            master.setEntryStatus((String) obj[i++]);
            master.setUsername((String) obj[i++]);
            master.setEntryDate((String) obj[i++]);
            master.setEntryTime((String) obj[i++]);
            List<Object[]> scanDetail = qrcodeScanDetailRepo.findAllbyToken(master.getTokenId());
            for (Object[] objNew : scanDetail) {
                master.setReportingDate((String) objNew[1]);
                master.setReportingTime((String) objNew[2]);
            }
            mList.add(master);
        }
        return mList;
    }

    public List<MasterTable> getPendingPassengerList(String date, UserDetails user) {
        String district = userRepo.findByUsername(user.getUsername()).get().getUserScope().getDistrict()
                .getDistrictName();
        List<Object[]> objList = qrCodeRepo.findAllPendingByDateAndDistrict("COMPLETED", date, district);
        List<MasterTable> mList = new ArrayList<>();
        for (Object[] obj : objList) {
            int i = 0;
            MasterTable master = new MasterTable();
            master.setQrcodeId(String.valueOf(obj[i++]));
            master.setReachedScreeningCenter(((Boolean) obj[i++]) ? "1" : "0");
            master.setTokenId((String) obj[i++]);
            master.setDestinationDistrict((String) obj[i++]);
            master.setName((String) obj[i++]);
            master.setMobileNumber((String) obj[i++]);
            master.setPin((String) obj[i++]);
            master.setThana((String) obj[i++]);
            master.setAddress((String) obj[i++]);
            master.setAssignedScreeningCenter((String) obj[i++]);
            master.setEntryStatus((String) obj[i++]);
            master.setUsername((String) obj[i++]);
            master.setEntryDate((String) obj[i++]);
            master.setEntryTime((String) obj[i++]);
            mList.add(master);
        }
        return mList;
    }

    public List<MasterTable> getReportedPassengerList(String date, UserDetails user) {
        String district = userRepo.findByUsername(user.getUsername()).get().getUserScope().getDistrict()
                .getDistrictName();
        List<Object[]> objList = qrCodeRepo.findAllArrivedByDateAndDistrict("COMPLETED", date, district);
        List<MasterTable> mList = new ArrayList<>();
        for (Object[] obj : objList) {
            int i = 0;
            MasterTable master = new MasterTable();
            master.setQrcodeId(String.valueOf(obj[i++]));
            master.setReachedScreeningCenter(((Boolean) obj[i++]) ? "1" : "0");
            master.setTokenId((String) obj[i++]);
            master.setDestinationDistrict((String) obj[i++]);
            master.setName((String) obj[i++]);
            master.setMobileNumber((String) obj[i++]);
            master.setPin((String) obj[i++]);
            master.setThana((String) obj[i++]);
            master.setAddress((String) obj[i++]);
            master.setAssignedScreeningCenter((String) obj[i++]);
            master.setEntryStatus((String) obj[i++]);
            master.setUsername((String) obj[i++]);
            master.setEntryDate((String) obj[i++]);
            master.setEntryTime((String) obj[i++]);
            List<Object[]> scanDetail = qrcodeScanDetailRepo.findAllbyToken(master.getTokenId());
            for (Object[] objNew : scanDetail) {
                master.setReportingDate((String) objNew[1]);
                master.setReportingTime((String) objNew[2]);
            }
            mList.add(master);
        }
        return mList;
    }

}
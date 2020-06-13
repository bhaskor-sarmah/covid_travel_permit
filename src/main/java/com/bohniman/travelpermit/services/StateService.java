package com.bohniman.travelpermit.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bohniman.travelpermit.model.District;
import com.bohniman.travelpermit.payload.DistrictDashboard;
import com.bohniman.travelpermit.payload.MasterTable;
import com.bohniman.travelpermit.repository.DistrictRepo;
import com.bohniman.travelpermit.repository.QrCodeDataRepo;
import com.bohniman.travelpermit.repository.QrcodeScanDetailRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    @Autowired
    DistrictRepo distRepo;

    @Autowired
    QrCodeDataRepo qrCodeRepo;

    @Autowired
    QrcodeScanDetailRepo qrcodeScanDetailRepo;

    public List<MasterTable> getAllPassengerList(String date) {
        List<Object[]> objList = qrCodeRepo.findAllByDate("COMPLETED", date);
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

    public List<MasterTable> getPendingPassengerList(String date) {
        List<Object[]> objList = qrCodeRepo.findAllPendingByDate("COMPLETED", date);
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

    public List<MasterTable> getReportedPassengerList(String date) {
        List<Object[]> objList = qrCodeRepo.findAllArrivedByDate("COMPLETED", date);
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
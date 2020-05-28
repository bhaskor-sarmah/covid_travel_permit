package com.bohniman.travelpermit.services;

import java.util.List;
import java.util.Optional;

import com.bohniman.travelpermit.model.District;
import com.bohniman.travelpermit.model.QrCodeData;
import com.bohniman.travelpermit.model.QrCodeMemberDetail;
import com.bohniman.travelpermit.model.ScanLocation;
import com.bohniman.travelpermit.repository.DistrictRepo;
import com.bohniman.travelpermit.repository.QrCodeDataRepo;
import com.bohniman.travelpermit.repository.QrCodeMemberDetailRepo;
import com.bohniman.travelpermit.repository.ScanLocationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeoService {

    @Autowired
    QrCodeDataRepo qrCodeDataRepo;

    @Autowired
    DistrictRepo districtRepo;

    @Autowired
    ScanLocationRepo scanLocationRepo;

    @Autowired
    QrCodeMemberDetailRepo qrCodeMemberDetailRepo;

    public QrCodeMemberDetail getMember(String token) {
        Optional<QrCodeData> qrcodeData = qrCodeDataRepo.findByTokenId(token);
        if (qrcodeData.isPresent()) {
            List<QrCodeMemberDetail> memList = qrcodeData.get().getMemberDetails();
            if (memList.size() > 0) {
                return memList.get(0);
            }
        }
        return null;
    }

    public List<District> getAllDistrict() {
        return districtRepo.findAllByOrderByDistrict();
    }

    public QrCodeData getQrCodeData(String token) {
        Optional<QrCodeData> qrcodeData = qrCodeDataRepo.findByTokenId(token);
        if (qrcodeData.isPresent()) {
            return qrcodeData.get();
        }
        return null;
    }

    public List<ScanLocation> getAllScreeningCentre() {
        return scanLocationRepo.findAllByScreeningCenterOrderByLocation(true);
    }

    public QrCodeMemberDetail saveMemberDetails(Long id, String address, String thana, String pin) {
        QrCodeMemberDetail mem = qrCodeMemberDetailRepo.findById(id).get();
        mem.setAddress(address);
        mem.setThana(thana);
        mem.setPin(pin);
        return qrCodeMemberDetailRepo.save(mem);
    }

    public QrCodeMemberDetail saveMemberDetails(QrCodeMemberDetail member) {
        QrCodeMemberDetail mem = qrCodeMemberDetailRepo.findById(member.getId()).get();
        mem.setAddress(member.getAddress());
        mem.setThana(member.getThana());
        mem.setPin(member.getPin());
        return qrCodeMemberDetailRepo.save(mem);
    }

}
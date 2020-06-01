package com.bohniman.travelpermit.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Null;

import com.bohniman.travelpermit.model.ClickedData;
import com.bohniman.travelpermit.model.District;
import com.bohniman.travelpermit.model.Document;
import com.bohniman.travelpermit.model.QrCodeData;
import com.bohniman.travelpermit.model.QrCodeMemberDetail;
import com.bohniman.travelpermit.model.ScanLocation;
import com.bohniman.travelpermit.payload.QrCodePayload;
import com.bohniman.travelpermit.repository.ClickedDataRepository;
import com.bohniman.travelpermit.repository.DistrictRepo;
import com.bohniman.travelpermit.repository.DocumentRepository;
import com.bohniman.travelpermit.repository.QrCodeDataRepo;
import com.bohniman.travelpermit.repository.QrCodeMemberDetailRepo;
import com.bohniman.travelpermit.repository.ScanLocationRepo;
import com.bohniman.travelpermit.utils.AppStaticData;

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

    @Autowired
    ClickedDataRepository clickedDataRepository;

    @Autowired
    DocumentRepository documentRepository;

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

    // public QrCodePayload getQrCodePayload(String username) {

    // QrCodeData qrCodeData =
    // qrCodeDataRepo.findByStatusAndUsernameAndEntryStatus("ACTIVE", username,
    // "Pending");
    // if (Objects.equals(qrCodeData, null)) {
    // qrCodeData =
    // qrCodeDataRepo.findTopByStatusAndEntryStatusIsNullOrderByTokenIdAsc("ACTIVE");
    // qrCodeData.setUsername(username);
    // qrCodeData.setEntryStatus("Pending");
    // qrCodeDataRepo.save(qrCodeData);
    // }

    // QrCodePayload qrCodePayload = new QrCodePayload();
    // qrCodePayload.setId(qrCodeData.getId());
    // qrCodePayload.setTokenId(qrCodeData.getTokenId());
    // qrCodePayload.setDestinationDistrict(qrCodeData.getDestinationDistrict());
    // qrCodePayload.setImagePath(qrCodeData.getImagePath());
    // return qrCodePayload;
    // }

    public Map<String, String> saveEntry(QrCodePayload qrCodePayload, String username) {

        Map<String, String> result = new HashMap<>();

        Optional<QrCodeData> optionalQrCodeData = qrCodeDataRepo.findByTokenId(qrCodePayload.getTokenId());

        if (!optionalQrCodeData.isPresent()) {

            try {
                QrCodeData qrCodeData = new QrCodeData();

                QrCodeMemberDetail qrCodeMemberDetail = new QrCodeMemberDetail();
                qrCodeMemberDetail.setAddress(qrCodePayload.getAddress());
                qrCodeMemberDetail.setAssignedScreeningCenter(qrCodePayload.getAssignedScreeningCenter());
                qrCodeMemberDetail.setDistrict(qrCodePayload.getDistrict());
                qrCodeMemberDetail.setMobileNumber(qrCodePayload.getMobileNumber());
                qrCodeMemberDetail.setName(qrCodePayload.getName());
                qrCodeMemberDetail.setPin(qrCodePayload.getPin());
                qrCodeMemberDetail.setThana(qrCodePayload.getThana());

                List<QrCodeMemberDetail> members = new ArrayList<>();
                members.add(qrCodeMemberDetail);
                qrCodeData.setMemberDetails(members);
                qrCodeData.setReachedScreeningCenter(false);
                qrCodeData.setDestinationDistrict(qrCodePayload.getDistrict());

                qrCodeDataRepo.save(qrCodeData);

                Optional<ClickedData> optionalClickedData = clickedDataRepository
                        .findById(qrCodePayload.getClickedDataId());
                if (optionalClickedData.isPresent()) {
                    ClickedData clickedData = optionalClickedData.get();
                    clickedData.setTokenId(qrCodeData.getTokenId());
                    clickedData.setEntryStatus(AppStaticData.ENTRY_STATUS_COMPLETED);
                    clickedDataRepository.save(clickedData);

                    result.put(AppStaticData.ENTRY_STATUS_COMPLETED, "Data saved successfully. Please try again.");
                } else {

                    result.put(AppStaticData.ENTRY_STATUS_ERROR, "Invalid data. Please try again.");
                }
            } catch (Exception e) {
                result.put(AppStaticData.ENTRY_STATUS_ERROR, "Couldn't save data. Please try again.");
            }

        } else {

            try {
                QrCodeData qrCodeData = optionalQrCodeData.get();

                if (qrCodeData.getMemberDetails().size() == 0) {
                    QrCodeMemberDetail qrCodeMemberDetail = new QrCodeMemberDetail();
                    qrCodeMemberDetail.setAddress(qrCodePayload.getAddress());
                    qrCodeMemberDetail.setAssignedScreeningCenter(qrCodePayload.getAssignedScreeningCenter());
                    qrCodeMemberDetail.setDistrict(qrCodePayload.getDistrict());
                    qrCodeMemberDetail.setMobileNumber(qrCodePayload.getMobileNumber());
                    qrCodeMemberDetail.setName(qrCodePayload.getName());
                    qrCodeMemberDetail.setPin(qrCodePayload.getPin());
                    qrCodeMemberDetail.setThana(qrCodePayload.getThana());

                    List<QrCodeMemberDetail> members = new ArrayList<>();
                    members.add(qrCodeMemberDetail);
                    qrCodeData.setMemberDetails(members);

                    qrCodeDataRepo.save(qrCodeData);

                    Optional<ClickedData> optionalClickedData = clickedDataRepository
                            .findById(qrCodePayload.getClickedDataId());
                    if (optionalClickedData.isPresent()) {
                        ClickedData clickedData = optionalClickedData.get();
                        clickedData.setTokenId(qrCodeData.getTokenId());
                        clickedData.setEntryStatus(AppStaticData.ENTRY_STATUS_COMPLETED);
                        clickedDataRepository.save(clickedData);

                        result.put(AppStaticData.ENTRY_STATUS_COMPLETED, "Data saved successfully. Please try again.");
                    } else {

                        result.put(AppStaticData.ENTRY_STATUS_ERROR, "Invalid data. Please try again.");
                    }

                } else {
                    result.put(AppStaticData.ENTRY_STATUS_DUPLICATE,
                            "Duplicate data. Are you sure want to mark it as Duplicate ?");
                }
            } catch (Exception e) {
                result.put(AppStaticData.ENTRY_STATUS_ERROR, "Couldn't save data. Please try again.");
            }

        }
        return result;
    }

    public ClickedData getClickedData(String username) {
        ClickedData clickedData = clickedDataRepository.findByUsernameAndEntryStatus(username, "Pending");
        if (Objects.equals(clickedData, null)) {
            clickedData = clickedDataRepository.findTopByEntryStatusIsNullOrderByIdAsc();
            clickedData.setUsername(username);
            clickedData.setEntryStatus(AppStaticData.ENTRY_STATUS_PENDING);
            clickedDataRepository.save(clickedData);
        }

        for (Document document : clickedData.getDocuments()) {
            document.setByteFile(null);
            document.setFile(null);
        }
        return clickedData;
    }

    public Map<String, String> duplicateEntry(@Valid QrCodePayload qrCodePayload, String username) {
        Map<String, String> result = new HashMap<>();

        try {
            Optional<ClickedData> optionalClickedData = clickedDataRepository
                    .findById(qrCodePayload.getClickedDataId());
            if (optionalClickedData.isPresent()) {
                ClickedData clickedData = optionalClickedData.get();
                clickedData.setTokenId(qrCodePayload.getTokenId());
                clickedData.setEntryStatus(AppStaticData.ENTRY_STATUS_DUPLICATE);
                clickedDataRepository.save(clickedData);

                result.put(AppStaticData.ENTRY_STATUS_COMPLETED, "Data saved successfully. Please try again.");
            } else {

                result.put(AppStaticData.ENTRY_STATUS_ERROR, "Invalid data. Please try again.");
            }
        } catch (Exception e) {
            result.put(AppStaticData.ENTRY_STATUS_ERROR, "Couldn't save data. Please try again.");
        }

        return result;
    }

    public Optional<Document> getDocumentId(Long documentId) {
        return documentRepository.findById(documentId);
    }

}
package com.bohniman.travelpermit.repository;

import java.util.List;
import java.util.Optional;

import com.bohniman.travelpermit.model.QrCodeData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QrCodeDataRepo extends JpaRepository<QrCodeData, Long> {

        List<QrCodeData> findAllByReachedScreeningCenter(boolean b);

        List<QrCodeData> findAllByReachedScreeningCenterAndDestinationDistrict(boolean b, String string);

        List<QrCodeData> findAllByReachedScreeningCenterAndDestinationDistrictAndStatus(boolean reportedAtScreening,
                        String district, String status);

        Optional<QrCodeData> findByTokenId(String token);

        List<QrCodeData> findAllByReachedScreeningCenterAndStatus(boolean b, String string);

        // QrCodeData findByStatusAndUsernameAndEntryStatus(String string, String
        // username, String string2);

        QrCodeData findTopByOrderByTokenIdAsc();

        // QrCodeData findTopByStatusOrderByTokenIdAsc(String string);

        // QrCodeData findTopByStatusAndEntryStatusNotOrderByTokenIdAsc(String string,
        // String string2);

        // QrCodeData findTopByStatusAndEntryStatusIsNullOrderByTokenIdAsc(String
        // string);

        @Query(value = "SELECT COUNT(*) FROM (SELECT * FROM (SELECT a.token_id,a.reached_screening_center,SUBSTRING_INDEX(b.scan_date_time,\" \",1) AS `date`,SUBSTRING_INDEX(b.scan_date_time,\" \",-1) AS `time`,e.name,e.mobile_number,e.district "
                        + "FROM qrcode_data a,qrcode_scan_detail b,qrcode_data_qrcode_member_detail_mapping c,qrcode_data_qrcode_scan_detail_mapping d,qrcode_member_detail e "
                        + "WHERE a.id = d.qrcode_data_id AND b.id = d.qrcode_scan_detail_id AND a.id = c.qrcode_data_id AND e.id = c.qrcode_member_detail_id) main_table "
                        + "WHERE main_table.district = ?1 GROUP BY main_table.token_id) second_table;", nativeQuery = true)
        Long getDistrictWiseTotalCount(String district);

        @Query(value = "SELECT COUNT(*) FROM (SELECT * FROM (SELECT a.token_id,a.reached_screening_center,SUBSTRING_INDEX(b.scan_date_time,\" \",1) AS `date`,SUBSTRING_INDEX(b.scan_date_time,\" \",-1) AS `time`,e.name,e.mobile_number,e.district "
                        + "FROM qrcode_data a,qrcode_scan_detail b,qrcode_data_qrcode_member_detail_mapping c,qrcode_data_qrcode_scan_detail_mapping d,qrcode_member_detail e "
                        + "WHERE a.id = d.qrcode_data_id AND b.id = d.qrcode_scan_detail_id AND a.id = c.qrcode_data_id AND e.id = c.qrcode_member_detail_id) main_table "
                        + "WHERE main_table.district = ?1 AND main_table.reached_screening_center = 1 GROUP BY main_table.token_id) second_table;", nativeQuery = true)
        Long getDistrictWiseScreenedTotalCount(String district);

        @Query(value = "SELECT COUNT(*) FROM (SELECT * FROM (SELECT a.token_id,a.reached_screening_center,SUBSTRING_INDEX(b.scan_date_time,\" \",1) AS `date`,SUBSTRING_INDEX(b.scan_date_time,\" \",-1) AS `time`,e.name,e.mobile_number,e.district "
                        + "FROM qrcode_data a,qrcode_scan_detail b,qrcode_data_qrcode_member_detail_mapping c,qrcode_data_qrcode_scan_detail_mapping d,qrcode_member_detail e "
                        + "WHERE a.id = d.qrcode_data_id AND b.id = d.qrcode_scan_detail_id AND a.id = c.qrcode_data_id AND e.id = c.qrcode_member_detail_id) main_table "
                        + "WHERE main_table.district = ?1 AND main_table.reached_screening_center = 0 GROUP BY main_table.token_id) second_table;", nativeQuery = true)
        Long getDistrictWiseNotScreenedTotalCount(String district);

        List<QrCodeData> findAllByStatus(String string);

        List<QrCodeData> findAllByDestinationDistrictAndStatus(String district, String string);

}
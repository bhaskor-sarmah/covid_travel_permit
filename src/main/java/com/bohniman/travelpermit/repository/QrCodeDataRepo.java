package com.bohniman.travelpermit.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bohniman.travelpermit.model.QrCodeData;
import com.bohniman.travelpermit.payload.MasterTable;

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

        @Query(value = "SELECT COUNT(*) FROM ( " + "SELECT * FROM ( "
                        + "SELECT a.token_id,a.reached_screening_center,e.name,e.mobile_number,e.district FROM qrcode_data a, "
                        + "qrcode_data_qrcode_member_detail_mapping c,qrcode_member_detail e WHERE a.id = c.qrcode_data_id AND "
                        + "e.id = c.qrcode_member_detail_id) main_table "
                        + "WHERE main_table.district = ?1 GROUP BY main_table.token_id) second_table;", nativeQuery = true)
        Long getDistrictWiseTotalCount(String district);

        @Query(value = "SELECT COUNT(*) FROM ( " + "SELECT * FROM ( "
                        + "SELECT a.token_id,a.reached_screening_center,e.name,e.mobile_number,e.district FROM qrcode_data a, "
                        + "qrcode_data_qrcode_member_detail_mapping c,qrcode_member_detail e WHERE a.id = c.qrcode_data_id AND "
                        + "e.id = c.qrcode_member_detail_id) main_table "
                        + "WHERE main_table.district = ?1 AND main_table.reached_screening_center = 1 GROUP BY main_table.token_id) second_table;", nativeQuery = true)
        Long getDistrictWiseScreenedTotalCount(String district);

        @Query(value = "SELECT COUNT(*) FROM ( " + "SELECT * FROM ( "
                        + "SELECT a.token_id,a.reached_screening_center,e.name,e.mobile_number,e.district FROM qrcode_data a, "
                        + "qrcode_data_qrcode_member_detail_mapping c,qrcode_member_detail e WHERE a.id = c.qrcode_data_id AND "
                        + "e.id = c.qrcode_member_detail_id) main_table "
                        + "WHERE main_table.district = ?1 AND main_table.reached_screening_center = 0 GROUP BY main_table.token_id) second_table;", nativeQuery = true)
        Long getDistrictWiseNotScreenedTotalCount(String district);

        List<QrCodeData> findAllByStatus(String string);

        List<QrCodeData> findAllByDestinationDistrictAndStatus(String district, String string);

        @Query(value = "select qrcodedata0_.id," + "        qrcodedata0_.destination_district,"
                        + "        qrcodedata0_.reached_screening_center," + "        qrcodedata0_.status,"
                        + "        qrcodedata0_.token_id " + "    from" + "        qrcode_data qrcodedata0_ "
                        + "    left outer join" + "        qrcode_data_qrcode_scan_detail_mapping scandetail1_ "
                        + "            on qrcodedata0_.id=scandetail1_.qrcode_data_id " + "    left outer join"
                        + "        qrcode_scan_detail qrcodescan2_ "
                        + "            on scandetail1_.qrcode_scan_detail_id=qrcodescan2_.id " + "    where"
                        + "        SUBSTRING_INDEX(qrcodescan2_.scan_date_time,\" \",1)=?1 "
                        + "        and qrcodedata0_.destination_district=?2 "
                        + "        and qrcodedata0_.status=?3", nativeQuery = true)
        List<QrCodeData> findAllByScanDetails_scanDateTimeAndDestinationDistrictAndStatus(String date, String district,
                        String string);

        @Query(value = "SELECT qrcode_id,reached_screening_center,token_id,destination_district,name,mobile_number,pin,thana,address,assigned_screening_center,entry_status,username,entry_date,entry_time FROM master_table WHERE entry_date = ?1", nativeQuery = true)
        List<Object[]> findAllByDate(String date);

        @Query(value = "SELECT qrcode_id,reached_screening_center,token_id,destination_district,name,mobile_number,pin,thana,address,assigned_screening_center,entry_status,username,entry_date,entry_time FROM master_table WHERE entry_date = ?1 AND destination_district=?2", nativeQuery = true)
        List<Object[]> findAllByDateAndDistrict(String date, String district);

        @Query(value = "SELECT qrcode_id,reached_screening_center,token_id,destination_district,name,mobile_number,pin,thana,address,assigned_screening_center,entry_status,username,entry_date,entry_time FROM master_table WHERE entry_date = ?1 AND reached_screening_center = 0", nativeQuery = true)
        List<Object[]> findAllPendingByDate(String date);

        @Query(value = "SELECT qrcode_id,reached_screening_center,token_id,destination_district,name,mobile_number,pin,thana,address,assigned_screening_center,entry_status,username,entry_date,entry_time FROM master_table WHERE entry_date = ?1 AND reached_screening_center = 0 AND destination_district=?2", nativeQuery = true)
        List<Object[]> findAllPendingByDateAndDistrict(String date, String district);

        @Query(value = "SELECT qrcode_id,reached_screening_center,token_id,destination_district,name,mobile_number,pin,thana,address,assigned_screening_center,entry_status,username,entry_date,entry_time FROM master_table WHERE entry_date = ?1 AND reached_screening_center = 1", nativeQuery = true)
        List<Object[]> findAllArrivedByDate(String date);

        @Query(value = "SELECT qrcode_id,reached_screening_center,token_id,destination_district,name,mobile_number,pin,thana,address,assigned_screening_center,entry_status,username,entry_date,entry_time FROM master_table WHERE entry_date = ?1 AND reached_screening_center = 1 AND destination_district=?2", nativeQuery = true)
        List<Object[]> findAllArrivedByDateAndDistrict(String date, String district);

}
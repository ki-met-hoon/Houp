package com.example.houp.support.excelparser.service;

import com.example.houp.nursingfacility.NursingFacilities;
import com.example.houp.nursingfacility.department.dto.ClinicDepartmentDto;
import com.example.houp.nursingfacility.department.repository.ClinicDepartmentsRepository;
import com.example.houp.nursingfacility.detail.dto.FacilityDetailDto;
import com.example.houp.nursingfacility.detail.repository.FacilityDetailsRepository;
import com.example.houp.nursingfacility.dto.NursingFacilityDto;
import com.example.houp.nursingfacility.hour.dto.ClinicHourDto;
import com.example.houp.nursingfacility.hour.repository.ClinicHoursRepository;
import com.example.houp.nursingfacility.repository.NursingFacilitiesRepository;
import com.example.houp.supplement.dto.SupplementDto;
import com.example.houp.supplement.repository.SupplementsRepository;
import com.example.houp.user.UserInfos;
import com.example.houp.user.dto.UserInfoDto;
import com.example.houp.user.repository.UserInfosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExcelParerService {

    private static final int WEEK = 7;

    private final NursingFacilitiesRepository nursingFacilitiesRepository;
    private final FacilityDetailsRepository facilityDetailsRepository;
    private final ClinicHoursRepository clinicHoursRepository;
    private final ClinicDepartmentsRepository clinicDepartmentsRepository;
    private final UserInfosRepository userInfosRepository;
    private final SupplementsRepository supplementsRepository;

    @Transactional
    public void readExcel(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet1 = workbook.getSheetAt(0);
            XSSFSheet sheet2 = workbook.getSheetAt(1);

            saveNursingFacilityInfo(sheet1);
            saveUserInfoAndSupplement(sheet2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUserInfoAndSupplement(XSSFSheet sheet2) {
        int rowCount = sheet2.getPhysicalNumberOfRows();

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            XSSFRow row = sheet2.getRow(rowIndex);

            UserInfos userInfo = saveUserInfoDto(row);

            saveSupplementDto(row, userInfo);
        }
    }

    private void saveSupplementDto(XSSFRow row, UserInfos userInfo) {
        SupplementDto supplementDto1 = SupplementDto.of(
                userInfo,
                getStringValue(row.getCell(3)),
                getStringValue(row.getCell(4)),
                getStringValue(row.getCell(5))
        );

        SupplementDto supplementDto2 = SupplementDto.of(
                userInfo,
                getStringValue(row.getCell(6)),
                getStringValue(row.getCell(7)),
                getStringValue(row.getCell(8))
        );

        supplementsRepository.save(supplementDto1.toEntity());
        supplementsRepository.save(supplementDto2.toEntity());
    }

    private UserInfos saveUserInfoDto(XSSFRow row) {
        UserInfoDto userInfoDto = UserInfoDto.of(
                getStringValue(row.getCell(0)),
                getStringValue(row.getCell(1)),
                getStringValue(row.getCell(2))
        );

        return userInfosRepository.save(userInfoDto.toEntity());
    }

    private void saveNursingFacilityInfo(XSSFSheet sheet1) {
        int rowCount = sheet1.getPhysicalNumberOfRows();

        for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
            XSSFRow row = sheet1.getRow(rowIndex);

            NursingFacilities nursingFacility = saveNursingFacilityDto(row);
            saveFacilityDetailDto(row, nursingFacility);
            saveClinicHourDto(row, nursingFacility);
            saveClinicDepartmentDto(row, nursingFacility);
        }
    }

    private void saveClinicDepartmentDto(XSSFRow row, NursingFacilities nursingFacility) {
        int count = 0;

        while (true) {
            String clinicCode = getStringValue(row.getCell(28 + count));

            if (clinicCode == null) {
                break;
            }

            ClinicDepartmentDto clinicDepartmentDto = ClinicDepartmentDto.of(
                    nursingFacility,
                    row.getCell(29 + count).toString(),
                    Integer.parseInt(clinicCode)
            );

            clinicDepartmentsRepository.save(clinicDepartmentDto.toEntity());

            count += 2;
        }
    }

    private void saveClinicHourDto(XSSFRow row, NursingFacilities nursingFacility) {
        for (int day = 0; day < WEEK; day++) {
            Integer startTime = getIntegerValue(row.getCell(14 + day * 2));
            Integer endTime = getIntegerValue(row.getCell(15 + day * 2));

            if (startTime == null || endTime == null) {
                continue;
            }

            ClinicHourDto clinicHourDto = ClinicHourDto.of(
                    nursingFacility,
                    day,
                    startTime,
                    endTime
            );

            clinicHoursRepository.save(clinicHourDto.toEntity());
        }
    }

    private void saveFacilityDetailDto(XSSFRow row, NursingFacilities nursingFacility) {
        String parkingAvailable = getStringValue(row.getCell(10));
        String lunchTime = getStringValue(row.getCell(13));
        String closedOnSunday = getStringValue(row.getCell(11));
        String memo = getStringValue(row.getCell(12));

        if (parkingAvailable == null && lunchTime == null && closedOnSunday == null && memo == null) {
            return;
        }

        FacilityDetailDto facilityDetailDto = FacilityDetailDto.of(
                nursingFacility,
                parkingAvailable,
                lunchTime,
                closedOnSunday,
                memo
        );

        facilityDetailsRepository.save(facilityDetailDto.toEntity());
    }

    private NursingFacilities saveNursingFacilityDto(XSSFRow row) {
        NursingFacilityDto nursingFacilityDto = NursingFacilityDto.of(
                row.getCell(0).toString(),
                row.getCell(1).toString(),
                (int) row.getCell(2).getNumericCellValue(),
                row.getCell(3).toString(),
                (int) row.getCell(4).getNumericCellValue(),
                row.getCell(5).toString(),
                row.getCell(7).toString(),
                row.getCell(8).getNumericCellValue(),
                row.getCell(9).getNumericCellValue(),
                row.getCell(6).toString()
        );

        return nursingFacilitiesRepository.save(nursingFacilityDto.toEntity());
    }

    private String getStringValue(XSSFCell cell) {
        if (cell != null && (!cell.toString().equals(""))) {
            return cell.toString();

        }

        return null;
    }

    private Integer getIntegerValue(XSSFCell cell) {
        if (cell != null && (!cell.toString().equals(""))) {
            return (int) cell.getNumericCellValue();

        }

        return null;
    }
}

package com.example.houp.support.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KindValidator {

    private static final Set<String> VALID_DISEASE_KINDS = new HashSet<>(Arrays.asList(
            "근골격계질환 (척추질환)",
            "근골격계질환(척추질환 제외)",
            "기타 간질환",
            "난청",
            "뇌혈관질환",
            "독성감염",
            "사인미상",
            "석면폐증",
            "심장질환",
            "악성신생물(직업성 암 포함)",
            "안면신경 마비",
            "안질환",
            "이상기압으로 인한 질병(압착증|| 감압병 등)",
            "일사병,열사병,화상,동상",
            "일사병||열사병||화상||동상",
            "자해행위(자살 포함)",
            "정신질환",
            "진동으로 인한 증상",
            "진폐",
            "피부질환",
            "호흡기질환(천식 포함)"
    ));

    private static final Set<String> VALID_JOB_KINDS = new HashSet<>(Arrays.asList(
            "(건설기계)콘크리트믹서트럭기사",
            "(특수직종)[생보||(농협2012.03.02폐지)]보험설계사",
            "(특수직종)[손보||대리점||우체국]보험설계사",
            "(특수직종)가전제품설치원",
            "(특수직종)골프장캐디",
            "(특수직종)대리운전기사",
            "(특수직종)방문점검원",
            "(특수직종)콘크리트믹서트럭운전자",
            "(특수직종)퀵서비스기사",
            "(특수직종)택배기사",
            "(특수직종)학습지교사",
            "(후원)방문판매원",
            "[대출모집법인]대출모집인",
            "[생보||(농협2012.03.02폐지)]보험설계사",
            "[손보||대리점||우체국]보험설계사",
            "가사 및 육아 도우미",
            "가전제품 설치원",
            "간호사",
            "건설 및 광업 단순 종사원",
            "건설 및 채굴 기계운전원",
            "건설.전기 및 생산 관련 관리자",
            "건설?전기 및 생산 관련 관리자",
            "건설·전기 및 생산 관련 관리자",
            "건설관련 기능 종사자",
            "건설구조관련 기능 종사자",
            "건축 목공",
            "건축 및 토목 공학 기술자및 시험원",
            "건축마감관련 기능 종사자",
            "경비원 및 검표원",
            "경영관련 사무원",
            "경찰.소방 및 교도 관련 종사자",
            "경찰·소방 및 교도 관련 종사자",
            "경호 및 보안 관련 종사자",
            "계기검침.수금 및주차 관련 종사원",
            "계기검침?수금 및주차 관련 종사원",
            "계기검침·수금 및주차 관련 종사원",
            "고객 상담 및 기타 사무원",
            "고객서비스 관리자",
            "골프장캐디",
            "공예 및 귀금속 세공원",
            "구매대리인",
            "굴삭기기사",
            "금속.재료 공학 기술자및 시험원",
            "금속·재료 공학 기술자및 시험원",
            "금속공작기계 조작원",
            "금속기계부품 조립원",
            "금융 및 보험 관련 사무 종사자",
            "금융 및 보험 전문가",
            "금형.주조 및 단조원",
            "기계장비 설치 및 정비원",
            "기술영업 및 중개 관련종사자",
            "기업고위임원",
            "기타 건물 청소원",
            "기타 건설.전기 및 생산 관련 관리자",
            "기타 건설?전기 및 생산 관련 관리자",
            "기타 건설·전기 및 생산 관련 관리자",
            "기타 공학 전문가 및 관련종사자",
            "기타 교육 전문가",
            "기타 기능관련 종사자",
            "기타 서비스관련 단순 종사원",
            "기타 수위|| 경비 및 관련 종사원",
            "기타 식품가공관련 기계조작원",
            "기타 이미용.예식 및 의료보조 서비스 종사자",
            "기타 이미용?예식 및 의료보조 서비스 종사자",
            "기타 이미용·예식 및 의료보조 서비스 종사자",
            "기타 전문서비스 관리자",
            "기타 제조관련 기계조작원",
            "기타 판매 및 고객 서비스 관리자",
            "냉.난방 관련 설비 조작원",
            "냉?난방 관련 설비 조작원",
            "냉·난방 관련 설비 조작원",
            "농림어업관련 단순 종사원",
            "대리운전기사",
            "대학 교수 및 강사",
            "덤프트럭기사",
            "도장 및 도금기 조작원",
            "도장원|| 건설 도장원 제외",
            "디자이너",
            "매니저 및 기타 문화.예술관련 종사자",
            "매니저 및 기타 문화?예술관련 종사자",
            "매니저 및 기타 문화·예술관련 종사자",
            "매장 판매 종사자",
            "목재 및 종이 관련 기계조작원",
            "목재.가구.악기 및 간판 관련 기능 종사자",
            "목재?가구?악기 및 간판 관련 기능 종사자",
            "목재·가구·악기 및 간판 관련 기능 종사자",
            "문리.기술 및 예능 강사",
            "문리?기술 및 예능 강사",
            "문리·기술 및 예능 강사",
            "문화.예술.디자인 및 영상 관련 관리자",
            "문화·예술·디자인 및 영상 관련 관리자",
            "물품이동 장비 조작원",
            "발전 및 배전 장치 조작원",
            "방문.노점 및 통신 판매 관련 종사자",
            "방문?노점 및 통신 판매 관련 종사자",
            "방문·노점 및 통신 판매 관련 종사자",
            "방문강사",
            "방문점검원",
            "배관공",
            "배달원",
            "법률 및 감사 사무 종사자",
            "법률 전문가",
            "보건 및 사회복지 관련 관리자",
            "보건의료관련 종사자",
            "보험 및 금융 관리자",
            "비금속 제품 생산기 조작원",
            "비서 및 사무 보조원",
            "사회복지관련 종사자",
            "상.하수도 처리장치 조작원",
            "상·하수도 처리장치 조작원",
            "상품 대여 종사자",
            "상품기획.홍보 및 조사 전문가",
            "상품기획·홍보 및 조사 전문가",
            "생명 및 자연 과학 관련 시험원",
            "생명 및 자연 과학 관련전문가",
            "생산사무 종사자",
            "석유 및 화학물 가공장치 조작원",
            "선박 갑판승무원 및관련 종사원",
            "섬유 및 가죽 관련 기능 종사자",
            "섬유제조 및 가공기계조작원",
            "세탁관련 기계조작원",
            "스포츠 및 레크레이션 관련 전문가",
            "식품가공관련 기계조작원",
            "식품가공관련 기능 종사자",
            "아스팔트살포기기사",
            "안전관리 및 검사원",
            "약사 및 한약사",
            "어업관련 종사자",
            "여가 및 스포츠 관련 종사자",
            "여행.안내 및 접수 사무원",
            "여행?안내 및 접수 사무원",
            "여행·안내 및 접수 사무원",
            "연구.교육 및 법률 관련 관리자",
            "연구?교육 및 법률 관련 관리자",
            "연구·교육 및 법률 관련 관리자",
            "연극.영화 및 영상 전문가",
            "연극?영화 및 영상 전문가",
            "연극·영화 및 영상 전문가",
            "영상 및 통신 장비 관련 설치 및 수리원",
            "영양사",
            "영업종사자",
            "용접원",
            "운송 서비스 종사자",
            "운송장비 정비원",
            "운송차량 및 기계 관련 조립원",
            "원예 및 조경 종사자",
            "유치원 교사",
            "음료 제조관련 기계조작원",
            "음식관련 단순 종사원",
            "음식서비스 종사자",
            "의료.복지 관련 서비스종사자",
            "의료?복지 관련 서비스종사자",
            "의료·복지 관련 서비스종사자",
            "의료진료 전문가",
            "의복 제조관련 기능 종사자",
            "의회의원·고위공무원 및 공공단체임원",
            "이.미용 및 관련 서비스 종사자",
            "이?미용 및 관련 서비스 종사자",
            "이·미용 및 관련 서비스 종사자",
            "인문 및 사회 과학 전문가",
            "인사 및 경영 전문가",
            "인쇄 및 사진현상 관련 기계조작원",
            "임업관련 종사자",
            "자동조립라인 및 산업용 로봇 조작원",
            "자동차 운전원",
            "자동차 정비원",
            "작가.기자 및 출판 전문가",
            "작가?기자 및 출판 전문가",
            "작가·기자 및 출판 전문가",
            "작물재배 종사자",
            "장기 부사관 및 준위",
            "재활용 처리 및 소각로 조작원",
            "전기 및 전자 설비 조작원",
            "전기 및 전자기기 설치 및 수리원",
            "전기 및 전자장비 조립 종사자",
            "전기.전자 및 기계 공학 기술자 및 시험원",
            "전기.전자 부품 및 제품 제조장치 조작원",
            "전기.전자 부품 및 제품 조립원",
            "전기?전자 및 기계 공학 기술자 및 시험원",
            "전기?전자 부품 및 제품 제조장치 조작원",
            "전기?전자 부품 및 제품 조립원",
            "전기·전자 및 기계 공학 기술자 및 시험원",
            "전기·전자 부품 및 제품 제조장치 조작원",
            "전기·전자 부품 및 제품 조립원",
            "전기공",
            "정보 시스템 운영자",
            "정보시스템 개발 전문가",
            "정보통신관련 관리자",
            "제관원 및 판금원",
            "제조관련 단순 종사원",
            "제품 운반원",
            "종교관련 종사자",
            "주방장 및 조리사",
            "주조 및 금속 가공관련 기계조작원",
            "지게차기사",
            "직물 및 신발 관련 기계조작원 및 조립원",
            "채굴 및 토목 관련 기능 종사자",
            "천공기기사",
            "철도 및 전동차 기관사",
            "청소원 및 환경 미화원",
            "축산 및 사육 관련 종사자",
            "치료사 및 의료기사",
            "컴퓨터 하드웨어 및통신공학 전문가",
            "콘크리트믹서트럭기사",
            "콘크리트믹서트럭운전자",
            "퀵서비스기사",
            "큐레이터.사서 및 기록물관리사",
            "큐레이터·사서 및 기록물관리사",
            "택배기사",
            "통계관련 사무원",
            "통신 및 방송송출 장비 기사",
            "특수건설기계기사",
            "판매 및 운송 관리자",
            "판매관련 단순 종사원",
            "품질 검사원",
            "하역 및 적재 단순 종사원",
            "학교 교사",
            "학습지교사",
            "항공기.선박 기관사 및 관제사",
            "항공기?선박 기관사 및 관제사",
            "항공기·선박 기관사 및 관제사",
            "행정 및 경영지원 관리자",
            "행정 사무원",
            "행정 전문가",
            "혼례 및 장례 종사자",
            "화가.사진가 및 공연예술가",
            "화가?사진가 및 공연예술가",
            "화가·사진가 및 공연예술가",
            "화물열차 차장 및 관련 종사원",
            "화물차주(수출입컨테이너운송)",
            "화물차주(위험물질운송)",
            "화물차주(철강재운송)",
            "화학.고무 및 플라스틱 제품 생산기 조작원",
            "화학?고무 및 플라스틱 제품 생산기 조작원",
            "화학·고무 및 플라스틱 제품 생산기 조작원",
            "화학공학 기술자 및 시험원",
            "환경.청소 및 경비 관련 관리자",
            "환경?청소 및 경비 관련 관리자",
            "환경·청소 및 경비 관련 관리자",
            "환경공학 기술자 및 시험원",
            "회계 및 경리 사무원",
            "화물열차 차장 및 관련 종사원",
            "화물차주(수출입컨테이너운송)"
    ));

    public static boolean isValidDiseaseKind(String decodedDiseaseKind) {
        return VALID_DISEASE_KINDS.contains(decodedDiseaseKind);
    }

    public static boolean isValidJobKind(String decodedJobKind) {
        return VALID_JOB_KINDS.contains(decodedJobKind);
    }
}
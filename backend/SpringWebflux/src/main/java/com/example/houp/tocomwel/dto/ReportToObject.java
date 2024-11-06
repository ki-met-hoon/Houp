package com.example.houp.tocomwel.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportToObject {

    @XmlElement(name = "header")
    private Header header;

    @XmlElement(name = "body")
    private Body body;

    @Getter
    @NoArgsConstructor
    public static class Header {
        @XmlElement(name = "resultCode")
        private String resultCode;

        @XmlElement(name = "resultMsg")
        private String resultMsg;
    }

    @Getter
    @NoArgsConstructor
    public static class Body {
        @XmlElement(name = "items")
        private Items items;

        @XmlElement(name = "numOfRows")
        private String numOfRows;

        @XmlElement(name = "pageNo")
        private String pageNo;

        @XmlElement(name = "totalCount")
        private String totalCount;
    }

    @Getter
    @NoArgsConstructor
    public static class Items {
        @XmlElement(name = "item")
        private List<Item> item;
    }

    @Getter
    @NoArgsConstructor
    public static class Item {
        @XmlElement(name = "accnum")
        private String accnum;

        @XmlElement(name = "kinda")
        private String kinda;

        @XmlElement(name = "kindb")
        private String kindb;

        @XmlElement(name = "kindc")
        private String kindc;

        @XmlElement(name = "title")
        private String title;

        @XmlElement(name = "noncontent")
        @JacksonXmlCData
        private String noncontent;
    }
}

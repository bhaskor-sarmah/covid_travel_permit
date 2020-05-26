package com.bohniman.travelpermit.payload;

import java.util.Objects;

public class PageDetails {

    private Integer pageNumber;
    private Integer pageElements;
    private Integer pageSize;
    private Boolean hasPrevious;
    private Boolean hasNext;
    private Long totalElements;
    private Integer totalPages;

    public PageDetails() {
    }

    public PageDetails(Integer pageNumber, Integer pageElements, Integer pageSize, Boolean hasPrevious, Boolean hasNext,
            Long totalElements, Integer totalPages) {
        this.pageNumber = pageNumber;
        this.pageElements = pageElements;
        this.pageSize = pageSize;
        this.hasPrevious = hasPrevious;
        this.hasNext = hasNext;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageElements() {
        return this.pageElements;
    }

    public void setPageElements(Integer pageElements) {
        this.pageElements = pageElements;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean isHasPrevious() {
        return this.hasPrevious;
    }

    public Boolean getHasPrevious() {
        return this.hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public Boolean isHasNext() {
        return this.hasNext;
    }

    public Boolean getHasNext() {
        return this.hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Long getTotalElements() {
        return this.totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public PageDetails pageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public PageDetails pageElements(Integer pageElements) {
        this.pageElements = pageElements;
        return this;
    }

    public PageDetails pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PageDetails hasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
        return this;
    }

    public PageDetails hasNext(Boolean hasNext) {
        this.hasNext = hasNext;
        return this;
    }

    public PageDetails totalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public PageDetails totalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PageDetails)) {
            return false;
        }
        PageDetails pageDetails = (PageDetails) o;
        return Objects.equals(pageNumber, pageDetails.pageNumber)
                && Objects.equals(pageElements, pageDetails.pageElements)
                && Objects.equals(pageSize, pageDetails.pageSize)
                && Objects.equals(hasPrevious, pageDetails.hasPrevious) && Objects.equals(hasNext, pageDetails.hasNext)
                && Objects.equals(totalElements, pageDetails.totalElements)
                && Objects.equals(totalPages, pageDetails.totalPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNumber, pageElements, pageSize, hasPrevious, hasNext, totalElements, totalPages);
    }

    @Override
    public String toString() {
        return "{" + " pageNumber='" + getPageNumber() + "'" + ", pageElements='" + getPageElements() + "'"
                + ", pageSize='" + getPageSize() + "'" + ", hasPrevious='" + isHasPrevious() + "'" + ", hasNext='"
                + isHasNext() + "'" + ", totalElements='" + getTotalElements() + "'" + ", totalPages='"
                + getTotalPages() + "'" + "}";
    }

}
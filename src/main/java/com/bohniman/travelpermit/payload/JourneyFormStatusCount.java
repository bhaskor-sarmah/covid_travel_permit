package com.bohniman.travelpermit.payload;

import java.util.Objects;

public class JourneyFormStatusCount {

    private String status;
    private Long count;

    public JourneyFormStatusCount() {
    }

    public JourneyFormStatusCount(String status, Long count) {
        this.status = status;
        this.count = count;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public JourneyFormStatusCount status(String status) {
        this.status = status;
        return this;
    }

    public JourneyFormStatusCount count(Long count) {
        this.count = count;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JourneyFormStatusCount)) {
            return false;
        }
        JourneyFormStatusCount journeyFormStatusCount = (JourneyFormStatusCount) o;
        return Objects.equals(status, journeyFormStatusCount.status)
                && Objects.equals(count, journeyFormStatusCount.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, count);
    }

    @Override
    public String toString() {
        return "{" + " status='" + getStatus() + "'" + ", count='" + getCount() + "'" + "}";
    }

}
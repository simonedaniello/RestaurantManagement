package uni.isssr.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by francesco on 03/07/17.
 */
public class AnalyticsDto {

    private Date data;

    private final String location = "prova";

    List<PietanzaAnalyticsDto> pietanzaAnalyticsDtos;

    public AnalyticsDto(){}

    public AnalyticsDto(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLocation() {
        return location;
    }

    public List<PietanzaAnalyticsDto> getPietanzaAnalyticsDtos() {
        return pietanzaAnalyticsDtos;
    }

    public void setPietanzaAnalyticsDtos(List<PietanzaAnalyticsDto> pietanzaAnalyticsDtos) {
        this.pietanzaAnalyticsDtos = pietanzaAnalyticsDtos;
    }
}

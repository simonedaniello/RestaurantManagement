package uni.isssr.dto;

/**
 * Created by francesco on 03/07/17.
 */
public class PietanzaAnalyticsDto {

    private PietanzaMenuDto pietanzaMenuDto;

    private Integer venduto;

    private Integer preparato;

    private String categoria;

    public PietanzaAnalyticsDto(PietanzaMenuDto pietanzaMenuDto, Long venduto, Long preparato) {
        this.pietanzaMenuDto = pietanzaMenuDto;
        this.venduto = venduto;
        this.preparato = preparato;
    }

    public PietanzaMenuDto getPietanzaMenuDto() {
        return pietanzaMenuDto;
    }

    public void setPietanzaMenuDto(PietanzaMenuDto pietanzaMenuDto) {
        this.pietanzaMenuDto = pietanzaMenuDto;
    }



    public Integer getVenduto() {
        return venduto;
    }

    public void setVenduto(Integer venduto) {
        this.venduto = venduto;
    }

    public Integer getPreparato() {
        return preparato;
    }

    public void setPreparato(Integer preparato) {
        this.preparato = preparato;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

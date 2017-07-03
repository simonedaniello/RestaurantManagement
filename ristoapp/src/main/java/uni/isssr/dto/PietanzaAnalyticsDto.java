package uni.isssr.dto;

/**
 * Created by francesco on 03/07/17.
 */
public class PietanzaAnalyticsDto {

    private PietanzaMenuDto pietanzaMenuDto;

    private Long venduto;

    private Long preparato;

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



    public Long getVenduto() {
        return venduto;
    }

    public void setVenduto(Long venduto) {
        this.venduto = venduto;
    }

    public Long getPreparato() {
        return preparato;
    }

    public void setPreparato(Long preparato) {
        this.preparato = preparato;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

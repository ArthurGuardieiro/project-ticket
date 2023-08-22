package br.com.Iticket.project.DTO;

import br.com.Iticket.project.entities.City;
import jakarta.validation.constraints.NotBlank;

public class CityDTO {

    private Long id;
    @NotBlank(message = "campo obrigatório")
    private String name;
    @NotBlank(message = "campo obrigatório")
    private String state;

    public CityDTO(){

    }

    public CityDTO(Long id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public CityDTO(City entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.state = entity.getState();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

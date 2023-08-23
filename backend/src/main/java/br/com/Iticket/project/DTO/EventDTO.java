package br.com.Iticket.project.DTO;



import br.com.Iticket.project.entities.Event;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class EventDTO {

    private Long id;
    @Size(min = 3, max = 60, message = "Nome deve ter entre 3 e 60 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String name;
    private String addres;
    @Future
    private Instant date;
    private Long cityId;

    public EventDTO() {

    }

    public EventDTO(Long id, String name, String addres, Instant date, Long cityId) {
        this.id = id;
        this.name = name;
        this.addres = addres;
        this.date = date;
        this.cityId = cityId;
    }

    public EventDTO(Event entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.addres = entity.getAddres();
        this.date = entity.getDate();
        this.cityId = entity.getCity().getId();
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

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}

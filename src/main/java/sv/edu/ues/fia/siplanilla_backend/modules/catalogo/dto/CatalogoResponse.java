package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO genérico para respuestas de catálogos
 */
public class CatalogoResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombre")
    private String nombre;

    public CatalogoResponse() {}

    public CatalogoResponse(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}

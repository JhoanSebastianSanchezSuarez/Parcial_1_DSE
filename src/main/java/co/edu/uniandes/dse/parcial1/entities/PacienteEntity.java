package co.edu.uniandes.dse.parcial1.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class PacienteEntity extends BaseEntity {

    private String nombre;

    private String apellido;

    private String documento;

    @PodamExclude
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    List<CitaEntity> citas = new ArrayList<>();
    
}

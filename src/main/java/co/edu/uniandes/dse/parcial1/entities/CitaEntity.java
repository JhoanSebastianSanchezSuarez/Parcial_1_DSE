package co.edu.uniandes.dse.parcial1.entities;

import java.sql.Date;

import javax.persistence.ManyToOne;

import uk.co.jemos.podam.common.PodamExclude;

public class CitaEntity extends BaseEntity{

    private Date fecha;

    @PodamExclude
    @ManyToOne
    PacienteEntity paciente;
    
}

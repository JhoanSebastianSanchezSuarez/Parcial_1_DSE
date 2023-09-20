package co.edu.uniandes.dse.parcial1.entities;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.edu.uniandes.dse.parcial1.podam.DateStrategy;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

public class CitaEntity extends BaseEntity{

    @Temporal(TemporalType.DATE)
	@PodamStrategyValue(DateStrategy.class)
    private Date fechaCita;

    @PodamExclude
    @ManyToOne
    PacienteEntity paciente;
    
}

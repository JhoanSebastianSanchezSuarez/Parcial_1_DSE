package co.edu.uniandes.dse.parcial1.services;

import javax.transaction.Transactional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.CitaEntity;
import co.edu.uniandes.dse.parcial1.entities.PacienteEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.repositories.CitaRespository;
import co.edu.uniandes.dse.parcial1.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteCitaService {
    
    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    CitaRespository citaRespository;

    @Transactional
    public CitaEntity addCita(Long idPaciente, Long idCita) throws EntityNotFoundException{

        log.info("Inicia proceso de asociarle una cita al paciente con id");

        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(idPaciente);

        Optional<CitaEntity> citaEntity = citaRespository.findById(idCita);
<
        if(pacienteEntity.isEmpty()){
            throw new EntityNotFoundException("El paciente no existe");
        }

        if(citaEntity.isEmpty()){
            throw new EntityNotFoundException("La cita no existe");
        }

        citaEntity.get().setPaciente(pacienteEntity.get());
        log.info("Termino el proceso de asociarle una cita al paciente");
        return citaEntity.get();
    }
}

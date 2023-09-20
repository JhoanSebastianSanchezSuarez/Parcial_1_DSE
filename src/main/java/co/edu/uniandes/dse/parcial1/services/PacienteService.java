package co.edu.uniandes.dse.parcial1.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.PacienteEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Transactional
    public PacienteEntity createPaciente(PacienteEntity pacienteEntity) throws IllegalOperationException{
        log.info("Inicia la creacion del paciente");

        if(pacienteEntity.getDocumento()== null || pacienteEntity.getDocumento().isEmpty()){
            throw new IllegalOperationException("Documento no puede ser vacio");
        }

        if(pacienteEntity.getNombre()== null || pacienteEntity.getNombre().isEmpty()){
            throw new IllegalOperationException("Nombre no puede ser vacio");
        }

        if(pacienteEntity.getApellido()== null || pacienteEntity.getApellido().isEmpty()){
            throw new IllegalOperationException("Apellido no puede ser vacio");
        }

        String docNuevo = pacienteEntity.getDocumento();
        if(!docNuevo.startsWith("CC", 0) && !docNuevo.startsWith("TI", 0) && !docNuevo.startsWith("CE",0) && !docNuevo.startsWith("RC",0)){
            throw new IllegalOperationException("Documento no tiene prefijo valido");
        }

        //Evitar prefijos como PC
        if(docNuevo.charAt(0)== 'P' && (docNuevo.charAt(1) != '1' || docNuevo.charAt(1) != '1' || docNuevo.charAt(1) != '2' || docNuevo.charAt(1) != '3' || docNuevo.charAt(1) != '4' || docNuevo.charAt(1) != '5' || docNuevo.charAt(1) != '6' || docNuevo.charAt(1) != '7' || docNuevo.charAt(1) != '8'|| docNuevo.charAt(1) != '9')){
            throw new IllegalOperationException("Documento no tiene prefijo valido");
        }

        return pacienteRepository.save(pacienteEntity);
    }
    
}

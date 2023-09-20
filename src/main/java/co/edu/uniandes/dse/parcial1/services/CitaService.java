package co.edu.uniandes.dse.parcial1.services;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.CitaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.CitaRespository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CitaService {

    @Autowired
    CitaRespository citaRespository;

    @Transactional
    public CitaEntity createCita(CitaEntity citaEntity) throws IllegalOperationException{

        Calendar calendar= Calendar.getInstance();

        if(citaEntity.getFechaCita().compareTo(calendar.getTime()) < 0 ){
            throw new IllegalOperationException("Fecha cita ya paso");
        }

        return citaRespository.save(citaEntity);
    }
    
}

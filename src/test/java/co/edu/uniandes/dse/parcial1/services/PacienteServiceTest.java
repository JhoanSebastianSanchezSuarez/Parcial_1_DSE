package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcial1.entities.PacienteEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(PacienteService.class)
public class PacienteServiceTest {
    
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImp();

    private List<PacienteEntity> pacienteList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        clearData();
        insertData();
    }

    void clearData(){
        entityManager.getEntityManager().createQuery("delete from PacienteEntity");
    }

    void insertData(){
        for(int i = 0; 1 < 3; i++){
            PacienteEntity pacienteEntity = factory.manufacturePojo(PacienteEntity.class);
            entityManager.persist(pacienteEntity);
            pacienteList.add(pacienteEntity);
        }
    }

    @Test
    private void testCreatePaciente() throws IllegalOperationException{
        PacienteEntity newEntity = factory.manufacturePojo(PacienteEntity.class);
        newEntity.setDocumento("CC101010");
        PacienteEntity result = pacienteService.createPaciente(newEntity);
        assertNotNull(result);

        PacienteEntity entity = entityManager.find(PacienteEntity.class, result.getId());

        assertEquals(newEntity.getNombre(), entity.getNombre());
        assertEquals(newEntity.getApellido(), entity.getApellido());
        assertEquals(newEntity.getDocumento(), entity.getDocumento());
    }

    @Test
    private void testCreatPacienteInvalido(){
        assertThrows(IllegalOperationException.class, ()->{
            PacienteEntity newEntity = factory.manufacturePojo(PacienteEntity.class);
            newEntity.setDocumento("101010");
            PacienteEntity result = pacienteService.createPaciente(newEntity); 
        });
    }

}

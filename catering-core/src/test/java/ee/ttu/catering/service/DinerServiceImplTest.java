package ee.ttu.catering.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ee.ttu.catering.rest.exception.DinerNotFoundException;
import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.repository.DinerRepository;
import ee.ttu.catering.rest.service.DinerService;
import ee.ttu.catering.rest.service.DinerServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class DinerServiceImplTest {
    
    @Mock DinerRepository dinerRepo;
    
    @InjectMocks DinerService dinerService = new DinerServiceImpl();
    
    Diner diner;
    
    public DinerServiceImplTest() {
    }

    @Before
    public void setUp(){
        diner = new Diner();
        diner.setName("test");
        diner.setId(1);
    }
    
    @Test
    public void testDelete() {
        Mockito.when(dinerRepo.findOne(1)).thenReturn(diner);
        Diner deletedDiner = dinerService.delete(1);
        assertEquals(diner.getName(), deletedDiner.getName());
    }
    
    @Test(expected = DinerNotFoundException.class)
    public void testDeleteThrows(){
       Mockito.when(dinerRepo.findOne(1)).thenReturn(null);
       dinerService.delete(1);
    }
}
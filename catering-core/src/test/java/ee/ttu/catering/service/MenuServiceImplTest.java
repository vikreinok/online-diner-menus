package ee.ttu.catering.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ee.ttu.catering.rest.exception.MenuNotFoundException;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.repository.MenuRepository;
import ee.ttu.catering.rest.service.MenuService;
import ee.ttu.catering.rest.service.MenuServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {
    
    @Mock MenuRepository MenuRepository;
    
    @InjectMocks MenuService menuService = new MenuServiceImpl();
    
    Menu menu;
    
    public MenuServiceImplTest() {
    }

    @Before
    public void setUp(){
        menu = new Menu();
        menu.setName("test");
        menu.setId(1);
    }
    
    @Test
    public void testDelete() {
        Mockito.when(MenuRepository.findOne(1)).thenReturn(menu);
        Menu ex = menuService.delete(1);
        assertEquals(menu.getName(), ex.getName());
    }
    
    @Test(expected = MenuNotFoundException.class)
    public void testDeleteThrows(){
       Mockito.when(MenuRepository.findOne(1)).thenReturn(null);
       menuService.delete(1);
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.rest.service;

import com.example.rest.exception.ExampleNotFoundException;
import com.example.rest.model.Example;
import com.example.rest.repository.ExampleRepository;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author craigbrookes
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleServiceImplTest {
    
    @Mock ExampleRepository exampleRepository;
    
    @InjectMocks ExampleService exampleService = new ExampleServiceImpl();
    
    Example example;
    
    public ExampleServiceImplTest() {
    }

    @Before
    public void setUp(){
        example = new Example();
        example.setName("test");
        example.setId(1);
        
    }
    
    @Test
    public void testDelete() {
        Mockito.when(exampleRepository.findOne(1)).thenReturn(example);
        Example ex = exampleService.delete(1);
        assertEquals(example.getName(), ex.getName());
    }
    
    @Test(expected = ExampleNotFoundException.class)
    public void testDeleteThrows(){
       Mockito.when(exampleRepository.findOne(1)).thenReturn(null);
       exampleService.delete(1);
    }
}
package com.labmanagement;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import controller.LaboratoryClassController;
import model.LaboratoryClass;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LaboratoryClassControllerTester {

	 @Autowired
	 private MockMvc mvc;
	 
	 @MockBean
	 private LaboratoryClassController controller;
	 
	 @Test
	 public void testFindLaboratoryById()
	   throws Exception {
	      
	     LaboratoryClass lab = new LaboratoryClass();
	     lab.setLabId((long) 1);
	     lab.setTitle("BST");
	     lab.setNumber(1);
	     lab.setCurricula("BT, BST and Traversal Methods");
	     lab.setLabText("Given concrete structures and examples, "
	     		+ "students are invited into the beautiful world of tree data structures");
	     
	     @SuppressWarnings("deprecation")
	     Date date = new Date(2018, 4, 22, 13, 47, 36);
	     lab.setDate(date);
	     
	     when(controller.getLaboratoryClassById((long)1)).thenReturn(lab);
	    
	     mvc.perform(get("/laboratory/{id}", 1)
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      .andExpect(jsonPath("labId", is(1)))
	    	      .andExpect(jsonPath("number", is(1)))
	    	      .andExpect(jsonPath("date", is("3918-05-22T20:47:36.000+0000")))
	    	      .andExpect(jsonPath("title", is("BST")))
	    	      .andExpect(jsonPath("curricula", is(lab.getCurricula())))
	    	      .andExpect(jsonPath("labText", is(lab.getLabText())));
	    
	 }
	 

	 @Test
	 public void testDeleteLaboratoryById()
	   throws Exception {
		 
		 when(controller.deleteBookById((long)1)).thenReturn("Laboratory Class with id = 1 successful deleted!");
	    
	     mvc.perform(delete("/laboratory/{id}", 1)
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      .andExpect(content().string(containsString("Laboratory Class with id = 1 successful deleted!")));
	    
	 }
	
	 
}

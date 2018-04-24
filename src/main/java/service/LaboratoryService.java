package service;

import java.util.List;

import model.LaboratoryClass;

public interface LaboratoryService {

	List<LaboratoryClass> getAllLaboratoryClasses();

	LaboratoryClass getLaboratoryClassById(Long labId);

	LaboratoryClass saveLaboratoryClass(LaboratoryClass laboratory);

	LaboratoryClass updateLaboratoryClass(Long labId, LaboratoryClass laboratory);

    void deleteLaboratoryClassById(Long labId);
    
    List<LaboratoryClass> viewFilteredList(String kewyword);
	
}

package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ILaboratoryClassRepository;
import dao.dbmodel.LaboratoryClassDto;
import model.LaboratoryClass;
import service.LaboratoryService;

@Service
public class LaboratoryServiceImpl implements LaboratoryService{

	private ILaboratoryClassRepository laboratoryRepository;
	private ModelMapper myMapper;
	
	@Autowired
	public LaboratoryServiceImpl(ILaboratoryClassRepository laboratoryRepository) {
	        this.laboratoryRepository = laboratoryRepository;
	        myMapper = new ModelMapper();
	}
	
	@Override
	public List<LaboratoryClass> getAllLaboratoryClasses() {
		
		List<LaboratoryClassDto> laboratoryClasses = laboratoryRepository.findAll();
    	List<LaboratoryClass> result = new ArrayList<LaboratoryClass>();
    	
    	for(LaboratoryClassDto l: laboratoryClasses) {
			
			LaboratoryClass lc = myMapper.map(l, LaboratoryClass.class);
			result.add(lc);
			
		}
    	
        return result;
	}

	@Override
	public LaboratoryClass getLaboratoryClassById(Long labId) {
		
		Optional<LaboratoryClassDto> labDto = laboratoryRepository.findById(labId);
    	LaboratoryClass s = myMapper.map(labDto.get(), LaboratoryClass.class);
    	
    	return s;
	}

	@Override
	public LaboratoryClass saveLaboratoryClass(LaboratoryClass laboratory) {
		
		LaboratoryClassDto labDto = myMapper.map(laboratory, LaboratoryClassDto.class);
    	
        if (laboratoryRepository.findByTitle(laboratory.getTitle()) == null) {

            laboratoryRepository.save(labDto);
            return laboratory;

        }
        
        return null;
	}

	@Override
	public LaboratoryClass updateLaboratoryClass(Long labId, LaboratoryClass laboratory) {
		
		Optional<LaboratoryClassDto> labToBeUpdated = laboratoryRepository.findById(labId);
		LaboratoryClassDto newLabDto = myMapper.map(laboratory, LaboratoryClassDto.class);

        if (labToBeUpdated != null) {
        	
            laboratoryRepository.save(newLabDto);
            
            return laboratory;
            
        } else {
            return null;
        }
	}

	@Override
	public void deleteLaboratoryClassById(Long labId) {
		
		laboratoryRepository.deleteById(labId);
		
	}

	@Override
	public List<LaboratoryClass> viewFilteredList(String keyword) {
		
		List<LaboratoryClassDto> labClasses = laboratoryRepository.findAll();
		List<LaboratoryClass> result = new ArrayList<LaboratoryClass>();
		
		for(LaboratoryClassDto lab : labClasses) {
			
			if(lab.getLabText().contains(keyword) || lab.getCurricula().contains(keyword)) {
				
				LaboratoryClass c = myMapper.map(lab, LaboratoryClass.class);
				result.add(c);
				
			}	
		}
		
		return result;
	}

	
}

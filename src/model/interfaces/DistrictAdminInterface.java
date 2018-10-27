package model.interfaces;

import java.util.List;
import entities.District;

public interface DistrictAdminInterface {

	boolean createDistrict(District district);
	
	boolean editDistrict(District district);

	boolean deleteDistrict(District district);
	
	District readById(District district);
	District readByName(District district);
	
	List<District> readAllDistrict();

}

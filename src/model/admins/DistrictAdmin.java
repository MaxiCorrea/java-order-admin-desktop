package model.admins;

import java.util.List;
import entities.District;
import model.interfaces.DistrictAdminInterface;
import persistence.daos.DistrictDAO;

public class DistrictAdmin implements DistrictAdminInterface {

	private DistrictDAO districtDAO;

	public DistrictAdmin() {
		districtDAO = new DistrictDAO();
	}

	@Override
	public boolean createDistrict(District district) {
		District alreadyExists=districtDAO.readByName(district.getName());
		if(alreadyExists==null||alreadyExists.getProvince().getId()!=district.getProvince().getId())
		{
			return districtDAO.insert(district.getName(), district.getProvince());
		}else
		{
			System.out.println("La localidad ya existe en la DB");
			return false;
		}
	}
	
	@Override
	public boolean editDistrict(District district) {
		return districtDAO.update(district);
	}


	@Override
	public List<District> readAllDistrict() {
		return districtDAO.readAll();
	}

	@Override
	public boolean deleteDistrict(District district) {
		return districtDAO.delete(district);
	}

	@Override
	public District readById(District district) {
		return DistrictDAO.readById(district.getId());
	}

	@Override
	public District readByName(District district) {
		return districtDAO.readByName(district.getName());
	}

}

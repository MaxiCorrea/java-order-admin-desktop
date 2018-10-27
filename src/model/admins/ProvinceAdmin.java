package model.admins;

import java.util.List;
import entities.Province;
import model.interfaces.ProvinceAdminInterface;
import persistence.daos.ProvinceDAO;

public class ProvinceAdmin implements ProvinceAdminInterface {

	private ProvinceDAO provinceDAO;

	public ProvinceAdmin() {
		provinceDAO = new ProvinceDAO();
	}

	@Override
	public boolean createProvince(Province province) {
		Province alreadyExists = provinceDAO.readByName(province.getName());
		if (alreadyExists == null) {
			return provinceDAO.insert(province);
		} else {
				System.out.println("La provincia ya existe en la DB");
				return false;
		}
	}

	@Override
	public boolean editProvince(Province province) {
		return provinceDAO.update(province);
	}

	@Override
	public boolean deleteProvince(Province province) {
		return provinceDAO.deleteByName(province);
	}

	@Override
	public List<Province> readAllProvinces() {
		return provinceDAO.readAll();
	}

	@Override
	public Province readByName(Province province) {
		return provinceDAO.readByName(province.getName());
	}

	@Override
	public Province readById(Province province) {
		return ProvinceDAO.readById(province.getId());
	}

}

package model.interfaces;

import java.util.List;

import entities.Province;

public interface ProvinceAdminInterface {

  boolean createProvince(Province currentProvince);

  boolean editProvince(Province currentProvince);

  boolean deleteProvince(Province currentProvince);
  
  Province readByName(Province province);
  Province readById(Province province);

  List<Province> readAllProvinces();

}

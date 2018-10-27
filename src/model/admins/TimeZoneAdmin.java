package model.admins;

import java.util.List;
import entities.TimeZone;
import model.interfaces.TimeZoneAdminInterface;
import persistence.daos.TimeZoneDAO;

public class TimeZoneAdmin implements TimeZoneAdminInterface {

	private TimeZoneDAO dao;
	
	public TimeZoneAdmin() {
		dao = new TimeZoneDAO();
	}
	
	@Override
	public List<TimeZone> readAllTimeZone() {
		return dao.readAll();
	}

}

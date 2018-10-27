package model.admins;

import java.util.List;
import entities.MeasurementUnit;
import model.interfaces.MeasurementUnitAdminInterface;
import persistence.daos.MeasurementUnitDAO;

public class MeasurementUnitAdmin implements MeasurementUnitAdminInterface {

	private MeasurementUnitDAO measurementUnitDAO;

	public MeasurementUnitAdmin() {
		measurementUnitDAO = new MeasurementUnitDAO();
	}

	@Override
	public boolean createMeasurementUnit(MeasurementUnit o) {
		MeasurementUnit alreadyExists=MeasurementUnitDAO.readByName(o.getUnit());
		if(alreadyExists==null)
		{
			return measurementUnitDAO.insert(o);
		}else
		{
			System.out.println("La unidad de Medida ya existe");
			return false;
		}
	}

	@Override
	public boolean editMeasurementUnit(MeasurementUnit o) {
		return measurementUnitDAO.update(o.getId(), o);
	}

	@Override
	public boolean deleteMeasurementUnit(MeasurementUnit o) {
		return measurementUnitDAO.delete(o);
	}

	@Override
	public List<MeasurementUnit> readAllMeasurementUnit() {
		return measurementUnitDAO.readAll();
	}

}

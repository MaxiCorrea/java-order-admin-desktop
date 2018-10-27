package model.interfaces;

import java.util.List;

import entities.MeasurementUnit;

public interface MeasurementUnitAdminInterface {

  boolean createMeasurementUnit(MeasurementUnit o);
  
  boolean editMeasurementUnit(MeasurementUnit o);
  
  boolean deleteMeasurementUnit(MeasurementUnit o);
  
  List<MeasurementUnit> readAllMeasurementUnit();
  
}

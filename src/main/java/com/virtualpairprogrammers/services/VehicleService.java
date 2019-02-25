package com.virtualpairprogrammers.services;



import com.virtualpairprogrammers.dao.VehicleDAO;
import com.virtualpairprogrammers.domain.Vehicle;

import java.util.List;

public class VehicleService
{
    private VehicleDAO vehicleDAO;
    private static VehicleService reference;

    public static VehicleService getService(){
        if (reference == null)
            reference = new VehicleService();
        return reference;
    }

    public VehicleService()
    {
       this.vehicleDAO = new VehicleDAO();
    }

    public List<Vehicle> getAllVehicle()
    {
        return this.vehicleDAO.getAllVehicle();
    }
    public boolean insertVehicle(Vehicle vehicle){
        return this.vehicleDAO.insertVehicle(vehicle);
    }
   // public Integer getIdVehicle(String brand,String model, String fuel, String version, String capacity)
    //{ return this.vehicleDAO.getIdVehicle(brand, model, fuel, version, capacity); }
}

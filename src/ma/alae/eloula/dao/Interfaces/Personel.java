package ma.alae.eloula.dao.Interfaces;

import ma.alae.eloula.classes.Employee;

import java.util.Optional;

public interface Personel {
    Optional<Employee> ajouterEmployee(Employee employee);
    int SupprimerEmpl(int i);
    Optional<Employee> getEmployeeById(int id);


}

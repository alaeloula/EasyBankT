package ma.alae.eloula.classes;

import java.time.LocalDate;
import java.util.Date;

public class Employee extends Personel{
    private String email;
    private LocalDate dateRecrutement;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(LocalDate dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }
}

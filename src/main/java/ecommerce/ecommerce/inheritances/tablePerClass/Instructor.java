package ecommerce.ecommerce.inheritances.tablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tp_instructors")
public class Instructor extends User {
    private Float rating;
    private String specialization;
}

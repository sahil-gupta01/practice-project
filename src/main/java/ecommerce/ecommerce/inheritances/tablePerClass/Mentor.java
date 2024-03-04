package ecommerce.ecommerce.inheritances.tablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tp_mentors")
public class Mentor extends User {
    private String company;
    private Float rating;
}

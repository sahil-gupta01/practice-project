package ecommerce.ecommerce.inheritances.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ms_mentors")
public class Mentor extends User{
    private String company;
    private Float rating;
}

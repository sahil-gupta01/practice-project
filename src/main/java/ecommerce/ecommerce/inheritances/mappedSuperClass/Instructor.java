package ecommerce.ecommerce.inheritances.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ms_instructors")
public class Instructor extends User{
    private Float rating;
    private String specialization;
}

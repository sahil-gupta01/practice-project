package ecommerce.ecommerce.inheritances.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_instructors")
@DiscriminatorValue("2")
public class Instructor extends User {
    private Float rating;
    private String specialization;
}

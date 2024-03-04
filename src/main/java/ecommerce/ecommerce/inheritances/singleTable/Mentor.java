package ecommerce.ecommerce.inheritances.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_mentors")
@DiscriminatorValue("1")
public class Mentor extends User {
    private String company;
    private Float rating;
}

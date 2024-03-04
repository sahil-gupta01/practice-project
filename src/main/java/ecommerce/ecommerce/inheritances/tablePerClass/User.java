package ecommerce.ecommerce.inheritances.tablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tp_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    private String name;
    @Id
    private Long id;
    private String email;
    private String password;

}

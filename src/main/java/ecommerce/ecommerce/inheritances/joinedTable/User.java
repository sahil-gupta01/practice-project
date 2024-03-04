package ecommerce.ecommerce.inheritances.joinedTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    private String name;
    @Id
    private Long id;
    private String email;
    private String password;

}

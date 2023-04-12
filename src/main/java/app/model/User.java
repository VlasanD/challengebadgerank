package app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@org.hibernate.annotations.NamedQueries(
        {
                @org.hibernate.annotations.NamedQuery(name = "findByUserId", query = "from User entity where entity.id = :id"),
                @org.hibernate.annotations.NamedQuery(name = "findByUsername", query = "from User entity where entity.username = :username"),
                @org.hibernate.annotations.NamedQuery(name = "findAllUsers", query = "from User "
                )

        }
)

@Getter
@Setter
@Entity
//@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private int tokensOwned;

}
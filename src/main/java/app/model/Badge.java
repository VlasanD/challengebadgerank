package app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@org.hibernate.annotations.NamedQueries(
        {
                @org.hibernate.annotations.NamedQuery(name = "findByBadgeId", query = "from Badge entity where entity.id = :id"),
                @org.hibernate.annotations.NamedQuery(name = "findAllBadges", query = "from Badge ")
        }
)

@Getter
@Setter
@Entity
//@Table(name = "badges")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    private Challenge challenge;

}


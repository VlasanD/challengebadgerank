package app.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@org.hibernate.annotations.NamedQueries(
        {
                @org.hibernate.annotations.NamedQuery(name = "findByChallengeId", query = "from Challenge entity where entity.id = :id"),
                @org.hibernate.annotations.NamedQuery(name = "findAllChallenges", query = "Select entity.name,entity.description,entity.minimumCondition,entity.entryFee from Challenge entity"),
                @org.hibernate.annotations.NamedQuery(name = "findByChallengeName", query = "from Challenge entity where entity.name = :name"),
        }
)

@Getter
@Setter
@Entity
//@Table(name = "challenges")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate lastDate;

    @Column(name = "entry_fee", nullable = false)
    private int entryFee;

    @Column(name = "minimum_condition", nullable = false)
    private int minimumCondition;

    @Column(name = "places", nullable = false)
    private int places;

    @Column(name = "first_place_reward", nullable = false)
    private int firstPlaceReward;

    @Column(name = "second_place_reward", nullable = false)
    private int secondPlaceReward;

    @Column(name = "third_place_reward", nullable = false)
    private int thirdPlaceReward;

    @OneToMany(fetch = FetchType.EAGER)
    private List<UserChallengeRecord> userChallengeRecord;

    @OneToOne(fetch = FetchType.EAGER)
    private Badge badge;
}
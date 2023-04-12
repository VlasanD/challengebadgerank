package app.model;

import app.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@org.hibernate.annotations.NamedQueries(
        {
                @org.hibernate.annotations.NamedQuery(name = "findRecordById", query = "from UserChallengeRecord entity where entity.id = :id"),
                @org.hibernate.annotations.NamedQuery(name = "findAllRecords", query = "from UserChallengeRecord "),
                @org.hibernate.annotations.NamedQuery(name = "findByRecordUserId", query = " from UserChallengeRecord entity where entity.user.id = :id"),
                @org.hibernate.annotations.NamedQuery(name = "findByRecordUserIdAndChallengeId", query = " from UserChallengeRecord entity where entity.user.id = :userId and entity.challenge.id=:challengeId")
        }
)
@Getter
@Setter
@Entity
public class UserChallengeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Challenge challenge;

    @Column(name = "high_score")
    private int highScore;

    @Column(name = "last_day")
    private LocalDate lastDay;

    @Column(name = "status")
    private Status status;

}

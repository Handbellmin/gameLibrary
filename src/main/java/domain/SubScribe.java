package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class SubScribe {
    @Id @GeneratedValue
    @Column(name = "subscribe_id")
    private Long id;

    @ManyToOne(fetch =  LAZY)
    @JoinColumn(name= )
    private User user;

    @ManyToOne(fetch =  LAZY)
    private Game game;

    private Long GameId;

    private LocalDateTime subDate;
}

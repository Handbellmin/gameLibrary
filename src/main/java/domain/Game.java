package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Game {
    @Id @GeneratedValue
    @Column(name="game_id")
    private Long id;

    @OneToMany(mappedBy = "game")
    private List<SubScribe> subScribeList = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    private BoardCategory boardCategory;

    @OneToMany(mappedBy = "game")
    private List<PublishSite> publishSites = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @OneToOne(fetch = LAZY)
    private PriorityGame priorityGame;

    private String gameName;
    private int gamePrice;

    private Long gameSatis;

    private String gameDescription;
}

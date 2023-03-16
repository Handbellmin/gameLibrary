package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Board {
    @Id @GeneratedValue
    @Column(name="board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="boardCategory_id")
    private BoardCategory category;

    @OneToMany(mappedBy = "boardComment")
    private List<BoardComment> boardCommentList = new ArrayList<>();

    private String ttl;

    private String content;

    private String createId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String popupYn;


}

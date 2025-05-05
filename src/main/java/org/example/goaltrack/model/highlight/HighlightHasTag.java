package org.example.goaltrack.model.highlight;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "highlight_has_tag")
@IdClass(HighlightHasTagId.class)
public class HighlightHasTag {
    @Id
    @Column(name = "highlight_id")
    private Integer highlightId;

    @Id
    @Column(name = "tag_id")
    private Integer tagId;

    @ManyToOne
    @JoinColumn(name = "highlight_id", insertable = false, updatable = false)
    private Highlight highlight;

    @ManyToOne
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    private HighlightTag tag;
}

@Data
class HighlightHasTagId implements Serializable {
    private Integer highlightId;
    private Integer tagId;
}

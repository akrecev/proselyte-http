package com.kretsev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "events")
@NamedEntityGraph(
        name = "event-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode("file")
        }
)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    User user;
    @ManyToOne
    File file;
}

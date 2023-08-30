package com.kretsev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@NamedEntityGraph(
        name = "user-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("events")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false, length = 50)
    String name;
    @OneToMany(mappedBy = "user")
    List<Event> events = new ArrayList<>();

    @Override
    public String toString() {
        List<Integer> eventsId = events == null ? null : events.stream().map(Event::getId).collect(Collectors.toList());

        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", events id:" + eventsId +
                '}';
    }
}

package com.bordify.board.infrastructure.persistence;

import com.bordify.topic.infrastructure.persistence.TopicEntity;
import com.bordify.userdetail.infrastructure.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Represents a board entity in the Bordify application. A board is a collection
 * of topicEntities and is associated with a user.
 * <p>
 * The BoardEntity class leverages Lombok to automate the creation of getter, setter,
 * toString, equals, and hashCode methods with the @Data annotation. It uses
 *
 * @NoArgsConstructor and @AllArgsConstructor to generate constructors without and
 * with all parameters, respectively. @Builder provides a fluent API for building
 * BoardEntity instances.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "board")
public class BoardEntity {

    /**
     * Unique identifier for the BoardEntity. It is automatically generated and uses
     * UUID as the ID type.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Name of the board. It is a required field as denoted by @NonNull and cannot
     * be null as specified by @Column(nullable = false).
     */
    @NonNull
    @Column(nullable = false, unique = false)
    private String name;

    /**
     * The user ID associated with the board. This field is used for database
     * operations but is not directly updatable or insertable through the Board
     * entity to prevent inconsistency.
     */
    @Column(nullable = false, unique = false, name = "user_id")
    private UUID userId;

    /**
     * The UserDetail entity associated with this board. Mapped using a many-to-one
     * relationship where each board is owned by a single user. The @JoinColumn
     * annotation specifies that this entity uses the 'user_id' column in the
     * Board table to join to the UserDetail table.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    /**
     * List of topicEntities associated with this board. Defined as a one-to-many relationship
     * with cascade type ALL, meaning all persistence operations (like save and delete)
     * on a Board will cascade to its topicEntities.
     */
    // @JsonIgnoreProperties("board") // to avoid infinite recursion by circular imports
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL)
    private List<TopicEntity> topicEntities;

}

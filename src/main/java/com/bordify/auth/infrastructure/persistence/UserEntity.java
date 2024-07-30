package com.bordify.auth.infrastructure.persistence;

 import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
 import org.springframework.data.redis.core.index.Indexed;

 import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "\"user_auth_information\"")
@RedisHash
public class UserEntity implements Serializable {

    /**
     * Redis needs a primary key called as id, so we have to use userId as the primary key
     * so, we need to mark it with @Id
     * but, we need this  import org.springframework.data.annotation.Id;
     * not this import jakarta.persistence.Id;
    */

    @Id
    private UUID userId;
    @Indexed
    private String email;
    @Indexed
    private String userName;
    private String password;
    private Boolean isVerified;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
}

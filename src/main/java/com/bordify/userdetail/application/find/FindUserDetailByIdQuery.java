package com.bordify.userdetail.application.find;

import com.bordify.shared.domain.bus.query.Query;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class FindUserDetailByIdQuery implements Query {
    UUID id;
}

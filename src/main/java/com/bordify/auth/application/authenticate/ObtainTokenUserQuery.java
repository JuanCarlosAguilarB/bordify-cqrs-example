package com.bordify.auth.application.authenticate;

import com.bordify.shared.domain.bus.query.Query;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObtainTokenUserQuery implements Query {
    public String userName;
}

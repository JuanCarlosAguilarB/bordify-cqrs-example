package com.bordify.userdetail.application.find;

import com.bordify.shared.domain.bus.query.Query;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class FindAllUserDetailsQuery implements Query {
    int pageNumber;
    int pageSize;
}

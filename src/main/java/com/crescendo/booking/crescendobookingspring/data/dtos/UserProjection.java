package com.crescendo.booking.crescendobookingspring.data.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserProjection {

    private long id;
    private String email;
    private String role;
}

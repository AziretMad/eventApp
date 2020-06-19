package com.company.eventApp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaceDTO {
    Long id;
    String name;
    Set<String> placeTags;
    String address;
    BigDecimal rentalPrice;
    UserDTO userDTO;
}

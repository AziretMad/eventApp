package com.company.eventApp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaceDTO {
    Long id;
    String name;
    List<PlaceTagDTO> placeTagDTOS;
    String address;
    BigDecimal rentalPrice;
    UserDTO userDTO;
}

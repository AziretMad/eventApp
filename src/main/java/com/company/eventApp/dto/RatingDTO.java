package com.company.eventApp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingDTO {
    Long id;
    Integer rating;
    Long userId;
    Long eventId;
}

package com.company.eventApp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDTO {
    Long id;
    String text;
    Long userId;
    Long eventId;
    Date date;
}

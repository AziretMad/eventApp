package com.company.eventApp.dto;

import com.company.eventApp.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDTO {
    Long id;
    String name;
    Set<String> tags;
    String description;
    String picture;
    String video;
    Date date;
    Status status;
    Long UserId;
}

package com.company.eventApp.dto;

import com.company.eventApp.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

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
    Set<TagDTO> tagDTOs;
    String description;
    String picture;
    String video;
    String date;
    String place;
    Status status;
    Long UserId;
}

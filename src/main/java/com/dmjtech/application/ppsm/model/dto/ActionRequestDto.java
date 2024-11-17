package com.dmjtech.application.ppsm.model.dto;

import com.dmjtech.application.ppsm.util.ValueOfEnum;
import com.dmjtech.application.ppsm.util.enums.Action;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 7/13/2024, Saturday, 5:34 PM,
 * @project : basic-project
 * @package : com.dmjtech.application.basic_project.model.dto
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionRequestDto {
    @NotBlank(message = "action is required.")
    @ValueOfEnum(enumClass = Action.class, message = "action is invalid.")
    private String action;
}

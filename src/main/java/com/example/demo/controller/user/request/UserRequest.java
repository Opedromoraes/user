package com.example.demo.controller.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true,access = AccessLevel.PRIVATE)
public class UserRequest {

    @Schema(name = "name",example = "Pedro Moraes")
    @NotNull
    private String name;

    @Schema(name = "email",example = "pedro@gmail.com")
    @NotNull
    private String email;

    @Schema(name = "password",example = "Pedro@21")
    @NotNull
    private String password;

}

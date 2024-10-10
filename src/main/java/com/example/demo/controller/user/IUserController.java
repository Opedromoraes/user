package com.example.demo.controller.user;

import com.example.demo.controller.user.request.UserRequest;
import com.example.demo.controller.user.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@Tag(name = "Users")
@RequestMapping(value = "/users")
@Validated
public interface IUserController {

    @Operation(
            summary = "Create a User",
            description = "Endpoint responsible of create a new user",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "User successfully created.",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Request has at least one missing or invalid value.",
                            content = @Content(schema = @Schema(implementation = Exception.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unexpected error",
                            content = @Content(schema = @Schema(implementation = Exception.class)))
            })
    @PostMapping
    @ResponseStatus(CREATED)
    ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request);

    @Operation(
            summary = "Find user by id",
            description = "Endpoint responsible of finding a user by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User successfully found.",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
                            content = @Content(schema = @Schema(implementation = Exception.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unexpected error",
                            content = @Content(schema = @Schema(implementation = Exception.class)))
            })
    @GetMapping("{id}")
    @ResponseStatus(OK)
    ResponseEntity<UserResponse> findById(@PathVariable Long id);

    @Operation(
            summary = "Delete User",
            description = "Endpoint responsible for delete a user",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "User successfully deleted."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
                            content = @Content(schema = @Schema(implementation = Exception.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unexpected error",
                            content = @Content(schema = @Schema(implementation = Exception.class)))
            })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    ResponseEntity<String> delete(@PathVariable Long id);

}

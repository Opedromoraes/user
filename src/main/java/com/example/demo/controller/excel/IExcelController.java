package com.example.demo.controller.excel;

import com.example.demo.controller.user.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Tag(name = "Excel")
@RequestMapping(value = "/excel")
@Validated
public interface IExcelController {

    @Operation(
            summary = "Generate excel",
            description = "Endpoint responsible of generate an excel",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sheet successfully generated.",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unexpected error",
                            content = @Content(schema = @Schema(implementation = Exception.class)))
            })
    @GetMapping("download")
    @ResponseStatus(OK)
    void getEmailsCSV(HttpServletResponse response);
}

package br.com.bertanj.controllers.docs;

import br.com.bertanj.data.dto.v1.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PersonControllerDocs {
    @Operation(summary = "Find all People",
            description = "Finding all People",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))}),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    List<PersonDTO> findAll();

    @Operation(summary = "Find a Person",
            description = "Finding a specific person by your ID",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonDTO.class))}),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    PersonDTO findById(@PathVariable("id") Long id);

    @Operation(summary = "Create a Person", description = "Creating a new person", tags = {"People"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    PersonDTO create(@RequestBody PersonDTO person);

    @Operation(summary = "Update a Person", description = "Updating a person", tags = {"People"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    PersonDTO update(@RequestBody PersonDTO person);

    @Operation(summary = "Delete a Person", description = "Deleting a person", tags = {"People"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<?> delete(@PathVariable("id") Long id);
}

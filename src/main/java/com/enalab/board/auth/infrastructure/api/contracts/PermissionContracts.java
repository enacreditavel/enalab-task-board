package com.enalab.board.auth.infrastructure.api.contracts;

import com.enalab.board.auth.infrastructure.api.dto.CreatePermissionRequest;
import com.enalab.board.auth.infrastructure.api.dto.PermissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Permissions", description = "RBAC Permissions Management")
@RequestMapping("/api/v1/permissions")
public interface PermissionContracts {

    @Operation(
            summary = "Create a new permission",
            description = "Registers a new access permission in the system. Requires HOMELANDER or PERMISSION_WRITE profile. The format of the created permission must contain the scope and the type of permission, for example 'profile read' 'user write'",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permission created successfully!"),
            @ApiResponse(responseCode = "400", description = "Permission already exists",
                    content = @Content(schema = @Schema(example = "Permission already exists")))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PermissionResponse createPermission(@RequestBody @Valid CreatePermissionRequest request);

    @Operation(
            summary = "Get permissions by name or list all",
            description = "Retrieves a list of permissions. If the 'name' parameter is provided, it filters permissions containing the given name. If no parameter is provided, it returns all permissions. Requires HOMELANDER or PERMISSION_READ profile.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of permissions")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<PermissionResponse> getPermissions(@Parameter(description = "Name to filter permissions by (optional)") @RequestParam(required = false) String name);

    @Operation(
            summary = "Get permission by ID",
            description = "Retrieves a specific permission by its unique ID. Requires HOMELANDER or PERMISSION_READ profile.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission found"),
            @ApiResponse(responseCode = "404", description = "Permission not found")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    PermissionResponse getPermissionById(@Parameter(description = "ID of the permission to be retrieved") @PathVariable String id);

    @Operation(
            summary = "Delete a permission",
            description = "Deletes a permission by its unique ID. Requires HOMELANDER or PERMISSION_WRITE profile.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Permission deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Permission not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePermission(@Parameter(description = "ID of the permission to be deleted") @PathVariable String id);

}
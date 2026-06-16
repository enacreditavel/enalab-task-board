package com.enalab.board.auth.infrastructure.api;

import com.enalab.board.auth.application.permission.CreatePermissionUseCase;
import com.enalab.board.auth.application.permission.DeletePermissionUseCase;
import com.enalab.board.auth.application.permission.FindAllPermissionsUseCase;
import com.enalab.board.auth.infrastructure.api.dto.CreatePermissionRequest;
import com.enalab.board.auth.infrastructure.api.dto.PermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    private final CreatePermissionUseCase createPermissionUseCase;

    private final FindAllPermissionsUseCase findAllPermissionsUseCase;

    private final DeletePermissionUseCase deletePermissionUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('PERMISSION_WRITE') or hasRole('HOMELANDER')")
    public PermissionResponse createPermission(@RequestBody CreatePermissionRequest request){
        var output = createPermissionUseCase.execute(request.toInput());
        return PermissionResponse.from(output);
    }

    @GetMapping
    @PreAuthorize("hasRole('PERMISSION_READ') or hasRole('HOMELANDER')")
    public List<PermissionResponse> getAllPermission(){
        return findAllPermissionsUseCase.execute().stream().map(PermissionResponse::from).toList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('PERMISSION_WRITE') or hasRole('HOMELANDER')")
    public void deletePermission(@PathVariable String id){
        deletePermissionUseCase.execute(id);
    }
}

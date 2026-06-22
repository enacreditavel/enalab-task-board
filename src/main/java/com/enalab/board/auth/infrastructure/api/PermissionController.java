package com.enalab.board.auth.infrastructure.api;

import com.enalab.board.auth.application.permission.*;
import com.enalab.board.auth.infrastructure.api.contracts.PermissionContracts;
import com.enalab.board.auth.infrastructure.api.dto.CreatePermissionRequest;
import com.enalab.board.auth.infrastructure.api.dto.PermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PermissionController implements PermissionContracts {

    private final CreatePermissionUseCase createPermissionUseCase;
    private final FindAllPermissionsUseCase findAllPermissionsUseCase;
    private final FindPermissionByIdUseCase findPermissionByIdUseCase;
    private final SearchPermissionByNameUseCase searchPermissionByNameUseCase;
    private final DeletePermissionUseCase deletePermissionUseCase;

    @Override
    @PreAuthorize("hasRole('PERMISSION_WRITE') or hasRole('HOMELANDER')")
    public PermissionResponse createPermission(CreatePermissionRequest request){
        var output = createPermissionUseCase.execute(request.toInput());
        return PermissionResponse.from(output);
    }

    @Override
    @PreAuthorize("hasRole('PERMISSION_READ') or hasRole('HOMELANDER')")
    public List<PermissionResponse> getPermissions(String name){
        if (name != null && !name.trim().isEmpty()) {
            return searchPermissionByNameUseCase.execute(name).stream().map(PermissionResponse::from).toList();
        }
        else {
            return findAllPermissionsUseCase.execute().stream().map(PermissionResponse::from).toList();
        }
    }

    @Override
    @PreAuthorize("hasRole('PERMISSION_READ') or hasRole('HOMELANDER')")
    public PermissionResponse getPermissionById(String id) {
        return PermissionResponse.from(findPermissionByIdUseCase.execute(id));
    }

    @Override
    @PreAuthorize("hasRole('PERMISSION_WRITE') or hasRole('HOMELANDER')")
    public void deletePermission(String id){
        deletePermissionUseCase.execute(id);
    }
}

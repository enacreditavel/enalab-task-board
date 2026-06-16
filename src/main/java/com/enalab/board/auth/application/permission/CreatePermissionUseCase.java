package com.enalab.board.auth.application.permission;

import com.enalab.board.auth.application.permission.input.CreatePermissionInput;
import com.enalab.board.auth.application.permission.output.PermissionOutput;
import com.enalab.board.auth.domain.Permission;
import com.enalab.board.auth.domain.PermissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePermissionUseCase {
    private final PermissionRepository permissionRepository;

    @Transactional
    public PermissionOutput execute(CreatePermissionInput input){
        var permission = Permission.create(input.name());
        var saved = permissionRepository.save(permission);
        return PermissionOutput.from(saved);
    }
}

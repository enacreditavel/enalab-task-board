package com.enalab.board.auth.application.permission;

import com.enalab.board.auth.application.permission.input.CreatePermissionInput;
import com.enalab.board.auth.application.permission.output.PermissionOutput;
import com.enalab.board.auth.domain.Permission;
import com.enalab.board.auth.domain.PermissionRepository;
import com.enalab.board.common.exceptions.AlreadyExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePermissionUseCase {
    private final PermissionRepository permissionRepository;

    @Transactional
    public PermissionOutput execute(CreatePermissionInput input){

        if (permissionRepository.existsByName(input.name())){
            throw new AlreadyExistsException("Permission already exists. Name: " + input.name());}

        return PermissionOutput.from(
                permissionRepository.save(
                        Permission.create(input.name())));
    }
}

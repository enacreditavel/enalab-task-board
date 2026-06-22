package com.enalab.board.auth.application.permission;

import com.enalab.board.auth.application.permission.output.PermissionOutput;
import com.enalab.board.auth.domain.PermissionId;
import com.enalab.board.auth.domain.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindPermissionByIdUseCase {
    private final PermissionRepository permissionRepository;
    @Transactional(readOnly = true)
    public PermissionOutput execute(String permissionId){
        return PermissionOutput.from(permissionRepository.findById(PermissionId.generate(permissionId)));
    }
}

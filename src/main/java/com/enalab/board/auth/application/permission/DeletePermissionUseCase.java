package com.enalab.board.auth.application.permission;

import com.enalab.board.auth.domain.PermissionId;
import com.enalab.board.auth.domain.PermissionRepository;
import com.enalab.board.common.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class DeletePermissionUseCase {
    private final PermissionRepository permissionRepository;

    public void execute(String permissionId){
        if (!permissionRepository.existsById(PermissionId.generate(permissionId))){
            throw new ResourceNotFoundException("Permission not found with id: "+permissionId);
        }
        permissionRepository.deleteById(PermissionId.generate(permissionId));
    }
}

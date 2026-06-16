package com.enalab.board.auth.application.permission;

import com.enalab.board.auth.application.permission.output.PermissionOutput;
import com.enalab.board.auth.domain.PermissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindAllPermissionsUseCase {
    private final PermissionRepository permissionRepository;

    @Transactional
    public List<PermissionOutput> execute() {
        return permissionRepository.findAll().stream()
                .map(PermissionOutput::from)
                .toList();
    }
}

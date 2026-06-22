package com.enalab.board.auth.application.permission;

import com.enalab.board.auth.application.permission.output.PermissionOutput;
import com.enalab.board.auth.domain.PermissionRepository;
import com.enalab.board.common.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchPermissionByNameUseCase {
    private final PermissionRepository permissionRepository;

    @Transactional(readOnly = true)
    public List<PermissionOutput> execute(String name){
        List<PermissionOutput> list = permissionRepository.searchByName(name).stream().map(PermissionOutput::from).toList();
        if(list.isEmpty()){
            throw new ResourceNotFoundException("No permissions found with name: " + name);
        }
        return list;
    }
}


package com.enalab.board.auth.infrastructure.api;

import com.enalab.board.auth.application.user.FindAllUsersUseCase;
import com.enalab.board.auth.application.user.RemoveProfileFromUserUseCase;
import com.enalab.board.auth.application.user.AddProfileToUserUseCase;
import com.enalab.board.auth.application.user.CreateUserUseCase;
import com.enalab.board.auth.infrastructure.api.dto.AddPopProfileRequest;
import com.enalab.board.auth.infrastructure.api.dto.CreateUserRequest;
import com.enalab.board.auth.infrastructure.api.dto.UserCompleteResponse;
import com.enalab.board.auth.infrastructure.api.dto.UserResponse;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@PermitAll
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final AddProfileToUserUseCase addProfileToUserUseCase;
    private final RemoveProfileFromUserUseCase removeProfileFromUserUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;


    @PostMapping
//    @PreAuthorize("hasRole('USER_WRITE') or hasRole('HOMELANDER')")
    @PermitAll
    public UserResponse createUser(@RequestBody CreateUserRequest request){
        var output = createUserUseCase.execute(request.toInput());
        return UserResponse.from(output);
    }

    @PatchMapping("/{id}/add-profile")
    @PreAuthorize("hasRole('USER_WRITE') or hasRole('HOMELANDER')")
    public void addProfile(@PathVariable UUID id, @RequestBody AddPopProfileRequest profileId){
        addProfileToUserUseCase.execute(id,profileId.id());
    }

    @PatchMapping("/{id}/remove-profile")
    @PreAuthorize("hasRole('USER_WRITE') or hasRole('HOMELANDER')")
    public void removeProfile(@PathVariable UUID id, @RequestBody AddPopProfileRequest profileId){
        removeProfileFromUserUseCase.execute(id, profileId.id());
    }

    @GetMapping
    @PreAuthorize("hasRole('USER_WRITE') or hasRole('HOMELANDER')")
    public List<UserCompleteResponse> getAllUsers(){
        return findAllUsersUseCase.execute().stream().map(UserCompleteResponse::from).toList();
    }

//    @PutMapping("{id}")
//    @PreAuthorize("hasRole('USER_WRITE') or hasRole('HOMELANDER')")
//    public UserResponse updateUser(@PathVariable UUID id, @RequestBody CreateUserRequest request){

}

package com.enalab.board.auth.application.user;

import com.enalab.board.auth.application.user.output.UserCompleteOutput;
import com.enalab.board.auth.application.user.output.UserOutput;
import com.enalab.board.auth.domain.User;
import com.enalab.board.auth.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindAllUsersUseCase {
    private final UserRepository userRepository;

    public List<UserCompleteOutput> execute() {
        return userRepository.findAll().stream().map(UserCompleteOutput::from).toList();
    }
}

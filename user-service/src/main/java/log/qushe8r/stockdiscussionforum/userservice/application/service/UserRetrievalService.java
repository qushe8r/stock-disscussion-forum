package log.qushe8r.stockdiscussionforum.userservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserDetailsResponse;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserResponse;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserRetrievalUseCase;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.userservice.domain.exception.UserException;
import log.qushe8r.stockdiscussionforum.userservice.domain.exception.UserExceptionCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class UserRetrievalService implements UserRetrievalUseCase {

    private final UserMapper mapper;
    private final UserQueryPersistencePort queryPort;

    @Override
    public UserDetailsResponse retrieveSelfDetails(Long userId) {
        return queryPort.findById(userId)
                .map(mapper::toUserDetailsResponse)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));
    }

    @Override
    public UserResponse retrieveUserById(Long userId) {
        return queryPort.findById(userId)
                .map(mapper::toUserResponse)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));
    }

    @Override
    public UserResponse retrieveUserByUsername(String username) {
        return queryPort.findByUsername(username)
                .map(mapper::toUserResponse)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));
    }

    @Override
    public List<UserResponse> retrieveUsersByIds(List<Long> userIds) {
        return queryPort.findByIds(userIds)
                .stream()
                .map(mapper::toUserResponse)
                .toList();
    }
}

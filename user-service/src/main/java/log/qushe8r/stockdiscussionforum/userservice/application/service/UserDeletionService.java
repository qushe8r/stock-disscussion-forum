package log.qushe8r.stockdiscussionforum.userservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserDeletionUseCase;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserDeletionCommandPort;
import log.qushe8r.stockdiscussionforum.userservice.domain.exception.UserException;
import log.qushe8r.stockdiscussionforum.userservice.domain.exception.UserExceptionCode;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UserDeletionService implements UserDeletionUseCase {

    private final UserDeletionCommandPort commandPort;

    @Override
    public void deleteUser(Long requestingUserId, Long requestedUserId) {
        if (!requestedUserId.equals(requestingUserId)) {
            throw new UserException(UserExceptionCode.CANNOT_CHANGE_INFORMATION);
        }
        commandPort.deleteById(requestedUserId);
    }

}

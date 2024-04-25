package log.qushe8r.stockdiscussionforum.common.parser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdConverter {
    public static List<Long> parser(String userIds) {
        List<Long> userIdsList = new ArrayList<>();
        String[] userIdsArray = userIds.split(",");
        for (String userId : userIdsArray) {
            userIdsList.add(Long.parseLong(userId.trim()));
        }
        return userIdsList;
    }
}

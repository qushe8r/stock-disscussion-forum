package log.qushe8r.stockdiscussionforum.common.parser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdConverter {
    public static List<Long> parser(String userIds) {
        return Arrays.stream(userIds.split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .toList();
    }

    public static String parser(List<Long> ids) {
        return ids.stream().map(Object::toString)
                .collect(Collectors.joining(","));
    }
}

package log.qushe8r.stockdiscussionforum.stockbatch.client.dto;

import java.util.List;

public record Page<T>(List<T> stocks,
                      int totalCount,
                      int page,
                      int pageSize) {
}

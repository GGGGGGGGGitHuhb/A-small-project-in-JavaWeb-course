package util;

import java.util.*;

public class UpdateResult {
    private final List<String> updates; // sql 更新
    private final List<Object> params; // 字段值填充

    public UpdateResult(List<String> updates, List<Object> params) {
        this.updates = updates;
        this.params = params;
    }

    public List<String> getUpdates() {
        return updates;
    }

    public List<Object> getParams() {
        return params;
    }
}

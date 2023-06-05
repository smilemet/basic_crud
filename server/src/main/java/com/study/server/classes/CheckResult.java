package com.study.server.classes;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CheckResult<T> {
    private final boolean isSucceed;
    private final String errorMessage;
    private final T data;
}

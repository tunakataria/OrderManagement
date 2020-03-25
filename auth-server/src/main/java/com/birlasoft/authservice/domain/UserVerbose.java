package com.birlasoft.authservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserVerbose {

    private String userName;

    private Map<String, String> accesses;
}

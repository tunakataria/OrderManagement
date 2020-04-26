package com.birlasoft.authservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Permission {
    CAN_ADD(true),
    CAN_DELETE(true),
    CAN_ORDER(true);
    private boolean granted;

}

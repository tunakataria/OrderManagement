package com.birlasoft.authservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.birlasoft.authservice.domain.Permission.*;

@Getter
@NoArgsConstructor
public enum Role {
    ADMIN(1L, CAN_ADD, CAN_DELETE, CAN_ORDER);

    @Enumerated(EnumType.STRING)
    private List<Permission> allPermission = new ArrayList();
    @Id
    private Long id;

    Role(Long id, Permission... permisson) {
        this.id = id;
        Arrays.asList(permisson).stream().forEach(perm -> allPermission.add(perm));
    }
}

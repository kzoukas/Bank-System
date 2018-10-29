package com.blueharvest.banksystem.model;

import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class UserInfo {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private float balance;

    private List transactions;

}

package com.falynsky.jobms.app.enities.external;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Company {

    private Long id;
    private String name;
    private String city;
    private Long version;

}
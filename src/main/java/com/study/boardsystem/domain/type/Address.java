package com.study.boardsystem.domain.type;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * packageName    : com.study.boardsystem.domain.type
 * fileName       : Address
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@Embeddable
public class Address {

    @Column(name = "CITY", length = 20)
    private String city;

    @Column(name = "STREET", length = 20)
    private String street;

    @Column(name = "ZIPCODE", length = 20)
    private String zipcode;

}

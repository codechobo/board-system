package com.study.boardsystem.module.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * packageName    : com.study.boardsystem.module.user.domain
 * fileName       : Address
 * author         : tkdwk567@naver.com
 * date           : 2022/07/21
 */

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Address {

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "zipcode")
    private String zipcode;

    @Builder
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

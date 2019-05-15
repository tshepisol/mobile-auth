package za.co.pps.auth.domain;

import io.swagger.annotations.ApiModel;

@ApiModel
public enum LogVerbosityEnum {

    NUMBER_0(0),


    NUMBER_1(1),


    NUMBER_2(2),


    NUMBER_3(3),


    NUMBER_4(4),


    NUMBER_5(5),


    NUMBER_6(6);

    private Integer value;

    LogVerbosityEnum(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

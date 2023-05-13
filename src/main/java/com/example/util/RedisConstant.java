package com.example.util;

public interface RedisConstant {
    String PAGE_KEY = "page:";

    String CAR_ID_KEY = "car:";

    String AUTHORITY = "authority";

    String Email_CODE = "emailCode";

    String Key_CODE="keyname:";
    String Semster_CODE="semster::";
    String CONSUMER_PAGE_NUM = "consumerPageNum:";

    String CONSUMER_PAGE_SIZE= "consumerPageSize:";

    long PAGE_EXPIRE_TIME = 1000 * 60 * 60;

    long CODE_EXPIRE_TIME = 1000 * 60 * 60 * 2;
}

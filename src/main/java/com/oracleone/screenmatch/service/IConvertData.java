package com.oracleone.screenmatch.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> tClass);
}
